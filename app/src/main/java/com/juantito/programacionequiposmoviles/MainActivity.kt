package com.juantito.programacionequiposmoviles

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.util.Patterns
import android.view.MotionEvent
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.MultiAutoCompleteTextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import com.juantito.programacionequiposmoviles.databinding.ActivityPaletteTextBinding
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale

internal const val TAG_TEXTO = "PRACTICA-CAPITULO-8"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaletteTextBinding
    private var textoExpandido = false
    private var claveVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPaletteTextBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val bordes = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout() or
                    WindowInsetsCompat.Type.ime()
            )
            view.setPadding(bordes.left, bordes.top, bordes.right, bordes.bottom)
            insets
        }

        textoExpandido = savedInstanceState?.getBoolean(TEXTO_EXPANDIDO) == true
        binding.ctvOpcion.isChecked = savedInstanceState?.getBoolean(MARCADO) == true
        configurarTexto()
        configurarEdicion()
        configurarSugerencias()
        configurarSelecciones()
        binding.btnValidarCampos.setOnClickListener { validarCampos() }
        binding.btnRegistro.setOnClickListener { startActivity(Intent(this, RegistroActivity::class.java)) }
    }

    private fun configurarTexto() {
        mostrarTexto()
        binding.tvTextoDemostracion.setOnClickListener {
            textoExpandido = !textoExpandido
            mostrarTexto()
            Log.d(TAG_TEXTO, "TextView expandido: $textoExpandido")
        }
        binding.etPlainText.doAfterTextChanged { actualizarVistaPrevia(it ?: "") }
        actualizarVistaPrevia(binding.etPlainText.text ?: "")
        binding.btnEjemplo.setOnClickListener {
            binding.etPlainText.setText(R.string.texto_precargado)
            binding.etPlainText.setSelection(binding.etPlainText.length())
        }
        binding.btnLimpiar.setOnClickListener { binding.etPlainText.text?.clear() }
        binding.btnEnfocar.setOnClickListener {
            binding.etPlainText.requestFocus()
            WindowCompat.getInsetsController(window, binding.etPlainText).show(WindowInsetsCompat.Type.ime())
        }
        binding.etPlainText.setOnEditorActionListener { _, accion, _ ->
            if (accion == EditorInfo.IME_ACTION_DONE) {
                if (binding.etPlainText.text.isNullOrBlank()) {
                    binding.etPlainText.error = getString(R.string.campo_requerido)
                } else {
                    aviso(R.string.texto_recibido)
                    WindowCompat.getInsetsController(window, binding.etPlainText).hide(WindowInsetsCompat.Type.ime())
                }
                true
            } else {
                false
            }
        }
    }

    private fun mostrarTexto() {
        binding.tvTextoDemostracion.maxLines = if (textoExpandido) Int.MAX_VALUE else 2
        binding.tvTextoDemostracion.ellipsize = if (textoExpandido) null else TextUtils.TruncateAt.END
        binding.tvEstadoTexto.setText(if (textoExpandido) R.string.texto_expandido else R.string.texto_reducido)
    }

    private fun actualizarVistaPrevia(texto: CharSequence) {
        binding.tvVistaPrevia.text = if (texto.isBlank()) getString(R.string.vista_previa_vacia) else texto
        binding.tvLongitud.text = resources.getQuantityString(R.plurals.longitud_texto, texto.length, texto.length)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun configurarEdicion() {
        binding.btnMostrarClave.setOnClickListener {
            claveVisible = !claveVisible
            val campo = binding.etPassword
            val posicion = campo.selectionEnd.coerceIn(0, campo.length())
            campo.transformationMethod = if (claveVisible) null else PasswordTransformationMethod.getInstance()
            campo.setSelection(posicion)
            binding.btnMostrarClave.setText(if (claveVisible) R.string.ocultar_clave else R.string.mostrar_clave)
        }
        // Conserva el desplazamiento interno del campo cuando supera su altura máxima.
        binding.etMultiline.setOnTouchListener { vista, evento ->
            when (evento.actionMasked) {
                MotionEvent.ACTION_DOWN -> vista.parent.requestDisallowInterceptTouchEvent(
                    vista.canScrollVertically(1) || vista.canScrollVertically(-1)
                )
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL ->
                    vista.parent.requestDisallowInterceptTouchEvent(false)
            }
            false
        }
        binding.etMultiline.doAfterTextChanged { actualizarNota(it?.length ?: 0) }
        actualizarNota(binding.etMultiline.length())
    }

    private fun actualizarNota(cantidad: Int) {
        binding.tvNotaLongitud.text = getString(R.string.longitud_nota, cantidad)
    }

    private fun configurarSugerencias() {
        val estilo = android.R.layout.simple_dropdown_item_1line
        binding.actvPaises.setAdapter(ArrayAdapter(this, estilo, resources.getStringArray(R.array.paises)))
        binding.actvPaises.setOnItemClickListener { lista, _, posicion, _ ->
            binding.tvPais.text = getString(R.string.pais_elegido, lista.getItemAtPosition(posicion).toString())
        }
        binding.mactvTags.setAdapter(ArrayAdapter(this, estilo, resources.getStringArray(R.array.etiquetas)))
        binding.mactvTags.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
        binding.swSeparador.setOnCheckedChangeListener { _, usarEspacios ->
            val texto = binding.mactvTags.text.toString()
            val palabras = texto.split(',', ' ').filter { it.isNotBlank() }
            val separador = if (usarEspacios) " " else ", "
            binding.mactvTags.setTokenizer(
                if (usarEspacios) SpaceTokenizer() else MultiAutoCompleteTextView.CommaTokenizer()
            )
            binding.mactvTags.setText(palabras.joinToString(separador), false)
            binding.mactvTags.setSelection(binding.mactvTags.length())
        }
        binding.mactvTags.doAfterTextChanged { actualizarEtiquetas() }
        actualizarEtiquetas()
    }

    private fun actualizarEtiquetas() {
        val cantidad = binding.mactvTags.text.toString().split(',', ' ').count { it.isNotBlank() }
        binding.tvEtiquetas.text = resources.getQuantityString(R.plurals.etiquetas_elegidas, cantidad, cantidad)
    }

    private fun configurarSelecciones() {
        binding.ctvOpcion.setOnClickListener {
            binding.ctvOpcion.toggle()
            actualizarSeleccion()
        }
        binding.lvOpciones.adapter = ArrayAdapter(
            this, R.layout.item_checked_list, R.id.ctv_fila, resources.getStringArray(R.array.opciones_texto)
        )
        binding.lvOpciones.setOnItemClickListener { _, _, _, _ -> actualizarSeleccion() }
        actualizarSeleccion()
    }

    private fun actualizarSeleccion() {
        val cantidad = binding.lvOpciones.checkedItemCount + if (binding.ctvOpcion.isChecked) 1 else 0
        binding.tvSeleccion.text = resources.getQuantityString(R.plurals.opciones_marcadas, cantidad, cantidad)
    }

    private fun validarCampos() {
        val campos: List<Pair<EditText, (String) -> Int?>> = with(binding) {
            listOf(
                etPassword to ::errorClave,
                etPin to { if (PIN.matches(it)) null else R.string.pin_invalido },
                etEmail to ::errorCorreo,
                etPhone to ::errorTelefono,
                etPostalAddress to { if (it.length >= 10 && it.any(Char::isLetter)) null else R.string.direccion_invalida },
                etTime to { if (HORA.matches(it)) null else R.string.hora_invalida },
                etDate to { if (fechaValida(it)) null else R.string.fecha_invalida },
                etNumber to { if (ENTERO.matches(it) && it.toIntOrNull() != null) null else R.string.numero_invalido },
                etNumberSigned to { if (it.toIntOrNull() != null) null else R.string.numero_con_signo_invalido },
                etNumberDecimal to { if (DECIMAL.matches(it)) null else R.string.decimal_invalido }
            )
        }
        var total = 0
        var invalidos = 0
        for ((campo, validar) in campos) {
            val valor = if (campo === binding.etPassword) campo.text.toString() else campo.text.toString().trim()
            val error = if (valor.isEmpty()) null else validar(valor)
            campo.error = error?.let(::getString)
            if (valor.isNotEmpty()) total++
            if (error != null) invalidos++
        }

        binding.tvValidacion.text = when {
            total == 0 -> getString(R.string.validacion_sin_datos)
            invalidos > 0 -> resources.getQuantityString(R.plurals.validacion_errores, invalidos, invalidos)
            else -> getString(R.string.validacion_correcta)
        }
        val precio = binding.etNumberDecimal.text.toString().trim()
        binding.tvDecimalFormateado.text = if (DECIMAL.matches(precio)) {
            val moneda = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-PE"))
            getString(R.string.decimal_formateado, moneda.format(precio.replace(',', '.').toBigDecimal()))
        } else {
            ""
        }
        val hora = binding.etTime.text.toString().trim()
        binding.tvMinutos.text = if (HORA.matches(hora)) {
            val (horas, minutos) = hora.split(':').map(String::toInt)
            getString(R.string.hora_minutos, horas * 60 + minutos)
        } else {
            ""
        }
        Log.d(TAG_TEXTO, "Campos evaluados: $total. Campos con error: $invalidos.")
    }

    private fun errorTelefono(valor: String): Int? =
        if (valor.count { it in '0'..'9' } in 7..15 && Patterns.PHONE.matcher(valor).matches()) null
        else R.string.telefono_invalido

    private fun fechaValida(valor: String): Boolean {
        if (!FECHA.matches(valor)) return false
        val formato = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT).apply { isLenient = false }
        return runCatching { formato.parse(valor) != null }.getOrDefault(false)
    }

    private fun aviso(mensaje: Int) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        Log.d(TAG_TEXTO, getString(mensaje))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        actualizarVistaPrevia(binding.etPlainText.text ?: "")
        actualizarNota(binding.etMultiline.length())
        actualizarEtiquetas()
        actualizarSeleccion()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(TEXTO_EXPANDIDO, textoExpandido)
        outState.putBoolean(MARCADO, binding.ctvOpcion.isChecked)
    }

    private companion object {
        const val TEXTO_EXPANDIDO = "texto_expandido"
        const val MARCADO = "marcado"
        val PIN = Regex("[0-9]{4,6}")
        val HORA = Regex("(?:[01]?[0-9]|2[0-3]):[0-5][0-9]")
        val FECHA = Regex("[0-9]{2}/[0-9]{2}/[0-9]{4}")
        val ENTERO = Regex("[0-9]+")
        val DECIMAL = Regex("[0-9]+(?:[.,][0-9]{1,2})?")
    }
}

internal fun errorCorreo(valor: String): Int? =
    if (Patterns.EMAIL_ADDRESS.matcher(valor).matches()) null else R.string.correo_invalido

internal fun errorClave(valor: String): Int? = when {
    valor.isBlank() -> R.string.campo_requerido
    valor.length < 6 -> R.string.clave_corta
    valor.none { it in '0'..'9' } -> R.string.clave_sin_numero
    else -> null
}

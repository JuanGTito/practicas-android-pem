package com.juantito.programacionequiposmoviles

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import com.juantito.programacionequiposmoviles.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val bordes = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout() or
                    WindowInsetsCompat.Type.ime()
            )
            view.setPadding(bordes.left, bordes.top, bordes.right, bordes.bottom)
            insets
        }

        binding.tieNombre.doAfterTextChanged { revisarNombre() }
        binding.tieEmail.doAfterTextChanged { revisarCorreo() }
        binding.tiePassword.doAfterTextChanged { revisarClave() }
        binding.tieBio.doAfterTextChanged { revisarBio() }
        binding.tiePassword.setOnEditorActionListener { _, accion, _ ->
            if (accion == EditorInfo.IME_ACTION_DONE) {
                validarRegistro()
                true
            } else {
                false
            }
        }
        binding.btnRegistrar.setOnClickListener { validarRegistro() }
    }

    private fun validarRegistro() {
        val resultados = listOf(revisarNombre(), revisarCorreo(), revisarClave(), revisarBio())
        val valido = resultados.all { it }
        if (valido) {
            binding.tiePassword.text?.clear()
            binding.tilPassword.error = null
        }
        val mensaje = if (valido) R.string.registro_correcto else R.string.registro_revisar
        binding.tvEstadoRegistro.setText(mensaje)
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        Log.d(TAG_TEXTO, "Formulario de demostración válido: $valido")
    }

    private fun revisarNombre(): Boolean {
        val error = if (binding.tieNombre.text.isNullOrBlank()) R.string.campo_requerido else null
        binding.tilNombre.error = error?.let(::getString)
        return error == null
    }

    private fun revisarCorreo(): Boolean {
        val correo = binding.tieEmail.text.toString().trim()
        val error = if (correo.isEmpty()) R.string.campo_requerido else errorCorreo(correo)
        binding.tilEmail.error = error?.let(::getString)
        return error == null
    }

    private fun revisarClave(): Boolean {
        val error = errorClave(binding.tiePassword.text.toString())
        binding.tilPassword.error = error?.let(::getString)
        return error == null
    }

    private fun revisarBio(): Boolean {
        val valida = binding.tieBio.length() <= binding.tilBio.counterMaxLength
        binding.tilBio.error = if (valida) null else getString(R.string.bio_larga)
        return valida
    }
}

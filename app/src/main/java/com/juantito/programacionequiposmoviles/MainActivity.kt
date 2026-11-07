package com.juantito.programacionequiposmoviles

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.Toast
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val removedChipIds = mutableSetOf<Int>()
    private var restoringViews = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_buttons)

        // Dejamos espacio para las barras del sistema sin tapar los controles.
        val pantalla = findViewById<View>(R.id.pantalla_botones)
        ViewCompat.setOnApplyWindowInsetsListener(pantalla) { view, insets ->
            val barras = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            )
            view.setPadding(barras.left, barras.top, barras.right, barras.bottom)
            insets
        }

        savedInstanceState?.getIntArray(CHIPS_ELIMINADOS)?.let { removedChipIds.addAll(it.toList()) }
        practicaCapitulo9(primeraVez = savedInstanceState == null)
    }

    private fun practicaCapitulo9(primeraVez: Boolean) {
        // BUTTON / IMAGEBUTTON --------------------------------------------------------------------
        // findViewById conecta el id del XML con la vista de Kotlin.
        val boton = findViewById<Button>(R.id.btn_boton)
        boton.setOnClickListener { mostrarMensaje(getString(R.string.iniciar_sesion)) }
        boton.setOnLongClickListener {
            mostrarMensaje(getString(R.string.pulsacion_larga))
            true // Consumimos la pulsación larga para que no dispare el clic normal.
        }
        findViewById<MaterialButton>(R.id.btn_material).setOnClickListener {
            mostrarMensaje(getString(R.string.boton_material))
        }
        findViewById<ImageButton>(R.id.ib_email).setOnClickListener {
            mostrarMensaje(getString(R.string.accion_email))
        }

        // CHIP / CHIPGROUP ------------------------------------------------------------------------
        val condiciones = findViewById<ChipGroup>(R.id.cg_condiciones)
        // Al recrear la pantalla respetamos los chips que el usuario ya cerró.
        condiciones.children.filter { it.id in removedChipIds }.toList().forEach(condiciones::removeView)
        if (R.id.chip_opcion !in removedChipIds) {
            // Este Chip se agrega desde Kotlin. Su id fijo permite reconocerlo al guardar el estado.
            val opcion = Chip(this).apply {
                id = R.id.chip_opcion
                setText(R.string.chip_opcion)
                isCloseIconVisible = true
            }
            condiciones.addView(opcion)
        }
        condiciones.children.filterIsInstance<Chip>().forEach { chip ->
            chip.setOnCloseIconClickListener {
                removedChipIds.add(chip.id)
                condiciones.removeView(chip)
                mostrarMensaje(getString(R.string.chip_eliminado, chip.text))
            }
        }
        condiciones.findViewById<Chip>(R.id.chip_opcion)?.setOnClickListener {
            mostrarMensaje(getString(R.string.chip_opcion))
        }
        // El grupo permite elegir varias condiciones a la vez y entrega sus ids.
        condiciones.setOnCheckedStateChangeListener { grupo, ids ->
            val seleccion = ids.joinToString { grupo.findViewById<Chip>(it).text }
            mostrarMensaje(
                if (seleccion.isEmpty()) getString(R.string.sin_condiciones)
                else getString(R.string.condiciones_elegidas, seleccion)
            )
        }

        // RADIOGROUP / RADIOBUTTON ----------------------------------------------------------------
        val destinos = findViewById<RadioGroup>(R.id.rg_vacaciones)
        val transporte = findViewById<RadioGroup>(R.id.rg_transporte)
        if (primeraVez) destinos.check(R.id.rb_montana) // Selección inicial desde Kotlin.
        // Cada grupo mantiene una sola opción marcada. Usamos el id para leer el texto elegido.
        destinos.setOnCheckedChangeListener { grupo, id -> mostrarRadio(grupo, id, R.string.destino_elegido) }
        transporte.setOnCheckedChangeListener { grupo, id -> mostrarRadio(grupo, id, R.string.transporte_elegido) }

        // CHECKBOX / TOGGLEBUTTON / SWITCH --------------------------------------------------------
        val seguro = findViewById<CheckBox>(R.id.cb_seguro)
        seguro.isEnabled = true
        if (primeraVez) seguro.isChecked = true
        observarEstado(seguro, R.string.seguro)
        observarEstado(findViewById<ToggleButton>(R.id.tg_normal), R.string.toggle_normal)
        observarEstado(findViewById<ToggleButton>(R.id.tg_custom), R.string.toggle_custom)
        observarEstado(findViewById<Switch>(R.id.sw_normal), R.string.switch_normal)
        observarEstado(findViewById<SwitchCompat>(R.id.sw_custom), R.string.switch_custom)

        // FLOATINGACTIONBUTTON --------------------------------------------------------------------
        findViewById<FloatingActionButton>(R.id.fab_button).setOnClickListener {
            mostrarMensaje(getString(R.string.accion_fab))
        }
    }

    // Android llama a este método público desde android:onClick en la casilla del XML.
    fun onCheckBoxClicked(view: View) {
        val reserva = view as CheckBox
        mostrarEstado(R.string.reserva_cancelable, reserva.isChecked)
    }

    private fun mostrarRadio(grupo: RadioGroup, id: Int, formato: Int) {
        grupo.findViewById<RadioButton>(id)?.let { mostrarMensaje(getString(formato, it.text)) }
    }

    // Estos controles comparten CompoundButton y entregan el nuevo estado como Boolean.
    private fun observarEstado(control: CompoundButton, etiqueta: Int) {
        control.setOnCheckedChangeListener { _, marcado -> mostrarEstado(etiqueta, marcado) }
    }

    private fun mostrarEstado(etiqueta: Int, marcado: Boolean) {
        val estado = if (marcado) R.string.estado_activo else R.string.estado_inactivo
        mostrarMensaje(getString(R.string.estado_control, getString(etiqueta), getString(estado)))
    }

    private fun mostrarMensaje(mensaje: String) {
        if (restoringViews) return
        Log.d(TAG, mensaje)
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntArray(CHIPS_ELIMINADOS, removedChipIds.toIntArray())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        // Restaurar un control puede disparar su listener igual que una interacción.
        restoringViews = true
        super.onRestoreInstanceState(savedInstanceState)
        restoringViews = false
    }

    private companion object {
        const val TAG = "PRACTICA-CAPITULO-9"
        const val CHIPS_ELIMINADOS = "chips_eliminados"
    }
}

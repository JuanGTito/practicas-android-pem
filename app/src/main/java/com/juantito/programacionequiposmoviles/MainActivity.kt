package com.juantito.programacionequiposmoviles

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.juantito.programacionequiposmoviles.databinding.ActivityWidgetsBinding
import java.text.DateFormat
import java.util.Calendar
import java.util.Date
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityWidgetsBinding
    private var progresoJob: Job? = null
    private var fechaElegida = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // View Binding crea una propiedad con el tipo de cada vista que tenga id en el XML.
        binding = ActivityWidgetsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val bordes = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout() or
                    WindowInsetsCompat.Type.ime()
            )
            view.setPadding(bordes.left, bordes.top, bordes.right, bordes.bottom)
            insets
        }

        practicaCapitulo10(savedInstanceState)
    }

    private fun practicaCapitulo10(estado: Bundle?) {
        // IMAGEVIEW -------------------------------------------------------------------------------
        // La imagen viene de los recursos locales. El clic muestra la acción en Toast y Logcat.
        binding.ivEjemplo.setOnClickListener { mostrarMensaje(getString(R.string.imagen_pulsada)) }

        // PROGRESSBAR -----------------------------------------------------------------------------
        // El indicador circular solo señala que hay una tarea activa. La barra muestra cuánto avanzó.
        actualizarProgreso(estado?.getInt(PROGRESO) ?: 0)
        binding.btnIniciarProgreso.setOnClickListener { simularProgreso(desde = 0) }
        if (estado?.getBoolean(PROGRESO_ACTIVO) == true) {
            simularProgreso(desde = binding.pbHorizontal.progress)
        }

        // SEEKBAR CONTINUO Y DISCRETO --------------------------------------------------------------
        // El volumen va de 0 a 100. El tamaño de texto usa tres posiciones con un valor fijo en sp.
        observarBarra(binding.sbVolumen) { progreso, fromUser ->
            val mensaje = getString(R.string.volumen_valor, progreso)
            binding.tvVolumen.text = mensaje
            if (fromUser) Log.d(TAG, mensaje)
        }
        val tamanos = resources.getIntArray(R.array.tamanos_texto_sp)
        val opciones = resources.getStringArray(R.array.opciones_tamano_texto)
        binding.sbTamano.max = tamanos.lastIndex
        observarBarra(binding.sbTamano) { indice, fromUser ->
            binding.tvTamano.textSize = tamanos[indice].toFloat()
            binding.tvTamano.text = getString(R.string.tamano_valor, opciones[indice])
            if (fromUser) Log.d(TAG, binding.tvTamano.text.toString())
        }

        // RATINGBAR -------------------------------------------------------------------------------
        binding.tvRating.text = getString(R.string.calificacion_valor, binding.rbCalificacion.rating)
        binding.rbCalificacion.setOnRatingBarChangeListener { _, estrellas, fromUser ->
            val mensaje = getString(R.string.calificacion_valor, estrellas)
            binding.tvRating.text = mensaje
            if (fromUser) mostrarMensaje(mensaje)
        }

        // CALENDARVIEW / TEXTCLOCK -----------------------------------------------------------------
        // CalendarView entrega el mes desde cero, igual que Calendar. TextClock se actualiza solo.
        fechaElegida = estado?.getLong(FECHA) ?: binding.cvCalendario.date
        binding.cvCalendario.date = fechaElegida
        mostrarFecha()
        binding.cvCalendario.setOnDateChangeListener { _, anio, mes, dia ->
            fechaElegida = Calendar.getInstance().apply {
                clear()
                set(anio, mes, dia)
            }.timeInMillis
            mostrarFecha()
            Log.d(TAG, binding.tvFecha.text.toString())
        }

        // SEARCHVIEW / LISTVIEW -------------------------------------------------------------------
        prepararBusqueda(estado?.getString(CONSULTA).orEmpty())
    }

    private fun simularProgreso(desde: Int) {
        actualizarProgreso(desde)
        binding.btnIniciarProgreso.isEnabled = false
        binding.pbIndeterminado.isVisible = true

        // delay suspende la corrutina sin bloquear la pantalla. lifecycleScope la cancela al salir.
        progresoJob = lifecycleScope.launch {
            for (avance in (desde + 1)..binding.pbHorizontal.max) {
                delay(35)
                actualizarProgreso(avance)
            }
            binding.pbIndeterminado.isVisible = false
            binding.btnIniciarProgreso.isEnabled = true
            mostrarMensaje(getString(R.string.progreso_completado))
        }
    }

    private fun actualizarProgreso(avance: Int) {
        binding.pbHorizontal.progress = avance
        binding.tvPorcentaje.text = getString(R.string.porcentaje, avance)
    }

    private fun observarBarra(barra: SeekBar, mostrarValor: (Int, Boolean) -> Unit) {
        mostrarValor(barra.progress, false)
        barra.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                mostrarValor(progress, fromUser)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) = Unit

            override fun onStopTrackingTouch(seekBar: SeekBar) = Unit
        })
    }

    private fun mostrarFecha() {
        val fecha = DateFormat.getDateInstance(DateFormat.MEDIUM).format(Date(fechaElegida))
        binding.tvFecha.text = getString(R.string.fecha_elegida, fecha)
    }

    private fun prepararBusqueda(consultaInicial: String) {
        val temas = resources.getStringArray(R.array.temas_busqueda)
        val adaptador = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)
        binding.lvResultados.adapter = adaptador

        // Filtramos siempre desde los datos originales para recuperar filas al borrar la consulta.
        fun filtrar(consulta: String) {
            val resultados = temas.filter { it.contains(consulta.trim(), ignoreCase = true) }
            adaptador.setNotifyOnChange(false)
            adaptador.clear()
            adaptador.addAll(resultados)
            adaptador.notifyDataSetChanged()
            binding.lvResultados.isVisible = resultados.isNotEmpty()
            binding.tvSinResultados.isVisible = resultados.isEmpty()
        }

        binding.svBusqueda.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                binding.svBusqueda.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filtrar(newText)
                return true
            }
        })
        binding.svBusqueda.setQuery(consultaInicial, false)
        filtrar(consultaInicial)
        binding.lvResultados.setOnItemClickListener { _, _, posicion, _ ->
            adaptador.getItem(posicion)?.let { mostrarMensaje(getString(R.string.tema_elegido, it)) }
        }
    }

    private fun mostrarMensaje(mensaje: String) {
        Log.d(TAG, mensaje)
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(PROGRESO, binding.pbHorizontal.progress)
        outState.putBoolean(PROGRESO_ACTIVO, progresoJob?.isActive == true)
        outState.putLong(FECHA, fechaElegida)
        outState.putString(CONSULTA, binding.svBusqueda.query.toString())
    }

    private companion object {
        const val TAG = "PRACTICA-CAPITULO-10"
        const val PROGRESO = "progreso"
        const val PROGRESO_ACTIVO = "progreso_activo"
        const val FECHA = "fecha"
        const val CONSULTA = "consulta"
    }
}

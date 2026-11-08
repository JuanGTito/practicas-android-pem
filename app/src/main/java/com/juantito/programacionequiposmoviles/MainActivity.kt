package com.juantito.programacionequiposmoviles

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import com.juantito.programacionequiposmoviles.databinding.ActivityComponentsBinding

internal const val TAG_COMPONENTES = "PRACTICA-CAPITULO-6"

class MainActivity : FragmentActivity() {
    private lateinit var binding: ActivityComponentsBinding
    private var verInformacion = false
    private val receptor = AirplaneModeReceiver { activo -> mostrarModoAvion(activo) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityComponentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val bordes = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            )
            view.setPadding(bordes.left, bordes.top, bordes.right, bordes.bottom)
            insets
        }
        verInformacion = savedInstanceState?.getBoolean(PANEL_INFORMACION) == true
        binding.btnMapa.setOnClickListener {
            verInformacion = false
            mostrarPaneles()
        }
        binding.btnInformacion.setOnClickListener {
            verInformacion = true
            mostrarPaneles()
        }
        mostrarPaneles()
        registrar("onCreate")
    }

    private fun mostrarPaneles() {
        val dosPaneles = resources.getBoolean(R.bool.componentes_dos_paneles)
        val informacionPrincipal = !dosPaneles && verInformacion
        binding.botonesPanel.isVisible = !dosPaneles
        binding.panelSecundario.isVisible = dosPaneles
        binding.btnMapa.isEnabled = informacionPrincipal
        binding.btnInformacion.isEnabled = !informacionPrincipal

        val principal = supportFragmentManager.findFragmentById(R.id.panel_principal) as? MapaJuliacaFragment
        val secundario = supportFragmentManager.findFragmentById(R.id.panel_secundario)
        supportFragmentManager.beginTransaction().apply {
            setReorderingAllowed(true)
            if (principal?.esInformacion != informacionPrincipal) {
                replace(R.id.panel_principal, MapaJuliacaFragment.crear(informacionPrincipal))
            }
            if (dosPaneles && secundario == null) {
                add(R.id.panel_secundario, MapaJuliacaFragment.crear(informacion = true))
            } else if (!dosPaneles && secundario != null) {
                remove(secundario)
            }
        }.commitNow()
    }

    override fun onStart() {
        super.onStart()
        ContextCompat.registerReceiver(
            this, receptor, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED),
            ContextCompat.RECEIVER_NOT_EXPORTED
        )
        mostrarModoAvion(Settings.Global.getInt(contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) != 0)
        registrar("onStart")
    }

    override fun onResume() {
        super.onResume()
        registrar("onResume")
    }

    override fun onPause() {
        registrar("onPause")
        super.onPause()
    }

    override fun onStop() {
        unregisterReceiver(receptor)
        registrar("onStop")
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
        registrar("onRestart")
    }

    override fun onDestroy() {
        registrar("onDestroy")
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(PANEL_INFORMACION, verInformacion)
        registrar("onSaveInstanceState")
    }

    private fun mostrarModoAvion(activo: Boolean) {
        binding.tvModoAvion.setText(if (activo) R.string.modo_avion_activo else R.string.modo_avion_inactivo)
    }

    private fun registrar(evento: String) {
        Log.d(TAG_COMPONENTES, "MainActivity: $evento")
        binding.tvCiclo.text = getString(R.string.ciclo_actividad, evento)
    }

    private companion object {
        const val PANEL_INFORMACION = "panel_informacion"
    }
}

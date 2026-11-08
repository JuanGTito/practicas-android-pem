package com.juantito.programacionequiposmoviles

import android.Manifest
import android.annotation.SuppressLint
import android.appwidget.AppWidgetManager
import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.juantito.programacionequiposmoviles.databinding.FragmentMapaJuliacaBinding
import java.io.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.XYTileSource
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker

class MapaJuliacaFragment : Fragment() {
    private var binding: FragmentMapaJuliacaBinding? = null
    internal val esInformacion get() = requireArguments().getBoolean(INFORMACION)

    private val pedirFotos = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        if (binding != null) {
            if (tieneAccesoFotos()) consultarUltimaFoto() else binding?.tvFoto?.setText(R.string.fotos_permiso_denegado)
        }
    }
    private val pedirNotificaciones = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        if (binding != null) reproducirAudio()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Configuration.getInstance().apply {
            val directorio = File(context.cacheDir, "osmdroid")
            osmdroidBasePath = directorio
            osmdroidTileCache = File(directorio, "tiles")
            userAgentValue = "${context.packageName}/capitulo-6"
        }
        registrar("onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registrar("onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        registrar("onCreateView")
        return FragmentMapaJuliacaBinding.inflate(inflater, container, false).also { binding = it }.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pantalla = requireNotNull(binding)
        pantalla.tvPanel.setText(if (esInformacion) R.string.panel_informacion else R.string.panel_mapa)
        pantalla.tvContexto.setText(if (esInformacion) R.string.contexto_informacion else R.string.contexto_mapa)
        pantalla.mapOsm.apply {
            // El fragmento se encarga de liberar el mapa en onDestroyView.
            setDestroyMode(false)
            setTileSource(XYTileSource(
                "OpenStreetMap", 0, 19, 256, ".png", arrayOf("https://tile.openstreetmap.org/")
            ))
            setMultiTouchControls(true)
            controller.setZoom(savedInstanceState?.getDouble(ZOOM, 16.0) ?: 16.0)
            controller.setCenter(
                GeoPoint(savedInstanceState?.getDouble(LATITUD, LAT_JULIACA) ?: LAT_JULIACA,
                    savedInstanceState?.getDouble(LONGITUD, LON_JULIACA) ?: LON_JULIACA)
            )
            overlays.add(Marker(this).apply {
                position = GeoPoint(LAT_JULIACA, LON_JULIACA)
                title = getString(R.string.plaza_juliaca)
                setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            })
            setOnTouchListener { mapa, evento ->
                mapa.parent.requestDisallowInterceptTouchEvent(
                    evento.actionMasked != MotionEvent.ACTION_UP && evento.actionMasked != MotionEvent.ACTION_CANCEL
                )
                false
            }
        }
        pantalla.btnPlay.setOnClickListener {
            if (Build.VERSION.SDK_INT >= 33 && !tienePermiso(Manifest.permission.POST_NOTIFICATIONS)) {
                pedirNotificaciones.launch(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                reproducirAudio()
            }
        }
        pantalla.btnStop.setOnClickListener {
            requireContext().stopService(Intent(requireContext(), MusicService::class.java))
        }
        pantalla.btnExplicitIntent.setOnClickListener { abrirDetalles(animada = false) }
        pantalla.btnAnimacion.setOnClickListener { abrirDetalles(animada = true) }
        pantalla.btnImplicitIntent.setOnClickListener {
            val ubicacion = Uri.encode(getString(R.string.plaza_juliaca))
            abrirUri(Uri.parse("geo:$LAT_JULIACA,$LON_JULIACA?q=$LAT_JULIACA,$LON_JULIACA($ubicacion)"),
                R.string.mapa_externo_no_disponible)
        }
        pantalla.tvAtribucion.setOnClickListener {
            abrirUri(Uri.parse("https://www.openstreetmap.org/copyright"), R.string.navegador_no_disponible)
        }
        pantalla.btnMediaStore.setOnClickListener {
            if (tieneAccesoFotos()) consultarUltimaFoto() else pedirFotos.launch(permisosFotos())
        }
        pantalla.btnWidget.setOnClickListener { agregarWidget() }
        registrar("onViewCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        registrar("onViewStateRestored")
    }

    override fun onStart() {
        super.onStart()
        registrar("onStart")
    }

    override fun onResume() {
        super.onResume()
        binding?.mapOsm?.onResume()
        registrar("onResume")
    }

    override fun onPause() {
        binding?.mapOsm?.onPause()
        registrar("onPause")
        super.onPause()
    }

    override fun onStop() {
        registrar("onStop")
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding?.mapOsm?.let { mapa ->
            outState.putDouble(LATITUD, mapa.mapCenter.latitude)
            outState.putDouble(LONGITUD, mapa.mapCenter.longitude)
            outState.putDouble(ZOOM, mapa.zoomLevelDouble)
        }
        registrar("onSaveInstanceState")
    }

    override fun onDestroyView() {
        binding?.mapOsm?.onDetach()
        binding = null
        registrar("onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        registrar("onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        registrar("onDetach")
        super.onDetach()
    }

    private fun reproducirAudio() {
        val contexto = requireContext()
        try {
            ContextCompat.startForegroundService(contexto, Intent(contexto, MusicService::class.java))
        } catch (_: IllegalStateException) {
            Toast.makeText(contexto, R.string.audio_no_disponible, Toast.LENGTH_SHORT).show()
        }
    }

    @Suppress("DEPRECATION")
    private fun abrirDetalles(animada: Boolean) {
        val actividad = requireActivity()
        startActivity(Intent(actividad, DetailActivity::class.java).apply {
            putExtra(DetailActivity.UBICACION, getString(R.string.plaza_juliaca))
            putExtra(DetailActivity.ANIMADA, animada)
        })
        if (animada && Build.VERSION.SDK_INT < 34) {
            actividad.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }

    private fun abrirUri(uri: Uri, sinAplicacion: Int) {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        } catch (_: ActivityNotFoundException) {
            Toast.makeText(requireContext(), sinAplicacion, Toast.LENGTH_SHORT).show()
        }
    }

    private fun agregarWidget() {
        val contexto = requireContext()
        val manager = AppWidgetManager.getInstance(contexto)
        if (Build.VERSION.SDK_INT >= 26 && manager.isRequestPinAppWidgetSupported &&
            manager.requestPinAppWidget(ComponentName(contexto, MiWidgetJuliaca::class.java), null, null)
        ) return
        Toast.makeText(contexto, R.string.widget_agregar_manual, Toast.LENGTH_LONG).show()
    }

    private fun permisosFotos(): Array<String> = when {
        Build.VERSION.SDK_INT >= 34 -> arrayOf(
            Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
        )
        Build.VERSION.SDK_INT >= 33 -> arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
        else -> arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    private fun tieneAccesoFotos(): Boolean = permisosFotos().any(::tienePermiso)

    private fun tienePermiso(permiso: String): Boolean =
        ContextCompat.checkSelfPermission(requireContext(), permiso) == PackageManager.PERMISSION_GRANTED

    private fun consultarUltimaFoto() {
        val contexto = requireContext().applicationContext
        viewLifecycleOwner.lifecycleScope.launch {
            val resultado = try {
                withContext(Dispatchers.IO) {
                    val columnas = arrayOf(MediaStore.Images.Media.DISPLAY_NAME, MediaStore.Images.Media.SIZE)
                    val orden = "${MediaStore.Images.Media.DATE_ADDED} DESC, ${MediaStore.Images.Media._ID} DESC"
                    contexto.contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columnas, null, null, orden)
                        ?.use { cursor ->
                            if (cursor.moveToFirst()) {
                                val nombre = cursor.getString(0) ?: contexto.getString(R.string.foto_sin_nombre)
                                contexto.getString(R.string.foto_encontrada, nombre, cursor.getLong(1) / 1024)
                            } else {
                                contexto.getString(R.string.fotos_vacias)
                            }
                        } ?: contexto.getString(R.string.fotos_vacias)
                }
            } catch (_: SecurityException) {
                getString(R.string.fotos_permiso_denegado)
            }
            binding?.tvFoto?.text = resultado
        }
    }

    private fun registrar(evento: String) {
        Log.d(TAG_COMPONENTES, "MapaJuliacaFragment (${if (esInformacion) "información" else "mapa"}): $evento")
    }

    companion object {
        private const val INFORMACION = "informacion"
        private const val LATITUD = "latitud"
        private const val LONGITUD = "longitud"
        private const val ZOOM = "zoom"
        private const val LAT_JULIACA = -15.4967
        private const val LON_JULIACA = -70.1333

        fun crear(informacion: Boolean) = MapaJuliacaFragment().apply {
            arguments = bundleOf(INFORMACION to informacion)
        }
    }
}

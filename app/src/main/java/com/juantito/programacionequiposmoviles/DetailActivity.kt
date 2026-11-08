package com.juantito.programacionequiposmoviles

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.juantito.programacionequiposmoviles.databinding.ActivityDetailBinding

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= 34 && intent.getBooleanExtra(ANIMADA, false)) {
            overrideActivityTransition(OVERRIDE_TRANSITION_OPEN, R.anim.slide_in_right, R.anim.slide_out_left)
        }
        enableEdgeToEdge()
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val bordes = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            )
            view.setPadding(bordes.left, bordes.top, bordes.right, bordes.bottom)
            insets
        }
        binding.tvUbicacion.text = getString(
            R.string.detalle_ubicacion, intent.getStringExtra(UBICACION) ?: getString(R.string.plaza_juliaca)
        )
        binding.btnAnimarTexto.setOnClickListener {
            binding.tvUbicacion.apply {
                animate().cancel()
                alpha = 0f
                translationX = 24 * resources.displayMetrics.density
                animate().alpha(1f).translationX(0f).setDuration(250).start()
            }
        }
    }

    companion object {
        const val UBICACION = "ubicacion"
        const val ANIMADA = "animada"
    }
}

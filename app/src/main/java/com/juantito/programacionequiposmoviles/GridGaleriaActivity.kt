package com.juantito.programacionequiposmoviles

import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.ImageView
import kotlin.math.roundToInt

class GridGaleriaActivity : LayoutDemoActivity(R.layout.activity_grid_galeria) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val grid = findViewById<GridLayout>(R.id.grid_galeria)
        val altura = (84 * resources.displayMetrics.density).roundToInt()
        val imagenes = listOf(
            android.R.drawable.ic_menu_camera,
            android.R.drawable.ic_menu_gallery,
            android.R.drawable.ic_menu_compass,
            android.R.drawable.ic_menu_mapmode,
            android.R.drawable.ic_menu_today,
            android.R.drawable.ic_menu_agenda
        )
        imagenes.forEachIndexed { indice, imagen ->
            val etiqueta = getString(R.string.galeria_imagen, indice + 1)
            val celda = ImageButton(this).apply {
                setImageResource(imagen)
                scaleType = ImageView.ScaleType.CENTER_INSIDE
                contentDescription = etiqueta
                // Usamos el índice para calcular la fila y la columna de cada imagen.
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 0
                    height = altura
                    rowSpec = GridLayout.spec(indice / grid.columnCount)
                    columnSpec = GridLayout.spec(indice % grid.columnCount, GridLayout.FILL, 1f)
                }
                setOnClickListener { avisar(etiqueta) }
            }
            grid.addView(celda)
        }
        Log.d(TAG_LAYOUTS, resources.getQuantityString(R.plurals.galeria_cargada, imagenes.size, imagenes.size))
    }
}

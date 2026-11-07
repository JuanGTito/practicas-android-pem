package com.juantito.programacionequiposmoviles

import android.os.Bundle
import android.util.Log
import android.widget.TableLayout
import androidx.annotation.StringRes

class TableDinamicActivity : LayoutDemoActivity(R.layout.activity_table_dinamic) {
    private data class Producto(val codigo: String, @get:StringRes val nombre: Int, val precio: Int)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tabla = findViewById<TableLayout>(R.id.table_productos)
        val productos = listOf(
            Producto("001", R.string.producto_uno, 12),
            Producto("002", R.string.producto_dos, 18),
            Producto("003", R.string.producto_tres, 65)
        )
        val moneda = monedaDeEjemplo()
        // El encabezado está en XML. Las filas de datos se agregan desde Kotlin.
        productos.forEach { producto ->
            tabla.agregarFila(producto.codigo, getString(producto.nombre), moneda.format(producto.precio))
        }
        Log.d(TAG_LAYOUTS, resources.getQuantityString(R.plurals.filas_productos, productos.size, productos.size))
    }
}

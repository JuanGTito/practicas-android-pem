package com.juantito.programacionequiposmoviles

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.juantito.programacionequiposmoviles.databinding.FragmentReporteBinding

class ReporteFragment : Fragment(R.layout.fragment_reporte) {
    private data class Venta(val fecha: String, val cliente: String, val monto: Int)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabla = FragmentReporteBinding.bind(view).tableVentas
        val ventas = listOf(
            Venta("10/11/2026", "A", 150),
            Venta("11/11/2026", "B", 200),
            Venta("12/11/2026", "C", 120)
        )
        val moneda = monedaDeEjemplo()
        ventas.forEach { venta ->
            tabla.agregarFila(
                venta.fecha, getString(R.string.cliente_ejemplo, venta.cliente), moneda.format(venta.monto)
            )
        }
        // La última fila se calcula con los mismos datos usados para dibujar la tabla.
        tabla.agregarFila("", getString(R.string.total), moneda.format(ventas.sumOf { it.monto }), destacada = true)
        Log.d(TAG_LAYOUTS, resources.getQuantityString(R.plurals.filas_ventas, ventas.size, ventas.size))
    }
}

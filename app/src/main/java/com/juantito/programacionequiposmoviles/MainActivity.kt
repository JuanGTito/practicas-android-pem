package com.juantito.programacionequiposmoviles

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.juantito.programacionequiposmoviles.databinding.ActivityMainBinding

class MainActivity : LayoutDemoActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // View Binding conecta los botones del menú con sus Activities.
        val binding = ActivityMainBinding.bind(findViewById(R.id.menu_layouts))

        // LinearLayout
        binding.btnLinearBasic.abrir(LinearBasicActivity::class.java)
        binding.btnLinearLogin.abrir(LinearLoginActivity::class.java)
        binding.btnLinearCard.abrir(LinearCardActivity::class.java)
        binding.btnLinearNested.abrir(LinearNestedActivity::class.java)
        binding.btnLinearWeight.abrir(LinearWeightActivity::class.java)

        // FrameLayout
        binding.btnFrameBasic.abrir(FrameBasicActivity::class.java)
        binding.btnFrameBadge.abrir(FrameBadgeActivity::class.java)
        binding.btnFrameFragment.abrir(FrameFragmentActivity::class.java)

        // RelativeLayout
        binding.btnRelativeBasic.abrir(RelativeBasicActivity::class.java)
        binding.btnRelativeTransform.abrir(RelativeTransformActivity::class.java)

        // ConstraintLayout
        binding.btnConstraintBasic.abrir(ConstraintBasicActivity::class.java)
        binding.btnConstraintGuideline.abrir(ConstraintGuidelineActivity::class.java)
        binding.btnConstraintBarrier.abrir(ConstraintBarrierActivity::class.java)
        binding.btnConstraintChain.abrir(ConstraintChainActivity::class.java)

        // TableLayout
        binding.btnTableBasic.abrir(TableBasicActivity::class.java)
        binding.btnTableDinamic.abrir(TableDinamicActivity::class.java)
        binding.btnReporte.abrir(ReporteActivity::class.java)

        // GridLayout
        binding.btnGridBasic.abrir(GridBasicActivity::class.java)
        binding.btnGridCalculator.abrir(GridCalculatorActivity::class.java)
        binding.btnGridGaleria.abrir(GridGaleriaActivity::class.java)

        // Space
        binding.btnSpaceBasic.abrir(SpaceBasicActivity::class.java)
    }

    private fun Button.abrir(destino: Class<out LayoutDemoActivity>) {
        setOnClickListener {
            Log.d(TAG_LAYOUTS, "Abriendo ${destino.simpleName}")
            startActivity(Intent(this@MainActivity, destino))
        }
    }
}

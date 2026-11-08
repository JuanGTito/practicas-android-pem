package com.juantito.programacionequiposmoviles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

const val TAG_PRACTICA_03 = "Practica-03"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Ejecutamos los temas síncronos del capítulo 4.
        Practica03()

        // Las corrutinas se ligan al ciclo de vida de la Activity.
        lifecycleScope.launch {
            ejecutarCorrutinasFundamentos()
            ejecutarCorrutinasFlow()
        }
    }
}

// Función principal de la práctica 03, siguiendo el patrón de la práctica 01.
fun Practica03() {
    ejecutarFuncionesOrdenSuperior()
    ejecutarFuncionesInline()
    ejecutarRecursionTailrec()
    ejecutarGenericos()
    ejecutarOperadoresColecciones()
    ejecutarSecuencias()
    ejecutarManejoErrores()
    ejecutarLanzamientoExcepciones()
    ejecutarRunCatchingResult()
    ejecutarSealedClass()
    ejecutarDelegacion()
    ejecutarScopeFunctions()
    ejecutarTypeAlias()
}

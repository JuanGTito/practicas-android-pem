package com.juantito.programacionequiposmoviles

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Pratica01()
        //enableEdgeToEdge()
        //setContent {
        //    ProgramacionEquiposMovilesTheme {
        //        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        //            Greeting(
        //                name = "Practica 1",
        //                modifier = Modifier.padding(innerPadding)
        //            )
        //        }
        //    }
        //}
    }
}
fun Pratica01(){
    //Variables - Sintaxis Básica

    val TAG = "Practica-01"

    val nombre = "Juan Gedoen"
    Log.d(TAG, "${nombre}")
    val apellido: String = "Tito Moya"
    Log.d(TAG, "${apellido}")
    var edad = 25
    Log.d(TAG, "${edad}")
    var estatura: Double = 1.72
    Log.d(TAG, "${estatura}")
    val result: Int
    result = 10

    var contador: Int
    contador = 1
    contador = 2

    var correo: String = "Juangedeont@gmail.com"
    var telefono: String? = null
    telefono = "987654321"

    //Operadores Matemáticos

    val a = 15
    val b = 7

    Log.d(TAG, "${a + b}")
    Log.d(TAG, "${a - b}")
    Log.d(TAG, "${a / b}")
    Log.d(TAG, "${a * b}")
    Log.d(TAG, "${a % b}")

    var cont = 10
    cont++
    Log.d(TAG, "${cont}")

    val resultado = 5 / 2
    Log.d(TAG, "${resultado}")

    val exacto = 5.0 / 2
    Log.d(TAG, "${exacto}")

    val calculo = 10 + 5 * 2
    Log.d(TAG, "${calculo}")
    val conParentesis = (10 + 5) * 2
    Log.d(TAG, "${conParentesis}")

    // Operadores de Comparación
    val x = 10
    val y = 20

    Log.d(TAG, "${x == y}")
    Log.d(TAG, "${x != y}")
    Log.d(TAG, "${x < y}")
    Log.d(TAG, "${x >= y}")

    val c = "Hola"
    val d = "Hola"

    Log.d(TAG, "${c == d}")
    Log.d(TAG, "${c === d}")

    // llamamo a edad que declaramos arriba

    if (edad in 18..30){
        Log.d(TAG, "Estas en el rango de edad")
    }
    if (edad in 1..17){
        Log.d(TAG, "Eres menor de edad")
    }

    val algo: Any = "soy un texto"

    if (algo is String){
        Log.d(TAG, "la longitud es ${algo.length}")
    }
    if (algo !is Int) {
        Log.d(TAG, " Es un numero entero")
    }

    //Estructura de control If-Else
    var temperatura = 25

    if (temperatura > 30){
        Log.d(TAG, "Hace calor")
    } else if (temperatura in 15..30){
        Log.d(TAG, "el clima esta agradable")
    } else {
        Log.d(TAG, "hace frio")
    }

    edad = 18
    val mensaje = if (edad >= 18) "es mayor de edad" else "es menor de edad"
    Log.d(TAG, "${mensaje}")

    val e = 10
    val f = 20

    val mayor = if (e > f){
        Log.d(TAG, "Eligiendo a ...")
        a
    } else {
        Log.d(TAG, "ELigiendo b ...")
        b
    }

    val tieneEntrada = true
    edad = 20
    if (tieneEntrada && edad >= 18){
        Log.d(TAG, "Bienvenido al concierto")
    } else {
        Log.d(TAG, "NO puedes pasar")
    }

    //EStructura de Control When
    val dia  = 3
    when (dia) {
        1       -> Log.d(TAG, "Lunes")
        2       -> Log.d(TAG, "Martes")
        3       -> Log.d(TAG, "Miercoles")
        else    -> Log.d(TAG, "Otro dia")
    }

    val nota = 8
    when (nota) {
        10, 9   -> Log.d(TAG, "Excelnte")
        8, 7    -> Log.d(TAG, "Muy Excelnte")
        in 5..6 -> Log.d(TAG, "Aprovado")
        !in 0..10   -> Log.d(TAG, "Nota no valida")
        else -> Log.d(TAG, "Suspendido")
    }

    val operacion = "Suma"
    val ab = 10
    val bc = 5
    val resultad = when (operacion) {
        "Suma"              -> ab + bc
        "Resta"             -> ab - bc
        "Multiplicacion"    -> ab * bc
        else                -> 0
    }
    Log.d(TAG, "El resultado es: ${resultad}")

    val xy = 15
    val yz = 20
    when {
        xy > yz -> Log.d(TAG, "x es mayor que y")
        xy < yz -> Log.d(TAG, "x es menor que y")
        xy == yz -> Log.d(TAG, "son iguales")
        else -> Log.d(TAG, "condicion extraña")
    }

    fun describir(obj: Any){
        when (obj){
            is Int  -> Log.d(TAG, "Es un numero entero: $obj")
            is String  -> Log.d(TAG, "Es un texto de longitudo: ${obj.length}")
            is Double  -> Log.d(TAG, "Es un decimal: $obj")
            else -> Log.d(TAG, "Tipo desconocido")
        }
    }
    describir(9)

    //Estructura de Control Do-While

//    var numero: Int
//    do {
//        Log.d(TAG, "Introduce un numero mayor que 10 para salir")
//        numero = readLine()?.toInt() ?: 0
//    } while (numero <= 10)
//    Log.d(TAG, "Gracias!! Saliste del bucle,")

    var xyz = 100

    while (xyz < 10) {
        Log.d(TAG, "Esto no se vera")
        xyz++
    }

    do {
        Log.d(TAG, "Entre al menos una vez aunque la condicion sea falsa")
    } while (xyz < 10)

//    do {
//        val datoProcesado = calcularAlgo()
//        Log.d(TAG, "Procesando...")
//    } while (datoProcesado != null)

    for (i in 1..10) {
        if (i == 5) {
            break
        }
        Log.d(TAG, "$i")
    }
    Log.d(TAG, "Bucle terminado")

    for (i in 1..3) {
        for (j in 1..3) {
            if (i == 2 && j == 2) break
            Log.d(TAG, "i: $i, j: $j")
        }
    }

    bucleExterno@ for (i in 1..3) {
        for (j in 1..3) {
            if (i == 2 && j == 2) {
                break@bucleExterno
            }
            Log.d(TAG, "i: $i, j: $j")
        }
    }

    //Estructura de Bucle For

    for (i in 1..5) {
        Log.d(TAG, "$i")
    }

    for (i in 1..5) {Log.d(TAG, "$i")}
    for (i in 1 until 5) {Log.d(TAG, "$i")}
    for (i in 5 downTo 1) {Log.d(TAG, "$i")}
    for (i in 1..10 step 2) {Log.d(TAG, "$i")}

    var frutas = listOf("Manzana", "PLatano", "Cereza")
    for (fruta in frutas) {
        Log.d(TAG, "hoy comere: $frutas")
    }

    var lenguajes = arrayOf("Kotlin", "Java", "Python", "C++")
    for (i in lenguajes.indices) {
        Log.d(TAG, "EL lenguaje en la posicion $i es ${lenguajes[i]}")
    }

    for ((indice, valor) in lenguajes.withIndex()) {
        Log.d(TAG, "Indice: $indice --> Valor: $valor")
    }

    val edades = mapOf("Ana" to 25, "Luis" to 30, "Marta" to 22)
    for ((nombre, edad) in edades) {
        Log.d(TAG, "$nombre tiene $edad años")
    }
}
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ProgramacionEquiposMovilesTheme {
//        Greeting("Android")
//    }
//}
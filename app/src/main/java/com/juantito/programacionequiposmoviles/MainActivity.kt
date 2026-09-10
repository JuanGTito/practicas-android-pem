package com.juantito.programacionequiposmoviles

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Llamamos a la funcion dentro del mainActiviy
        Pratica01()
        // Borramos el resto ya que no nesesitamos por ahora en la compilacion
        // o en la ejecucion de la app, El capitulo 1 aun no llega a mostrar algo en mobil.
    }
}
// Cambiamos el main por una Funcion llamada Practica01 que se avanza en el capitulo 3
fun Pratica01(){
    //VARIABLES - SINTAXIS SIMPLE ------------------------------------------------------------------

    // Segun el Cap 1 declaramos un TAG para identificar mas rapido en LogCat y
    // esta variable llamamos en Log.d(TAG, ....)
    val TAG = "Practica-01"

    //declaramos variables tanto String, Int, Bouble
    val nombre = "Juan Gedoen" // Cadena (String)
    val apellido: String = "Tito Moya" // Aqui a diferencia de nombre especificamos la variable "String"
    var edad = 25 // Entero (Int)
    val estatura: Double = 1.72 // Decimal (Double)
    val result: Int // Con val nos referimos que es constante no cambia su valor como en "Global", "let", "Const", etc de otro lenguajes
    result = 10
    Log.d(TAG, "$nombre") // Llamamos a la variable nombre dentro de ${}
    Log.d(TAG, "$apellido")
    Log.d(TAG, "$edad")
    Log.d(TAG, "$estatura")
    Log.d(TAG, "$result")


    var contador: Int // A diferencia del val -> var si puede cambiar su valor  como en este caso en un contador
    contador = 1
    contador = 2

    var correo: String = "Juangedeont@gmail.com"
    var telefono: String? = null // Intregamos un null y tambien con "?" decimos q acepta null
    telefono = "987654321"

    //OPERADORES MATEMATICOS -----------------------------------------------------------------------

    val a = 15
    val b = 7

    Log.d(TAG, "${a + b}") // Dentro de {} ponemos la operacion con o lo que va operar sea +,-,*, etc.
    Log.d(TAG, "${a - b}")
    Log.d(TAG, "${a / b}")
    Log.d(TAG, "${a * b}")
    Log.d(TAG, "${a % b}")

    var cont = 10 // Operador de cont = cont + 1 esta simplificado con cont++ se visualiza en la siguiente linea
    cont++
    Log.d(TAG, "$cont")

    val resultado = 5 / 2 // En la misma declaracion de variables tambien podemos incluir operaciones que almacena el resultado
    Log.d(TAG, "$resultado")

    val exacto = 5.0 / 2
    Log.d(TAG, "$exacto")

    val calculo = 10 + 5 * 2
    Log.d(TAG, "$calculo")
    val conParentesis = (10 + 5) * 2 // Similar una operacion pero solo ahora incluye el parentesis, para priorizar la operacion q inicia
    Log.d(TAG, "$conParentesis")

    // Operadores de Comparación
    val x = 10
    val y = 20

    // Son expresiones Booleanas como resultado las siguiente linea te dara un valor de True or False
    Log.d(TAG, "${x == y}") // si la variable X es igual a Y dara resultado True pero si no False que en nuestro caso es Falso
    Log.d(TAG, "${x != y}") // aqui similar solo dira q X no es igual a Y, si es verdad True sino False
    Log.d(TAG, "${x < y}") // Similar pero ahora compara si X es menor a Y, como resultado True 10 es menor q 20
    Log.d(TAG, "${x >= y}") //  Similar ahora dice si X = 10 es mayor y igual que Y = 20, Resultado es FAlse

    val c = "Hola"
    val d = "Hola"

    Log.d(TAG, "${c == d}") // Anteriormente ya vimos el caso aqui sera True por que ambos String son los mismo
    Log.d(TAG, "${c === d}") // Esto ya hace referencia si apuntan a la misma direccion de memoria

    // llamamo a edad que declaramos arriba que es "val edad = 25"

    // En la condicion if de abajo dentro de los parentesis hay una condicion dice que si o si debe cumplir
    // En este caso dice que edad debe estra dentro del rango de 18 a 30 como edad es 25 y esta dentro del rango
    // Pasa a las llaves del if y ejecuta la linea de -- Log
    if (edad in 18..30){ // in es con condicionador de "debe estar dentro de"
        Log.d(TAG, "Estas en el rango de edad")
    }
    if (edad in 1..17){ // aqui similar solo q son para el rango de 1 a 17 si cumple pasa a las llaves del if
        Log.d(TAG, "Eres menor de edad")
    }

    val algo: Any = "soy un texto" // Any es un tipo de super clase q acepta String

    // El is es como confirmar, dice si la variable algo es de tipo String
    if (algo is String){
        Log.d(TAG, "la longitud es ${algo.length}")
    }
    if (algo !is Int) { // el !is no es de tipo, la variable algo no es de tipo Int
        Log.d(TAG, " Es un numero entero")
    }

    //ESTRUCTURA DE CONTROL IF-ELSE ----------------------------------------------------------------

    var temperatura = 25

    if (temperatura > 30){ // Si cumple con la condicion (...)
        Log.d(TAG, "Hace calor")
    } else if (temperatura in 15..30){ // si no cumple el 1er if de la condicion esta la verifica
        Log.d(TAG, "el clima esta agradable")
    } else { // Si en caso no cumple ninguna de las condiciones o los ifs de arriba
        Log.d(TAG, "hace frio")
    }

    edad = 18
    // Si lo anterior era en bloques los If de la forma tradicional
    // en la siguiente vemos como un operador ternario pero lo remplazamos con If en kotlin
    // es seria otra forma de hacer un if con la declaracion de variable sin usar {}
    val mensaje = if (edad >= 18) "es mayor de edad" else "es menor de edad"
    Log.d(TAG, "$mensaje")

    val e = 10
    val f = 20

    // Aqui el metodo ternacion seria pero con bloques el if junto a la declaracion de variable
    val mayor = if (e > f){
        Log.d(TAG, "Eligiendo a ...")
        a
    } else {
        Log.d(TAG, "ELigiendo b ...")
        b
    }

    val tieneEntrada = true
    edad = 20
    // aqui seria operadores logicos, como el && representa y
    // Seria como dos condiciones dentro de un (...) de if
    // cuando es && significa que ambas condiciones deben cumplir osea deben ser True
    if (tieneEntrada && edad >= 18){
        Log.d(TAG, "Bienvenido al concierto")
    } else { // Si en caso no cumpla la condicon de arriba
        Log.d(TAG, "NO puedes pasar")
    }

    //ESTRUCTURA DE CONTROL WHEN -------------------------------------------------------------------

    val dia  = 3
    // Similar al switch de python
    // When toma la variable, segun eso busca si el valor es igual al que esta dentro de {}
    when (dia) {
        1       -> Log.d(TAG, "Lunes")
        2       -> Log.d(TAG, "Martes")
        3       -> Log.d(TAG, "Miercoles")
        else    -> Log.d(TAG, "Otro dia") // Si en caso no sea ninguna esto seria la ultima opcion como if-else
    }

    val nota = 8
    // Aqui manejamos con rango
    when (nota) {
        10, 9   -> Log.d(TAG, "Excelnte") // Si nota es 10 y 9
        8, 7    -> Log.d(TAG, "Muy Excelnte")
        in 5..6 -> Log.d(TAG, "Aprovado") // Si nota "esta dentro" de 5 a 6
        !in 0..10   -> Log.d(TAG, "Nota no valida") // Si nota "no esta dentro" de 0 a 10
        else -> Log.d(TAG, "Suspendido") // Si en caso no es ninguno de los anteriores
    }

    val operacion = "Suma"
    val ab = 10
    val bc = 5
    // SImilar el caso pero como operacion ternario en esta caso si el string o cadena o palabra esta dentro de when {}
    // Segun la variable operacion hace la operacion en when
    val resultad = when (operacion) {
        "Suma"              -> ab + bc // Ya no es una salida simple si nos ña salida es una operacion y se guarda en resultado
        "Resta"             -> ab - bc
        "Multiplicacion"    -> ab * bc
        else                -> 0 // Si en caso no
    }
    Log.d(TAG, "El resultado es: $resultad") // Imprimimos el resultado segun lo que hace when

    val xy = 15
    val yz = 20
    // En este caso primero evalua la operacion de comparacion (>, <, ==) y luego imprime
    when {
        xy > yz -> Log.d(TAG, "x es mayor que y") // Primero evalua si xy > yz es True
        xy < yz -> Log.d(TAG, "x es menor que y")
        xy == yz -> Log.d(TAG, "son iguales")
        else -> Log.d(TAG, "condicion extraña")
    }

    // La funcion pide un valor osea esta declarada con parametro esa funcion
    // el "is" dentro de when dice si obj es entero o string o double
    fun describir(obj: Any){
        when (obj){ // segun el parametro o valor pasado por funcion evalua
            is Int  -> Log.d(TAG, "Es un numero entero: $obj") // Si es de tipo in el valor o parametro imprime eso
            is String  -> Log.d(TAG, "Es un texto de longitudo: ${obj.length}") // Si es string imprime la linea
            is Double  -> Log.d(TAG, "Es un decimal: $obj")
            else -> Log.d(TAG, "Tipo desconocido")
        }
    }
    // Aqui pasamos el parametro o valor a evaluar que es nueve
    describir(9)

    //ESTRUCTURA DO-WHILE --------------------------------------------------------------------------

    var numero: Int
    // Aqui sera como si nosotros ingresaramos el numero donde nos pide en do
    numero = 17

    // Primero la ejecucion donde "do" ejecuta primero y luego while
    // si no pusieramos numero = 17 el bucle seguiria preguntando introduce...
    do {
        Log.d(TAG, "Introduce un numero mayor que 10 para salir")
        numero = readLine()?.toInt() ?: 0 // Lee si la linea es 10
    } while (numero <= 10) // Va preguntar hasta q numero sea mayor que 10
    Log.d(TAG, "Gracias!! Saliste del bucle,")

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

    //ESTRUCTURA DE BUCLE FOR

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

    // ESTRUCTURA DE BUCLE WHILE

    var conteo = 0
    while (conteo < 10) {
        Log.d(TAG, "$conteo")
        conteo++
    }

    var linea: String? = ""
    Log.d(TAG, "Escribe salir para SALIR del bucle")
    while (linea != "SALIR") {
        linea = readLine()
        if (linea != "SALIR") {
            Log.d(TAG, "Escribiste >> $linea")
        }
    }

    while(true) {
        Log.d(TAG, "Esto nunca detendra")
    }

    //ARREGLOS (Array)

    val  nombres = arrayOf("Ana", "Pedro", "Luis")
    val numeros = arrayOf(1, 2, 3, 6, 2, 6, 7)

    val multiplosDeDos = Array(6) {i -> i * 2}
    Log.d(TAG, "mi array: ${multiplosDeDos}")

    val age = intArrayOf(14, 29, 14, 17, 34, 23)
    val price = doubleArrayOf(10.9, 14.45, 20.9, 12.9, 5.0)

    val planetas =  arrayOf("Mercurio", "Tierra", "Marte")
    Log.d(TAG, planetas[0])
    planetas[0] = "Jupiter"
    Log.d(TAG, "Número de planetas: ${planetas.size}")

    val colores = arrayOf("Blanco", "Rojo", "Verde", "Celeste")
    for(color in colores) {
        Log.d(TAG, color)
    }
    for ((indice, valor) in colores.withIndex()) {
        Log.d(TAG, "Posicion: $indice $valor")
    }

    val number = intArrayOf(5, 4, 8, 3, 9)
    Log.d(TAG, "${number.sum()}")
    Log.d(TAG, "${number.average()}")
    Log.d(TAG, "${number.maxOrNull()}")
    Log.d(TAG, "${number.sorted()}")

    //MATRIZ ARRAY<Array<T>>

    val matriz = arrayOf(
        arrayOf(1, 1, 4),
        arrayOf(3, 7 ,2),
        arrayOf(1, 1, 3)
    )

    val tablero = Array(3) { IntArray(4) }
    val matrizLetras = Array(3) { Array(2) { "Vacio" } }


    val matrizMod = arrayOf(
        arrayOf("A", "B"),
        arrayOf("F", "G")
    )
    Log.d(TAG, matrizMod[1][0])
    matrizMod[1][1] = "L"

    for (i in matrizMod.indices) {
        for (j in matrizMod[i].indices) {
            Log.d(TAG, "MI matriz ${matrizMod[i][j]}")
        }
    }

    matrizMod.forEach { fila ->
        fila.forEach { elemento ->
            Log.d(TAG, "$elemento")
        }
    }

    val irregular = arrayOf(
        arrayOf(1, 4, 7, 3, 9),
        arrayOf(6, 3, 0),
        arrayOf(3, 4)
    )

    //COLECCION (Set)

    val colors = setOf("Blue", "Yellow", "Brown", "Skyblue")
    Log.d(TAG, "$colors")
    Log.d(TAG, "${colors.size}")


    val inviteds = mutableSetOf("Ana", "Carlos")
    inviteds.add("Jaime")
    inviteds.add("Pedro")
    inviteds.remove("Ana")
    Log.d(TAG, "$inviteds")

    val numA = setOf(1, 2, 3)
    val numB = setOf(3, 4, 5)
    Log.d(TAG, "${numA union numB}")
    Log.d(TAG, "${numA intersect numB}")
    Log.d(TAG, "${numA subtract numB}")

    val numberSorted = sortedSetOf(5, 2, 7, 4, 1)
    Log.d(TAG, "$numberSorted")

    val lenguages = setOf("Python", "Java", "Html", "Ruby", "Kotlin")
    if ("Kotlin" in lenguages) {
        Log.d(TAG, "Kotlin está en el conjunto!")
    }

    //LISTAS

    val fruits = listOf("Apple", "Banana", "Tomato")
    Log.d(TAG, fruits[0])
    Log.d(TAG, fruits.get(1))

    val tasks = mutableListOf("Shower dog", "Buy Laptop")
    tasks.add("Study Kotlin")
    tasks.add("Read Book Programing")
    tasks.removeAt(0)
    tasks[0] = "Do Ejercise"
    Log.d(TAG,"$tasks")

    val numbersList = listOf(1, 5, 3, 7, 9, 4)
    Log.d(TAG, "${numbersList.first()}")
    Log.d(TAG, "${numbersList.last()}")
    Log.d(TAG, "${numbersList.reversed()}")
    Log.d(TAG, "${numbersList.sorted()}")
    Log.d(TAG, "${numbersList.contains(5)}")

    val mutable = numbersList.toMutableList()
    val inmutable = numbersList.toList()
    val precies = listOf(10, 50, 100, 20)
    val richs = precies.filter { it > 30 }
    Log.d(TAG, "$richs")

    //MAPS

    val paises = mapOf(
        "MX" to "México",
        "ES" to "España",
        "AR" to "Argentina"
        )
    Log.d(TAG, "${paises["MX"]}")
    Log.d(TAG, "${paises.get("ES")}")
    Log.d(TAG, "${paises["FR"]}")

    val inventario = mutableMapOf(
        "Manzanas" to 10,
        "Peras" to 5
        )
    inventario["Plátanos"] = 20
    inventario["Manzanas"] = 15
    inventario.remove("Peras")
    Log.d("MI_TAG", "$inventario")

    val capitales = mapOf("Perú" to "Lima", "Chile" to "Santiago")
    for ((pais, capital) in capitales) {
        Log.d("MI_TAG", "La capital de $pais es $capital")
        }

    val edadMap = mapOf("Luis" to 25)
    Log.d("MI_TAG", "${edadMap.getOrDefault("Ana", 0)}")
    Log.d("MI_TAG", "${edadMap.containsKey("Luis")}")

    val ranking = sortedMapOf("C" to 3, "A" to 1, "B" to 2)
    Log.d("MI_TAG", "$ranking")

    //NULL SAFETY

    var nameSafety: String = "Gemini"
    // nombre = null ← ERROR de compilación
    var nameNulo: String? = "Gemini"
    nameNulo = null

    val longitud = nameNulo?.length

    val cantidad = nameNulo?.length ?: 0

    val aLaFuerza = nameNulo!!

    val usuario: String? = "Carlos"
    usuario?.let {
        Log.d("MI_TAG", "Enviando correo a $it")
        }

    val texto: String? = "Hola"
    if (texto != null) {
        Log.d("MI_TAG", "${texto.length}") // No necesitas "?" aquí dentro
        }
}

//se elimina @Composable y @Preview como capitulo 2 no corresponde a ello.
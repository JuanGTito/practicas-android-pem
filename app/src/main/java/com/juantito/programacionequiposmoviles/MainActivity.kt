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

    // Primero la ejecucion donde "do" ejecuta primero y luego while
    // si no pusieramos numero = 17 el bucle seguiria preguntando introduce...
    do {
        Log.d(TAG, "Introduce un numero mayor que 10 para salir")
        // numero = readLine()?.toInt() ?: 0 --- Lee si la linea es 10
        // Aqui sera como si nosotros ingresaramos el numero donde nos pide en do
        numero = 17
    } while (numero <= 10) // Va preguntar hasta q numero sea mayor que 10
    Log.d(TAG, "Gracias!! Saliste del bucle,")

    var xyz = 100
    // Entra en un bucle que si la condicion de bucle es mayor que 10 se sale del bucle
    while (xyz < 10) {
        Log.d(TAG, "Esto no se vera")
        // itera cada vez q entra al bucle osea se suma xyz cada vez que entra al bucle,
        // digamos xyz 1, 2, 3 hasta llegar a 10 cuando sea xyz = 10 se sale del bucle.
        xyz++
    }

    do {
        Log.d(TAG, "Entre al menos una vez aunque la condicion sea falsa")
        // Similar pero aqui cuando empieza con do () {} siempre entra a do una vez,
        // luego se va a while y empieza el bucle segun la condicion.
    } while (xyz < 10) // --> condicion del bucle

    // Aqui el proceso el el mismo pero con una funcion que devuelve algo un entero, etc
    // y con la condicion del while dice q datoProcesado debe ser diferente a vacio = null
//    do {
//        val datoProcesado = calcularAlgo()
//        Log.d(TAG, "Procesando...")
//    } while (datoProcesado != null)

    // Con for decimos q i itera desde 1 y llega hasta 10 por eso in
    for (i in 1..10) {
        // Y con la condicion evaluacion si i es igual a 5 y si es igual con brak sale del bucle for
        if (i == 5) {
            break
        }
        Log.d(TAG, "$i")
    }
    // Una vez terminado imprime esto.
    Log.d(TAG, "Bucle terminado")

    // Esto seria bucle tras bucle por que hay dos for, primero ejecuta el i luego el j
    for (i in 1..3) {
        for (j in 1..3) {
            // Y aqui evalua la si ambos tanto i como j son 2 se sale del bucle, pero solo cierra el bucle interno
            if (i == 2 && j == 2) break
            // Si no es 2 imprime
            Log.d(TAG, "i: $i, j: $j")
        }
    }

    // Usamo la siguiente linea para romper desde uno interno como vemos for tras for
    bucleExterno@ for (i in 1..3) {
        for (j in 1..3) {
            if (i == 2 && j == 2) {
                // Con esto cerramos el bucle externo y asi no se ejecutaria mas despues de cumplir la condicion
                break@bucleExterno
            }
            Log.d(TAG, "i: $i, j: $j")
        }
    }

    //ESTRUCTURA DE BUCLE FOR

    // Con for, i itera de 1 hasta 5, osea 1, 2, 3, 4, 5 y termina el bucle
    for (i in 1..5) {
        // Esto imprime  cada vez q i cambia o itera un numero diferente
        Log.d(TAG, "$i")
    }

    for (i in 1..5) {Log.d(TAG, "$i")} // For donde i itera desde 1 a 5
    for (i in 1 until 5) {Log.d(TAG, "$i")} // For donde excluye  el limite osea 5, no se imprime el 5
    for (i in 5 downTo 1) {Log.d(TAG, "$i")} // For donde itera hacia atras desde 5 a 1
    for (i in 1..10 step 2) {Log.d(TAG, "$i")} // imprime de 2 en 2, empezando desde 1, 3, ...

    // Tenemos un nuevo tipo de valor que son las listas, listOf
    var frutas = listOf("Manzana", "PLatano", "Cereza")
    // Fruta es como i itera sobre la lista por eso in frutas q es la lista
    for (fruta in frutas) {
        // IMprime la lista de posicion en posicion empezando desde "Manzana"
        Log.d(TAG, "hoy comere: $frutas")
    }

    // Esto ya es otro tipo de valor Arrays de sierta forma similar a lista
    var lenguajes = arrayOf("Kotlin", "Java", "Python", "C++")
    // Itera ya no por nombre decirlo si no por indice con esto me refiero a decir la posicion
    // "Kotlin" --> 0, "Java" --> 1 etc
    for (i in lenguajes.indices) {
        //Imprime la posicion i y con eso el valor q esta en esa posicion
        Log.d(TAG, "EL lenguaje en la posicion $i es ${lenguajes[i]}")
    }

    // es similar al de arriba pero es como se haria en Kotlin declaras ya no solo i,
    // declaras 2 variables q seria el indice osea posicion, y valor lo que tiene en esa posicion
    for ((indice, valor) in lenguajes.withIndex()) {
        // IMprime los valores cada vez q pase por for
        Log.d(TAG, "Indice: $indice --> Valor: $valor")
    }

    // similiar al anterior hace recorrido o iteracion por clave, valor con mapOf
    val edades = mapOf("Ana" to 25, "Luis" to 30, "Marta" to 22)
    // nombre -> clave, edad -> valor
    for ((nombre, edad) in edades) {
        Log.d(TAG, "$nombre tiene $edad años")
    }

    // ESTRUCTURA DE BUCLE WHILE

    // conteo seria el iterador como i en for
    var conteo = 0
    // igual condicion en while que debe ser siempre falso
    while (conteo < 10) {
        Log.d(TAG, "$conteo")
        // se suma conteo +1 cada vez que entra a while
        conteo++
    }

    // Se declara linea de tipo String
    var linea: String? = "HOLA"
    Log.d(TAG, "Escribe salir para SALIR del bucle")
    while (linea != "SALIR") {
        // linea = readLine() --- Con readLine leemos la linea o la variable linea
        linea = "SALIR" // Le damos SALIR porque en consola no podemos ingresar
        // Ya no entra a if por que ya no es verdadero la condicion, SALIR  no es diferente de SALIR
        if (linea != "SALIR") {
            Log.d(TAG, "Escribiste >> $linea")
        }
    }
    // Grave error si declaramos asi por que nunca se detendra el bucle ya que siempre sera TRUE
//    while(true) {
//        Log.d(TAG, "Esto nunca detendra")
//    }

    //ARREGLOS (Array)

    // Similar a Listas solo que los Arrays solo almacenan un tipo de valor, y no pueden cambiar de tamaño
    val  nombres = arrayOf("Ana", "Pedro", "Luis") // Array de tipo String
    val numeros = arrayOf(1, 2, 3, 6, 2, 6, 7) // Tipo Int

    // Creamos un array de tamaño igual a 6, con una regla basica
    val multiplosDeDos = Array(6) {i -> i * 2} // creara de 0, 2, 4 etc hasta completar 6 valores
    Log.d(TAG, "mi array: ${multiplosDeDos}") // imprime la posicion

    val age = intArrayOf(14, 29, 14, 17, 34, 23) // Array de un solo tipo Int para ser mas eficiente
    val price = doubleArrayOf(10.9, 14.45, 20.9, 12.9, 5.0) // de tipo double

    // array de planetas
    val planetas =  arrayOf("Mercurio", "Tierra", "Marte")
    // imprime la posicion 0 del array
    Log.d(TAG, planetas[0])
    //cambia el valor de la posicion 0 o actualiza el valor
    planetas[0] = "Jupiter"
    // imprime el tamaño del array con .size
    Log.d(TAG, "Número de planetas: ${planetas.size}")

    // Array de colores
    val colores = arrayOf("Blanco", "Rojo", "Verde", "Celeste")
    // Iteramos con for dentro de colores
    for(color in colores) {
        Log.d(TAG, color)
    }
    // similar al anterior pero incluyendo con indice y valor
    for ((indice, valor) in colores.withIndex()) {
        // imprime la posicion y el valor de esa posicion en array
        Log.d(TAG, "Posicion: $indice $valor")
    }

    val number = intArrayOf(5, 4, 8, 3, 9) // array de enteros (Int)
    Log.d(TAG, "${number.sum()}") // suma de todo el array
    Log.d(TAG, "${number.average()}") // promedio del array
    Log.d(TAG, "${number.maxOrNull()}") // valor maximo de todo el array
    Log.d(TAG, "${number.sorted()}") // debuelve un array en orden ascendente

    //MATRIZ ARRAY<Array<T>>

    // Las matrizes serian un array dentro de otro array y puede haber mas array dentro
    val matriz = arrayOf(
        arrayOf(1, 1, 4),
        arrayOf(3, 7 ,2),
        arrayOf(1, 1, 3)
    )

    // declarando array dentro de otro array con tamaño o valor
    val tablero = Array(3) { IntArray(4) }
    val matrizLetras = Array(3) { Array(2) { "Vacio" } }

    // matriz de string
    val matrizMod = arrayOf(
        arrayOf("A", "B"),
        arrayOf("F", "G")
    )
    // imprime por posicion en matriz osea fila 1 columna 0 la letra F
    Log.d(TAG, matrizMod[1][0])
    matrizMod[1][1] = "L" // cambia el valor de la fila 1 y columna 1 la letra G cambia de valor a L

    // con for imprimimos la matriz posicion por posicion por eso se declara dos for por que
    // en la posicion hay dos valores q considerar que seria la fila y columna por eso i y j
    for (i in matrizMod.indices) {
        for (j in matrizMod[i].indices) {
            // imprime la primera letra de la posicion 0,0 asi susesivamente
            Log.d(TAG, "MI matriz ${matrizMod[i][j]}")
        }
    }

    // similar al anterior solo con metodo diferente que es el forEach
    // se considera fila lo que iterara y en la ultima o dentro del for lo que imprime
    matrizMod.forEach { fila ->
        fila.forEach { elemento ->
            Log.d(TAG, "$elemento")
        }
    }

    // irregular por que no son del mismo tamaño los arrays el primero es mas grande y el ultimo peuqeño
    val irregular = arrayOf(
        arrayOf(1, 4, 7, 3, 9),
        arrayOf(6, 3, 0),
        arrayOf(3, 4)
    )

    //COLECCION (Set)

    // Los sets son especiales, en que forma por que no duplican son para valores unicos q no cambiaran
    val colors = setOf("Blue", "Yellow", "Brown", "Skyblue") // Por ejemplo los colores
    Log.d(TAG, "$colors") // imprime la coleccion set
    Log.d(TAG, "${colors.size}") // imprime el tamaño


    // son set pero mutables osea pueden codificarse
    val inviteds = mutableSetOf("Ana", "Carlos")
    //  con add agregamos a la coleccion de set
    inviteds.add("Jaime")
    inviteds.add("Pedro")
    // con remove eliminamos de la coleccion
    inviteds.remove("Ana")
    // imprime la coleccion
    Log.d(TAG, "$inviteds")

    // 2 colecciones diferentes
    val numA = setOf(1, 2, 3)
    val numB = setOf(3, 4, 5)
    // con union unimos los dos conjuntos
    Log.d(TAG, "${numA union numB}") // 1, 2, 3, 4, 5
    // con intersect unimos y se queda la intersecion el valor repetido
    Log.d(TAG, "${numA intersect numB}") // 3
    // con subtract sacamos los valores de a osea la diferencia
    Log.d(TAG, "${numA subtract numB}") //1, 2

    val numberSorted = sortedSetOf(5, 2, 7, 4, 1) // con sortedSetOf devuelve set ordenado ascendente
    Log.d(TAG, "$numberSorted") // 1, 2, 4, 5, 7


    val lenguages = setOf("Python", "Java", "Html", "Ruby", "Kotlin")
    if ("Kotlin" in lenguages) { // con esa condicion verificamos si kotlin esta dentro de lenguages o no
        Log.d(TAG, "Kotlin está en el conjunto!")
    }

    //LISTAS

    // Lista de frutas
    val fruits = listOf("Apple", "Banana", "Tomato")
    Log.d(TAG, fruits[0]) // imprime la posicion 0 osea Apple
    Log.d(TAG, fruits.get(1)) // imprime Banana, osea obtenemos con get la posicion 1

    // lista mutable se hacen cambios actualizaciones de la lista
    val tasks = mutableListOf("Shower dog", "Buy Laptop")
    tasks.add("Study Kotlin") // se agrega con add y se agrega al final
    tasks.add("Read Book Programing")
    tasks.removeAt(0) // se elimina con removeAt osea le damos la posicion q queremos borrar
    tasks[0] = "Do Ejercise" // cambiamos la posicion 0 por eso tasks[0] =
    Log.d(TAG,"$tasks") // imprime la lista

    // lista de numeros
    val numbersList = listOf(1, 5, 3, 7, 9, 4)
    Log.d(TAG, "${numbersList.first()}") // como resultado imprime 1 osea el primero
    Log.d(TAG, "${numbersList.last()}") // como resultado imprime 4 osea el ultimo
    Log.d(TAG, "${numbersList.reversed()}") // imprime toda la lista pero de atras hacia adelante
    Log.d(TAG, "${numbersList.sorted()}") // imprime en orden osea la lista esta desordenada pero con sortede imprime en orden
    Log.d(TAG, "${numbersList.contains(5)}") // imprime True por que verifica si 5 existe en la lista

    val mutable = numbersList.toMutableList() // convierte de inmutable a mutable
    val inmutable = numbersList.toList() // de mutable a inmutable
    val precies = listOf(10, 50, 100, 20)
    val richs = precies.filter { it > 30 } // filtra los precios de la lista y lo separa en rich solo los que sean mayor a 30
    Log.d(TAG, "$richs") // imprime la nueva lista con datos filtrados

    //MAPS

    // usamos para valores con clave-valor como un diccionario
    val paises = mapOf(
        "MX" to "México", // con to lo relacionamos
        "ES" to "España",
        "AR" to "Argentina",
        "FR" to "Francia"
        )
    Log.d(TAG, "${paises["MX"]}") //imprime el valor de MX
    Log.d(TAG, "${paises.get("ES")}") // consigue el valor de ES y lo imprime
    Log.d(TAG, "${paises["FR"]}")
    // si no hay valor devuelve null

    // igual map mutable para hacer update o delete
    val inventario = mutableMapOf(
        "Manzanas" to 10,
        "Peras" to 5
        )
    inventario["Plátanos"] = 20 // agregamos platano con valor de 20
    inventario["Manzanas"] = 15 // actualizamos por que ya existe manzana
    inventario.remove("Peras") // eliminamos con remove
    Log.d(TAG, "$inventario") // imprime todo el mapOf

    val capitales = mapOf("Perú" to "Lima", "Chile" to "Santiago")
    // iteramos sobre capitales con clave, valor
    for ((pais, capital) in capitales) {
        Log.d(TAG, "La capital de $pais es $capital")
        }

    val edadMap = mapOf("Luis" to 25)
    // obtenemos la clave pero si no existe en mapOf entonces lo llavama con un valor default con getOrDefault
    Log.d(TAG, "${edadMap.getOrDefault("Ana", 0)}")
    // con containsKey verificamos si existe una clave Luis y devuelve True
    Log.d(TAG, "${edadMap.containsKey("Luis")}")

    val ranking = sortedMapOf("C" to 3, "A" to 1, "B" to 2)
    Log.d(TAG, "$ranking") // como es sortedMaoOf devuelve un mapOf ordenado

    //NULL SAFETY

    // una variable no puede ser null o tener un valor vacio por decir
    var nameSafety: String = "Gemini"
    // nombre = null ← ERROR de compilación
    // por eso  ponemos el ? para que acepte nulo
    var nameNulo: String? = "Gemini"
    nameNulo = null

    val longitud = nameNulo?.length // si namenulo es null devulvera null longitud, pero si tiene un valor devolvera segun el tipo
    // "Hola" = longitud es 4

    val cantidad = nameNulo?.length ?: 0 // solo si es null con ?: 0 damos un valor  por defecto

    val aLaFuerza = nameNulo!! // convierte a la fuerza en un tipo de valor aceptable

    val usuario: String? = "Carlos"
    usuario?.let { // let para valores seguros osea cuando no es null usuario
        Log.d(TAG, "Enviando correo a $it") // it se refiere a usuario
        }

    val texto: String? = "Hola"
    if (texto != null) { // verificamos que texto no es null osea la condicion es esa q texto nunca sea null
        Log.d(TAG, "${texto.length}") // No necesitas "?" aquí dentro
        }
}

//se elimina @Composable y @Preview como capitulo 2 no corresponde a ello.
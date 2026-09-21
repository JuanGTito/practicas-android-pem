package com.juantito.programacionequiposmoviles

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.juantito.programacionequiposmoviles.configurationApp.TAG
import com.juantito.programacionequiposmoviles.configurationApp.mostrarInfo
import com.juantito.programacionequiposmoviles.ui.theme.ProgramacionEquiposMovilesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Declaramos main para ejecutar aplicacion
        main()
        enableEdgeToEdge()
        setContent {
            ProgramacionEquiposMovilesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
// PRACTICA-02
// OBJECT ------------------------------------------------------------------------------------------
// Object es instanciar un object en kotlin, es de tipo object singleton, en unica
// podemos decir q si una clase es molde de funciones, el object es un unico funcion q no cambia.
object configurationApp { // Se declara un object con variables globales q podemos mostrar en nuestro codigo
    const val TAG = "PRACTICA-02" // Una variable global con "const val" = TAG
    const val attempts = 5 // HOla modificadores ---------
    const val VERSION = "1.0.2" // Variable de tipo String
    var userActive: String = "guest" // Varaible de tipo String explicito

    fun mostrarInfo () { // Esta funcion dentro de Object es para mostrar esas variables globales y solo llamaos a esta funcion para ejecutar
        Log.d(TAG, "My version: $VERSION, Usuario: $userActive")
    }

}

// Se declara una clase, y dentro un object con companion similar a static en java, podemos acceder a
// ese object atraves de la clase, es otro tipo de object con etiqueta que es el companion
class validator {
    companion object { // asi se declara
        const val MIN_AGE = 18 // Constante de edad minima

        fun isvalid (age: Int): Boolean { // funcion donde el parametro pide edad de tipo entero y Boolean es para devolver True o False
            val result = age >= MIN_AGE // Decimos q result tiene q se la comparacion de age y min_age si es true o false
            Log.d(TAG, "valid age $age: $result") // devuelve edad mas truo o false
            return result // con return devolvemos el resultado el Log.d
        }
    }
}

interface notification { // declaramos interfaces con una funcion dentro de enviar
    fun send ()
}
// Declaramos un variable pero instanciamos un object anonimo que tiene la interfaz anonima
val myNotification = object : notification {
    override fun send () { // sobreescribe la funcion de interface segun la recla de interface
        Log.d(TAG, "Send notification anonymous ...") // Envia una notificacion
    }
}

// Funciones de nivel superior
fun calculateIGV (amount: Double, percentage: Double = 0.18) = amount * percentage
fun formatCurrency (amount: Double, symbol: String = "S/") = "$symbol ${"%.2f".format(amount)}"
fun validateEmail (email: String) = email.contains("@") && email.contains(".")
fun obtainCategory (score: Int): String {
    return when {
        score >= 18 -> "Excellent"
        score >= 14 -> "Well"
        score >= 11 -> "Regular"
        else -> "Disapproved"
    }
}

fun String.capitalizar () = this.trim().replaceFirstChar { it.uppercase() }
fun String.isValidEmail () = this.contains("@") && this.contains(".")
fun String.repeatText (times: Int) = this.repeat(times)
fun Int.isEven () = this % 2 == 0
fun Int.factorial (): Long = if (this <= 1) 1 else this * (this - 1).factorial()
fun List<Double>.averageValue () = if (isEmpty()) 0.0 else sum() / size

fun sumAll (vararg numbers: Double) = numbers.sum()
fun showOptions (title: String, vararg options: String) {
    Log.d(TAG, "=== $title ===")
    options.forEachIndexed { index, option -> Log.d(TAG, "${index + 1}. $option") }
}
fun greatest (vararg numbers: Int) = numbers.maxOrNull() ?: 0
fun calculateDiscount (price: Double, percentage: Double) = price * percentage
internal fun obtainConfiguration () = "production_config"
private fun cleanText (text: String) = text.trim().lowercase()
private fun validateRange (value: Int, min: Int, max: Int) = value in min..max
fun processPrice (price: Double): String {
    val clean = cleanText(" $price ")
    return if (validateRange(price.toInt(), 0, 10000)) "Valid price: $clean" else "Price out of range"
}

// Lambdas y objetos anonimos
val greetingLambda: (String) -> String = { name -> "Hello, $name!" }
val duplicateLambda: (Int) -> Int = { it * 2 }
val isAdultLambda: (Int) -> Boolean = { it >= 18 }
val currencyLambda: (Double) -> String = { "S/ ${"%.2f".format(it)}" }

val ascendingComparator: ComparatorL = object : ComparatorL {
    override fun compare (a: Int, b: Int) = a - b
    override fun describe () = "Ascending order"
}
val descendingComparator: ComparatorL = object : ComparatorL {
    override fun compare (a: Int, b: Int) = b - a
    override fun describe () = "Descending order"
}
private val configAnonymous = object {
    val host = "https://api.company.com"
    val timeout = 5000
    val debug = true
}
val fadeInAnimation: AnimationL = object : AnimationL {
    override fun start () { Log.d(TAG, "Fade in started") }
    override fun finish () { Log.d(TAG, "Fade in completed") }
}

// Funcion principal para imprimir y se llama dentro de MainActivity
fun main (){
    // Llamamos a la funcion de Object principal la unica declaracion
    mostrarInfo()
    // Otra funcion de Object con etiqueta companion
    validator.isvalid(20)
    // Un objecto declarado llamando a la funcion de interface
    myNotification.send()
    // CLASES --------------------------------------------------------------------------------------
    // Llamamos a la clase Person
    val Person1 = Person()
    Person1.name = "Lalo"
    Person1.walk()

    val Person2 = Person()
    Person2.name = "Kal"
    Person2.age = 3
    Person2.walk()

    val Car1 = Car("Toyota", "Black")
    Car1.drive()

    Car1.color = "Blue"
    Car1.drive()

    val MyAccount = Account()
    MyAccount.deposit(150.00)
    MyAccount.checkStatus()
    // ATRIBUTOS Y METODOS -------------------------------------------------------------------------
    val personA = PersonA ()
    personA.name = "Paolo"
    personA.walk()

    val personRE = PersonRE ("Juan", 2003)
    val ageResult = personRE.getAge(2026)
    Log.d(TAG, "Age obtained: $ageResult")

    val personaREU = PersonREU (76.4, 1.73)
    personaREU.checkStatus()
    // MODIFICADORES DE ATRIBUTOS Y METODOS

    Log.d(TAG, "Max of attempts: ${configurationApp.attempts}")
    val userManager = UserManager()
    userManager.setup("Pedro", "admin@gmail.com")
    userManager.showInfo()

    val generator = ReportGenerator()
    generator.PrintReport()

    val personP = PersonP("Johan")
    Log.d(TAG, "${personP.id}")
    personP.Greet()

    val config = Configuration()
    Log.d(TAG, "${config.apiKey}")
    config.reset()

    val animal = Animal("Dog")

    val accountB = AccountBanking("Andrea")
    accountB.deposit(50000.00)
    Log.d(TAG, "Bank balance: ${accountB.consultBalance()}")

    val dog = Dog("Rex")
    dog.bark()

    // FUNCIONES ------------------------------------------------------------------------------------
    val price = 250.00
    val igv = calculateIGV(price)
    Log.d(TAG, formatCurrency(price + igv))
    Log.d(TAG, "${validateEmail("yaramosuca@gmail.com")}")
    Log.d(TAG, obtainCategory(15))

    Log.d(TAG, " hello world ".capitalizar())
    Log.d(TAG, "${"jean@mail.com".isValidEmail()}")
    Log.d(TAG, "Kotlin ".repeatText(3))
    Log.d(TAG, "${6.isEven()}")
    Log.d(TAG, "${5.factorial()}")
    val scores = listOf(15.0, 18.0, 12.5, 16.0)
    Log.d(TAG, "Average: ${scores.averageValue()}")

    val laptopF = ProductF("Laptop", 3500.0, 5)
    val mouseF = ProductF("Mouse", 80.0, 0)
    val keyboardF = ProductF("Keyboard", 150.0, 12)
    val catalogF = listOf(laptopF, mouseF, keyboardF)
    Log.d(TAG, laptopF.label())
    Log.d(TAG, mouseF.label())
    Log.d(TAG, "Inventory total: ${catalogF.inventoryTotal()}")
    catalogF.onlyAvailable().forEach { Log.d(TAG, it.name) }

    Log.d(TAG, "${sumAll(1.0, 2.5, 3.0, 4.5)}")
    showOptions("Menu", "View profile", "Edit", "Log out")
    Log.d(TAG, "${greatest(3, 17, 5, 22, 9)}")
    Log.d(TAG, "${calculateDiscount(100.0, 0.10)}")
    Log.d(TAG, obtainConfiguration())
    Log.d(TAG, processPrice(1500.0))

    // TIPOS DE CONSTRUCTORES -----------------------------------------------------------------------
    PersonCP("Jean", 25).info()
    PersonCBI("Farfan")
    PersonCS("Carlos", 30).logData()
    PersonCS("Marta").logData()

    // SOBRECARGA DE CONSTRUCTORES ------------------------------------------------------------------
    PersonSVD("Jean", 25, "jean@mail.com").logData()
    PersonSVD("Ana", 30).logData()
    PersonSVD("Luis").logData()
    ProductAN(name = "Laptop", price = 3500.0).logInfo()
    ProductAN(name = "Mouse", stock = 50, category = "Peripherals").logInfo()
    AccountSC("Jean", "Checking", 500.0).logStatus()
    AccountSC("Ana", "Business").logStatus()
    AccountSC("Luis").logStatus()
    VehicleBI("Toyota", "Corolla", 2020).logInfo()
    VehicleBI("Honda", "Civic").logInfo()

    // DESTRUCTORES Y RECURSOS ----------------------------------------------------------------------
    var legacyConnection: LegacyConnection? = LegacyConnection("192.168.1.1")
    Log.d(TAG, "Legacy connection: ${legacyConnection?.host}")
    legacyConnection = null
    Log.d(TAG, "Legacy reference released")

    val temporaryFile = TemporaryFile("temp.txt")
    temporaryFile.write("Hello Kotlin")
    temporaryFile.close()
    DatabaseConnection("users.db").use { database ->
        Log.d(TAG, database.query("SELECT * FROM users"))
    }
    UserSession("user_42").use { Log.d(TAG, it.obtainData()) }

    // SOBRECARGA -----------------------------------------------------------------------------------
    val calculator = Calculator()
    calculator.add(3, 5)
    calculator.add(3.5, 2.1)
    calculator.add(1, 2, 3)
    calculator.add(listOf(10, 20, 30, 40))

    val vector1 = Vector(3.0, 4.0)
    val vector2 = Vector(1.0, 2.0)
    Log.d(TAG, "${vector1 + vector2}")
    Log.d(TAG, "${vector1 - vector2}")
    Log.d(TAG, "${vector1 * 2.0}")
    Log.d(TAG, "Magnitude: ${vector1.magnitude()}")

    val productOC1 = ProductOC("Laptop", 3500.0)
    val productOC2 = ProductOC("Mouse", 80.0)
    val productOC3 = ProductOC("Laptop", 3500.0)
    Log.d(TAG, "${productOC1 > productOC2}")
    Log.d(TAG, "${productOC1 == productOC3}")
    Log.d(TAG, "Cheapest: ${listOf(productOC1, productOC2).minOrNull()}")

    val matrix = Matrix(2, 3)
    matrix[0, 0] = 1.0
    matrix[0, 1] = 2.0
    matrix[1, 2] = 5.0
    Log.d(TAG, "${matrix[0, 0]}")
    matrix.show()

    // ENCAPSULAMIENTO ------------------------------------------------------------------------------
    val temperature = Temperature()
    temperature.celsius = 100.0
    Log.d(TAG, "${temperature.celsius} C | ${temperature.fahrenheit} F")
    temperature.celsius = -300.0

    val counter = Counter()
    counter.increment()
    counter.increment()
    Log.d(TAG, "${counter.value}")
    counter.reset()

    val userE = UserE(1, "Jean", "secure123")
    Log.d(TAG, "${userE.name}: ${userE.verifyPassword("secure123")}")
    val savings = SavingsAccount("Jean")
    savings.deposit(1000.0)
    savings.withdraw(200.0)
    Log.d(TAG, "${savings.consultBalance()}")
    val productEC1 = ProductEC.create("Laptop", 1500.0)
    val productEC2 = ProductEC.create("Pencil", -5.0)
    Log.d(TAG, "${productEC1.name}: ${productEC1.price}")
    Log.d(TAG, "${productEC2.name}: ${productEC2.price}")

    // GETTERS Y SETTERS ----------------------------------------------------------------------------
    val personGS = PersonGS()
    personGS.setName("Jean")
    personGS.setAge(25)
    Log.d(TAG, "${personGS.getName()} - ${personGS.getAge()}")

    val productGSV = ProductGSV()
    productGSV.setName(" Laptop ")
    productGSV.setPrice(1500.0)
    productGSV.setPrice(-50.0)
    Log.d(TAG, "${productGSV.getName()}: ${productGSV.getPrice()}")

    val employeeGT = EmployeeGT()
    employeeGT.setName("jean torres")
    employeeGT.setMonthlySalary(3000.0)
    Log.d(TAG, "${employeeGT.getName()}: ${employeeGT.getAnnualSalary()}")

    val studentGS = StudentGS()
    studentGS.setName("Ana Garcia")
    studentGS.setCode("STU-2024")
    studentGS.setAverage(17.5)
    studentGS.showInfo()

    // USO DE FIELD ---------------------------------------------------------------------------------
    val exampleField = ExampleField()
    exampleField.text = "Hello"
    Log.d(TAG, exampleField.text)

    val personField = PersonField()
    personField.name = "jean"
    personField.age = 25
    Log.d(TAG, "${personField.name}: ${personField.age}")

    val productField = ProductField()
    productField.price = 150.0
    productField.price = -10.0
    productField.name = " laptop "
    Log.d(TAG, "${productField.name}: ${productField.price}")

    val thermostat = Thermostat()
    thermostat.temperature = 35.0
    thermostat.temperature = 22.0
    Log.d(TAG, "${thermostat.temperature}")

    val studentField = StudentField()
    studentField.name = "ana garcia"
    studentField.code = "STU-2024"
    studentField.average = 17.5
    studentField.showInfo()

    val circleField = CircleField(5.0)
    Log.d(TAG, "Area: ${circleField.area} | Perimeter: ${circleField.perimeter}")
    val playerField = PlayerField("jean")
    playerField.score(60)
    playerField.score(50)
    Log.d(TAG, "${playerField.name}: ${playerField.points}, level ${playerField.level}")

    // HERENCIA Y SUBCLASES -------------------------------------------------------------------------
    val dogH = DogH("Rex")
    dogH.breathe()
    dogH.makeSound()
    dogH.fetchBall()
    val catH = CatH("Luna")
    catH.makeSound()
    catH.climb()

    val rectangleH = RectangleH("red", 5.0, 3.0)
    rectangleH.describe()
    Log.d(TAG, "${rectangleH.calculateArea()}")
    val circleH = CircleH("blue", 4.0)
    circleH.describe()
    Log.d(TAG, "${circleH.calculateArea()}")

    val sportsCar = SportsCar("Ferrari", true)
    sportsCar.showInfo()
    sportsCar.activateTurbo()
    val pickupTruck = PickupTruck("Ford", 2.5)
    pickupTruck.showInfo()
    pickupTruck.showLoad()

    val digitalProduct = DigitalProduct("Kotlin Course", 49.99, "https://courses.com/kotlin")
    digitalProduct.showInfo()
    digitalProduct.download()
    val physicalProduct = PhysicalProduct("Keyboard", 180.0, 0.8)
    physicalProduct.showInfo()
    Log.d(TAG, "Shipping: ${physicalProduct.calculateShipping()}")

    val urgentH = UrgentNotificationH("Critical error", "Server down", 3)
    urgentH.show()
    urgentH.register()
    SilentNotificationH("Reminder", "Meeting at 3pm").show()

    val wolf = Wolf("Akela")
    wolf.describe()
    wolf.feed()
    wolf.howl()
    val premiumAccount = PremiumAccount("Jean")
    premiumAccount.deposit(1000.0)
    Log.d(TAG, "${premiumAccount.consultBalance()}")
    val duck = Duck("Donald")
    duck.swim()
    duck.fly()
    duck.run()
    DoubleGreeting().greet()

    // POLIMORFISMO --------------------------------------------------------------------------------
    val animalsP: List<AnimalP> = listOf(
        DogP("Rex"), CatP("Luna"), CowP("Lola"), DogP("Max"), CatP("Mishi")
    )
    processAnimals(animalsP)

    val shapesP: List<ShapeP> = listOf(
        RectangleP("red", 5.0, 3.0),
        CircleP("blue", 4.0),
        TriangleP("green", 6.0, 4.0)
    )
    shapesP.forEach { showShapeInfo(it) }
    Log.d(TAG, "Total area: ${calculateTotalArea(shapesP)}")
    val newShapesP: List<ShapeP> = listOf(PentagonP("purple", 3.0), RhombusP("orange", 8.0, 5.0))
    processShapes(newShapesP)
    shapesP.forEach { shape ->
        if (shape is CircleP) Log.d(TAG, "Radius: ${shape.radius}")
        val rectangle = shape as? RectangleP
        rectangle?.let { Log.d(TAG, "${it.width}x${it.height}") }
    }

    // CLASES ABSTRACTAS ---------------------------------------------------------------------------
    val team: List<EmployeeA> = listOf(
        Developer("Jean", "EMP-001", "Kotlin"),
        ProjectManager("Ana", "EMP-002", 5),
        Designer("Luis", "EMP-003")
    )
    team.forEach { it.showInfo() }

    val payments: List<PaymentProcess> = listOf(
        CardPayment(150.0, "4321", 500.0),
        TransferPayment(300.0, "BCP", true),
        CardPayment(800.0, "9999", 200.0)
    )
    payments.forEach { it.process() }

    val gameA: GameA = AdventureGame("Dragon Quest")
    gameA.advance()
    gameA.advance()
    gameA.advance()
    gameA.showStatus()

    val notificationsA: List<NotificationA> = listOf(
        NotificationEmail("Welcome", "Your account was created", "jean@mail.com"),
        NotificationUrgent("Server down", "Review immediately")
    )
    notificationsA.forEach { it.send(); it.register() }

    // INTERFACES -----------------------------------------------------------------------------------
    val exporters: List<Exportable> = listOf(PdfExporter(), CsvExporter(), JsonExporter())
    exporters.forEach { exporter ->
        val data = "name,age,city"
        if (exporter.validate(data)) Log.d(TAG, exporter.export(data))
    }

    val repositories: List<Registrable> = listOf(UserRepository(), ProductRepository())
    repositories.forEach { it.register(); Log.d(TAG, it.search("ID-123")); it.delete() }

    val documentI = DocumentI("Q1 Report", "https://docs.com/report.pdf")
    documentI.print()
    documentI.share("team@company.com")
    documentI.download()
    val imageI = ImageI("logo.png", "https://assets.com/logo.png")
    imageI.share("design@company.com")
    imageI.download()
    SystemI().log("Server started")
    SystemSimpleI().log("Connection established")
    val channels: List<NotifierI> = listOf(
        NotifierEmailI("jean@mail.com"), NotifierSmsI("+51 999 888 777"), NotifierPushI("token-123")
    )
    alert(channels, "Your order was dispatched")

    // COLABORACION ENTRE CLASES --------------------------------------------------------------------
    val carC = CarC("Toyota", Engine(4, "Gasoline"))
    carC.start()
    carC.stop()
    val printer = Printer("HP LaserJet")
    PrintOffice(printer).processDocument(DocumentC("Q1 Report", "Total sales: S/ 45,000"))

    val classroom = Classroom("5th A")
    classroom.add(StudentC("Jean", 16.5))
    classroom.add(StudentC("Ana", 9.0))
    classroom.add(StudentC("Luis", 13.0))
    classroom.showResults()
    Log.d(TAG, "${classroom.calculateAverage()}")

    Motorcycle("Honda").showBrand()
    val smartWatch = SmartWatch("Galaxy Watch", Battery(300), Gps("Google Maps"))
    smartWatch.showInfo()
    smartWatch.chargeDevice()
    OrderService(OrderEmail("jean@mail.com")).confirmOrder("ORD-001")
    OrderService(OrderSms("+51 999 888 777")).cancelOrder("ORD-002")

    // LAMBDAS Y OBJETOS ANONIMOS ------------------------------------------------------------------
    Log.d(TAG, greetingLambda("Jean"))
    Log.d(TAG, "${duplicateLambda(7)}")
    Log.d(TAG, "${isAdultLambda(20)}")
    Log.d(TAG, currencyLambda(1500.0))

    val buttonL = ButtonL("Send")
    buttonL.click()
    buttonL.onClick = { Log.d(TAG, "Sending form..."); Log.d(TAG, "Validating data...") }
    buttonL.onValidate = { text -> text.length >= 3 }
    buttonL.click()
    Log.d(TAG, "${buttonL.validate("AB")}")

    Log.d(TAG, "${processList(listOf(1, 2, 3, 4, 5)) { it * 3 }}")
    val reportL = ReportL("Sales")
    reportL.add("Laptop: S/ 3500")
    reportL.add("Mouse: S/ 80")
    reportL.showWith { " -> $it" }
    Log.d(TAG, "${reportL.filter { it.contains("3500") }}")

    Log.d(TAG, ascendingComparator.describe())
    Log.d(TAG, "${ascendingComparator.compare(3, 7)}")
    Log.d(TAG, descendingComparator.describe())
    Log.d(TAG, "${descendingComparator.compare(3, 7)}")
    Log.d(TAG, "${configAnonymous.host} | ${configAnonymous.timeout} | ${configAnonymous.debug}")

    val networkServer = NetworkServer("api.company.com")
    networkServer.connect()
    networkServer.setListener(object : ConnectionListener {
        override fun onConnect (host: String) { Log.d(TAG, "Successful connection to $host") }
        override fun onDisconnect (host: String) { Log.d(TAG, "Disconnected from $host") }
        override fun onError (code: Int, message: String) { Log.d(TAG, "Error [$code]: $message") }
    })
    networkServer.connect()
    networkServer.simulateError(503, "Service unavailable")
    networkServer.disconnect()
    val transformer = TransformerL { it * 2 }
    Log.d(TAG, "${transformer.transform(5)}")
    fadeInAnimation.start()
    fadeInAnimation.finish()

    // DATA CLASS -----------------------------------------------------------------------------------
    val userData = UserData(1, "Jean", "jean@mail.com")
    val productData = ProductData("P-001", "Laptop", 3500.0, 10)
    Log.d(TAG, "$userData")
    Log.d(TAG, "$productData")

    val pointData1 = PointData(3, 5)
    val pointData2 = PointData(3, 5)
    val pointNormal1 = PointNormal(3, 5)
    val pointNormal2 = PointNormal(3, 5)
    Log.d(TAG, "${pointData1 == pointData2}")
    Log.d(TAG, "${pointNormal1 == pointNormal2}")
    Log.d(TAG, "${setOf(PointData(1, 2), PointData(1, 2), PointData(3, 4)).size}")

    val configBase = ConfigurationData()
    Log.d(TAG, "$configBase")
    Log.d(TAG, "${configBase.copy(theme = "dark")}")
    Log.d(TAG, "${configBase.copy(language = "en", fontSize = 18)}")

    val coordinate = CoordinateData(-16.4, -71.5, 2335.0)
    val (latitude, longitude, altitude) = coordinate
    Log.d(TAG, "$latitude | $longitude | $altitude")
    val (success, message, resultData) = searchUserData(1)
    Log.d(TAG, "$success | $message | $resultData")

    val employeeData1 = EmployeeData(1, "Jean", 4500.0, "Development")
    val employeeData2 = EmployeeData(2, "Ana", 7000.0, "Management")
    Log.d(TAG, "$employeeData1")
    Log.d(TAG, "${employeeData1.calculateBonus(0.10)}")
    Log.d(TAG, "${employeeData2.isSenior()}")
    Log.d(TAG, "${employeeData1 == employeeData1.copy()}")
    Log.d(TAG, "${ProductNormal("Laptop", 3500.0)}")
    Log.d(TAG, "${ProductDataComparison("Laptop", 3500.0)}")
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProgramacionEquiposMovilesTheme {
        Greeting("Android")
    }
}

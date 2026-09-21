package com.juantito.programacionequiposmoviles

class ProductF (val name: String, val price: Double, val stock: Int)

fun ProductF.isAvailable () = stock > 0
fun ProductF.priceWithIGV () = price * 1.18

fun ProductF.label (): String {
    val availability = if (isAvailable()) "In stock" else "Sold out"
    return "$name - S/ ${"%.2f".format(priceWithIGV())} ($availability)"
}

fun List<ProductF>.inventoryTotal () = this.sumOf { it.price * it.stock }
fun List<ProductF>.onlyAvailable () = this.filter { it.isAvailable() }

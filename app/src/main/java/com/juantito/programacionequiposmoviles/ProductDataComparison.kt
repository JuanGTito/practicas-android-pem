package com.juantito.programacionequiposmoviles

class ProductNormal (val name: String, val price: Double) {
    override fun equals (other: Any?): Boolean {
        if (other !is ProductNormal) return false
        return name == other.name && price == other.price
    }
    override fun hashCode () = 31 * name.hashCode() + price.hashCode()
    override fun toString () = "ProductNormal(name=$name, price=$price)"
}
data class ProductDataComparison (val name: String, val price: Double)

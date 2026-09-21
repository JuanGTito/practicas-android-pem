package com.juantito.programacionequiposmoviles

class ProductOC (val name: String, val price: Double) : Comparable<ProductOC> {
    override operator fun compareTo (other: ProductOC): Int {
        return price.compareTo(other.price)
    }

    override fun equals (other: Any?): Boolean {
        if (other !is ProductOC) return false
        return name == other.name && price == other.price
    }

    override fun hashCode(): Int = 31 * name.hashCode() + price.hashCode()
    override fun toString(): String = "$name (S/ $price)"
}

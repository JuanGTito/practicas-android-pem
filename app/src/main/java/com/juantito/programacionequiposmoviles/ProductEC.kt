package com.juantito.programacionequiposmoviles

class ProductEC (val name: String, price: Double) {
    val price: Double = validatePrice(price)

    companion object {
        private const val MINIMUM_PRICE: Double = 0.01

        private fun validatePrice (price: Double): Double {
            return if (price >= MINIMUM_PRICE) price else MINIMUM_PRICE
        }

        fun create (name: String, price: Double) = ProductEC(name, price)
    }
}

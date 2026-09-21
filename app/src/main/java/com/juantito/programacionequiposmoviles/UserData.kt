package com.juantito.programacionequiposmoviles

data class UserData (val id: Int, val name: String, val email: String)
data class ProductData (val code: String, val name: String, val price: Double, val stock: Int = 0)

package com.juantito.programacionequiposmoviles

data class UserE (val id: Int, val name: String, private val password: String) {
    fun verifyPassword (input: String) = input == password
}

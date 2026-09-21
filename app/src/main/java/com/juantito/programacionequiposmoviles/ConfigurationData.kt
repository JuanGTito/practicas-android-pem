package com.juantito.programacionequiposmoviles

data class ConfigurationData (
    val language: String = "es",
    val theme: String = "light",
    val notifications: Boolean = true,
    val fontSize: Int = 16
)

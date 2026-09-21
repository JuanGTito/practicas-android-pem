package com.juantito.programacionequiposmoviles

data class CoordinateData (val latitude: Double, val longitude: Double, val altitude: Double = 0.0)
data class ResultData (val success: Boolean, val message: String, val data: String? = null)

fun searchUserData (id: Int): ResultData {
    return if (id > 0) ResultData(true, "User found", "Jean Torres")
    else ResultData(false, "User not found")
}

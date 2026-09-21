package com.juantito.programacionequiposmoviles

data class EmployeeData (val id: Int, val name: String, val salary: Double, val department: String) {
    fun calculateBonus (percentage: Double) = salary * percentage
    fun totalSalary () = salary + calculateBonus(0.10)
    fun isSenior () = salary > 5000.0
}

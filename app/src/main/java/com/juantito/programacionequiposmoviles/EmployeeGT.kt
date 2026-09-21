package com.juantito.programacionequiposmoviles

class EmployeeGT {
    private var _name: String = ""
    private var _monthlySalary: Double = 0.0

    fun getName () = _name.uppercase()
    fun setName (value: String) { _name = value.trim() }
    fun getMonthlySalary () = _monthlySalary
    fun setMonthlySalary (value: Double) {
        if (value >= 0) _monthlySalary = value
    }
    fun getAnnualSalary () = _monthlySalary * 12
}

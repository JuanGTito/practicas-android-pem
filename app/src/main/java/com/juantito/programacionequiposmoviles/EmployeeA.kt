package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

abstract class EmployeeA (val name: String, val id: String) {
    abstract val position: String
    abstract val baseSalary: Double
    val company = "TechCorp S.A."
    abstract fun calculateBonus (): Double
    fun calculateTotalSalary () = baseSalary + calculateBonus()
    fun showInfo () {
        Log.d(TAG, "[$id] $name - $position at $company")
        Log.d(TAG, "Total salary: S/ ${calculateTotalSalary()}")
    }
}

class Developer (name: String, id: String, val language: String) : EmployeeA(name, id) {
    override val position = "$language Developer"
    override val baseSalary = 4500.0
    override fun calculateBonus () = baseSalary * 0.15
}
class ProjectManager (name: String, id: String, val teamSize: Int) : EmployeeA(name, id) {
    override val position = "Project Manager"
    override val baseSalary = 7000.0
    override fun calculateBonus () = baseSalary * 0.20 + teamSize * 100.0
}
class Designer (name: String, id: String) : EmployeeA(name, id) {
    override val position = "UX/UI Designer"
    override val baseSalary = 3800.0
    override fun calculateBonus () = baseSalary * 0.10
}

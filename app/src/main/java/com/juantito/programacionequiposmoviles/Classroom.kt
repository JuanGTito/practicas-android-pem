package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

class StudentC (val name: String, val grade: Double) {
    fun showResult () {
        val status = if (grade >= 11) "Approved" else "Disapproved"
        Log.d(TAG, "$name: $grade - $status")
    }
}
class Classroom (val level: String) {
    private val students = mutableListOf<StudentC>()
    fun add (student: StudentC) {
        students.add(student); Log.d(TAG, "${student.name} added to classroom $level")
    }
    fun showResults () { students.forEach { it.showResult() } }
    fun calculateAverage () = if (students.isEmpty()) 0.0 else students.sumOf { it.grade } / students.size
}

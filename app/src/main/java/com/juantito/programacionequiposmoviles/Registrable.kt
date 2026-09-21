package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

interface Registrable {
    val entity: String
    fun register () { Log.d(TAG, "[$entity] Registered in system") }
    fun delete () { Log.d(TAG, "[$entity] Deleted from system") }
    fun search (id: String): String
}
class UserRepository : Registrable {
    override val entity = "User"
    override fun search (id: String) = "User found with id: $id"
    override fun register () { Log.d(TAG, "[$entity] Registered and welcome email sent") }
}
class ProductRepository : Registrable {
    override val entity = "Product"
    override fun search (id: String) = "Product found with id: $id"
}

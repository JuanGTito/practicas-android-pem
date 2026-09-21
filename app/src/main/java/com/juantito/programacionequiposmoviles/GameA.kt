package com.juantito.programacionequiposmoviles

import android.util.Log
import com.juantito.programacionequiposmoviles.configurationApp.TAG

abstract class GameA (val name: String) {
    protected var points = 0
    protected var level = 1
    protected var lives = 3
    abstract fun playRound (): Int
    fun advance () {
        points += playRound()
        Log.d(TAG, "Round completed - Points: $points")
        if (points >= level * 100) { level++; Log.d(TAG, "Level $level unlocked!") }
    }
    fun loseLife () { lives--; Log.d(TAG, "Remaining lives: $lives") }
    fun showStatus () { Log.d(TAG, "$name - Level: $level | Points: $points | Lives: $lives") }
}

class AdventureGame (name: String) : GameA(name) {
    override fun playRound (): Int { Log.d(TAG, "Exploring dungeon..."); return 45 }
}
class RacingGame (name: String) : GameA(name) {
    override fun playRound (): Int { Log.d(TAG, "Completing circuit..."); return 30 }
}

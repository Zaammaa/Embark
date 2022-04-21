package com.example.embark.Challenges

import android.app.Dialog
import android.content.Context
import android.widget.TextView
import com.example.embark.R

abstract class Challenge(numberOfPlayers: Int, difficulty: Int) {
    abstract val weight: Int
    abstract val difficultyMod: Array<Int>
    abstract val description: String
    abstract val iconPath: String

    var challengeDifficulty = 0
    var players = 0
    init {
        challengeDifficulty = difficulty
        players = numberOfPlayers
    }


    open fun chooseChallenge(): Challenge {
        return this
    }

    fun getDifficutlyMod(): Int {
        return difficultyMod[players - 3]
    }

    fun displayDescription(context: Context){
//        var dialog: Dialog = Dialog(context)
//        var txt: TextView = dialog.findViewById<TextView>(R.id.textbox)
//        dialog.
    }

}
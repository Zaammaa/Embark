package com.example.embark.Challenges

import android.app.Dialog
import android.content.Context
import android.widget.TextView
import com.example.embark.R

//A Challenge is any modifier to the game. They can affect the selection of tasks, or how the game is played (such as communication)
//Most tasks make increase the difficulty, but some decrease it. After all the modifiers have been generated, the app subtracts them from the difficulty and uses that for the effective difficulty

//This is the base class for challenges. Other challenges should be subclasses of this.
abstract class Challenge(numberOfPlayers: Int, difficulty: Int) {
    //Weight: This is how common the modifier is. The higher the weight, the bigger chance it gets chosen by the ChallengeSelector class
    abstract val weight: Int
    //difficultyMod: This is how challenging the challenge is for each player count
    //If the challenge does not change difficulties based on player count, just use the same value for all three indices
    abstract val difficultyMod: Array<Int>
    //description: Text that appears in the UI when the user wants to read what the challenge means
    abstract val description: String
    //iconPath: R.drawable reference
    abstract val icon: Int
    //challengeDifficulty: the total difficulty of the challenge, determined by the subclass. This is different from the difficulty mod because the subclass often has extra modifiers that affect the difficulty
    //for example, the passes challenge can choose differing numbers of passes.
    var challengeDifficulty = 0
    //players: The number of players
    var players = 0
    init {
        challengeDifficulty = difficulty
        players = numberOfPlayers
    }


    open fun chooseChallenge(): Challenge {
        return this
    }

    fun getDifficultyMod(): Int {
        return difficultyMod[players - 3]
    }

    fun displayDescription(context: Context){
//        var dialog: Dialog = Dialog(context)
//        var txt: TextView = dialog.findViewById<TextView>(R.id.textbox)
//        dialog.
    }

}
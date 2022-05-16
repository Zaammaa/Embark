package com.example.embark.Challenges

import com.example.embark.R
import kotlin.reflect.KClass

class CommanderIsSkippedChallenge(numberOfPlayers: Int, difficulty: Int) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty) {
    override val weight: Int
        get() = 5
    override val difficultyMod: Array<Int>
        get() = arrayOf(5,4,3)
    override val description: String
        get() = "The captain cannot take any tasks. Reshuffle hands if this is not possible."
    override var icon: Int = R.drawable.no_captain

    override val crew1Combatible: Boolean
        get() = true
    override val crew2Combatible: Boolean
        get() = true

    override fun chooseChallenge(): Challenge {
        challengeDifficulty = getDifficultyMod()
        return this
    }

    override fun displayFullDescription(): String{
        return description
    }

    override fun displayShortDescription(): String{
        return "The Captain cannot take any tasks"
    }
}
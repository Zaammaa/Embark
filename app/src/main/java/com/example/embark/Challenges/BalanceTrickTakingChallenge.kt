package com.example.embark.Challenges

import com.example.embark.R
import kotlin.math.roundToInt

class BalanceTrickTakingChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, gameMode = game) {
    override val weight: Int
        get() = 7
    override val difficultyMod: Array<Int>
        get() = arrayOf(0,0,0)
    override val description: String
        get() = "At no point can a crew member have two tricks more than any other crew member"
    override var icon: Int = R.drawable.balance_tricks

    override val crew1Compatible: Boolean
        get() = true
    override val crew2Compatible: Boolean
        get() = false

    override fun chooseChallenge(): Challenge {
        challengeDifficulty = (challengeDifficulty * .75).roundToInt() + getDifficultyMod()
        return this
    }

    override fun displayFullDescription(): String{
        return description
    }

    override fun displayShortDescription(): String{
        return "Balance trick taking"
    }
}
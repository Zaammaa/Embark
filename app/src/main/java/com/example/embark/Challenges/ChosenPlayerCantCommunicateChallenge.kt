package com.example.embark.Challenges

import com.example.embark.R
import kotlin.random.Random

class ChosenPlayerCantCommunicateChallenge(numberOfPlayers: Int, difficulty: Int) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty) {
    override val weight: Int
        get() = 10
    override val difficultyMod: Array<Int>
        get() = arrayOf(2,1,1)
    override val description: String
        get() = "The captain chooses a crew member (including themselves) who cannot communicate"
    override var icon: Int = R.drawable.player_cant_communicate

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
        return "Choose a crew member who cannot communicate"
    }
}
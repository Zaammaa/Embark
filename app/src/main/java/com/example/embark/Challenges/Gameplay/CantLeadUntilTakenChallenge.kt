package com.example.embark.Challenges.Gameplay

import com.example.embark.Challenges.Challenge
import com.example.embark.R
import kotlin.random.Random

class CantLeadUntilTakenChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, gameMode = game) {
    override val weight: Int
        get() = 10
    override val difficultyMod: Array<Int>
        get() = arrayOf(2,1,1)
    override val description: String
        get() = "(Suit chosen by app) cannot be lead until it is taken in a trick"
    override var icon: Int = R.drawable.heart

    override val crew1Compatible: Boolean
        get() = true
    override val crew2Compatible: Boolean
        get() = true

    var suit = ""

    override fun chooseChallenge(): Challenge {
        challengeDifficulty = getDifficultyMod()
        suit = pickRandomSuit(trumpAllowed = true)
        return this
    }

    override fun displayFullDescription(): String{
        return "$suit cards cannot be lead until one has been taken in a trick. In the event someone cannot lead, the round is lost".replaceFirstChar { it.uppercase() }
    }

    override fun displayShortDescription(): String{
        return "$suit cards cannot be lead until one has been taken in a trick".replaceFirstChar { it.uppercase() }
    }
}
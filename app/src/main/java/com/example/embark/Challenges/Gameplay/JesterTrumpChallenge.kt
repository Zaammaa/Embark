package com.example.embark.Challenges.Gameplay

import com.example.embark.Challenges.Challenge
import com.example.embark.R

class JesterTrumpChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, gameMode = game) {
    override val weight: Int
        get() = 5
    override val difficultyMod: Array<Int>
        get() = arrayOf(-2,-2,-2)
    override val description: String
        get() = "${GameSpecificNames.trump} cards no longer function as trump, but may be played at any time even to not follow suit. Leading a ${GameSpecificNames.trump} card behaves normally".replaceFirstChar { it.uppercase() }
    override var icon: Int = R.drawable.jester

    override val crew1Compatible: Boolean
        get() = true
    override val crew2Compatible: Boolean
        get() = true

    override fun chooseChallenge(): Challenge {
        challengeDifficulty = getDifficultyMod()
        return this
    }

    override fun displayFullDescription(): String{
        return description
    }

    override fun displayShortDescription(): String{
        return "${GameSpecificNames.trump} cards are no longer trump, but can be played at any time".replaceFirstChar { it.uppercase() }
    }
}
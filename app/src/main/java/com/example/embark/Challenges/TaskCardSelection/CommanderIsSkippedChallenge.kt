package com.example.embark.Challenges.TaskCardSelection

import com.example.embark.Challenges.Challenge
import com.example.embark.R

class CommanderIsSkippedChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, gameMode = game) {
    override val weight: Int
        get() = 5
    override val difficultyMod: Array<Int>
        get() = arrayOf(5,4,3)
    override val description: String
        get() = "The ${GameSpecificNames.captain} cannot take any tasks. Reshuffle hands if this is not possible."
    override var icon: Int = R.drawable.no_captain

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
        return "The ${GameSpecificNames.captain} cannot take any tasks"
    }
}
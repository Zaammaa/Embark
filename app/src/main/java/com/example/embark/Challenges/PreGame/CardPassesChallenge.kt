package com.example.embark.Challenges.PreGame

import com.example.embark.Challenges.Challenge
import com.example.embark.R
import kotlin.random.Random

class CardPassesChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, gameMode = game) {

    override val weight: Int
        get() = 20
    override val difficultyMod: Array<Int>
        get() = arrayOf(-2,-3,-3)
    override val description: String
        get() = "After all tasks are chosen and after any passing of tasks, players may pass a card from hand"
    override var icon: Int = R.drawable.distress_signal

    override val crew1Compatible: Boolean
        get() = true
    override val crew2Compatible: Boolean
        get() = true

    var direction = ""

    override fun chooseChallenge(): Challenge {
        if (Random.nextBoolean()){
            direction = "left"
        } else {
            direction = "right"
        }
        challengeDifficulty = getDifficultyMod()
        return this
    }

    override fun displayFullDescription(): String{
        return description
    }

    override fun displayShortDescription(): String{
        return "pass a card to the $direction"
    }
}
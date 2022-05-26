package com.example.embark.Challenges.PreGame

import com.example.embark.Challenges.Challenge
import com.example.embark.R
import kotlin.random.Random

class RandomCardPassChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, gameMode = game) {

    override val weight: Int
        get() = 5
    override val difficultyMod: Array<Int>
        get() = arrayOf(1,2,3)
    override val description: String
        get() = "After the first trick, each player must draw a card randomly from another player's hand"
    override var icon: Int = R.drawable.card_back

    override val crew1Compatible: Boolean
        get() = true
    override val crew2Compatible: Boolean
        get() = false

    var direction = ""

    override fun chooseChallenge(): Challenge {
        direction = pickRandomDirection()
        challengeDifficulty = getDifficultyMod()
        return this
    }

    override fun displayFullDescription(): String{
        return description
    }

    override fun displayShortDescription(): String{
        return "Draw a card from player to the $direction"
    }
}
package com.example.embark.Challenges

import com.example.embark.R
import kotlin.random.Random

class RandomPlayerCantCommunicateChallenge(numberOfPlayers: Int, difficulty: Int) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty) {
    override val weight: Int
        get() = 10
    override val difficultyMod: Array<Int>
        get() = arrayOf(3,2,2)
    override val description: String
        get() = "The chosen player (Counted clockwise from the captain) cannot communicate"
    override var icon: Int = R.drawable.player_cant_communicate

    override val crew1Combatible: Boolean
        get() = true
    override val crew2Combatible: Boolean
        get() = true

    var player = 1

    override fun chooseChallenge(): Challenge {
        challengeDifficulty = getDifficultyMod()
        player = Random.nextInt(players) + 1
        return this
    }

    override fun displayFullDescription(): String{
        return description
    }

    override fun displayShortDescription(): String{
        return "Player $player cannot communicate"
    }
}
package com.example.embark.Challenges.Communication

import com.example.embark.Challenges.Challenge
import com.example.embark.R
import kotlin.random.Random

class RandomPlayerCantCommunicateChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, gameMode = game) {
    override val weight: Int
        get() = 10
    override val difficultyMod: Array<Int>
        get() = arrayOf(3,2,2)
    override val description: String
        get() = "The chosen player (Counted clockwise from the ${GameSpecificNames.captain}) cannot communicate"
    override var icon: Int = R.drawable.player_cant_communicate

    override val crew1Compatible: Boolean
        get() = true
    override val crew2Compatible: Boolean
        get() = true

    var player = 1

    init {
        tags.add(TagOptions.Communication)
    }

    override fun chooseChallenge(): Challenge {
        challengeDifficulty = getDifficultyMod()
        player = pickRandomPlayer()
        return this
    }

    override fun displayFullDescription(): String{
        return description
    }

    override fun displayShortDescription(): String{
        return "Player $player cannot communicate"
    }
}
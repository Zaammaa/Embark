package com.example.embark.Challenges.Communication

import com.example.embark.Challenges.Challenge
import com.example.embark.R
import kotlin.random.Random

class FewerCommunicationTokensChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, gameMode = game) {
    override val weight: Int
        get() = 20
    override val difficultyMod: Array<Int>
        get() = arrayOf(1,0,0)
    override val description: String
        get() = "Place all communication tokens in the center, then remove a number of them. Any player can take from the pool of communication tokens"
    override var icon: Int = R.drawable.minus_communication_token_1

    override val crew1Compatible: Boolean
        get() = true
    override val crew2Compatible: Boolean
        get() = true

    var tokens = -1

    init {
        tags.add(TagOptions.Communication)
    }

    override fun chooseChallenge(): Challenge {
        challengeDifficulty = getDifficultyMod()
        if (Random.nextInt(100) > 30){
            tokens = -2
            challengeDifficulty += 2
            icon = R.drawable.minus_communication_token_2
        }
        return this
    }

    override fun displayFullDescription(): String{
        return description
    }

    override fun displayShortDescription(): String{
        return "You have $tokens tokens"
    }
}
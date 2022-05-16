package com.example.embark.Challenges

import com.example.embark.R
import kotlin.random.Random
import kotlin.reflect.KClass

class FewerCommunicationTokensChallenge(numberOfPlayers: Int, difficulty: Int) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty) {
    override val weight: Int
        get() = 20
    override val difficultyMod: Array<Int>
        get() = arrayOf(1,0,0)
    override val description: String
        get() = "Place all communication tokens in the center, then remove a number of them. Any player can take from the pool of communication tokens"
    override var icon: Int = R.drawable.minus_communication_token_1

    override val crew1Combatible: Boolean
        get() = true
    override val crew2Combatible: Boolean
        get() = true

    var tokens = -1
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
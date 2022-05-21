package com.example.embark.Challenges.Gameplay

import com.example.embark.Challenges.Challenge
import com.example.embark.R
import kotlin.random.Random

class FirstTrickPoolChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, gameMode = game) {

    override val weight: Int
        get() = 5
    override val difficultyMod: Array<Int>
        get() = arrayOf(-4,-4,-5)
    override val description: String
        get() = "After the first trick is taken, all cards in it are placed face $cardDirection on the table instead of being taken as a trick. Players cannot use their communication tokens normally, " +
                "but can instead spend them to swap a card from hand with one on the table, placing it face $cardDirection. The cards may only be looked at when using a token to swap."
    override var icon: Int = R.drawable.card_pool

    override val crew1Compatible: Boolean
        get() = true
    override val crew2Compatible: Boolean
        get() = true

    var cardDirection = "up"

    init {
        tags.add(TagOptions.Communication)
        tags.add(TagOptions.Unique)
    }

    override fun chooseChallenge(): Challenge {
        challengeDifficulty = getDifficultyMod()
        if (Random.nextBoolean()){
            cardDirection = "down"
            challengeDifficulty += 3
        }
        return this
    }

    override fun displayFullDescription(): String{
        return description
    }

    override fun displayShortDescription(): String{
        return "First trick becomes a pool of cards face $cardDirection"
    }
}
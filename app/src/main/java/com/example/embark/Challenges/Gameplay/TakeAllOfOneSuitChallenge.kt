package com.example.embark.Challenges.Gameplay

import com.example.embark.Challenges.Challenge
import com.example.embark.R
import kotlin.random.Random

class TakeAllOfOneSuitChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, gameMode = game) {
    override val weight: Int
        get() = 10
    override val difficultyMod: Array<Int>
        get() = arrayOf(4,5,6)
    override val description: String
        get() = "One player must take all cards in one of the four colored suits"
    override var icon: Int = R.drawable.take_all_suit

    override val crew1Compatible: Boolean
        get() = true
    override val crew2Compatible: Boolean
        get() = true

    var suit = ""

    override fun chooseChallenge(): Challenge {
        if (Random.nextBoolean()) {
            suit = "any"
            challengeDifficulty = getDifficultyMod()
        } else {
            suit = pickRandomSuit(trumpAllowed = false)
            challengeDifficulty = getDifficultyMod() * 2
        }
        return this
    }

    override fun displayFullDescription(): String{
        if (suit == "any") {
            return description
        }
        return description + " (chosen randomly). If a $suit task card is drawn, replace it with a different task"
    }

    override fun displayShortDescription(): String{
        if (suit == "any") {
            return description
        }
        return "All $suit cards to one player (replace $suit task cards)"
    }
}
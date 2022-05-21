package com.example.embark.Challenges.Gameplay

import com.example.embark.Challenges.Challenge
import com.example.embark.R
import kotlin.random.Random

class WinEachTrumpChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, gameMode = game) {

    override val weight: Int
        get() = 10
    override val difficultyMod: Array<Int>
        get() = arrayOf(4,4,5)
    override val description: String
        get() = "Each ${GameSpecificNames.trump} card must win a trick."
    override var icon: Int = R.drawable.rocket_cards

    override val crew1Compatible: Boolean
        get() = true
    override val crew2Compatible: Boolean
        get() = false

    var order = ""

    override fun chooseChallenge(): Challenge {
        var rand: Int = Random.nextInt(3)
        when (rand) {
            0 -> {
                order = "unordered"
                challengeDifficulty = getDifficultyMod()
            }
            1 -> {
                order = "ascending"
                challengeDifficulty = getDifficultyMod() + 8
            }
            2 -> {
                order = "descending"
                challengeDifficulty = getDifficultyMod() + 8
            }
        }
        return this
    }

    override fun displayFullDescription(): String{
        if (order == "unordered") {
            return "$description The order they are taken in does not matter."
        } else {
            return "$description They must be taken in $order order."
        }
    }

    override fun displayShortDescription(): String{
        return "Each ${GameSpecificNames.trump} win a trick ($order)"
    }
}
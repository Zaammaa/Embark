package com.example.embark.Challenges.Gameplay

import com.example.embark.Challenges.Challenge
import com.example.embark.R
import kotlin.random.Random

class CardDoneLastChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, gameMode = game) {
    override val weight: Int
        get() = 5
    override val difficultyMod: Array<Int>
        get() = arrayOf(5,5,6)
    override val description: String
        get() = "$card must be taken on the last trick"
    override var icon: Int = R.drawable.card_last_trick

    override val crew1Compatible: Boolean
        get() = false
    override val crew2Compatible: Boolean
        get() = true

    var card = pickRandomCard()
    var last = false

    override fun chooseChallenge(): Challenge {
        challengeDifficulty = getDifficultyMod()

        var roll = Random.nextInt(100)
        if (roll >= 80){
            icon = R.drawable.card_last_card
            challengeDifficulty += 4
            last = true
        }
        return this
    }

    override fun displayFullDescription(): String{
        if (last){
            return "$card must be the last card PLAYED in the round. Not counting extra cards from a 3 player game or other special rules"
        }
        return description
    }

    override fun displayShortDescription(): String{
        if (last){
            return "$card must be the last card PLAYED in the round"
        }
        return description
    }
}
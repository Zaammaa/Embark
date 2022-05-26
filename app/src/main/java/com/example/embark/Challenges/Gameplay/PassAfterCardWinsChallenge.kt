package com.example.embark.Challenges.Gameplay

import com.example.embark.Challenges.Challenge
import com.example.embark.R
import kotlin.random.Random

class PassAfterCardWinsChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, gameMode = game) {
    override val weight: Int
        get() = 1000
    override val difficultyMod: Array<Int>
        get() = arrayOf(-2,-2,-2)
    override val description: String
        get() = "After the [card chosen by app] wins a trick, players pass a card to their left/right [chosen by app]"
    override var icon: Int = R.drawable.win_to_pass

    override val crew1Compatible: Boolean
        get() = true
    override val crew2Compatible: Boolean
        get() = true

    var card = ""
    var direction = "right"
    var random = false

    override fun chooseChallenge(): Challenge {
        challengeDifficulty = getDifficultyMod()
        card = pickRandomCard(trumpAllowed = true)
        random = Random.nextBoolean()
        if (random){
            challengeDifficulty += 3
        }

        direction = pickRandomDirection()
        if (card.contains("[8-9]".toRegex()) && !random){
            challengeDifficulty -= 1
        } else if (card.contains("9") && random){
            challengeDifficulty += 1
        }
        return this
    }

    override fun displayFullDescription(): String{
        if (random){
            return "After the $card wins a trick (not just when it is taken), players must pass a random card to their $direction"
        } else {
            return "After the $card wins a trick (not just when it is taken), players must pass a card to their $direction of their choice"
        }

    }

    override fun displayShortDescription(): String{
        if (random){
            return "After the $card wins a trick, players must pass a random card to their $direction"
        } else {
            return "After the $card wins a trick, players must pass a card to their $direction"
        }
    }
}
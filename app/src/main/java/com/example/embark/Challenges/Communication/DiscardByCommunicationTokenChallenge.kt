package com.example.embark.Challenges.Communication

import com.example.embark.Challenges.Challenge
import com.example.embark.R

class DiscardByCommunicationTokenChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, gameMode = game) {

    override val weight: Int
        get() = 7
    override val difficultyMod: Array<Int>
        get() = arrayOf(-1,0,0)
    override val description: String
        get() = "Instead of normal communication, players spend their communication tokens to discard a card from hand. Do not show what the card is. The game ends when any player runs out of cards"
    override var icon: Int = R.drawable.communication_tokens_discard

    override val crew1Compatible: Boolean
        get() = true
    override val crew2Compatible: Boolean
        get() = true

    init {
        tags.add(TagOptions.Communication)
    }

    override fun chooseChallenge(): Challenge {
        challengeDifficulty = getDifficultyMod()
        return this
    }

    override fun displayFullDescription(): String{
        return description
    }

    override fun displayShortDescription(): String{
        return "Communication tokens discard cards"
    }
}
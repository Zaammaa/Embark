package com.example.embark.Challenges

import com.example.embark.R

class UnorderedCommunicationChallenge(numberOfPlayers: Int, difficulty: Int) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty) {

    override val weight: Int
        get() = 20
    override val difficultyMod: Array<Int>
        get() = arrayOf(3,3,3)
    override val description: String
        get() = "Communication tokens cannot be used to show the position of cards in hand"
    override val icon: Int
        get() = R.drawable.unordered_communication

    override fun chooseChallenge(): Challenge {
        challengeDifficulty = getDifficultyMod()
        return this
    }

    override fun displayFullDescription(): String{
        return description
    }

    override fun displayShortDescription(): String{
        return "Unordered Communication"
    }
}
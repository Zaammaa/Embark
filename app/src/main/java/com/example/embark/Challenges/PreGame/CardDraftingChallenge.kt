package com.example.embark.Challenges.PreGame

import com.example.embark.Challenges.Challenge
import com.example.embark.R

class CardDraftingChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, gameMode = game) {
    override val weight: Int
        get() = 10
    override val difficultyMod: Array<Int>
        get() = arrayOf(-3,-4,-4)
    override val description: String
        get() = "When dealing, lay aside one card per player. Each crew member picks a card in the same way they pick a task, adding it to their hand. " +
                "This happens at the same time as selecting tasks if tasks are available. If the 4 ${GameSpecificNames.trump} is one of the cards, the dealer starts the draft."
    override var icon: Int = R.drawable.nines

    override val crew1Compatible: Boolean
        get() = true
    override val crew2Compatible: Boolean
        get() = true

    override fun chooseChallenge(): Challenge {
        challengeDifficulty = getDifficultyMod()
        return this
    }

    override fun displayFullDescription(): String{
        return description
    }

    override fun displayShortDescription(): String{
        return "Players draft cards when selecting tasks"
    }
}
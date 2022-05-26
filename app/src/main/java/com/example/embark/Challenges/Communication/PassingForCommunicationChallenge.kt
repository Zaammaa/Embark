package com.example.embark.Challenges.Communication

import com.example.embark.Challenges.Challenge
import com.example.embark.R

class PassingForCommunicationChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, gameMode = game) {

    override val weight: Int
        get() = 7
    override val difficultyMod: Array<Int>
        get() = arrayOf(2,3,2)
    override val description: String
        get() = "Instead of normal communication, players can pass a card face down to 1 other player to look at, who then passes it back. In 5 player games, " +
                "this may be done twice for each communication token you have, and can be different cards to different players or the same player. You cannot tell the position, but may show any card in hand besides ${GameSpecificNames.trump}s"
    override var icon: Int = R.drawable.passing_for_communication

    override val crew1Compatible: Boolean
        get() = true
    override val crew2Compatible: Boolean
        get() = true

    var passesPerCommunication = 1

    init {
        tags.add(TagOptions.Communication)
    }

    override fun chooseChallenge(): Challenge {
        if (players == 5){
            passesPerCommunication = 2
        }
        challengeDifficulty = getDifficultyMod()
        return this
    }

    override fun displayFullDescription(): String{
        return description
    }

    override fun displayShortDescription(): String{
        if (passesPerCommunication == 1){
            return "Communicate by passing a card secretly to $passesPerCommunication player"
        } else {
            return "Communicate by passing $passesPerCommunication cards secretly"
        }

    }
}
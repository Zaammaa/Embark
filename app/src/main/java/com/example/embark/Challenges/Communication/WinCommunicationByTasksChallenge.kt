package com.example.embark.Challenges.Communication

import com.example.embark.Challenges.Challenge
import com.example.embark.R

class WinCommunicationByTasksChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, gameMode = game) {

    override val weight: Int
        get() = 5
    override val difficultyMod: Array<Int>
        get() = arrayOf(2,3,4)
    override val description: String
        get() = "Players start with 0 communication tokens, but gain one token for every task they complete. Won tokens are awarded to the player who completed the task. You can only gain the token if you don't have one"
    override var icon: Int = R.drawable.tasks_for_comms

    override val crew1Compatible: Boolean
        get() = true
    override val crew2Compatible: Boolean
        get() = true

    init {
        tags.add(TagOptions.Communication)
    }

    override fun chooseChallenge(): Challenge {
        challengeDifficulty = getDifficultyMod()
        //This challenge will be slightly easier in crew 1 since all tasks can be completed before the end of the round
        if (game == GameModes.PlanetNine){
            challengeDifficulty -= 1
        }
        return this
    }

    override fun displayFullDescription(): String{
        return description
    }

    override fun displayShortDescription(): String{
        return "Win communication tokens by completing tasks"
    }
}
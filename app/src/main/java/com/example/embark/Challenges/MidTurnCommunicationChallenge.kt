package com.example.embark.Challenges

import com.example.embark.R

class MidTurnCommunicationChallenge(numberOfPlayers: Int, difficulty: Int) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty) {
    override val weight: Int
        get() = 5
    override val difficultyMod: Array<Int>
        get() = arrayOf(-1,-1,-1)
    override val description: String
        get() = "Players can communicate mid trick"
    override var icon: Int = R.drawable.mid_trick_communication

    override val crew1Combatible: Boolean
        get() = true
    override val crew2Combatible: Boolean
        get() = true

    override fun chooseChallenge(): Challenge {
        challengeDifficulty = getDifficultyMod()
        return this
    }

    override fun displayFullDescription(): String{
        return description
    }

    override fun displayShortDescription(): String{
        return "Players can communicate mid trick"
    }
}
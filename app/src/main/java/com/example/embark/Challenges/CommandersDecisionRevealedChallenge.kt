package com.example.embark.Challenges

import com.example.embark.R

class CommandersDecisionRevealedChallenge(numberOfPlayers: Int, difficulty: Int) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty) {

    override val weight: Int
        get() = 20
    override val difficultyMod: Array<Int>
        get() = arrayOf(3,4,4)
    override val description: String
        get() = "With tasks face-down, commander asks each crew member 'yes' or 'no' if they can take all the tasks. Once the commander decides, reveal all the task cards"
    override val icon: Int
        get() = R.drawable.commanders_decision

    var tasks = 0

    override fun chooseChallenge(): Challenge {
        tasks = (challengeDifficulty / getDifficultyMod()).toInt()
        challengeDifficulty = tasks * getDifficultyMod()
        return this
    }

    override fun displayFullDescription(): String{
        return description
    }

    override fun displayShortDescription(): String{
        if (tasks == 1) {
            return "$tasks task to a crew member (revealed)"
        }
            return "$tasks tasks to one crew member (revealed)"
    }
}
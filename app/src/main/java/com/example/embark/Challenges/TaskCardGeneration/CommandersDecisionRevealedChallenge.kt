package com.example.embark.Challenges.TaskCardGeneration

import com.example.embark.Challenges.Challenge
import com.example.embark.R

class CommandersDecisionRevealedChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Crew1TaskCardsChallenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, game = game) {

    override val weight: Int
        get() = 20
    override val difficultyMod: Array<Int>
        get() = arrayOf(3,4,4)
    override val description: String
        get() = "With tasks face-down, commander asks each crew member 'yes' or 'no' if they can take all the tasks. Once the commander decides, reveal all the task cards"
    override var icon: Int = R.drawable.commanders_decision
    override val crew1Compatible: Boolean
        get() = true
    override val crew2Compatible: Boolean
        get() = false
    override var tasks: Int = 0

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
            return "The $tasks task to a crew member (revealed)"
        }
            return "All $tasks tasks to one crew member (revealed)"
    }
}
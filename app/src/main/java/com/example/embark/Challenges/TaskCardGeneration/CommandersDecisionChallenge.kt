package com.example.embark.Challenges.TaskCardGeneration

import com.example.embark.Challenges.Challenge
import com.example.embark.R
import kotlin.random.Random

class CommandersDecisionChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Crew1TaskCardsChallenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, game = game) {

    override val weight: Int
        get() = 100
    override val difficultyMod: Array<Int>
        get() = arrayOf(2,3,4)
    override val description: String
        get() = "With only the commander looking at the face-down task cards, the commander asks each crew member 'yes' or 'no' if they can take all the tasks. Once the commander decides, "
    override var icon: Int = R.drawable.commanders_decision
    override val crew1Compatible: Boolean
        get() = true
    override val crew2Compatible: Boolean
        get() = false
    override var tasks: Int = 0
    var type = ""

    override fun chooseChallenge(): Challenge {
        var useMod = getDifficultyMod()
        if (Random.nextBoolean()) {
            type = "revealed"
        } else {
            type = "secret"
            useMod += 1
        }
        tasks = (challengeDifficulty / useMod).toInt()
        if (tasks == 0 ) {
            tasks = 1
        }
        var extraMod = if (tasks > 3) tasks - 3 else 0
        challengeDifficulty = tasks * useMod + extraMod
        return this
    }

    override fun displayFullDescription(): String{
        if (type == "revealed") {
            return description + "reveal all the task cards"
        }
        return description + "only the chosen crew member may look at the task cards"
    }

    override fun displayShortDescription(): String{
        if (tasks == 1) {
            return "The $tasks task to a crew member ($type)"
        }
            return "All $tasks tasks to one crew member ($type)"
    }
}
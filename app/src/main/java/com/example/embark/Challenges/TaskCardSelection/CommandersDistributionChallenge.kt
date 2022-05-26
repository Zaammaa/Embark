package com.example.embark.Challenges.TaskCardSelection

import com.example.embark.Challenges.Challenge
import com.example.embark.R
import kotlin.math.roundToInt

class CommandersDistributionChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, gameMode = game) {
    override val weight: Int
        get() = 25
    override val difficultyMod: Array<Int>
        get() = arrayOf(0,1,2)
    override val description: String
        get() = "Draw task cards one at a time (placing tokens if relevant). The commander asks each crew member 'yes' or 'no' if they can take the task. The commander then decides who should take the task (including themselves). By the end of the distribution, no player can have two more tasks than another"
    override var icon: Int = R.drawable.commanders_distribution

    override val crew1Compatible: Boolean
        get() = true
    override val crew2Compatible: Boolean
        get() = false

    override fun chooseChallenge(): Challenge {
        var percent = .10 + .05 * getDifficultyMod()
        challengeDifficulty = (challengeDifficulty * percent).roundToInt()
        return this
    }

    override fun displayFullDescription(): String{
        return description
    }

    override fun displayShortDescription(): String{
        return "Commander distributes tasks evenly"
    }
}
package com.example.embark.Challenges.TaskCardSelection
import com.example.embark.Challenges.Challenge
import com.example.embark.R
import kotlin.random.Random

class TaskPassesChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, gameMode = game) {
    override val weight: Int
        get() = 150
    override val difficultyMod: Array<Int>
        get() = arrayOf(-2,-2,-2)
    override val description: String
        get() = "Each pass lets one player pass a task to another player after the tasks have been chosen,"
    override var icon: Int = R.drawable.octopus_black

    override val crew1Compatible: Boolean
        get() = true
    override val crew2Compatible: Boolean
        get() = true

    var passes = 1

    override fun chooseChallenge(): Challenge {
        passes = chooseNumberOfPasses()

        //Planet nine with 5 players get a pass always
        if (players == 5 && game == GameModes.PlanetNine){
            challengeDifficulty = getDifficultyMod() * (passes - 1)
        } else {
            challengeDifficulty = getDifficultyMod() * passes
        }
        return this
    }

    //Planet nine with 5 players get a pass always. This function is used to add it to the challenge list as a reminder
    //This method always uses 1 pass without changing the difficulty
    fun guaranteedPass(): Challenge{
        passes = 1
        challengeDifficulty = 0
        return this
    }

    //We don't want an even distribution of results
    private fun chooseNumberOfPasses(): Int {
        val roll = Random.nextInt(100)
        if (roll < 45) {
            return 1
        } else if (roll < 70){
            return 2
        } else if (roll < 90){
            return 3
        } else {
            return 4
        }
    }

    override fun displayFullDescription(): String{
        return description
    }

    override fun displayShortDescription(): String{
        if (passes > 1){
            return "$passes task card passes"
        }
        return "$passes task card pass"
    }
}
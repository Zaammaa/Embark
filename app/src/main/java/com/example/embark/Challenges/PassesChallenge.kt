package com.example.embark.Challenges
import com.example.embark.R
import kotlin.random.Random

class PassesChallenge(numberOfPlayers: Int, difficulty: Int) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty) {
    override val weight: Int
        get() = 100
    override val difficultyMod: Array<Int>
        get() = arrayOf(-2,-2,-2)
    override val description: String
        get() = "Each pass lets one player pass a task to another player after the tasks have been chosen,"
    override val icon: Int
        get() = R.drawable.octopus_black

    var passes = 1

    override fun chooseChallenge(): Challenge {
        passes = chooseNumberOfPasses()
        challengeDifficulty = when (passes) {
            0 -> {
                1 - getDifficultyMod()
            }
            1 -> {
                //1 pass is considered normal difficulty
                0
            }
            else -> {
                //subtract the difficulty modifier for each pass above 1
                getDifficultyMod() * (passes - 1)
            }
        }
        return this
    }

    //We don't want an even distribution of results. Most of the time we want 1.
    //Next most common are 0 and 2
    //And then 3 is rare and 4 is very rare
    private fun chooseNumberOfPasses(): Int {
        val zeroWeight = 15
        val oneWeight = 60
        val twoWeight = 15
        val threeWeight = 9
        val fourWeight = 1

        var roll = Random.nextInt(zeroWeight + oneWeight + twoWeight + threeWeight + fourWeight)
        when {
            roll < fourWeight -> {
                return 4
            }
            roll < fourWeight + threeWeight -> {
                return 3
            }
            roll < fourWeight + threeWeight + twoWeight -> {
                return 2
            }
            roll < fourWeight + threeWeight + twoWeight + oneWeight -> {
                return 1
            }
            else -> {
                return 0
            }
        }
    }
}
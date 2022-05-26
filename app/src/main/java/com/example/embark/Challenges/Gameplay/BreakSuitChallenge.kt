package com.example.embark.Challenges.Gameplay

import com.example.embark.Challenges.Challenge
import com.example.embark.R
import kotlin.random.Random

class BreakSuitChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, gameMode = game) {
    override val weight: Int
        get() = 7
    override val difficultyMod: Array<Int>
        get() = arrayOf(-4,-4,-4)
    override val description: String
        get() = "A random player can break suit one time"
    override var icon: Int = R.drawable.break_suit

    override val crew1Compatible: Boolean
        get() = true
    override val crew2Compatible: Boolean
        get() = true

    var player = 1
    var sayWhen = false

    override fun chooseChallenge(): Challenge {
        challengeDifficulty = getDifficultyMod()
        player = pickRandomPlayer()
        sayWhen = Random.nextBoolean()
        if (sayWhen){
            challengeDifficulty -= 2
        }
        return this
    }

    override fun displayFullDescription(): String{
        if (sayWhen){
            return "Crew member $player can break suit one time, and can say that they did so"
        } else {
            return "Crew member $player can break suit one time, and cannot say that they did so"
        }
    }

    override fun displayShortDescription(): String{
        if (sayWhen){
            return "Crew member $player can break suit one time, and can say that they did so"
        } else {
            return "Crew member $player can break suit one time, and cannot say that they did so"
        }
    }
}
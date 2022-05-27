package com.example.embark.Challenges.Gameplay

import com.example.embark.Challenges.Challenge
import com.example.embark.R
import kotlin.random.Random

class TakeWithOnesChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, gameMode = game) {

    override val weight: Int
        get() = 7
    override val difficultyMod: Array<Int>
        get() = arrayOf(4,5,6)
    override val description: String
        get() = "Win a trick using a non-trump 1"
    override var icon: Int = R.drawable.ones

    override val crew1Compatible: Boolean
        get() = true
    override val crew2Compatible: Boolean
        get() = true
    var times = 1

    override fun chooseChallenge(): Challenge {
        challengeDifficulty = getDifficultyMod()
        if (Random.nextInt(3) == 0) {
            times = 2
            challengeDifficulty = getDifficultyMod() * 2 + 1
        }
        return this
    }

    override fun displayFullDescription(): String{
        if (times == 2) {
            return description + ", twice"
        }
        return description
    }

    override fun displayShortDescription(): String{
        if (times == 2) {
            return "Win trick using a colored 1, twice"
        }
        return "Win trick using a colored 1"
    }
}
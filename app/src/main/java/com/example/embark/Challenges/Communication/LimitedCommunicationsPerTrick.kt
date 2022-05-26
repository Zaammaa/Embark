package com.example.embark.Challenges.Communication

import com.example.embark.Challenges.Challenge
import com.example.embark.R
import kotlin.random.Random

class LimitedCommunicationsPerTrick(numberOfPlayers: Int, difficulty: Int, game: String) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, gameMode = game) {
    override val weight: Int
        get() = 10
    override val difficultyMod: Array<Int>
        get() = arrayOf(1,1,2)
    override val description: String
        get() = "After a crew member communicates, no new communication can happen for 1 round"
    override var icon: Int = R.drawable.comms_per_1_trick

    override val crew1Compatible: Boolean
        get() = true
    override val crew2Compatible: Boolean
        get() = true

    var turns = 1

    init {
        tags.add(TagOptions.Communication)
    }

    override fun chooseChallenge(): Challenge {
        challengeDifficulty = getDifficultyMod()
        turns = Random.nextInt(2) + 1
        if (turns == 2){
            icon = R.drawable.comms_per_2_tricks
            challengeDifficulty += 2
        }
        return this
    }

    override fun displayFullDescription(): String{
        if (turns > 1){
            return "After a crew member communicates, no new communication can happen for $turns rounds"
        } else {
            return "After a crew member communicates, no new communication can happen for $turns round"
        }

    }

    override fun displayShortDescription(): String{
        if (turns > 1){
            return "After a crew member communicates, no new communication can happen for $turns rounds"
        } else {
            return "After a crew member communicates, no new communication can happen for $turns round"
        }
    }
}
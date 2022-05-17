package com.example.embark.Challenges

import com.example.embark.R
import kotlin.random.Random
import kotlin.reflect.KClass

class OmegaTokenChallenge(numberOfPlayers: Int, difficulty: Int) : Crew1TokensChallenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty) {
    override val weight: Int
        get() = 10
    override val difficultyMod: Array<Int>
        get() = arrayOf(2,2,2)
    override val description: String
        get() = "Place token onto the last task card drawn.  This task must be taken "
    override var icon: Int = R.drawable.last_task

    override val crew1Combatible: Boolean
        get() = true
    override val crew2Combatible: Boolean
        get() = false
    override var tokens = 1
    override var maxTokens = 1
    var type: String = ""

    override fun chooseChallenge(): Challenge {
        if (Random.nextInt(3) > 0) {
            type = "last"
        } else {
            type = "final"
        }
        challengeDifficulty = getDifficultyMod()
        if (type == "final") {
            challengeDifficulty += 4
            icon = R.drawable.final_trick
        }
        return this
    }

    override fun displayFullDescription(): String{
        if (type == "last") {
            return description + "last"
        }
        return description + "on the final trick"
    }

    override fun displayShortDescription(): String{
        if (type == "last") {
            return "omega token (last task)"
        }
        return "omega token (final trick)"
    }
}
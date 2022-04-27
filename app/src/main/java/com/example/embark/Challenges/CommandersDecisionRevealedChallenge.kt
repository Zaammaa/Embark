package com.example.embark.Challenges

import com.example.embark.R
import kotlin.reflect.KClass

class CommandersDecisionRevealedChallenge(numberOfPlayers: Int, difficulty: Int) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty) {

    override val weight: Int
        get() = 20
    override val difficultyMod: Array<Int>
        get() = arrayOf(3,4,4)
    override val description: String
        get() = "With tasks face-down, commander asks each crew member 'yes' or 'no' if they can take all the tasks. Once the commander decides, reveal all the task cards"
    override val icon: Int
        get() = R.drawable.commanders_decision
    override val crew1Combatible: Boolean
        get() = true
    override val crew2Combatible: Boolean
        get() = false
    override val incompatibleWith: List<KClass<out Challenge>>
        get() =  mutableListOf<KClass<out Challenge>>(CommandersDecisionSecretChallenge::class)

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
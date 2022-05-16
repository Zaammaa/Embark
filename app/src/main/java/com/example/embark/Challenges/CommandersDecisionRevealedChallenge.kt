package com.example.embark.Challenges

import com.example.embark.R
import kotlin.reflect.KClass

class CommandersDecisionRevealedChallenge(numberOfPlayers: Int, difficulty: Int) : Crew1TaskCardsChallenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty) {

    override val weight: Int
        get() = 20
    override val difficultyMod: Array<Int>
        get() = arrayOf(3,4,4)
    override val description: String
        get() = "With tasks face-down, commander asks each crew member 'yes' or 'no' if they can take all the tasks. Once the commander decides, reveal all the task cards"
    override var icon: Int = R.drawable.commanders_decision
    override val crew1Combatible: Boolean
        get() = true
    override val crew2Combatible: Boolean
        get() = false
    override val incompatibleWith: List<KClass<out Challenge>>
        get() =  mutableListOf<KClass<out Challenge>>(CommandersDecisionSecretChallenge::class, BasicTaskCardsChallenge::class)
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
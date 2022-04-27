package com.example.embark.Challenges

import com.example.embark.R
import kotlin.random.Random
import kotlin.reflect.KClass

class DisruptedCommunicateChallenge(numberOfPlayers: Int, difficulty: Int) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty) {
    override val weight: Int
        get() = 20
    override val difficultyMod: Array<Int>
        get() = arrayOf(0,1,2)
    override val description: String
        get() = "Players cannot use communication tokens until after a certain number of tricks have been taken"
    override var icon: Int = R.drawable.communication_down_1

    override val crew1Combatible: Boolean
        get() = true
    override val crew2Combatible: Boolean
        get() = true
    override val incompatibleWith: List<KClass<out Challenge>>
        get() =  mutableListOf<KClass<out Challenge>>(FirstTurnCommunication::class)

    var turns = 1

    override fun chooseChallenge(): Challenge {
        turns = Random.nextInt(4) + 1
        challengeDifficulty = getDifficultyMod() + turns
        when (turns) {
            1 -> {
                icon = R.drawable.communication_down_1
            }
            2 -> {
                icon = R.drawable.communication_down_2
            }
            3 -> {
                icon = R.drawable.communication_down_3
            }
            4 -> {
                icon = R.drawable.communication_down_4
                //scale this a bit higher
                challengeDifficulty += 1
            }
        }

        return this
    }

    override fun displayFullDescription(): String{
        return description
    }

    override fun displayShortDescription(): String{
        return if (turns <= 1){
            "Can't communicate for $turns turn"
        } else {
            "Can't communicate for $turns turns"
        }

    }
}
package com.example.embark.Challenges

import com.example.embark.R
import kotlin.random.Random
import kotlin.reflect.KClass

class FirstTurnCommunication(numberOfPlayers: Int, difficulty: Int) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty) {
    override val weight: Int
        get() = 10
    override val difficultyMod: Array<Int>
        get() = arrayOf(4,4,4)
    override val description: String
        get() = "All communication must be done before the first trick"
    override var icon: Int = R.drawable.communication_token_disabled

    override val crew1Combatible: Boolean
        get() = true
    override val crew2Combatible: Boolean
        get() = true
    override val incompatibleWith: List<KClass<out Challenge>>
        get() =  mutableListOf<KClass<out Challenge>>(DisruptedCommunicateChallenge::class)

    override fun chooseChallenge(): Challenge {
        challengeDifficulty = getDifficultyMod()
        return this
    }

    override fun displayFullDescription(): String{
        return description
    }

    override fun displayShortDescription(): String{
        return "Communicate before first trick"
    }
}
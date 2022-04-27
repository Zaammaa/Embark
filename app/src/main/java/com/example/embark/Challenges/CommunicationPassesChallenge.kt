package com.example.embark.Challenges

import com.example.embark.R
import kotlin.random.Random
import kotlin.reflect.KClass

class CommunicationPassesChallenge(numberOfPlayers: Int, difficulty: Int) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty) {
    override val weight: Int
        get() = 20
    override val difficultyMod: Array<Int>
        get() = arrayOf(-1,-1,-1)
    override val description: String
        get() = "Players can pass a communication token after all tasks are selected"
    override var icon: Int = R.drawable.communication_token_pass

    override val crew1Combatible: Boolean
        get() = true
    override val crew2Combatible: Boolean
        get() = true
    override val incompatibleWith: List<KClass<out Challenge>>
        get() =  mutableListOf<KClass<out Challenge>>(FewerCommunicationTokensChallenge::class)

    var passes = 1
    override fun chooseChallenge(): Challenge {
        challengeDifficulty = getDifficultyMod()
        if (Random.nextInt(100) > 85){
            passes = 2
            challengeDifficulty -= 1
        }
        return this
    }

    override fun displayFullDescription(): String{
        return description
    }

    override fun displayShortDescription(): String{
        return if (passes > 1){
            "You may pass $passes communication tokens"
        } else {
            "You may pass $passes communication token"
        }

    }
}
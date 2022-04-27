package com.example.embark.Challenges

import com.example.embark.R
import kotlin.reflect.KClass

class UnorderedCommunicationChallenge(numberOfPlayers: Int, difficulty: Int) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty) {

    override val weight: Int
        get() = 20
    override val difficultyMod: Array<Int>
        get() = arrayOf(3,3,3)
    override val description: String
        get() = "Communication tokens cannot be used to show the position of cards in hand"
    override var icon: Int = R.drawable.unordered_communication
    override val crew1Combatible: Boolean
        get() = true
    override val crew2Combatible: Boolean
        get() = true
    override val incompatibleWith: List<KClass<out Challenge>>
        get() =  mutableListOf<KClass<out Challenge>>()

    override fun chooseChallenge(): Challenge {
        challengeDifficulty = getDifficultyMod()
        return this
    }

    override fun displayFullDescription(): String{
        return description
    }

    override fun displayShortDescription(): String{
        return "Unordered Communication"
    }
}
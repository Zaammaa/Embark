package com.example.embark.Challenges

import com.example.embark.R
import kotlin.random.Random
import kotlin.reflect.KClass

class RandomCardPassChallenge(numberOfPlayers: Int, difficulty: Int) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty) {

    override val weight: Int
        get() = 5
    override val difficultyMod: Array<Int>
        get() = arrayOf(1,2,3)
    override val description: String
        get() = "After the first trick, each player must draw a card randomly from another player's hand"
    override var icon: Int = R.drawable.card_back

    override val crew1Combatible: Boolean
        get() = true
    override val crew2Combatible: Boolean
        get() = false
    override val incompatibleWith: List<KClass<out Challenge>>
        get() =  mutableListOf<KClass<out Challenge>>()

    var direction = ""

    override fun chooseChallenge(): Challenge {
        if (Random.nextBoolean()){
            direction = "left"
        } else {
            direction = "right"
        }
        challengeDifficulty = getDifficultyMod()
        return this
    }

    override fun displayFullDescription(): String{
        return description
    }

    override fun displayShortDescription(): String{
        return "draw a card from player to the $direction"
    }
}
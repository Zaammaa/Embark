package com.example.embark.Challenges

import com.example.embark.R
import kotlin.random.Random
import kotlin.reflect.KClass

class OrderedTokensChallenge(numberOfPlayers: Int, difficulty: Int) : Crew1TokensChallenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty) {

    override val weight: Int
        get() = 30
    override val difficultyMod: Array<Int>
        get() = arrayOf(2,2,2)
    override val description: String
        get() = "Place given tokens onto task cards as they are drawn.  These tasks must be taken strictly in order and before any other token types"
    override var icon: Int = R.drawable.ordered_tasks_1

    override val crew1Combatible: Boolean
        get() = true
    override val crew2Combatible: Boolean
        get() = false
    override var tokens = 0
    override var maxTokens = 5

    override fun chooseChallenge(): Challenge {
        // Only randomize if tokens has not been set (appropriately)
        if (tokens < 1) {
            tokens = 1 + Random.nextInt(maxTokens)
        } else if (tokens > maxTokens) {
            tokens = maxTokens
        }
        challengeDifficulty = getDifficultyMod() * tokens
        return this
    }

    override fun displayFullDescription(): String{
        return description
    }

    override fun displayShortDescription(): String{
        if (tokens == 1) {
            return "$tokens ordered token"
        }
        return "$tokens ordered tokens"
    }
}
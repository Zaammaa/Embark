package com.example.embark.Challenges.TaskTokens

import com.example.embark.Challenges.Challenge
import com.example.embark.R
import kotlin.random.Random

class OrderedTokensChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Crew1TokensChallenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, game = game) {

    override val weight: Int
        get() = 30
    override val difficultyMod: Array<Int>
        get() = arrayOf(2,2,2)
    override val description: String
        get() = "Place given tokens onto task cards as they are drawn.  These tasks must be taken strictly in order and before any other token types"
    override var icon: Int = R.drawable.ordered_tasks_1

    private val iconArray = arrayOf(
        R.drawable.ordered_tasks_1,
        R.drawable.ordered_tasks_2,
        R.drawable.ordered_tasks_3,
        R.drawable.ordered_tasks_4,
        R.drawable.ordered_tasks_5
    )

    override val crew1Compatible: Boolean
        get() = true
    override val crew2Compatible: Boolean
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
        icon = iconArray[tokens - 1]
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
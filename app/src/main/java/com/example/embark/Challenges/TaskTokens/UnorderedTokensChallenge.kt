package com.example.embark.Challenges.TaskTokens

import com.example.embark.Challenges.Challenge
import com.example.embark.R
import kotlin.random.Random

class UnorderedTokensChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Crew1TokensChallenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, game = game) {

    override val weight: Int
        get() = 30
    override val difficultyMod: Array<Int>
        get() = arrayOf(1,1,1)
    override val description: String
        get() = "Place given tokens onto task cards as they are drawn.  These tasks must be taken in order amongst themselves"
    override var icon: Int = R.drawable.unordered_tasks_1

    private val iconArray = arrayOf(
        R.drawable.unordered_tasks_1,
        R.drawable.unordered_tasks_2,
        R.drawable.unordered_tasks_3,
        R.drawable.unordered_tasks_4
    )

    override val crew1Compatible: Boolean
        get() = true
    override val crew2Compatible: Boolean
        get() = false
    override var tokens = 0
    override var maxTokens = 4

    override fun chooseChallenge(): Challenge {
        // Only randomize if tokens has not been set (appropriately)
        if (tokens <= 1) {
            tokens = 2 + Random.nextInt(maxTokens - 1)
        } else if (tokens > maxTokens) {
            tokens = maxTokens
        }
        icon = iconArray[tokens - 1]
        challengeDifficulty = getDifficultyMod() * (tokens - 1)
        return this
    }

    override fun displayFullDescription(): String{
        return description
    }

    override fun displayShortDescription(): String{
        return "$tokens unordered tokens"
    }
}
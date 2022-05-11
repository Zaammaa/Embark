package com.example.embark.Challenges

import com.example.embark.R
import kotlin.random.Random
import kotlin.reflect.KClass

class Crew1TaskCardChallenge(numberOfPlayers: Int, difficulty: Int) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty) {

    override val weight: Int
        get() = 100
    override val difficultyMod: Array<Int>
        get() = arrayOf(0, 1, 2)
    override val description: String
        get() = "Draw given number of task cards"
    override var icon: Int = R.drawable.card_back

    override val crew1Combatible: Boolean
        get() = true
    override val crew2Combatible: Boolean
        get() = false
    override val incompatibleWith: List<KClass<out Challenge>>
        get() =  mutableListOf<KClass<out Challenge>>(CommandersDecisionRevealedChallenge::class, CommandersDecisionSecretChallenge::class)

    var tasks = 0

    // Difficulty mod is used to determine how quickly tasks get high modifiers
    // With 5 players, tasks 1-3 are 1 each, 4-6 are 2 each, 7+ are 3 each
    // With 4 players, tasks 1-4 are 1 each, tasks 5-8 are 2 each, 9+ are 3 each
    // With 3 players, tasks 1-5 are 1 each, tasks 6-10 are 2 each, 11+ are 3 each
    override fun chooseChallenge(): Challenge {
        var currentDifficulty = tasks
        var nextMod = 1
        var taskRange = 5 - getDifficultyMod()
        while (currentDifficulty + nextMod <= challengeDifficulty) {
            tasks += 1
            currentDifficulty += nextMod
            taskRange -= 1
            if (nextMod < 3 && taskRange == 0) {
                taskRange = 5 - getDifficultyMod()
                nextMod += 1
            }
        }

        challengeDifficulty = currentDifficulty
        return this
    }

    override fun displayFullDescription(): String{
        return description
    }

    override fun displayShortDescription(): String{
        if (tasks == 1) {
            return "$tasks task card"
        }
        return "$tasks task cards"
    }
}
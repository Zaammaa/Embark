package com.example.embark.Challenges

import kotlin.reflect.KClass

//A Challenge is any modifier to the game. They can affect the selection of tasks, or how the game is played (such as communication)
//Most tasks make increase the difficulty, but some decrease it. After all the modifiers have been generated, the app subtracts them from the difficulty and uses that for the effective difficulty

//This is the base class for challenges. Other challenges should be subclasses of this.
abstract sealed class Challenge(numberOfPlayers: Int, difficulty: Int) {
    //Weight: This is how common the modifier is. The higher the weight, the bigger chance it gets chosen by the ChallengeSelector class
    abstract val weight: Int
    //difficultyMod: This is how challenging the challenge is for each player count
    //If the challenge does not change difficulties based on player count, just use the same value for all three indices
    abstract val difficultyMod: Array<Int>
    //description: Text that appears in the UI when the user wants to read what the challenge means
    abstract val description: String
    //iconPath: R.drawable reference
    abstract var icon: Int
    //challengeDifficulty: the total difficulty of the challenge, determined by the subclass. This is different from the difficulty mod because the subclass often has extra modifiers that affect the difficulty
    //for example, the passes challenge can choose differing numbers of passes.
    var challengeDifficulty = 0
    //players: The number of players
    var players = 0
    //compatibility: which versions of crew the challenge can be used with
    abstract val crew1Combatible: Boolean
    abstract val crew2Combatible: Boolean
    //incompatibleWith: list of other challenges the challenge is incompatible with
    abstract val incompatibleWith: List<KClass<out Challenge>>

    init {
        challengeDifficulty = difficulty
        players = numberOfPlayers
    }


    open fun chooseChallenge(): Challenge {
        throw NotImplementedError("Function not overridden")
    }

    fun getDifficultyMod(): Int {
        return difficultyMod[players - 3]
    }

    open fun displayFullDescription(): String{
        throw NotImplementedError("Function not overridden")
    }

    open fun displayShortDescription(): String{
        throw NotImplementedError("Function not overridden")
    }

}
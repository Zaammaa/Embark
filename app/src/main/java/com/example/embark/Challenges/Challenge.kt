package com.example.embark.Challenges

import kotlin.random.Random

//A Challenge is any modifier to the game. They can affect the selection of tasks, or how the game is played (such as communication)
//Most tasks make increase the difficulty, but some decrease it. After all the modifiers have been generated, the app subtracts them from the difficulty and uses that for the effective difficulty

//This is the base class for challenges. Other challenges should be subclasses of this.
abstract class Challenge(numberOfPlayers: Int, difficulty: Int, gameMode: String) {
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
    //game: Which version of crew is in use
    var game = GameModes.PlanetNine
    enum class GameModes {
        PlanetNine,
        DeepSea
    }
    //GameSpecificNames: This is to use the same terminology the game using between games
    object GameSpecificNames {
        var trump = "rocket"
        var communication = "radio"
        var captain = "commander"
    }
    //tags: These can be used by the challengeSelector to limit the amount of challenges of the same type
    var tags: MutableList<TagOptions> = mutableListOf()
    //TagOptions: current available tags
    enum class TagOptions{
        //Used for challenges that use the communication token
        Communication,
        //Used for challenges that drastically shape the way the game is played
        Unique
    }
    //compatibility: which versions of crew the challenge can be used with
    abstract val crew1Compatible: Boolean
    abstract val crew2Compatible: Boolean

    init {
        challengeDifficulty = difficulty
        players = numberOfPlayers
        if (gameMode.lowercase() == "deep sea"){
            game = GameModes.DeepSea
            GameSpecificNames.trump = "submarine"
            GameSpecificNames.communication = "sonar"
            GameSpecificNames.captain = "captain"
        } else {
            game = GameModes.PlanetNine
        }
    }


    open fun chooseChallenge(): Challenge {
        throw NotImplementedError("Function not overridden")
    }

    fun getDifficultyMod(): Int {
        return difficultyMod[players - 3]
    }

    fun pickRandomSuit(trumpAllowed: Boolean = false): String {
        val suits: Array<String> = arrayOf("pink", "blue", "green", "yellow", GameSpecificNames.trump)

        if (trumpAllowed){
            return suits[Random.nextInt(5)]
        } else {
            return suits[Random.nextInt(4)]
        }
    }

    fun pickRandomCard(trumpAllowed: Boolean = false): String{
        val suit = pickRandomSuit(trumpAllowed)
        if (suit != GameSpecificNames.trump){
            return "$suit ${Random.nextInt(9)+1}"
        } else {
            return "$suit ${Random.nextInt(4)+1}"
        }
    }

    open fun displayFullDescription(): String{
        throw NotImplementedError("Function not overridden")
    }

    open fun displayShortDescription(): String{
        throw NotImplementedError("Function not overridden")
    }

}
package com.example.embark.Challenges.Gameplay

import com.example.embark.Challenges.Challenge
import com.example.embark.R

class WildTrumpChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, gameMode = game) {
    override val weight: Int
        get() = 5
    override val difficultyMod: Array<Int>
        get() = arrayOf(-3,-3,-3)
    override val description: String
        get() = "The player who takes the 1 ${GameSpecificNames.trump} card can count any 1 of their tasks as completed. This needs to be done right away"
    override var icon: Int = R.drawable.wild_one_rocket

    override val crew1Compatible: Boolean
        get() = true
    override val crew2Compatible: Boolean
        get() = true

    init {
        tags.add(TagOptions.Unique)
    }

    override fun chooseChallenge(): Challenge {
        challengeDifficulty = getDifficultyMod()

        //Deep sea has more difficult challenges, so this ability becomes much stronger
        if (game == GameModes.DeepSea){
            challengeDifficulty -= 2
        }
        return this
    }

    override fun displayFullDescription(): String{
        return description
    }

    override fun displayShortDescription(): String{
        return "The 1 ${GameSpecificNames.trump} can be substituted for any task"
    }
}
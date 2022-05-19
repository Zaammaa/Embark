package com.example.embark.Challenges

import com.example.embark.R

class UpdateCommunicationTokenChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, gameMode = game) {
    override val weight: Int
        get() = 10
    override val difficultyMod: Array<Int>
        get() = arrayOf(-1,-1,-1)
    override val description: String
        get() = "Players can adjust the position of the communication token after it has been placed. Adjusting can only happen at times when you can communicate in the round"
    override var icon: Int = R.drawable.communication_token_updating

    override val crew1Compatible: Boolean
        get() = true
    override val crew2Compatible: Boolean
        get() = true

    init {
        tags.add(TagOptions.Communication)
    }

    override fun chooseChallenge(): Challenge {
        challengeDifficulty = getDifficultyMod()
        return this
    }

    override fun displayFullDescription(): String{
        return description
    }

    override fun displayShortDescription(): String{
        return "Communication token position can be updated"
    }
}
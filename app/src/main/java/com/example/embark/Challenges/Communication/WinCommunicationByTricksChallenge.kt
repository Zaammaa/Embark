package com.example.embark.Challenges.Communication

import com.example.embark.Challenges.Challenge
import com.example.embark.R
import kotlin.random.Random

class WinCommunicationByTricksChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, gameMode = game) {

    override val weight: Int
        get() = 5
    override val difficultyMod: Array<Int>
        get() = arrayOf(-1,0,0)
    override val description: String
        get() = "Players start with 0 communication tokens, but gain one token for every trick they take. Won tokens are placed in a pool and can be taken by any player."
    override var icon: Int = R.drawable.tricks_for_comms

    override val crew1Compatible: Boolean
        get() = true
    override val crew2Compatible: Boolean
        get() = true

    init {
        tags.add(TagOptions.Communication)
    }

    var tricksPerToken = 1

    override fun chooseChallenge(): Challenge {
        challengeDifficulty = getDifficultyMod()
        tricksPerToken = Random.nextInt(3) + 1

        if (tricksPerToken > 1){
            challengeDifficulty += 1
        }
        if (tricksPerToken > 2){
            challengeDifficulty += 1
        }
        return this
    }

    override fun displayFullDescription(): String{
        return "Players start with 0 communication tokens, but gain one token for every $tricksPerToken tricks they take. Won tokens are placed in a pool and can be taken by any player."
    }

    override fun displayShortDescription(): String{
        return "Players win communication tokens every time they take $tricksPerToken tricks"
    }
}
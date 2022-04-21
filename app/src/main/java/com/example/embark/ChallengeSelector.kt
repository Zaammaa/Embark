package com.example.embark

import com.example.embark.Challenges.Challenge
import com.example.embark.Challenges.PassesChallenge

class ChallengeSelector(difficulty: Int, playerCount: Int) {
    var difficulty = 0
    var playerCount = 0

    init{
        this.difficulty = difficulty
        this.playerCount = playerCount
    }

    fun generate(): MutableList<Challenge>{
        var challengeList: MutableList<Challenge> = mutableListOf()

        challengeList.add(PassesChallenge(playerCount,difficulty).chooseChallenge())

        return challengeList
    }
}
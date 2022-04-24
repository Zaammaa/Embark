package com.example.embark

import com.example.embark.Challenges.*

class ChallengeSelector(difficulty: Int, playerCount: Int) {
    var difficulty = 0
    var playerCount = 0

    init{
        this.difficulty = difficulty
        this.playerCount = playerCount
    }

    //TODO make this able to use any challenge, not just passing.
    fun generate(): MutableList<Challenge>{
        var challengeList: MutableList<Challenge> = mutableListOf()

        challengeList.add(PassesChallenge(playerCount,difficulty).chooseChallenge())
        challengeList.add(UnorderedCommunicationChallenge(playerCount,difficulty).chooseChallenge())
        challengeList.add(CardPassesChallenge(playerCount,difficulty).chooseChallenge())
        challengeList.add(CommandersDecisionRevealedChallenge(playerCount,difficulty).chooseChallenge())
        challengeList.add(CommandersDecisionSecretChallenge(playerCount,difficulty).chooseChallenge())
        return challengeList
    }
}
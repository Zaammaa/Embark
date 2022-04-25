package com.example.embark

import com.example.embark.Challenges.*

class ChallengeSelector(difficulty: Int, playerCount: Int, game: String) {
    var difficulty = 0
    var playerCount = 0
    var game = ""

    init{
        this.difficulty = difficulty
        this.playerCount = playerCount
        this.game = game
    }

    //TODO make this able to use any challenge, not just passing.
    fun generate(): MutableList<Challenge>{
        var challengeList: MutableList<Challenge> = mutableListOf()

        challengeList.add(PassesChallenge(playerCount,difficulty).chooseChallenge())
        challengeList.add(UnorderedCommunicationChallenge(playerCount,difficulty).chooseChallenge())
        challengeList.add(CardPassesChallenge(playerCount,difficulty).chooseChallenge())
        if (game == "planet nine") {
            challengeList.add(CommandersDecisionRevealedChallenge(playerCount, difficulty).chooseChallenge())
            challengeList.add(CommandersDecisionSecretChallenge(playerCount, difficulty).chooseChallenge())
        }
        return challengeList
    }
}
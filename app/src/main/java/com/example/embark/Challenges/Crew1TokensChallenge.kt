package com.example.embark.Challenges

abstract sealed class Crew1TokensChallenge(numberOfPlayers: Int, difficulty: Int): Challenge(numberOfPlayers, difficulty) {
    // Number of task cards to draw
    abstract var tokens: Int
    abstract var maxTokens: Int
}
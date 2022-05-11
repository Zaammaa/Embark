package com.example.embark.Challenges

abstract sealed class Crew1TaskCardsChallenge(numberOfPlayers: Int, difficulty: Int): Challenge(numberOfPlayers, difficulty) {
    // Number of task cards to draw
    abstract var tasks: Int
}
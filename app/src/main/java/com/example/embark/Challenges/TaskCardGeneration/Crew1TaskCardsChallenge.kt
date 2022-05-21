package com.example.embark.Challenges.TaskCardGeneration

import com.example.embark.Challenges.Challenge

abstract class Crew1TaskCardsChallenge(numberOfPlayers: Int, difficulty: Int, game: String) : Challenge(numberOfPlayers =  numberOfPlayers, difficulty = difficulty, gameMode = game) {
    // Number of task cards to draw
    abstract var tasks: Int
}
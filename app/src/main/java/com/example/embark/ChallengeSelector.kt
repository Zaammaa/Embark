package com.example.embark

import com.example.embark.Challenges.*
import kotlin.random.Random
import kotlin.reflect.KClass


class ChallengeSelector(difficulty: Int, playerCount: Int, game: String) {
    var difficulty = 0
    var playerCount = 0
    var game = ""

    val allChallenges: List<KClass<out Challenge>> = Challenge::class.sealedSubclasses

    init{
        this.difficulty = difficulty
        this.playerCount = playerCount
        this.game = game
    }

    fun generate(): MutableList<Challenge>{
        var challengeList: MutableList<Challenge> = mutableListOf<Challenge>()
        if (game == "planet nine"){
            allChallenges.forEach {
                var challenge: Challenge = it.constructors.first().call(playerCount,difficulty)
                if (challenge.crew1Combatible){
                    challengeList.add(challenge)
                }
            }
        } else if(game == "deep sea") {
            allChallenges.forEach {
                var challenge: Challenge = it.constructors.first().call(playerCount,difficulty)
                if (challenge.crew2Combatible){
                    challengeList.add(challenge)
                }
            }
        }
        return chooseChallenges((challengeList))
    }

    private fun chooseChallenges(challengeList: MutableList<Challenge>): MutableList<Challenge>{
        //TODO make the distribution favor certain number of challenges
        var numberOfChallenges: Int = Random.nextInt(4) + 1
        var currentList: MutableList<Challenge> = mutableListOf<Challenge>()
        var currentChallengeOptions = challengeList

        while(currentList.count() < numberOfChallenges && currentChallengeOptions.count() > 0){
            var totalWeight: Int = 0
            for (challenge in currentChallengeOptions) {
                totalWeight += challenge.weight
            }
            //Choose a value between 0 and total weight
            var targetWeight = Random.nextInt(totalWeight)
            var i = 0
            //Cycle through the list until the total values ov the list match the chosen target
            while(targetWeight >= 0){
                if (targetWeight > currentChallengeOptions[i].weight){
                    targetWeight -= currentChallengeOptions[i].weight
                    i++
                } else {
                    var challenge: Challenge = currentChallengeOptions[i]
                    challenge.chooseChallenge()
                    currentList.add (challenge)
                    break
                }
            }
            currentChallengeOptions = selectApplicableChallenges(currentList, challengeList)
        }
        //Sort makes it so the challenges usually appear in a similar order in the UI
        currentList.sortByDescending { it.weight }
        return currentList
    }
    //after picking a challenge, this function ensures the list of available options no longer includes itself or incompatible challenges
    private fun selectApplicableChallenges(currentList: MutableList<Challenge>, currentChallengeOptions: MutableList<Challenge>): MutableList<Challenge>{
        for(challenge: Challenge in currentList){
            //Check that challenges aren't incompatible with each other
            currentChallengeOptions.removeAll(currentChallengeOptions.filter{ challenge::class == it::class || it.incompatibleWith.contains(challenge::class) || challenge.incompatibleWith.contains(it::class) })
        }
        return currentChallengeOptions
    }

}
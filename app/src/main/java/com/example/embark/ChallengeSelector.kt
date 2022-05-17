package com.example.embark

import com.example.embark.Challenges.*
import kotlin.random.Random
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf


class ChallengeSelector(difficulty: Int, playerCount: Int, game: String) {
    var difficulty = 0
    var playerCount = 0
    var game = ""
    var minimumDifficulty = 2

    //We'll re-enable this if the Kotlin bug is fixed
    //private val allChallenges: List<KClass<out Challenge>> = Challenge::class.sealedSubclasses
    //For now, just list all challenges here manually
    private val allChallenges: List<KClass<out Challenge>> = mutableListOf<KClass<out Challenge>>(
        CardPassesChallenge::class,
        CommandersDecisionSecretChallenge::class,
        CommandersDecisionRevealedChallenge::class,
        TaskPassesChallenge::class,
        UnorderedCommunicationChallenge::class,
        DisruptedCommunicateChallenge::class,
        CommunicationPassesChallenge::class,
        FewerCommunicationTokensChallenge::class,
        CommanderIsSkippedChallenge::class,
        FirstTurnCommunicationChallenge::class,
        RandomCardPassChallenge::class,
        WinEachTrumpChallenge::class,
        NoNinesChallenge::class,
        UnorderedTokensChallenge::class,
        OrderedTokensChallenge::class,
        OmegaTokenChallenge::class
    )

    init{
        this.difficulty = difficulty
        this.playerCount = playerCount
        this.game = game
        this.minimumDifficulty = Math.min(difficulty,minimumDifficulty)
    }

    fun generate(): MutableList<Challenge>{
        var challengeList: MutableList<Challenge> = mutableListOf<Challenge>()
        var chosenChallenges: MutableList<Challenge> = mutableListOf<Challenge>()
        if (game == "planet nine"){
            allChallenges.forEach {
                var challenge: Challenge = it.constructors.first().call(playerCount,difficulty)
                if (challenge.crew1Combatible){
                    challengeList.add(challenge)
                }
            }
            chosenChallenges = chooseChallenges(challengeList)

            // If no task challenge was selected, use the base one with remaining difficulty
            if (chosenChallenges.filter { it::class.isSubclassOf(Crew1TaskCardsChallenge::class) }.isEmpty()) {
                var effectiveDifficulty = difficulty
                for(challenge: Challenge in chosenChallenges){
                    effectiveDifficulty -= challenge.challengeDifficulty
                }
                var challenge = BasicTaskCardsChallenge(playerCount, effectiveDifficulty)
                chosenChallenges.add(challenge.chooseChallenge())
            }
        } else if(game == "deep sea") {
            allChallenges.forEach {
                var challenge: Challenge = it.constructors.first().call(playerCount,difficulty)
                if (challenge.crew2Combatible){
                    challengeList.add(challenge)
                }
            }
            chosenChallenges = chooseChallenges(challengeList)
        }
        return chosenChallenges
    }

    private fun chooseChallenges(challengeList: MutableList<Challenge>): MutableList<Challenge>{
        //TODO make the distribution favor certain number of challenges
        var numberOfChallenges: Int = Random.nextInt(4) + 1
        var currentList: MutableList<Challenge> = mutableListOf<Challenge>()
        var currentChallengeOptions = challengeList
        var currentDifficulty = difficulty

        while(currentList.count() < numberOfChallenges && currentChallengeOptions.count() > 0){
            var totalWeight: Int = 0
            for (challenge in currentChallengeOptions) {
                totalWeight += challenge.weight
            }
            //Choose a value between 0 and total weight
            var targetWeight = Random.nextInt(totalWeight)
            var i = 0
            var chosenChallenge: Challenge? = null
            //Cycle through the list until the total values ov the list match the chosen target
            while(targetWeight >= 0){
                if (targetWeight > currentChallengeOptions[i].weight){
                    targetWeight -= currentChallengeOptions[i].weight
                    i++
                } else {
                    chosenChallenge = currentChallengeOptions[i]
                    chosenChallenge.chooseChallenge()
                    break
                }
            }
            if (chosenChallenge == null){
                throw Exception("No challenge chosen unexpectedly")
            }
            if (currentDifficulty - chosenChallenge.challengeDifficulty >= this.minimumDifficulty){
                currentDifficulty -= chosenChallenge.challengeDifficulty
                currentList.add (chosenChallenge)
                currentChallengeOptions = filterIncompatibleChallenges(chosenChallenge, currentChallengeOptions)
            } else {
                currentChallengeOptions.remove(chosenChallenge)
                continue
            }

        }
        //Sort makes it so the challenges usually appear in a similar order in the UI
        currentList.sortByDescending { it.weight }
        return currentList
    }
    //after picking a challenge, this function ensures the list of available options no longer includes itself or incompatible challenges
<<<<<<< HEAD
    private fun selectApplicableChallenges(newChallenge: Challenge, currentChallengeOptions: MutableList<Challenge>): MutableList<Challenge>{
=======
    private fun filterIncompatibleChallenges(newChallenge: Challenge, currentChallengeOptions: MutableList<Challenge>): MutableList<Challenge>{
>>>>>>> master
        currentChallengeOptions.removeAll(currentChallengeOptions.filter{ newChallenge::class == it::class || ChallengeIncompatibilityTable.incompatible(it::class,newChallenge::class) })
        return currentChallengeOptions
    }

}
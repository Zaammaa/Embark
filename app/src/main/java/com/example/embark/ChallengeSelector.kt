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
                var tokenChallenges = (chosenChallenges.filter { it::class.isSubclassOf(Crew1TokensChallenge::class) } as List<Crew1TokensChallenge>).toMutableList()
                if (!tokenChallenges.isEmpty()) {
                    chosenChallenges = chooseBalancedTokensAndTasks(chosenChallenges, tokenChallenges)
                } else {
                    var taskChallenge = chooseBasicTaskCardChallenge(chosenChallenges)
                    chosenChallenges.add(taskChallenge)
                }
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
    private fun filterIncompatibleChallenges(newChallenge: Challenge, currentChallengeOptions: MutableList<Challenge>): MutableList<Challenge>{
        currentChallengeOptions.removeAll(currentChallengeOptions.filter{ newChallenge::class == it::class || ChallengeIncompatibilityTable.incompatible(it::class,newChallenge::class) })
        return currentChallengeOptions
    }
    fun getRemainingDifficulty(challenges: List<Challenge>): Int {
        var remainingDifficulty = difficulty
        for(challenge: Challenge in challenges){
            remainingDifficulty -= challenge.challengeDifficulty
        }
        return remainingDifficulty
    }
    private fun chooseBasicTaskCardChallenge(chosenChallenges: List<Challenge>): BasicTaskCardsChallenge {
        var remainingDifficulty = getRemainingDifficulty(chosenChallenges)
        var taskChallenge = BasicTaskCardsChallenge(playerCount, remainingDifficulty).chooseChallenge()
        return taskChallenge as BasicTaskCardsChallenge
    }
    private fun chooseBalancedTokensAndTasks(chosenChallenges: MutableList<Challenge>, tokenChallenges: MutableList<Crew1TokensChallenge>): MutableList<Challenge> {
        var taskChallenge = chooseBasicTaskCardChallenge(chosenChallenges)
        chosenChallenges.removeAll(tokenChallenges)
        var tasks = taskChallenge.tasks
        var tokens = sumTokens(tokenChallenges)
        while (tasks < tokens) {
            subtractToken(tokenChallenges)
            tokens = sumTokens(tokenChallenges)
            tasks = chooseBasicTaskCardChallenge(chosenChallenges + tokenChallenges).tasks
        }
        var unordered = tokenChallenges.filter { it::class == UnorderedTokensChallenge::class }.isNotEmpty()
        var orderedList = tokenChallenges.filter { it::class == OrderedTokensChallenge::class }
        var ordered = orderedList.isNotEmpty()
        var omega = tokenChallenges.filter { it::class == OmegaTokenChallenge::class } as List<OmegaTokenChallenge>
        var omegaLast: Boolean = omega.isNotEmpty() && omega[0].type == "last"
        // A singleton task with a token makes no sense
        if (tasks == 1 && (ordered || omegaLast)) {
            subtractToken(tokenChallenges)
        }
        // Unordered needs at least one card without a token
        if (unordered) {
            if (tasks == tokens) {
                subtractToken(tokenChallenges)
            }
            // Without unordered tokens, one task card without a token when
            // ordered/omega-last is in play is harder than difficulty says
        } else if (tasks - 1 == tokens && (ordered || omegaLast)) {
            subtractToken(tokenChallenges)
        }
        taskChallenge = chooseBasicTaskCardChallenge(chosenChallenges + tokenChallenges)
        tasks = taskChallenge.tasks
        tokens = sumTokens(tokenChallenges)
        // When all cards have tokens, swap omega last for another ordered token if possible
        if (tasks == tokens && ordered && omegaLast) {
            var orderedTask = orderedList[0]
            if (orderedTask.tokens <= 5) {
                tokenChallenges.remove(orderedTask)
                orderedTask.tokens += 1
                orderedTask.chooseChallenge()
                tokenChallenges.add(orderedTask)
                tokenChallenges.remove(omega[0])
            }
        }
        chosenChallenges.add(taskChallenge)
        chosenChallenges.addAll(tokenChallenges)
        return chosenChallenges
    }
    private fun sumTokens(tokenChallenges: List<Crew1TokensChallenge>): Int {
        var tokens: Int = 0
        for (tChallenge in tokenChallenges) {
            tokens += tChallenge.tokens
        }
        return tokens
    }
    private fun subtractToken(tokenChallenges: MutableList<Crew1TokensChallenge>) {
        var i = Random.nextInt(tokenChallenges.size)
        var challenge = tokenChallenges[i] as Crew1TokensChallenge
        tokenChallenges.remove(challenge)
        // Return with challenge removed
        if (challenge.tokens == 1 || (challenge::class == UnorderedTokensChallenge::class && challenge.tokens == 2)) {
            return
        }
        challenge.tokens -= 1
        challenge.chooseChallenge()
        tokenChallenges.add(challenge)
    }
}
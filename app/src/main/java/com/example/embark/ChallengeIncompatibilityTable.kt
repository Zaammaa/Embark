package com.example.embark

import com.example.embark.Challenges.*
import kotlin.reflect.KClass

class ChallengeIncompatibilityTable {
    companion object{
        private val incompatibilityList: MutableList<Pair<KClass<out Challenge>,KClass<out Challenge>>> = mutableListOf(
            //Task cards
            Pair(CommandersDecisionRevealedChallenge::class, BasicTaskCardsChallenge::class),
            Pair(CommandersDecisionSecretChallenge::class, BasicTaskCardsChallenge::class),
            Pair(TaskPassesChallenge::class,CommandersDecisionSecretChallenge::class),

            //communication
            Pair(FewerCommunicationTokensChallenge::class, CommunicationPassesChallenge::class),
            Pair(DisruptedCommunicateChallenge::class, FirstTurnCommunicationChallenge::class),

            //other
            Pair(CommanderIsSkippedChallenge::class,CommandersDecisionRevealedChallenge::class),
            Pair(CommanderIsSkippedChallenge::class,CommandersDecisionSecretChallenge::class),
        )

        //returns true if the challenges are incompatible, false otherwise
        fun incompatible(challenge1: KClass<out Challenge>, challenge2: KClass<out Challenge>): Boolean{
            if(incompatibleWith(challenge1).contains(challenge2)){
                return true
            }
            return false
        }

        //returns a list of all the challenges that are incompatible with the inputted challenge
        private fun incompatibleWith(challenge: KClass<out Challenge>): List<KClass<out Challenge>>{
            var incompatibleChallenges: MutableList<KClass<out Challenge>> = mutableListOf()
            for(pairs: Pair<KClass<out Challenge>,KClass<out Challenge>> in incompatibilityList){
                if (pairs.first == challenge){
                    incompatibleChallenges.add(pairs.second)
                } else if (pairs.second == challenge){
                    incompatibleChallenges.add(pairs.first)
                }
            }

            return incompatibleChallenges
        }
    }




}
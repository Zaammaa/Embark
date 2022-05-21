package com.example.embark

import com.example.embark.Challenges.*
import com.example.embark.Challenges.Communication.*
import com.example.embark.Challenges.Gameplay.FirstTrickPoolChallenge
import com.example.embark.Challenges.Gameplay.JesterTrumpChallenge
import com.example.embark.Challenges.Gameplay.WinEachTrumpChallenge
import com.example.embark.Challenges.TaskCardGeneration.BasicTaskCardsChallenge
import com.example.embark.Challenges.TaskCardGeneration.CommandersDecisionRevealedChallenge
import com.example.embark.Challenges.TaskCardGeneration.CommandersDecisionSecretChallenge
import com.example.embark.Challenges.TaskCardSelection.CommanderIsSkippedChallenge
import com.example.embark.Challenges.TaskCardSelection.TaskPassesChallenge
import com.example.embark.Challenges.TaskTokens.OmegaTokenChallenge
import com.example.embark.Challenges.TaskTokens.OrderedTokensChallenge
import com.example.embark.Challenges.TaskTokens.UnorderedTokensChallenge
import kotlin.reflect.KClass

class ChallengeIncompatibilityTable {
    companion object{
        private val incompatibilityList: MutableList<Pair<KClass<out Challenge>,KClass<out Challenge>>> = mutableListOf(
            //Task cards
            Pair(CommandersDecisionRevealedChallenge::class, BasicTaskCardsChallenge::class),
            Pair(CommandersDecisionSecretChallenge::class, BasicTaskCardsChallenge::class),
            Pair(TaskPassesChallenge::class, CommandersDecisionRevealedChallenge::class),
            Pair(TaskPassesChallenge::class, CommandersDecisionSecretChallenge::class),
            Pair(CommandersDecisionSecretChallenge::class, CommandersDecisionRevealedChallenge::class),

            //Task card tokens
            Pair(UnorderedTokensChallenge::class, CommandersDecisionSecretChallenge::class),
            Pair(UnorderedTokensChallenge::class, CommandersDecisionRevealedChallenge::class),
            Pair(OrderedTokensChallenge::class, CommandersDecisionSecretChallenge::class),
            Pair(OrderedTokensChallenge::class, CommandersDecisionRevealedChallenge::class),
            Pair(OmegaTokenChallenge::class, CommandersDecisionSecretChallenge::class),
            Pair(OmegaTokenChallenge::class, CommandersDecisionRevealedChallenge::class),

            //communication
            Pair(FewerCommunicationTokensChallenge::class, CommunicationPassesChallenge::class),
            Pair(DisruptedCommunicateChallenge::class, FirstTurnCommunicationChallenge::class),
            Pair(UpdateCommunicationTokenChallenge::class, UnorderedCommunicationChallenge::class),

            Pair(RandomPlayerCantCommunicateChallenge::class, FewerCommunicationTokensChallenge::class),
            Pair(RandomPlayerCantCommunicateChallenge::class, CommunicationPassesChallenge::class),
            Pair(RandomPlayerCantCommunicateChallenge::class, ChosenPlayerCantCommunicateChallenge::class),
            Pair(ChosenPlayerCantCommunicateChallenge::class, CommunicationPassesChallenge::class),
            Pair(ChosenPlayerCantCommunicateChallenge::class, FewerCommunicationTokensChallenge::class),

            Pair(MidTurnCommunicationChallenge::class, FirstTurnCommunicationChallenge::class),
            Pair(PassingForCommunicationChallenge::class, UnorderedCommunicationChallenge::class),
            Pair(WinCommunicationByTasksChallenge::class, WinCommunicationByTricksChallenge::class),

            Pair(DiscardByCommunicationTokenChallenge::class, PassingForCommunicationChallenge::class),
            Pair(DiscardByCommunicationTokenChallenge::class, TrumpMayBeCommunicatedChallenge::class),
            Pair(DiscardByCommunicationTokenChallenge::class, UnorderedCommunicationChallenge::class),
            Pair(DiscardByCommunicationTokenChallenge::class, UpdateCommunicationTokenChallenge::class),

            Pair(FirstTrickPoolChallenge::class, DiscardByCommunicationTokenChallenge::class),
            Pair(FirstTrickPoolChallenge::class, UpdateCommunicationTokenChallenge::class),
            Pair(FirstTrickPoolChallenge::class, PassingForCommunicationChallenge::class),
            Pair(FirstTrickPoolChallenge::class, TrumpMayBeCommunicatedChallenge::class),
            Pair(FirstTrickPoolChallenge::class, UnorderedCommunicationChallenge::class),

            //other
            Pair(CommanderIsSkippedChallenge::class, CommandersDecisionRevealedChallenge::class),
            Pair(CommanderIsSkippedChallenge::class, CommandersDecisionSecretChallenge::class),
            Pair(JesterTrumpChallenge::class, WinEachTrumpChallenge::class),
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
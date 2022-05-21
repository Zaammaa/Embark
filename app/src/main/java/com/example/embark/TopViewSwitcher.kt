package com.example.embark

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.core.view.get
import com.example.embark.Challenges.Challenge
import com.example.embark.Challenges.TaskCardGeneration.Crew1TaskCardsChallenge
import kotlin.reflect.full.isSubclassOf

class TopViewSwitcher(context: Context, attrs: AttributeSet) : ViewSwitcher(context, attrs) {

    init {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.fragment_setup, this, true)

        this.addView(inflater.inflate(R.layout.fragment_game_view, this, false))

        var gameView: View = this[1]
        var winButton: ImageButton = gameView.findViewById(R.id.winButton)
        var loseButton: ImageButton = gameView.findViewById(R.id.loseButton)

        winButton.setOnClickListener {
            this.endGame()
        }
        loseButton.setOnClickListener {
            this.endGame()
        }
    }

    fun setupGame(challenges: List<Challenge>, baseDifficulty: Int, gameMode: String){
        var gameView: View = this[1]
        var difficultyDisplayDeepSea: TextView = gameView.findViewById(R.id.difficultyLevelDeepSea)
        var difficultyDisplayPlanetNine: TextView = gameView.findViewById(R.id.difficultyLevelPlanetNine)

        if (gameMode == "deep sea"){
            var effectiveDifficulty = baseDifficulty
            for(challenge: Challenge in challenges){
                effectiveDifficulty -= challenge.challengeDifficulty
            }
            difficultyDisplayPlanetNine.visibility = View.INVISIBLE
            difficultyDisplayDeepSea.visibility = View.VISIBLE
            difficultyDisplayDeepSea.text = effectiveDifficulty.toString()
        } else {
            difficultyDisplayPlanetNine.visibility = View.VISIBLE
            difficultyDisplayDeepSea.visibility = View.INVISIBLE

            var taskChallenge: List<Crew1TaskCardsChallenge> = challenges.filter { it::class.isSubclassOf(Crew1TaskCardsChallenge::class) } as List<Crew1TaskCardsChallenge>
            if (taskChallenge.isEmpty()) {
                difficultyDisplayPlanetNine.text = "0"
            } else {
                difficultyDisplayPlanetNine.text = taskChallenge[0].tasks.toString()
            }
        }

        this.showNext()
    }

    fun endGame(){
        var table = this.rootView.findViewById<TableLayout>(R.id.challengeTable)
        table.removeAllViews()
        this.showNext()
    }


}
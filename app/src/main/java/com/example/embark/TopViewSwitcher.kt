package com.example.embark

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import com.example.embark.Challenges.Challenge

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
        var effectiveDifficulty = baseDifficulty
        for(challenge: Challenge in challenges){
            effectiveDifficulty -= challenge.challengeDifficulty
        }

        var gameView: View = this[1]
        var difficultyDisplayDeepSea: TextView = gameView.findViewById(R.id.difficultyLevelDeepSea)
        var difficultyDisplayPlanetNine: TextView = gameView.findViewById(R.id.difficultyLevelPlanetNine)

        if (gameMode == "deep sea"){
            difficultyDisplayPlanetNine.visibility = View.INVISIBLE
            difficultyDisplayDeepSea.visibility = View.VISIBLE
            difficultyDisplayDeepSea.text = effectiveDifficulty.toString()
        } else {
            difficultyDisplayPlanetNine.visibility = View.VISIBLE
            difficultyDisplayDeepSea.visibility = View.INVISIBLE
            //TODO change to task cards
            difficultyDisplayPlanetNine.text = effectiveDifficulty.toString()
        }

        this.showNext()
    }

    fun endGame(){
        var table = this.rootView.findViewById<TableLayout>(R.id.challengeTable)
        table.removeAllViews()
        this.showNext()
    }


}
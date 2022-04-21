package com.example.embark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.example.embark.Challenges.Challenge
import android.widget.LinearLayout
import androidx.core.view.updateLayoutParams
import android.view.Gravity





class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Setup the challenge table layout
//        val tableParams = TableLayout.LayoutParams(
//            TableLayout.LayoutParams.WRAP_CONTENT,
//            TableLayout.LayoutParams.WRAP_CONTENT
//        )
//        var table = findViewById<TableLayout>(R.id.challengeTable)
//        table.layoutParams = tableParams

    }

    fun increaseDifficulty(button: View){
        var difficultyNumberView = findViewById<EditText>(R.id.difficultyNumber)
        var difficulty = difficultyNumberView.text.toString().toInt()
        difficulty += 1
        difficultyNumberView.setText(difficulty.toString())
    }

    fun decreaseDifficulty(button: View){
        val difficultyNumberView = findViewById<EditText>(R.id.difficultyNumber)
        var difficulty = difficultyNumberView.text.toString().toInt()
        if (difficulty > 1){
            difficulty -= 1
            difficultyNumberView.setText(difficulty.toString())
        }
    }

    fun increasePlayerCount(button: View){
        var playerCountView = findViewById<EditText>(R.id.playerCount)
        var playerCount = playerCountView.text.toString().toInt()
        if (playerCount < 5){
            playerCount += 1
            playerCountView.setText(playerCount.toString())
        }
    }

    fun decreasePlayerCount(button: View){
        var playerCountView = findViewById<EditText>(R.id.playerCount)
        var playerCount = playerCountView.text.toString().toInt()
        if (playerCount > 3){
            playerCount -= 1
            playerCountView.setText(playerCount.toString())
        }
    }

    fun generate(button: View){
        val difficultyView = findViewById<EditText>(R.id.difficultyNumber)
        var difficulty = difficultyView.text.toString().toInt()

        val playerCountView = findViewById<EditText>(R.id.playerCount)
        var playerCount = playerCountView.text.toString().toInt()

        var selector: ChallengeSelector = ChallengeSelector(difficulty,playerCount)
        var challengeList: MutableList<Challenge> = selector.generate()

        var table = findViewById<TableLayout>(R.id.challengeTable)
        table.removeAllViews()

        for(challenge in challengeList){
            createTableRow(challenge, table)
        }
    }

    fun createTableRow(challenge: Challenge,table: TableLayout){
        val tableParams = TableLayout.LayoutParams(
            TableLayout.LayoutParams.WRAP_CONTENT,
            TableLayout.LayoutParams.WRAP_CONTENT
        )
        val rowParams = TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )



        var row: TableRow = TableRow(this)

        row.setLayoutParams(
            TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
            )
        )

        row.setGravity(Gravity.LEFT)
        row.setWeightSum(1f) //total row weight

        var lp: TableRow.LayoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.66f)

        var description: TextView = TextView(this)
        description.setText(challenge.description)
        //description.maxWidth = 400
        description.layoutParams = lp

        row.addView(description)

        var difficulty: TextView = TextView(this)
        //difficulty.maxWidth = 200

        lp = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.34f)

        difficulty.layoutParams = lp
        //difficulty.setText("test Text")
        difficulty.setText(challenge.challengeDifficulty.toString())
        row.addView((difficulty))

        table.addView(row)
    }

}
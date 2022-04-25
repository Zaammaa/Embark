package com.example.embark

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.embark.Challenges.Challenge
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    var gameTab = "planet nine"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tabs = findViewById<TabLayout>(R.id.gameSelection)
        tabs.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                var table = findViewById<TableLayout>(R.id.challengeTable)
                table.removeAllViews()
                gameTab = tab.text.toString().lowercase()
            }
            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }
        })
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

        var selector: ChallengeSelector = ChallengeSelector(difficulty,playerCount, this.gameTab)
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
        row.setGravity(Gravity.CENTER)
        row.setWeightSum(1f) //total row weight

        var icon: ImageView = ImageView(this)
        icon.setImageResource(challenge.icon)
        icon.layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.25f)
        icon.setPadding(0, 20, 0, 20)
        row.addView(icon)

        var description: TextView = TextView(this)
        description.setText(challenge.displayShortDescription())
        createTableCell(row, description, .75f)

        row.setBackgroundResource(R.drawable.challenge_table_row_outline)
        table.addView(row)
    }
    fun createTableCell(row: TableRow, cell: TextView, weight: Float){
        cell.setGravity(Gravity.CENTER)
        cell.layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, weight)
        cell.setPadding(0,10, 0,10)
        row.addView(cell)
    }
}
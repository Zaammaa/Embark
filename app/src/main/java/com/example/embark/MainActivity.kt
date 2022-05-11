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
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
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
    }

    fun generate(button: View){
        val difficultyAdjuster: Adjuster = findViewById<EditText>(R.id.difficultyAdjuster) as Adjuster
        var difficulty = difficultyAdjuster.getAdjusterValue()

        val playerCountAdjuster: Adjuster = findViewById<EditText>(R.id.playerCountAdjuster) as Adjuster
        var playerCount = playerCountAdjuster.getAdjusterValue()

        var selector: ChallengeSelector = ChallengeSelector(difficulty,playerCount, this.gameTab)
        var challengeList: MutableList<Challenge> = selector.generate()

        var table = findViewById<TableLayout>(R.id.challengeTable)
        table.removeAllViews()

        for(challenge in challengeList){
            createTableRow(challenge, table)
        }

        var viewSwitcher: TopViewSwitcher = findViewById<TopViewSwitcher>(R.id.topViewSwitcher)
        viewSwitcher.setupGame(challengeList,difficulty,this.gameTab)
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
        row.setOnClickListener {
            val descBox: DescriptionDialog = DescriptionDialog()
            descBox.create(challenge, this)
        }


        table.addView(row)
    }
    fun createTableCell(row: TableRow, cell: TextView, weight: Float){
        cell.setGravity(Gravity.CENTER)
        cell.layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, weight)
        cell.setPadding(0,10, 0,10)
        row.addView(cell)
    }
}
package com.example.embark

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.get
import com.example.embark.Challenges.TaskCardGeneration.BasicTaskCardsChallenge
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

                if (gameTab.lowercase() == "planet nine"){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabReselected(p0: TabLayout.Tab?) {

            }
        })
        if (savedInstanceState == null) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    fun generate(button: View){
        val difficultyAdjuster: Adjuster = findViewById<EditText>(R.id.difficultyAdjuster) as Adjuster
        val difficulty = difficultyAdjuster.getAdjusterValue()

        val playerCountAdjuster: Adjuster = findViewById<EditText>(R.id.playerCountAdjuster) as Adjuster
        val playerCount = playerCountAdjuster.getAdjusterValue()

        val selector: ChallengeSelector = ChallengeSelector(difficulty,playerCount, this.gameTab)
        val challengeList: MutableList<Challenge> = selector.generate()

        val table = findViewById<TableLayout>(R.id.challengeTable)
        table.removeAllViews()

        for(challenge in challengeList){
            if (challenge::class != BasicTaskCardsChallenge::class ) {
                createTableRow(challenge, table)
            }
        }

        val viewSwitcher: TopViewSwitcher = findViewById<TopViewSwitcher>(R.id.topViewSwitcher)
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
        description.setTextColor(Color.parseColor("#000000"))
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

    override fun onSaveInstanceState(outState: Bundle) {

        val difficultyAdjuster: Adjuster = findViewById<EditText>(R.id.difficultyAdjuster) as Adjuster
        var difficulty: Int = difficultyAdjuster.getAdjusterValue()
        outState.putInt("DIFFICULTY",difficulty)

        val playerCountAdjuster: Adjuster = findViewById<EditText>(R.id.playerCountAdjuster) as Adjuster
        var playerCount: Int = playerCountAdjuster.getAdjusterValue()
        outState.putInt("PLAYER_COUNT",playerCount)

        outState.putString("GAME_MODE",gameTab)

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val difficultyAdjuster: Adjuster = findViewById<EditText>(R.id.difficultyAdjuster) as Adjuster
        val difficulty: Int = savedInstanceState.getInt("DIFFICULTY")
        difficultyAdjuster.setAdjusterValue(difficulty)

        val playerCountAdjuster: Adjuster = findViewById<EditText>(R.id.playerCountAdjuster) as Adjuster
        val playerCount = savedInstanceState.getInt("PLAYER_COUNT")
        playerCountAdjuster.setAdjusterValue(playerCount)

        var tabs = findViewById<TabLayout>(R.id.gameSelection)
        val game = savedInstanceState.getString("GAME_MODE")
        if (game?.lowercase() == "planet nine") {
            tabs.selectTab(tabs.getTabAt(0))
        } else {
            tabs.selectTab(tabs.getTabAt(1))
        }
    }
}
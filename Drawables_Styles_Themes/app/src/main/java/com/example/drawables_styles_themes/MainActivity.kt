package com.example.drawables_styles_themes
// MainActivity.kt


import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {
    private lateinit var player1Score: TextView
    private lateinit var player2Score: TextView
    private lateinit var player1Add: Button
    private lateinit var player1Subtract: Button
    private lateinit var player2Add: Button
    private lateinit var player2Subtract: Button
    private var isNightMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        player1Score = findViewById(R.id.player1Score)
        player2Score = findViewById(R.id.player2Score)
        player1Add = findViewById(R.id.player1Add)
        player1Subtract = findViewById(R.id.player1Subtract)
        player2Add = findViewById(R.id.player2Add)
        player2Subtract = findViewById(R.id.player2Subtract)

        player1Add.setOnClickListener {
            updateScore(player1Score, 1)
        }

        player1Subtract.setOnClickListener {
            updateScore(player1Score, -1)
        }

        player2Add.setOnClickListener {
            updateScore(player2Score, 1)
        }

        player2Subtract.setOnClickListener {
            updateScore(player2Score, -1)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_toggle_theme -> {
                toggleNightMode()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun toggleNightMode() {
        isNightMode = !isNightMode
        val mode = if (isNightMode) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(mode)
        recreate()
    }

    private fun updateScore(scoreTextView: TextView, change: Int) {
        val currentScore = scoreTextView.text.toString().toInt()
        scoreTextView.text = (currentScore + change).toString()
    }
}

package com.example.hellosharedprefs

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var tvCount: TextView
    private lateinit var sharedPreferences: SharedPreferences
    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCount = findViewById(R.id.tvCount)
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Load saved count from SharedPreferences
        count = sharedPreferences.getInt("count", 0)
        tvCount.text = count.toString()

        // Load saved background color from SharedPreferences
        val backgroundColor = sharedPreferences.getInt("backgroundColor", Color.WHITE)
        tvCount.setBackgroundColor(backgroundColor)


        findViewById<Button>(R.id.btnBlack).setOnClickListener {
            changeBackgroundColor(Color.BLACK)
        }
        findViewById<Button>(R.id.btnRed).setOnClickListener {
            changeBackgroundColor(Color.RED)
        }
        findViewById<Button>(R.id.btnBlue).setOnClickListener {
            changeBackgroundColor(Color.BLUE)
        }
        findViewById<Button>(R.id.btnGreen).setOnClickListener {
            changeBackgroundColor(Color.GREEN)
        }

        findViewById<Button>(R.id.btnCount).setOnClickListener {
            countNumber(it)
        }

        findViewById<Button>(R.id.btnReset).setOnClickListener {

            resetValues(it)
        }
    }

    fun changeBackgroundColor(color: Int) {

        findViewById<View>(android.R.id.content).setBackgroundColor(color)

        // Save selected background color to SharedPreferences
        sharedPreferences.edit().putInt("backgroundColor", color).apply()
    }

    fun countNumber(view: View) {
        count++
        tvCount.text = count.toString()

        // Save updated count to SharedPreferences
        sharedPreferences.edit().putInt("count", count).apply()
    }

    fun resetValues(view: View) {
        count = 0
        tvCount.text = count.toString()
        findViewById<View>(android.R.id.content).setBackgroundColor(Color.WHITE)

        // Reset SharedPreferences
        sharedPreferences.edit().clear().apply()
    }
}

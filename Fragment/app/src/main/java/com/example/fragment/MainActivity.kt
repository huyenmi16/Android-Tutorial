package com.example.fragment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

//        if (savedInstanceState == null) {
//            val dynamicFragment = FeedBackFragment()
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.dynamic_feedback_fragment_container, dynamicFragment)
//                .commit()
//        }

    }
}
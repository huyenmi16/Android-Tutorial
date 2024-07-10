package com.example.droidcafe

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageDessert1: ImageView = findViewById(R.id.imageView)
        val imageDessert2: ImageView = findViewById(R.id.imageView2)

        imageDessert1.setOnClickListener(this)
        imageDessert2.setOnClickListener(this)

        val fab : FloatingActionButton = findViewById(R.id.fabCart)

        fab.setOnClickListener {
            Toast.makeText(this, "Cart button clicked!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

    }
    override fun onClick(view: View){
        when (view.id) {
            R.id.imageView -> {
                Toast.makeText(this, "Dessert image 1 clicked!", Toast.LENGTH_SHORT).show()
            }
            R.id.imageView2 -> {
                Toast.makeText(this, "Dessert image 2 clicked!", Toast.LENGTH_SHORT).show()
            }

        }


    }
}
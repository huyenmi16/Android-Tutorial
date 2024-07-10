package com.example.recyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.adapter.WordAdapter
import com.example.recyclerview.model.Word
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private val words = mutableListOf(
        Word(1, "Hello"),
        Word(2, "World"),
        Word(3, "Kotlin"),
        Word(4, "Android"),
        Word(5, "RecyclerView")
    )

    private lateinit var adapter: WordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val fabAddWord : FloatingActionButton = findViewById(R.id.fabAddWord)
        adapter = WordAdapter(words)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        fabAddWord.setOnClickListener {
            val newWord = Word(words.size + 1, "New Word")
            words.add(newWord)
            adapter.notifyItemInserted(words.size - 1)
        }
    }
}

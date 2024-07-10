package com.example.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.model.Word
import com.example.recyclerview.R

class WordAdapter(private val words: MutableList<Word>) :
    RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_word, parent, false)
        return WordViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = words[position]
        holder.bind(word)
    }

    override fun getItemCount(): Int {
        return words.size
    }

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textWord: TextView = itemView.findViewById(R.id.textWord)

        fun bind(word: Word) {
            textWord.text = word.text
            itemView.isActivated = word.isSelected

            itemView.setOnClickListener {
                word.isSelected = !word.isSelected
                itemView.isActivated = word.isSelected
            }
        }
    }
}

package com.example.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FeedBackFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_feedback,container,false)
        val radio: RadioGroup = view.findViewById(R.id.radio_group)

        val txt: TextView = view.findViewById(R.id.txt)

        radio.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = view.findViewById<RadioButton>(checkedId)
            if (radioButton != null) {
                when (radioButton.id) {
                    R.id.radio_yes -> txt.text = "Article: Like"
                    R.id.radio_no -> txt.text = "Article: Thanks"
                }
            }
        }
        return view
    }
}
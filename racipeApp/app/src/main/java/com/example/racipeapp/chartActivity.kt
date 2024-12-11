package com.example.racipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class chartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)
        //val foodName = "Example Food Name"
        //val calories = "300 calories"

        // Find the TextViews in the layout
        val textViewFoodName = findViewById<TextView>(R.id.textViewFoodName)
        val textViewCalories = findViewById<TextView>(R.id.textViewCalories)

        // Set the text of the TextViews with the data
        //textViewFoodName.text = foodName
        //textViewCalories.text = calories
    }
}
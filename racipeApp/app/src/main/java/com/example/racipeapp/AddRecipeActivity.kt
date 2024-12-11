package com.example.racipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.racipeapp.databinding.ActivityAddRecipeBinding

class AddRecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddRecipeBinding
    private lateinit var db: NewRecipeDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NewRecipeDatabaseHelper(this)

        binding.saveButto.setOnClickListener{
            val title = binding.titleEditText.text.toString()
            val ingredient = binding.ingredientsEditText.text.toString()
            val steps = binding.stepsEditText.text.toString()

            val newrecipe = NewRecipe(0, title, ingredient, steps)
            db.insertNewRecipe(newrecipe)
            finish()
            Toast.makeText(this,"New Recipe Saved",Toast.LENGTH_LONG).show()
        }
    }
}
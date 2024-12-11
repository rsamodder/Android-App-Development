package com.example.racipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.racipeapp.databinding.ActivityAddRecipeBinding
import com.example.racipeapp.databinding.ActivityUpdateRecipeBinding

class UpdateRecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateRecipeBinding
    private lateinit var db: NewRecipeDatabaseHelper
    private var newRecipeId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NewRecipeDatabaseHelper(this)

        newRecipeId = intent.getIntExtra("newRecipe_id", -1)
        if(newRecipeId == -1){
            finish()
            return
        }

        val newRecipe = db.getNewRecipeBYID(newRecipeId)
        binding.UpdateTitleEditText.setText(newRecipe.name)
        binding.UpdateIngredientsEditText.setText(newRecipe.ingredient)
        binding.UpdateStepsEditText.setText(newRecipe.steps)

        binding.UpdateSaveButton.setOnClickListener{
            val newTitle = binding.UpdateTitleEditText.text.toString()
            val newIngredients = binding.UpdateIngredientsEditText.text.toString()
            val newSteps = binding.UpdateStepsEditText.text.toString()

            val updateNewRecipe = NewRecipe(newRecipeId, newTitle, newIngredients, newSteps)
            db.updateNewRecipe(updateNewRecipe)
            finish()
            Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()
        }
    }
}
package com.example.racipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.racipeapp.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private lateinit var db: NewRecipeDatabaseHelper
    private lateinit var newRecipeAdapter: NewRecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NewRecipeDatabaseHelper(this)
        newRecipeAdapter = NewRecipeAdapter(db.getAllNewRecipe(), this)

        binding.newRecipesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.newRecipesRecyclerView.adapter = newRecipeAdapter

        binding.addButton.setOnClickListener{
            val intent = Intent(this,AddRecipeActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        newRecipeAdapter.refreshData(db.getAllNewRecipe())
    }
}
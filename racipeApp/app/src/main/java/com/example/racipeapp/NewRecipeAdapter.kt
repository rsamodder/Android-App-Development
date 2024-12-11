package com.example.racipeapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

class NewRecipeAdapter(private var newRecipes: List<NewRecipe>, context: Context) : RecyclerView.Adapter<NewRecipeAdapter.NewRecipeViewHolder>(){

    private val db: NewRecipeDatabaseHelper = NewRecipeDatabaseHelper(context)

    class NewRecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val ingredientTextView: TextView = itemView.findViewById(R.id.ingredientTextView)
        val stepsTextView: TextView = itemView.findViewById(R.id.stepsTextView)

        val updateButton: ImageView = itemView.findViewById(R.id.newUpdateButton)

        val deleteButton: ImageView = itemView.findViewById(R.id.newDeleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewRecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.new_recipe_item, parent, false)
        return NewRecipeViewHolder(view)
    }

    override fun getItemCount(): Int = newRecipes.size

    override fun onBindViewHolder(holder: NewRecipeViewHolder, position: Int) {
        val newRecipe = newRecipes[position]
        holder.titleTextView.text = newRecipe.name
        holder.ingredientTextView.text = newRecipe.ingredient
        holder.stepsTextView.text = newRecipe.steps

        holder.updateButton.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateRecipeActivity::class.java).apply {
                putExtra("newRecipe_id", newRecipe.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.deleteButton.setOnClickListener{
            db.deleteNewRecipe(newRecipe.id)
            refreshData(db.getAllNewRecipe())
            Toast.makeText(holder.itemView.context, "Recipe Deleted", Toast.LENGTH_LONG).show()
        }
    }

    fun refreshData(newRecipe: List<NewRecipe>){
        newRecipes = newRecipe
        notifyDataSetChanged()
    }
}
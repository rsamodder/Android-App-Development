package com.example.racipeapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(private val context: Context) : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var recipeList = emptyList<MyRecipe>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Add references to the views here
        val idTxt: TextView = itemView.findViewById(R.id.id_txt)
        val recipeNameTxt: TextView = itemView.findViewById(R.id.recipeName_txt)
        val recipeIngredientsTxt: TextView = itemView.findViewById(R.id.recipeIngredients_txt)

        //val editButton: Button = itemView.findViewById(R.id.editButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.custom_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = recipeList[position]
        holder.idTxt.text = currentItem.id.toString()
        holder.recipeNameTxt.text = currentItem.name
        holder.recipeIngredientsTxt.text = currentItem.ingredient

        /* holder.editButton.setOnClickListener {
             val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
             holder.itemView.findNavController().navigate(action)
         }*/
    }

    fun setData(myRecipe: List<MyRecipe>) {
        this.recipeList = myRecipe
        notifyDataSetChanged()
    }
}
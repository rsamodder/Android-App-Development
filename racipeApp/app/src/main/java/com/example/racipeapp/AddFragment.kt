package com.example.racipeapp

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.racipeapp.databinding.FragmentAddBinding

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var myRecipeViewModel: MyRecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root

        myRecipeViewModel = ViewModelProvider(this).get(MyRecipeViewModel::class.java)

        // Set a click listener for the floating action button
        binding.saveButton.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val name = binding.recipeName.text.toString()
        val ingredient = binding.recipeIngredients.text.toString()
        val steps = binding.recipeSteps.text.toString()

        if(inputCheck(name, ingredient, steps)){
            // Create User Object
            val myRecipe = MyRecipe(0, name, ingredient, steps)
            // Add Data to Database
            myRecipeViewModel.addMyRecipe(myRecipe)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name: String, ingredient: String, steps: String): Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(ingredient) && TextUtils.isEmpty(steps) )
    }
}
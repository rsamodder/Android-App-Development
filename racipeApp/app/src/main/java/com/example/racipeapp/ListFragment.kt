package com.example.racipeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.racipeapp.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var myRecipeViewModel: MyRecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Use View Binding to inflate the layout
        binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root

        // Recyclerview
        val adapter = ListAdapter(requireContext())
        // Use View Binding to access the RecyclerView
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // UserViewModel
        myRecipeViewModel = ViewModelProvider(this).get(MyRecipeViewModel::class.java)
        myRecipeViewModel.readAllData.observe(viewLifecycleOwner, Observer { myRecipe ->
            adapter.setData(myRecipe)
        })

        // Set a click listener for the floating action button
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return view
    }
}
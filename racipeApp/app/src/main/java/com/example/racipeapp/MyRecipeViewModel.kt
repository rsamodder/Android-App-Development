package com.example.racipeapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyRecipeViewModel (application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<MyRecipe>>
    private val repository: MyRecipeRepository

    init {
        val myRecipeDao = MyRecipeDatabase.getDatabase(application).myRecipeDao()
        repository = MyRecipeRepository(myRecipeDao)
        readAllData = repository.readAllData
    }

    fun addMyRecipe(myRecipe: MyRecipe){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMyRecipe(myRecipe)
        }
    }
}
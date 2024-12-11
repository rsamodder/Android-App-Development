package com.example.racipeapp
import androidx.lifecycle.LiveData

class MyRecipeRepository(private val myRecipeDao: MyRecipeDao) {
    val readAllData: LiveData<List<MyRecipe>> = myRecipeDao.readAllData()

    suspend fun addMyRecipe(myRecipe: MyRecipe){
        myRecipeDao.addMyRecipe(myRecipe)
    }
}
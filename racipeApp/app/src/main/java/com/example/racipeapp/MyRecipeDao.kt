package com.example.racipeapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MyRecipeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMyRecipe(myRecipe: MyRecipe)

    @Query("SELECT * FROM my_recipe ORDER BY id ASC")
    fun readAllData(): LiveData<List<MyRecipe>>
}
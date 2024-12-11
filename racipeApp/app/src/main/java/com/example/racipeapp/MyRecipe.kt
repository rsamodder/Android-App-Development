package com.example.racipeapp
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="my_recipe")

class MyRecipe (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val ingredient: String,
    val steps: String
)
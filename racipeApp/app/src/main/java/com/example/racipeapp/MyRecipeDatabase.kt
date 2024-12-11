package com.example.racipeapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities =[MyRecipe::class],exportSchema = false, version = 1)
abstract class MyRecipeDatabase: RoomDatabase() {

    abstract fun myRecipeDao(): MyRecipeDao

    companion object {
        @Volatile
        private var INSTANCE: MyRecipeDatabase? = null

        fun getDatabase(context: Context): MyRecipeDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyRecipeDatabase::class.java,
                    "my_recipe"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
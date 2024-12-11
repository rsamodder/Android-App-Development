package com.example.racipeapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NewRecipeDatabaseHelper(context: Context):SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_NAME = "newrecipeapp.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "allnewrecipe"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "name"
        private const val COLUMN_INGREDIENT = "ingredient"
        private const val COLUMN_STEPS = "steps"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_TITLE TEXT, $COLUMN_INGREDIENT TEXT, $COLUMN_STEPS TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertNewRecipe(newRecipe: NewRecipe){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, newRecipe.name)
            put(COLUMN_INGREDIENT, newRecipe.ingredient)
            put(COLUMN_STEPS, newRecipe.steps)
        }
        db.insert(TABLE_NAME,null, values)
        db.close()
    }

    fun getAllNewRecipe(): List<NewRecipe>{
        val newRecipeList = mutableListOf<NewRecipe>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        while(cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
            val ingredient = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INGREDIENT))
            val steps = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STEPS))

            val newRecipe = NewRecipe(id, title, ingredient, steps)
            newRecipeList.add(newRecipe)
        }
        cursor.close()
        db.close()
        return newRecipeList
    }

    fun updateNewRecipe(newRecipe: NewRecipe){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, newRecipe.name)
            put(COLUMN_INGREDIENT, newRecipe.ingredient)
            put(COLUMN_STEPS, newRecipe.steps)
        }
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(newRecipe.id.toString())
        db.update(TABLE_NAME,values, whereClause, whereArgs)
        db.close()
    }

    fun getNewRecipeBYID(newRecipeId: Int): NewRecipe{
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = $newRecipeId"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()

        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
        val ingredient = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INGREDIENT))
        val steps = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STEPS))

        cursor.close()
        db.close()
        return NewRecipe(id, title, ingredient, steps)
    }

    fun deleteNewRecipe(newRecipeId: Int){
        val db = writableDatabase
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(newRecipeId.toString())
        db.delete(TABLE_NAME, whereClause, whereArgs)
        db.close()
    }
}
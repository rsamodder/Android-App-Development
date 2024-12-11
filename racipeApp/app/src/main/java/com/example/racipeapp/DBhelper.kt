package com.example.racipeapp
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.MatrixCursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBhelper(context:Context):SQLiteOpenHelper(context,"Usersdata",null,2) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table userdata (id integer primary key autoincrement, username TEXT, phone Text, passward TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists userdata")
    }

    fun insertdata(name:String,phn:String,pass:String): Boolean {
        val db=this.writableDatabase
        val cv=ContentValues()
        cv.put("username",name)
        cv.put("phone",phn)
        cv.put("passward",pass)
        val result=db.insert("userdata",null,cv)
        if(result==-1 .toLong()){
            return false
        }
        return true

    }

    //    fun checkuserpass(phn: String,pass: String):Boolean{
//        val db=this.writableDatabase
//        val query="select * from userdata where phone='$phn'and passward='$pass'"
//        val cursor=db.rawQuery(query,null)
//        if(cursor.count<=0){
//            cursor.close()
//            return false
//        }
//
//        cursor.close()
//        return true
//    }
    fun checkuserpass(phn: String, pass: String): Pair<Boolean, Int> {
        val db = this.writableDatabase
        val query = "select * from userdata where phone='$phn' and passward='$pass'"
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            val isLoggedIn = true
            val userId = cursor.getInt(cursor.getColumnIndex("id"))
            cursor.close()
            return Pair(isLoggedIn, userId)
        } else {
            cursor.close()
            return Pair(false, -1) // Return a default user ID or error value
        }
    }



    fun getUserdata(userid: String?): Cursor {
        if (userid == null) {
            // Handle the case where userid is null, such as showing an error message.
            // You can return an empty cursor as a placeholder in this case.
            return MatrixCursor(arrayOf("id", "username", "phone", "password"))
        }

        val db = readableDatabase
        return db.rawQuery("SELECT * FROM userdata WHERE id = ?", arrayOf(userid))
    }
    //    fun updateprofile(userid: String?): Cursor {
//        if (userid == null) {
//            // Handle the case where userid is null, such as showing an error message.
//            // You can return an empty cursor as a placeholder in this case.
//            return MatrixCursor(arrayOf("id", "username", "phone", "password"))
//        }
//
//        val db = readableDatabase
//        return db.rawQuery("SELECT * FROM userdata WHERE id = ?", arrayOf(userid))
//    }
    fun updatepass(phn1:String,pass1:String): Boolean {
        val db=this.writableDatabase
        val cv=ContentValues()


        cv.put("passward",pass1)
        //val result=db.update("userdata",null,cv)
        val result=db.update("userdata",cv, "phone=?", arrayOf(phn1))
        if(result==-1){
            return false
        }
        return true

    }

    fun updateprofile(name:String,phn1:String,id1:String): Boolean {
        val db=this.writableDatabase
        val cv=ContentValues()
        cv.put("username",name)
        cv.put("phone",phn1)

        //val result=db.update("userdata",null,cv)
        val result=db.update("userdata",cv, "id=?", arrayOf(id1))
        if(result==-1){
            return false
        }
        return true

    }

}
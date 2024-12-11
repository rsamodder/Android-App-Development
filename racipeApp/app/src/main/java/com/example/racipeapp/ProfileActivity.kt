package com.example.racipeapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.racipeapp.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var btnout: ImageView
    private lateinit var btnup: ImageView
    private lateinit var backb:ImageView
    private lateinit var dBhelper4: DBhelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dBhelper4=DBhelper(this)
        setContentView(R.layout.activity_profile)
        backb=findViewById(R.id. back)
        btnout=findViewById(R.id.button3)
        btnup=findViewById(R.id.imageup)
        // Retrieve the data passed from login
        val username = intent.getStringExtra("USERNAME")
        val phon = intent.getStringExtra("Phone")
        // val userid = intent.getStringExtra("idp")

        // Find the TextView in your layout
        val name = findViewById<TextView>(R.id.userName)
        val phone = findViewById<TextView>(R.id.phone)

        // Set the data in the TextView
        name.text = "Username: $username"
        phone.text = "Phone: $phon"


        btnout.setOnClickListener {

            val intent = Intent(this, Login::class.java)

            startActivity(intent)
        }
        backb.setOnClickListener {

            val intent = Intent(this, HomeActivity::class.java)

            startActivity(intent)
        }

        btnup.setOnClickListener {

            val  userid = intent.getStringExtra("idp")
            val cursor = dBhelper4.getUserdata(userid)

            if (cursor.moveToFirst()) {
                val username= cursor.getString(cursor.getColumnIndex("username"))
                val phone= cursor.getString(cursor.getColumnIndex("phone"))
                // Pass the username and email to the ProfileActivity
                val intent = Intent(this, UpdateProfile::class.java)
                intent.putExtra("USERNAME", username)
                intent.putExtra("Phone", phone)
                intent.putExtra("idp", userid)
                startActivity(intent)
            }

        }


    }

}
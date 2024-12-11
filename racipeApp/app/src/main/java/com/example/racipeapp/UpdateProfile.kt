package com.example.racipeapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.racipeapp.databinding.ActivityUpdateProfileBinding
import java.util.regex.Matcher
import java.util.regex.Pattern

class UpdateProfile : AppCompatActivity() {



    private lateinit var butup:Button

    private lateinit var dBhelper5: DBhelper

    private val mobileNoPattern = Pattern.compile("^\\+8801[7-9][0-9]{8}\$")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_update_profile)


        butup=findViewById(R.id.buttup)
        dBhelper5=DBhelper(this)
        val username = intent.getStringExtra("USERNAME")
        val phon = intent.getStringExtra("Phone")


        // Find the TextView in your layout
        val name1 = findViewById<EditText>(R.id.name)
        val phn1 = findViewById<EditText>(R.id.phn)

        // Set the data in the TextView
        name1.setText("$username")
        phn1.setText("$phon")
        butup.setOnClickListener {
            val  userid = intent.getStringExtra("idp")  ?: ""
            val username=name1.text.toString()
            val phone=phn1.text.toString()

            val updatedata=dBhelper5.updateprofile(username,phone,userid)
            if(TextUtils.isEmpty(username) || TextUtils.isEmpty(phone))
            {

                Toast.makeText(this,"Fillup All Data " , Toast.LENGTH_SHORT).show()
            }
            else {
                if (isValidPhoneNumber(phone)){

                    //  if (pass.equals(repass)) {

                    if (updatedata == true) {
                        phn1.setText("+880")

                        name1.setText("")

                        Toast.makeText(this, "Update Succesfull", Toast.LENGTH_SHORT).show()

                        val  userid = intent.getStringExtra("idp")
                        val cursor = dBhelper5.getUserdata(userid)

                        if (cursor.moveToFirst()) {
                            val username= cursor.getString(cursor.getColumnIndex("username"))
                            val phone= cursor.getString(cursor.getColumnIndex("phone"))
                            // Pass the username and email to the ProfileActivity
                            val intent = Intent(applicationContext, ProfileActivity::class.java)
                            intent.putExtra("USERNAME", username)
                            intent.putExtra("Phone", phone)
                            startActivity(intent)
                        } }

                    // }
//                    else {
//                        Toast.makeText(this, "Passward Not Match", Toast.LENGTH_SHORT).show()
//                    }
                }
                else {
                    phn1.error = "Invalid phone number"
                    // Toast.makeText(this, "Invalid Patten", Toast.LENGTH_SHORT).show()
                }
            }

        }

//        butup.setOnClickListener {
//            val intent = Intent(applicationContext, ProfileActivity::class.java)
//            startActivity(intent)
//        }
    }

    private fun isValidPhoneNumber(phone: String): Boolean {
//
        val matcher: Matcher =mobileNoPattern.matcher(phone)
        return matcher.find()
    }

}
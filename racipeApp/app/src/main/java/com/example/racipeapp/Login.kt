package com.example.racipeapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.service.autofill.UserData
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.component1
import androidx.core.graphics.component2
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.racipeapp.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {


    private lateinit var btnl: Button
    private lateinit var btreg: Button
    private lateinit var phon: EditText
    private lateinit var passw: EditText
    private lateinit var dBhelper2: DBhelper
    private lateinit var  forgot:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        phon=findViewById(R.id.pn)
        passw=findViewById(R.id.passw)
        btnl=findViewById(R.id.login)
        btreg=findViewById(R.id.reg)
        forgot=findViewById(R.id.textView2)
        dBhelper2=DBhelper(this)

        phon.setText("+880")

        btnl.setOnClickListener {



            val phone=phon.text.toString()
            val userpass=passw.text.toString()

            if(TextUtils.isEmpty(phone) || TextUtils.isEmpty(userpass) )
            {

                Toast.makeText(this,"Fillup All Data " , Toast.LENGTH_SHORT).show()
            }
            else{
                val (isLoggedIn, userId) = dBhelper2.checkuserpass(phone, userpass)

                if (isLoggedIn) {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                    val intent = Intent(applicationContext, HomeActivity::class.java)
                    intent.putExtra("USER_ID", userId.toString())
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Wrong Username or Password", Toast.LENGTH_SHORT).show()
                }


//
//            val check=dBhelper2.checkuserpass(phone, userpass)
//
//            if(check==true){
//                Toast.makeText(this,"Login Succesfull" ,Toast.LENGTH_SHORT).show()
//
//                val userId = dBhelper2.getUserId(phone)
////
//                val intent= Intent(applicationContext,HomeActivity::class.java)
//                intent.putExtra("uid", userId)
//                startActivity(intent)
//            }
//            else{
//                Toast.makeText(this,"Wrong Username And Passward" ,Toast.LENGTH_SHORT).show()
//            }

            }

//
        }
        btreg.setOnClickListener {
            val intent=Intent(this,Signup::class.java)
            startActivity(intent)
        }
        forgot.setOnClickListener {
            val intent=Intent(this,Forgotpass::class.java)
            startActivity(intent)
        }
    }

}
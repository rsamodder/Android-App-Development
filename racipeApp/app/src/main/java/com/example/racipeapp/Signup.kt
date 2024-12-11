package com.example.racipeapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Matcher
import java.util.regex.Pattern

class Signup : AppCompatActivity() {

    //
    private lateinit var bts: Button
    private lateinit var phn1:EditText
    private lateinit var name1:EditText
    private lateinit var pass1:EditText
    private lateinit var repass1:EditText
    private lateinit var dBhelper: DBhelper

    private val mobileNoPattern = Pattern.compile("^\\+8801[7-9][0-9]{8}\$")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_signup)
        phn1=findViewById(R.id.phn)
        phn1.setText("+880")
        name1=findViewById(R.id.name)
        pass1=findViewById(R.id.pass)
        repass1=findViewById(R.id.repass)
        bts=findViewById(R.id.button1)
        dBhelper=DBhelper(this)
        bts.setOnClickListener {
            val username=name1.text.toString()
            val phone=phn1.text.toString()
            val pass=pass1.text.toString()
            val repass=repass1.text.toString()
            val savedata=dBhelper.insertdata(username,phone,pass)
            if(TextUtils.isEmpty(username) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))
            {

                Toast.makeText(this,"Fillup All Data " ,Toast.LENGTH_SHORT).show()
            }
            else {
                if (isValidPhoneNumber(phone)){

                    if (pass.equals(repass)) {

                        if (savedata == true) {
                            phn1.setText("+880")
                            pass1.setText("")
                            name1.setText("")
                            repass1.setText("")
                            Toast.makeText(this, "SignUp Succesfull", Toast.LENGTH_SHORT).show()
                            val intent = Intent(applicationContext, Login::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "User Already Exists", Toast.LENGTH_SHORT).show()
                        }

                    }
                    else {
                        Toast.makeText(this, "Passward Not Match", Toast.LENGTH_SHORT).show()
                    }
                }
                else {
                    phn1.error = "Invalid phone number"
                    // Toast.makeText(this, "Invalid Patten", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun isValidPhoneNumber(phone: String): Boolean {
//        val phonePattern: Pattern = Patterns.PHONE
//
//        // Check if the phone number matches the regular expression
//        return phonePattern.matcher(phone).matches()
        val matcher:Matcher=mobileNoPattern.matcher(phone)
        return matcher.find()
    }


}
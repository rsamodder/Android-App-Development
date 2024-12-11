package com.example.racipeapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.racipeapp.databinding.ActivityForgotpassBinding
import java.util.regex.Matcher
import java.util.regex.Pattern

class Forgotpass : AppCompatActivity() {
    private lateinit var passf: EditText
    private lateinit var phnf: EditText
    private lateinit var repassf: EditText
    private lateinit var updatepass: Button
    private lateinit var dBhelper4: DBhelper



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_forgotpass)
        phnf=findViewById(R.id.fpn)
        phnf.setText("+880")

        passf=findViewById(R.id.fpass)
        repassf=findViewById(R.id.fpassw)
        updatepass=findViewById(R.id.login)
        dBhelper4=DBhelper(this)
        updatepass.setOnClickListener {
            val pass = passf.text.toString()
            val repass = repassf.text.toString()
            val phone = phnf.text.toString()

            if(TextUtils.isEmpty(phone) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))
            {

                Toast.makeText(this,"Fillup All Data " , Toast.LENGTH_SHORT).show()
            }
            else {
                // if (isValidPhoneNumber(phone)){

                if (pass.equals(repass)) {
                    val updatedata=dBhelper4.updatepass(phone,pass)

                    if (updatedata == true) {

                        phnf.setText("")
                        passf.setText("")
                        repassf.setText("")
                        Toast.makeText(this, "Upadated Succesfully", Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, Login::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Invalid User", Toast.LENGTH_SHORT).show()
                    }

                }
                else {
                    Toast.makeText(this, "Passward Not Match", Toast.LENGTH_SHORT).show()
                }
            }
//
        }

    }


//
}
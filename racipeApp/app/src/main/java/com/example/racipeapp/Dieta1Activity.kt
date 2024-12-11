package com.example.racipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class Dieta1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dieta1)
        var button_3 = findViewById<Button>(R.id.button_3)

        button_3.setOnClickListener {



                /*val intent = Intent(this, Dieta3Activity::class.java)
                    intent.putExtra("EER", TMB.toString())
                    startActivity(intent)*/

                startActivity(Intent (this@Dieta1Activity, Dieta3Activity::class.java).apply {



                })

            }
    }
}




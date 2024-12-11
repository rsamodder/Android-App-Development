package com.example.racipeapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.racipeapp.databinding.ActivityCreateAccountBinding
import com.example.racipeapp.databinding.ActivityHomeBinding

class CreateAccount : AppCompatActivity() {

    //    private lateinit var appBarConfiguration: AppBarConfiguration
//    private lateinit var binding: ActivityCreateAccountBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        setSupportActionBar(binding.toolbar)
//
//        val navController = findNavController(R.id.nav_host_fragment_content_create_account)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)
//
//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_create_account)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
    private lateinit var binding: ActivityHomeBinding
    //private lateinit var btns: Button
    private lateinit var btnl: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        // btns=findViewById(R.id.button)
        btnl=findViewById(R.id.button)
        btnl.setOnClickListener {
            val intent=Intent(this,Login::class.java)
            startActivity(intent)
        }
//    btnl.setOnClickListener {
//        val intent=Intent(this,Login::class.java)
//        startActivity(intent)
//    }
    }
}
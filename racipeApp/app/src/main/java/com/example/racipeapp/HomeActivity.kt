package com.example.racipeapp

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.racipeapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding:ActivityHomeBinding
    private lateinit var rvAdapter: PopularAdapter
    private lateinit var btnp: ImageView
    private lateinit var dBhelper3: DBhelper
    private lateinit var dataList:ArrayList<Recipe>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()

        dBhelper3=DBhelper(this)
        setUpRecyclerView()
        // val idp = intent.getStringExtra("uid")



        btnp=findViewById(R.id.button2)

        btnp.setOnClickListener {
            //
            val  userid = intent.getStringExtra("USER_ID")
            val cursor = dBhelper3.getUserdata(userid)

            if (cursor.moveToFirst()) {
                val username= cursor.getString(cursor.getColumnIndex("username"))
                val phone= cursor.getString(cursor.getColumnIndex("phone"))
                // Pass the username and email to the ProfileActivity
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra("USERNAME", username)
                intent.putExtra("Phone", phone)
                intent.putExtra("idp", userid)
                startActivity(intent)
            }


        }



        binding.search.setOnClickListener{
            startActivity(Intent(this,SearchActivity::class.java))
        }
        binding.salad.setOnClickListener{
            var myIntent=Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITTLE","Salad")
            myIntent.putExtra("CATEGORY","Salad")
            startActivity(myIntent)
        }

        binding.MainDish.setOnClickListener{
            var myIntent=Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITTLE","Main Dish")
            myIntent.putExtra("CATEGORY","Dish")
            startActivity(myIntent)
        }
        binding.more.setOnClickListener{
            var dialog= Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.bottom_sheet)

            dialog.show()
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window!!.setGravity(Gravity.BOTTOM)

            // Find the "Your Recipes" TextView in the dialog's view
            val yourRecipesTextView = dialog.findViewById<TextView>(R.id.textView4)

            // Set an OnClickListener to navigate to a new activity when the TextView is clicked
            yourRecipesTextView?.setOnClickListener {
                // Replace NewActivity::class.java with the actual activity you want to start
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
            }

            // Find the "Your Recipes" TextView in the dialog's view
            val bmi = dialog.findViewById<TextView>(R.id.bmi)
            val aboutDeveloper = dialog.findViewById<TextView>(R.id.aboutDeveloper)
            aboutDeveloper?.setOnClickListener {
                // Replace NewActivity::class.java with the actual activity you want to start
                val intent = Intent(this, DeveloperActivity::class.java)
                startActivity(intent)
            }

            // Set an OnClickListener to navigate to a new activity when the TextView is clicked
            bmi?.setOnClickListener {
                // Replace NewActivity::class.java with the actual activity you want to start
                val intent = Intent(this, BMIActivity::class.java)
                startActivity(intent)
            }
            val diet = dialog.findViewById<TextView>(R.id.diet)

            diet?.setOnClickListener {

                val intent = Intent(this, DietFilterActivity::class.java)
                startActivity(intent)
            }
        }


        binding.Drinks.setOnClickListener{
            var myIntent=Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITTLE","Drinks")
            myIntent.putExtra("CATEGORY","Drinks")
            startActivity(myIntent)
        }
        binding.Desserts.setOnClickListener{
            var myIntent=Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITTLE","Desserts")
            myIntent.putExtra("CATEGORY","Desserts")
            startActivity(myIntent)
        }
    }
    private fun setUpRecyclerView(){
        dataList=ArrayList()
     binding.rvPopular.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        var db= Room.databaseBuilder(this@HomeActivity,AppDatabase::class.java,"db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var daoObjects=db.getDao()
        var recipes=daoObjects.getAll()
        for(i in recipes!!.indices){
            if(recipes[i]!!.category.contains("Popular")){
                dataList.add(recipes[i]!!)
            }
            rvAdapter=PopularAdapter(dataList,this)
            binding.rvPopular.adapter=rvAdapter
        }
    }
}
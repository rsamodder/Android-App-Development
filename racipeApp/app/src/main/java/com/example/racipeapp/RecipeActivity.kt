package com.example.racipeapp

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.racipeapp.databinding.ActivityRacipeBinding
import com.example.racipeapp.databinding.ActivitySearch2Binding

class RecipeActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRacipeBinding
    var imgCrop=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRacipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImage)
        binding.tittle.text=intent.getStringExtra("tittle")
//        binding.ingData.text=intent.getStringExtra("ing")
        binding.stepData.text=intent.getStringExtra("des")



        var ing=intent.getStringExtra("ing")?.split("\n".toRegex())?.dropLastWhile { it.isEmpty() }?.toTypedArray()
        binding.time.text=ing?.get(0)
        for(i in 1 until (ing!!.size)){
              binding.ingData.text=
                """${binding.ingData.text} 🔯 ${ing[i]}${"\n"}
            """.trimIndent()
        }
        binding.nutritionDetailsData.text=intent.getStringExtra("nutrition_detail")

        binding.step.background=null
        binding.step.setTextColor(getColor(R.color.black))
        binding.step.setOnClickListener{
            binding.step.setBackgroundResource(R.drawable.btn_ing)

            binding.step.setTextColor(getColor(R.color.white))
            binding.ing.setTextColor(getColor(R.color.black))
            binding.ing.background=null
            binding.stepScroll.visibility=View.VISIBLE
            binding.ingScroll.visibility=View.GONE
        }
        binding.ing.setOnClickListener{
            binding.ing.setBackgroundResource(R.drawable.btn_ing)
            binding.ing.setTextColor(getColor(R.color.white))
            binding.step.setTextColor(getColor(R.color.black))
            binding.step.background=null
            binding.ingScroll.visibility=View.VISIBLE
            binding.stepScroll.visibility=View.GONE
        }
//        binding.nutritionDetails.background=null
        binding.nutritionDetails.setTextColor(getColor(R.color.black))
        binding.nutritionDetails.setOnClickListener{
            startActivity(Intent(this,chartActivity::class.java))
//            binding.nutritionDetails.setBackgroundResource(R.drawable.btn_ing)
//            binding.nutritionDetails.setTextColor(getColor(R.color.white))
//            binding.ing.setTextColor(getColor(R.color.black))
//            binding.ing.background=null
//            binding.nutritionDetailsScroll.visibility=View.VISIBLE
//            binding.ingScroll.visibility=View.GONE
        }
        binding.fullScrenn.setOnClickListener{

            if(imgCrop){
                binding.itemImage.scaleType=ImageView.ScaleType.FIT_CENTER
                Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImage)
                binding.fullScrenn.setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_ATOP)
                binding.shadow.visibility= View.GONE
                imgCrop=!imgCrop
            }else{
                binding.itemImage.scaleType=ImageView.ScaleType.CENTER_CROP
                Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImage)
                binding.fullScrenn.setColorFilter(null)
                binding.shadow.visibility= View.GONE
                imgCrop=!imgCrop
            }
        }
        binding.backBtn.setOnClickListener{
            finish()
        }
    }
}
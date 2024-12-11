package com.example.racipeapp

import android.annotation.SuppressLint
import android.inputmethodservice.InputMethodService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.racipeapp.databinding.ActivityHomeBinding
import com.example.racipeapp.databinding.ActivitySearch2Binding
import java.util.Locale.filter

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearch2Binding
    private lateinit var rvAdapter: SearchAdapter
    private lateinit var dataList:ArrayList<Recipe>
    private lateinit var recipes:List<Recipe?>
    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySearch2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        var db= Room.databaseBuilder(this@SearchActivity,AppDatabase::class.java,"db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var daoObjects=db.getDao()

        recipes = daoObjects.getAll()
        setUpRecyclerView()
        binding.goBackHome.setOnClickListener{
            finish()
        }
        binding.search1.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
              if(p0.toString()!=""){
                  filterData(p0.toString())
              }else{
                  setUpRecyclerView()
              }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })


//        var imm=getSystemService(INPUT_METHOD_SERVICE) as InputMethodService
//        binding.rvSearch.setOnTouchListener{v,event ->
//            imm.hideStatusIcon()
//        }
    }

    private fun filterData(filterText: String) {
    var filterData=ArrayList<Recipe>()
        for(i in recipes.indices){
            if(recipes[i]!!.tittle.lowercase().contains(filterText.lowercase())){
                filterData.add(recipes[i]!!)
            }
            rvAdapter.filterList(filterList=filterData)
        }
    }

    private fun setUpRecyclerView(){
        dataList=ArrayList()
//        binding.rvSearch.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.rvSearch.layoutManager=LinearLayoutManager(this)


        for(i in recipes!!.indices){
            if(recipes[i]!!.category.contains("Popular")){
                dataList.add(recipes[i]!!)
            }
            rvAdapter=SearchAdapter(dataList,this)
            binding.rvSearch.adapter=rvAdapter
        }
    }
}
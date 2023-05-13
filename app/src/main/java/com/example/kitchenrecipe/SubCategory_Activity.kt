package com.example.kitchenrecipe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import retrofit2.Call
import retrofit2.Response

class SubCategory_Activity : AppCompatActivity() {
    lateinit var adapter: SubCategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_category)

        val Id = intent.getStringExtra("catID")
        // var Id = intent.getIntExtra("catID",0)
        val anime = findViewById<LottieAnimationView>(R.id.notfound)

        val catid = Id?.toInt()

        val Title = findViewById<TextView>(R.id.Title)
        Title.text = intent.getStringExtra("title")

        getRecipe(catid, anime)

        val back = findViewById<ImageView>(R.id.backsub)
         back.setOnClickListener {
            finish()
         }
    }

    fun getRecipe(catid: Int?, anime: LottieAnimationView) {
        val progressbar = findViewById<ProgressBar>(R.id.subProBar)
        val recipe: Call<RecipeResponse> = Recipe_Data.recipeInterface.getRecipe(1)
        //  val category: Call<CategoryResponse> = Category_interface.Interface.getCategory(1)

        progressbar.isVisible = true
        anime.isVisible = false
        recipe.enqueue(object : retrofit2.Callback<RecipeResponse> {

            override fun onResponse(
                call: Call<RecipeResponse>,
                response: Response<RecipeResponse>
            ) {
                progressbar.isVisible = false

                Log.e("TAG", "onResponse: " + response.raw().toString())
                val recipe = response.body()

                var list: List<AppItem?> = mutableListOf()

                for (i in recipe?.app!!) {
                    if (i?.catID?.equals(catid)!!) {
                        list = recipe.app
                        adapter = SubCategoryAdapter(this@SubCategory_Activity, list)

                        val kitchen = findViewById<RecyclerView>(R.id.Subcategory)
                        kitchen.adapter = adapter
                        kitchen.layoutManager = LinearLayoutManager(this@SubCategory_Activity)

                        anime.isVisible = false
                    } else {
                        anime.isVisible = true
                    }
                }

            }

            override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {
                progressbar.isVisible = false
                Log.d("PracticeApp", "Error in Fetching Data", t)
            }

        })

    }
}
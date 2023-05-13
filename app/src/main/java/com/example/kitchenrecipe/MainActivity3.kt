package com.example.kitchenrecipe

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.ScrollingMovementMethod
import android.text.style.UnderlineSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import java.lang.reflect.Method


class MainActivity3 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)


        val image: ImageView = findViewById(R.id.open_recipe_Image)
        val name: TextView = findViewById(R.id.open_recipe_name)

        val image1 = intent.getStringExtra("image")
        val name1 = intent.getStringExtra("Name")

        name.text = name1
        Glide.with(this).load(image1).into(image)

        val ingredient = findViewById<TextView>(R.id.ingredient)
        val method = findViewById<TextView>(R.id.method)
        val data = findViewById<TextView>(R.id.textView2)

        val method2 = intent.getStringExtra("method")
        val ingredients2 = intent.getStringExtra("ingredients")
        data.text = ingredients2

        val back = findViewById<ImageView>(R.id.back)
//for back finish activity
        back.setOnClickListener {
            finish()
        }
 //for default underline
        val string = SpannableString("Ingredients")
        string.setSpan(UnderlineSpan(), 0, string.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        ingredient.text = string
        method.text = "Method"


        ingredient.setOnClickListener {
            data.text = ingredients2
            ingredient.setTextColor(Color.parseColor("#FF0000"))
            method.setTextColor(Color.parseColor("#808080"))

            val string = SpannableString("Ingredients")
            string.setSpan(UnderlineSpan(), 0, string.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            ingredient.text = string
            method.text = "Method"
        }

        method.setOnClickListener {
            data.text = method2
            data.movementMethod = ScrollingMovementMethod()
            method.setTextColor(Color.parseColor("#FF0000"))
            ingredient.setTextColor(Color.parseColor("#808080"))

            val string = SpannableString("Method")
            string.setSpan(UnderlineSpan(), 0, string.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            method.text = string
            ingredient.text = "Ingredients"

        }
    }
}

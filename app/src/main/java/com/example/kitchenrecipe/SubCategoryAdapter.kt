package com.example.kitchenrecipe

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SubCategoryAdapter(val context: Context, val recipess: List<AppItem?>?): RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>()  {

    class SubCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var Image: ImageView = itemView.findViewById<ImageView>(R.id.item_image)
//        var RecipeName: TextView = itemView.findViewById<TextView>(R.id.item_name)
//        var Ingredient : TextView = itemView.findViewById<TextView>(R.id.ingredient)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeAdapter.RecipeViewHolder{
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return RecipeAdapter.RecipeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recipess?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecipeAdapter.RecipeViewHolder, position: Int) {
        val recipe: AppItem? = recipess?.get(position)
        holder.RecipeName.text = recipe?.recipeName
        Glide.with(context).load(recipe?.image).into(holder.Image)

        Log.d("PracticeCat", recipe.toString())
        holder.itemView.setOnClickListener() {
            //   context.startActivity(Intent(context,MainActivity3::class.java))
            val i = Intent(context, MainActivity3::class.java)
            i.putExtra("image", recipe?.image)
            i.putExtra("Name", recipe?.recipeName)
            i.putExtra("ingredients",recipe?.ingredient)
            i.putExtra("method",recipe?.method)
            context.startActivity(i)
        }
    }
}
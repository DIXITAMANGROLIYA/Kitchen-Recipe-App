package com.example.kitchenrecipe

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlin.math.log

class CategoryAdapter(val context: Context, val category: List<CategoryItem?>?) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var CatImage: ImageView = itemView.findViewById(R.id.CategoryImage)
        var CatName: TextView = itemView.findViewById(R.id.CategotyName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.category_layout, parent, false)
        return CategoryAdapter.CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return category?.size ?: 0
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val catItem: CategoryItem? = category?.get(position)

        holder.CatName.text = catItem?.catNAME
        Glide.with(context).load(catItem?.image).into(holder.CatImage)

        holder.itemView.setOnClickListener() {
            val intent = Intent(context, SubCategory_Activity::class.java)
            intent.putExtra("catID", catItem?.catID.toString())
            intent.putExtra("title", catItem?.catNAME)
            intent.putExtra("image", catItem?.image)

            Log.d("catid",catItem?.catID.toString())

            context.startActivity(intent)
        }
    }
}
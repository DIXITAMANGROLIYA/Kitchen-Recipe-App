package com.example.kitchenrecipe

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class RecipeAdapter(
    val context: Context,
    private val recipes: List<AppItem?>?,
    private var addData: getData,
) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private var databaseHelper: DatabaseHelper = DatabaseHelper.getInstance(context)

    interface getData {
        fun onLike(position: Int)
    }


    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var Image: ImageView = itemView.findViewById(R.id.item_image)
        var RecipeName: TextView = itemView.findViewById(R.id.item_name)
        var like: ImageView = itemView.findViewById(R.id.like)

        //var Ingredient : TextView = itemView.findViewById(R.id.ingredient)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return RecipeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recipes?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe: AppItem? = recipes?.get(position)
        holder.RecipeName.text = recipe?.recipeName
        Glide.with(context).load(recipe?.image).into(holder.Image)

        //  databaseHelper = DatabaseHelper.getInstance(context)
        val k = databaseHelper.UserDao()?.getAppitem(recipe!!.recipeID)
        if (k != null) {
            holder.like.setImageResource(R.drawable.like_selected)
        } else {
            holder.like.setImageResource(R.drawable.like_unselected)
        }

        holder.itemView.setOnClickListener() {
            //   context.startActivity(Intent(context,MainActivity3::class.java))
            val i = Intent(context, MainActivity3::class.java)
            i.putExtra("image", recipe?.image)
            i.putExtra("Name", recipe?.recipeName)
            i.putExtra("ingredients", recipe?.ingredient)
            i.putExtra("method", recipe?.method)
            context.startActivity(i)
        }

        holder.like.setOnClickListener() {

            val x = databaseHelper.UserDao()?.getAppitem(recipe!!.recipeID)

            if (x != null) {
                //delete data
                databaseHelper.UserDao()!!.delete(recipe!!)

            } else {
                //add data
                addData.onLike(position)
            }
            //refresh item
            this@RecipeAdapter.notifyItemChanged(position)
        }

    }

//    var clicked = true
//    fun onLikeClick(view: View?) {
//        if (clicked) {
//            imageView.setImageResource(R.drawable.like_selected)
//        } else {
//            imageView.setImageResource(R.drawable.like_unselected)
//        }
//    }
}
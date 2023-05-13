package com.example.kitchenrecipe

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class RecipeResponse(

	@field:SerializedName("app")
	val app: List<AppItem?>? = null
)

@Entity(tableName = "like_table")
data class AppItem(

	@ColumnInfo("catID")
	@field:SerializedName("catID")
	val catID: Int,

	@ColumnInfo("RecipeName")
	@field:SerializedName("RecipeName")
	val recipeName: String? = null,

	@ColumnInfo("Ingredient")
	@field:SerializedName("Ingredient")
	val ingredient: String? = null,

	@PrimaryKey(autoGenerate = true)
	@ColumnInfo("RecipeID")
	@field:SerializedName("RecipeID")
	val recipeID: Int,

	@ColumnInfo("Method")
	@field:SerializedName("Method")
	val method: String? = null,

	@ColumnInfo("Image")
	@field:SerializedName("Image")
	val image: String? = null
)

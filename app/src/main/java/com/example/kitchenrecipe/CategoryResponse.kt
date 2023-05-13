package com.example.kitchenrecipe

import com.google.gson.annotations.SerializedName

data class CategoryResponse(

	@field:SerializedName("app")
	val app: List<CategoryItem?>? = null
)

data class CategoryItem(

	@field:SerializedName("catID")
	val catID: Int? = null,

	@field:SerializedName("Image")
	val image: String? = null,

	@field:SerializedName("CatNAME")
	val catNAME: String? = null
)

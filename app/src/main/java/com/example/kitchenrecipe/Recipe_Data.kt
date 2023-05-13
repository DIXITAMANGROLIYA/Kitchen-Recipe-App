package com.example.kitchenrecipe

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://script.googleusercontent.com/a/macros/aanibrothers.in/"

//https://script.googleusercontent.com/a/macros/aanibrothers.in/echo?user_content_key=Sk2ErjVEHRgB3e-axeIPeJssPgBOU-OYhPY7UwL3CNHi13dbedO4OpI7NGvEIRw6iGP-Tn0QjHcoDP3VgXkUEipXVC0TdxLROJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMi80zadyHLKD2b7RS78Wk6az_gaLMdrNWtUTb3Idx4pFB61t5CWcHEbd4PlVuGTTucCXDd0mStXFteQm0D-fA1jm67FD6ZxZl2BvJAH-zHXWJzg4GJRDbL5iLITacnWNDZDi2K3_A2IKagQEneoX2wQ&lib=MIH2qq_03TRhGFo_-SxK6W4YOidlTg8Sh

interface RecipeInterface {
    @GET("echo?user_content_key=Sk2ErjVEHRgB3e-axeIPeJssPgBOU-OYhPY7UwL3CNHi13dbedO4OpI7NGvEIRw6iGP-Tn0QjHcoDP3VgXkUEipXVC0TdxLROJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMi80zadyHLKD2b7RS78Wk6az_gaLMdrNWtUTb3Idx4pFB61t5CWcHEbd4PlVuGTTucCXDd0mStXFteQm0D-fA1jm67FD6ZxZl2BvJAH-zHXWJzg4GJRDbL5iLITacnWNDZDi2K3_A2IKagQEneoX2wQ&lib=MIH2qq_03TRhGFo_-SxK6W4YOidlTg8Sh")
    fun getRecipe(@Query("catID")catID:Int) : retrofit2.Call<RecipeResponse>
}

object Recipe_Data{
    val recipeInterface : RecipeInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        recipeInterface = retrofit.create(RecipeInterface::class.java)
    }
}
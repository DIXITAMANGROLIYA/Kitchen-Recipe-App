package com.example.kitchenrecipe

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://script.googleusercontent.com/a/macros/aanibrothers.in/echo?user_content_key=0L86lMc4TbN4CIKuiPy7D_j1xK7fhuX36IydbstkaNQycF6xDX27tWAk9B9kPAS1YEOCYZcwN7KIMJ7snDbiaPrEhondrti3OJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMi80zadyHLKD2b7RS78Wk6az_gaLMdrNWtUTb3Idx4pFItgINRypT4RhYjSjWXXmRG-hlkFysQsF3JGelZhaWapJIKbDC61MpMVyN8GcgbsKCl6cej2-l1XAKLnpeqhgWWtrpI-QCJjyagQEneoX2wQ&lib=M65TNN-rRhXeK3Fll8krqu4YOidlTg8Sh
const val CATEGORY_URL ="https://script.googleusercontent.com/a/macros/aanibrothers.in/"

interface CategoryInterface{
   @GET("echo?user_content_key=0L86lMc4TbN4CIKuiPy7D_j1xK7fhuX36IydbstkaNQycF6xDX27tWAk9B9kPAS1YEOCYZcwN7KIMJ7snDbiaPrEhondrti3OJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMi80zadyHLKD2b7RS78Wk6az_gaLMdrNWtUTb3Idx4pFItgINRypT4RhYjSjWXXmRG-hlkFysQsF3JGelZhaWapJIKbDC61MpMVyN8GcgbsKCl6cej2-l1XAKLnpeqhgWWtrpI-QCJjyagQEneoX2wQ&lib=M65TNN-rRhXeK3Fll8krqu4YOidlTg8Sh")
   fun getCategory(@Query("catID")catID:Int): retrofit2.Call<CategoryResponse>
}
object Category_interface{
   val Interface : CategoryInterface
   init {
      val retrofit = Retrofit.Builder()
         .baseUrl(CATEGORY_URL)
         .addConverterFactory(GsonConverterFactory.create())
         .build()
      Interface = retrofit.create(CategoryInterface::class.java)
   }
}

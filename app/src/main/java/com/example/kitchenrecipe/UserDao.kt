package com.example.kitchenrecipe

import androidx.room.*

@Dao
interface UserDao {
    @get:Query("SELECT * FROM like_table")
    val all: List<AppItem>

    @Query("SELECT * FROM like_table where like_table.RecipeID = (:recipeid)")
    fun getAppitem(recipeid : Int): AppItem

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insert(dataModal:AppItem)

    @Delete
    fun delete(dataModal: AppItem)
}

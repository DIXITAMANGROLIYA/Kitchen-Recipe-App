package com.example.kitchenrecipe

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [AppItem::class], exportSchema = false, version = 2)
abstract class DatabaseHelper: RoomDatabase() {

    abstract fun UserDao(): UserDao?

    companion object {
        private var instance: DatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseHelper {
            if (instance == null)
                instance = Room.databaseBuilder(
                    ctx.applicationContext, DatabaseHelper::class.java,
                    "like_table"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()

            return instance!!

        }

    }

}
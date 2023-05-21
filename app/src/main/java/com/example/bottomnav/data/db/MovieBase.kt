package com.example.bottomnav.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bottomnav.data.models.CategoryModel
import com.example.bottomnav.data.models.ProductModel

@Database(entities = [CategoryModel::class, ProductModel::class], version = 1)
abstract class MovieBase : RoomDatabase() {
    abstract val CategoryDAO: CategoryDao
    abstract val ProductDAO: ProductDao

    companion object{
        @Volatile
        private var INSTANCE: MovieBase? = null
        fun getInstance(context: Context): MovieBase {
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieBase::class.java,
                        "database"
                    ).build()
                }
                return instance
            }
        }
    }

}
package com.example.bottomnav.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bottomnav.models.CategoryModel
import com.example.bottomnav.models.ProductModel
import java.security.AccessControlContext

@Database(entities = [CategoryModel::class,ProductModel::class], version = 1)
abstract class MovieBase : RoomDatabase() {
    abstract val CategoryDAO: CategoryDao
    abstract val ProductDAO: ProductDao

    companion object{
        @Volatile
        private var INSTANCE:com.example.bottomnav.data.MovieBase? = null
        fun getInstance(context: Context):com.example.bottomnav.data.MovieBase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        com.example.bottomnav.data.MovieBase::class.java,
                        "database"
                    ).build()
                }
                return instance
            }
        }
    }

}
package com.example.bottomnav.repositories

import androidx.lifecycle.LiveData
import com.example.bottomnav.data.CategoryDao
import com.example.bottomnav.models.CategoryModel
import com.example.bottomnav.models.ProductModel

class CategoryRepository(private val categoryDao: CategoryDao) {

    val categories = categoryDao.getAllCategories()

    suspend fun insertCategory(categoryModel: CategoryModel){
        categoryDao.insertCategory(categoryModel)
    }

    suspend fun updateCategory(categoryModel: CategoryModel){
        categoryDao.updateCategory(categoryModel)
    }

    suspend fun deleteCategory(categoryModel: CategoryModel){
        categoryDao.deleteCategory(categoryModel)
    }

    suspend fun deleteAllCategories(){
        categoryDao.deleteAllCategories()
    }

    fun getFilterCategoryName(nameCategory:String):
            LiveData<List<CategoryModel>> {
        return categoryDao.getFilterCategoryName(nameCategory)
    }
}
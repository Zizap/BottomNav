package com.example.bottomnav.data.repositories

import androidx.lifecycle.LiveData
import com.example.bottomnav.data.db.CategoryDao
import com.example.bottomnav.data.models.CategoryModel
import com.example.bottomnav.domain.repository.CategoriesCall

class CategoryRepository(private val categoryDao: CategoryDao):CategoriesCall {

    override fun loadCategories():LiveData<List<CategoryModel>>{
        return categoryDao.getAllCategories()
    }

    override suspend fun insertCategory(categoryModel: CategoryModel){
        categoryDao.insertCategory(categoryModel)
    }

    override suspend fun updateCategory(categoryModel: CategoryModel){
        categoryDao.updateCategory(categoryModel)
    }

    override suspend fun deleteCategory(categoryModel: CategoryModel){
        categoryDao.deleteCategory(categoryModel)
    }

    override suspend fun deleteAllCategories(){
        categoryDao.deleteAllCategories()
    }

    override fun getFilterCategoryName(nameCategory:String):
            LiveData<List<CategoryModel>> {
        return categoryDao.getFilterCategoryName(nameCategory)
    }
}
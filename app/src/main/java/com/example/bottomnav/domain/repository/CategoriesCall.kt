package com.example.bottomnav.domain.repository

import androidx.lifecycle.LiveData
import com.example.bottomnav.data.models.CategoryModel

interface CategoriesCall {


    fun loadCategories(): LiveData<List<CategoryModel>>

    suspend fun insertCategory(categoryModel: CategoryModel)

    suspend fun updateCategory(categoryModel: CategoryModel)

    suspend fun deleteCategory(categoryModel: CategoryModel)

    suspend fun deleteAllCategories()

    fun getFilterCategoryName(nameCategory:String):LiveData<List<CategoryModel>>

}
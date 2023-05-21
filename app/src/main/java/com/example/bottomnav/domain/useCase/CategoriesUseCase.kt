package com.example.bottomnav.domain.useCase

import androidx.lifecycle.LiveData
import com.example.bottomnav.data.models.CategoryModel
import com.example.bottomnav.domain.repository.CategoriesCall

class CategoriesUseCase(private val categoriesCall: CategoriesCall) {

    fun loadCategories(): LiveData<List<CategoryModel>> {
        return categoriesCall.loadCategories()
    }

    suspend fun insertCategory(categoryModel: CategoryModel){
        categoriesCall.insertCategory(categoryModel)
    }

    suspend fun updateCategory(categoryModel: CategoryModel){
        categoriesCall.updateCategory(categoryModel)
    }

    suspend fun deleteCategory(categoryModel: CategoryModel){
        categoriesCall.deleteCategory(categoryModel)
    }

    suspend fun deleteAllCategories(){
        categoriesCall.deleteAllCategories()
    }

    fun getFilterCategoryName(nameCategory:String):
            LiveData<List<CategoryModel>> {
        return categoriesCall.getFilterCategoryName(nameCategory)
    }


}
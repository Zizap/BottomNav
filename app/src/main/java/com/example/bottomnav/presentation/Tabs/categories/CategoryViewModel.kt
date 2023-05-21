package com.example.bottomnav.presentation.Tabs.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnav.data.models.CategoryModel
import com.example.bottomnav.data.repositories.CategoryRepository
import com.example.bottomnav.domain.useCase.CategoriesUseCase
import com.example.bottomnav.domain.useCase.ProductsUseCase
import kotlinx.coroutines.launch

class CategoryViewModel(private val categoryUseCase: CategoriesUseCase):ViewModel() {

    fun loadCategory():LiveData<List<CategoryModel>>{
        return categoryUseCase.loadCategories()
    }
    fun startInsert(nameCategory:String){
        insert(CategoryModel(0,nameCategory))
    }

    fun startUpdateCategory(idCategory:Int,nameCategory: String){
        updateCategory(CategoryModel(idCategory,nameCategory))
    }

    fun insert(categoryModel: CategoryModel) = viewModelScope.launch{
        categoryUseCase.insertCategory(categoryModel)
    }

    fun updateCategory(categoryModel: CategoryModel) = viewModelScope.launch{
        categoryUseCase.updateCategory(categoryModel)
    }

    fun deleteCategory(categoryModel: CategoryModel) = viewModelScope.launch{
        categoryUseCase.deleteCategory(categoryModel)
    }

    fun deleteAllCategories() = viewModelScope.launch{
        categoryUseCase.deleteAllCategories()
    }

    fun getFilterCategoryName(nameCategory:String):
            LiveData<List<CategoryModel>> {
        return categoryUseCase.getFilterCategoryName(nameCategory)
    }


}
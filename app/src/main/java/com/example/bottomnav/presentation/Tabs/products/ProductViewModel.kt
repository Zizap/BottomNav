package com.example.bottomnav.presentation.Tabs.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnav.data.models.ProductModel
import com.example.bottomnav.data.repositories.ProductRepository
import com.example.bottomnav.domain.useCase.ProductsUseCase
import kotlinx.coroutines.launch

class ProductViewModel(private val productsUseCase: ProductsUseCase): ViewModel() {


    fun loadProducts():LiveData<List<ProductModel>>{
        return productsUseCase.loadProducts()
    }

    fun getFilter(nameCategory:String,priceProduct:String):
            LiveData<List<ProductModel>> {
        return productsUseCase.getFilter(nameCategory,priceProduct)
    }

    fun getFilterCategory(nameCategory:String):
            LiveData<List<ProductModel>> {
        return productsUseCase.getFilterCategory(nameCategory)
    }

    fun startInsert(nameProduct:String, categoryProduct:String,priceProduct: String){
        insertProduct(ProductModel(0,nameProduct,categoryProduct,priceProduct))
    }

    fun startUpdateProduct(idProduct:Int, nameProduct:String, categoryProduct:String,priceProduct: String){
        updateProduct(ProductModel(idProduct,nameProduct,categoryProduct,priceProduct))
    }

    fun insertProduct(productModel: ProductModel) = viewModelScope.launch {
        productsUseCase.insertProduct(productModel)
    }

    fun updateProduct(productModel: ProductModel) = viewModelScope.launch {
        productsUseCase.updateProduct(productModel)
    }

    fun deleteProduct(productModel: ProductModel) = viewModelScope.launch {
        productsUseCase.deleteProduct(productModel)
    }

    fun deleteAllProducts() = viewModelScope.launch {
        productsUseCase.deleteAllProduct()
    }

}
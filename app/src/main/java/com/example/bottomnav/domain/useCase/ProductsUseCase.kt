package com.example.bottomnav.domain.useCase

import androidx.lifecycle.LiveData
import com.example.bottomnav.data.models.ProductModel
import com.example.bottomnav.domain.repository.ProductsCall

class ProductsUseCase(private val productsCall: ProductsCall) {

    fun loadProducts(): LiveData<List<ProductModel>> {
        return productsCall.loadProducts()
    }

    fun getFilter(nameCategory:String, priceProduct:String):
            LiveData<List<ProductModel>> {
        return productsCall.getFilter(nameCategory,priceProduct)
    }

    fun getFilterCategory(nameCategory:String):
            LiveData<List<ProductModel>> {
        return productsCall.getFilterCategory(nameCategory)
    }

    suspend fun insertProduct(productModel: ProductModel){
        productsCall.insertProduct(productModel)
    }

    suspend fun updateProduct(productModel: ProductModel){
        productsCall.updateProduct(productModel)
    }

    suspend fun deleteProduct(productModel: ProductModel){
        productsCall.deleteProduct(productModel)
    }

    suspend fun deleteAllProduct(){
        productsCall.deleteAllProduct()
    }

}
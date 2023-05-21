package com.example.bottomnav.domain.repository

import androidx.lifecycle.LiveData
import com.example.bottomnav.data.models.ProductModel

interface ProductsCall {

    fun loadProducts(): LiveData<List<ProductModel>>

    fun getFilter(nameCategory:String, priceProduct:String):LiveData<List<ProductModel>>

    fun getFilterCategory(nameCategory:String):LiveData<List<ProductModel>>

    suspend fun insertProduct(productModel: ProductModel)

    suspend fun updateProduct(productModel: ProductModel)

    suspend fun deleteProduct(productModel: ProductModel)

    suspend fun deleteAllProduct()

}
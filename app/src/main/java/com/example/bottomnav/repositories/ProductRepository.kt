package com.example.bottomnav.repositories

import androidx.lifecycle.LiveData
import com.example.bottomnav.data.ProductDao
import com.example.bottomnav.models.ProductModel

class ProductRepository(private val productDao: ProductDao) {

    val product = productDao.getAllProduct()

    fun getFilter(nameCategory:String, priceProduct:String):
            LiveData<List<ProductModel>>{
        return productDao.getFilter(nameCategory,priceProduct)
    }

    fun exists(nameCategory: String):
    LiveData<Boolean> {
       return productDao.exists(nameCategory)
    }

    fun getFilterCategory(nameCategory:String):
            LiveData<List<ProductModel>>{
        return productDao.getFilterCategory(nameCategory)
    }

    suspend fun insertProduct(productModel: ProductModel){
        productDao.insertProduct(productModel)
    }

    suspend fun updateProduct(productModel: ProductModel){
        productDao.updateProduct(productModel)
    }

    suspend fun deleteProduct(productModel: ProductModel){
        productDao.deleteProduct(productModel)
    }

    suspend fun deleteAllProduct(){
        productDao.deleteAllProducts()
    }



}
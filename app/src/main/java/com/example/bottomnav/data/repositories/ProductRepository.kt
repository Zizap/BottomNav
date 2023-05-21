package com.example.bottomnav.data.repositories

import androidx.lifecycle.LiveData
import com.example.bottomnav.data.db.ProductDao
import com.example.bottomnav.data.models.CategoryModel
import com.example.bottomnav.data.models.ProductModel
import com.example.bottomnav.domain.repository.ProductsCall

class ProductRepository(private val productDao: ProductDao):ProductsCall {

    override fun loadProducts():LiveData<List<ProductModel>>{
        return productDao.getAllProduct()
    }

    override fun getFilter(nameCategory:String, priceProduct:String):
            LiveData<List<ProductModel>>{
        return productDao.getFilter(nameCategory,priceProduct)
    }

    override fun getFilterCategory(nameCategory:String):
            LiveData<List<ProductModel>>{
        return productDao.getFilterCategory(nameCategory)
    }

    override suspend fun insertProduct(productModel: ProductModel){
        productDao.insertProduct(productModel)
    }

    override suspend fun updateProduct(productModel: ProductModel){
        productDao.updateProduct(productModel)
    }

    override suspend fun deleteProduct(productModel: ProductModel){
        productDao.deleteProduct(productModel)
    }

    override suspend fun deleteAllProduct(){
        productDao.deleteAllProducts()
    }



}
package com.example.bottomnav.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnav.models.ProductModel
import com.example.bottomnav.repositories.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepository: ProductRepository): ViewModel() {

    val products = productRepository.product

    fun getFilter(nameCategory:String,priceProduct:String):
            LiveData<List<ProductModel>> {
        return productRepository.getFilter(nameCategory,priceProduct)
    }

    fun getFilterCategory(nameCategory:String):
            LiveData<List<ProductModel>> {
        return productRepository.getFilterCategory(nameCategory)
    }

    fun exists(nameCategory:String):
            Boolean? {
        return productRepository.exists(nameCategory).value
    }


    fun startInsert(nameProduct:String, categoryProduct:String,priceProduct: String){
        insertProduct(ProductModel(0,nameProduct,categoryProduct,priceProduct))
    }

    fun startUpdateProduct(idProduct:Int, nameProduct:String, categoryProduct:String,priceProduct: String){
        updateProduct(ProductModel(idProduct,nameProduct,categoryProduct,priceProduct))
    }

    fun insertProduct(productModel: ProductModel) = viewModelScope.launch {
        productRepository.insertProduct(productModel)
    }

    fun updateProduct(productModel: ProductModel) = viewModelScope.launch {
        productRepository.updateProduct(productModel)
    }

    fun deleteProduct(productModel: ProductModel) = viewModelScope.launch {
        productRepository.deleteProduct(productModel)
    }

    fun deleteAllProducts() = viewModelScope.launch {
        productRepository.deleteAllProduct()
    }



}
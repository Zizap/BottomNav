package com.example.bottomnav.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bottomnav.models.ProductModel

@Dao
interface ProductDao {

    @Insert
    suspend fun insertProduct(productModel: ProductModel)

    @Delete
    suspend fun deleteProduct(productModel: ProductModel)

    @Update
    suspend fun updateProduct(productModel: ProductModel)

    @Query("DELETE FROM product_data_table")
    suspend fun deleteAllProducts()

    @Query("SELECT * FROM product_data_table")
    fun getAllProduct(): LiveData<List<ProductModel>>

    @Query("SELECT * FROM product_data_table WHERE product_category = :nameCategory OR product_price = :priceProduct")
    fun getFilter(nameCategory:String, priceProduct:String):LiveData<List<ProductModel>>

    @Query("SELECT * FROM product_data_table WHERE product_category = :nameCategory")
    fun getFilterCategory(nameCategory:String):LiveData<List<ProductModel>>

    @Query("SELECT EXISTS (SELECT 1 FROM product_data_table WHERE product_category = :nameCategory)")
    fun exists(nameCategory: String): LiveData<Boolean>
}
//@Query("SELECT EXISTS (SELECT 1 FROM product_data_table WHERE product_category = :nameCategory)")
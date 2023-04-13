package com.example.bottomnav.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnav.databinding.ProductRecycleItemBinding
import com.example.bottomnav.models.ProductModel

class ProductAdapter(private val deleteProduct:(ProductModel)->Unit,
                     private val editProduct:(ProductModel)->Unit):RecyclerView.Adapter<ProductHolder>() {

    private val productList = ArrayList<ProductModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding: ProductRecycleItemBinding = ProductRecycleItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return ProductHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bind(productList[position],deleteProduct,editProduct)
    }

    fun setListProduct(products: List<ProductModel>){
        productList.clear()
        productList.addAll(products)
    }


}

class ProductHolder (val binding: ProductRecycleItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(products: ProductModel,
             deleteProduct: (ProductModel) -> Unit,
             editProduct: (ProductModel) -> Unit){

        binding.nameProduct.text = products.name
        binding.categoryProduct.text = products.category
        binding.priceProduct.text = products.price
        binding.deleteProductBtn.setOnClickListener(View.OnClickListener {
            deleteProduct(products)
        })
        binding.editProductBtn.setOnClickListener(View.OnClickListener {
            editProduct(products)
        })

    }
}

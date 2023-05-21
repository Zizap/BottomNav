package com.example.bottomnav.presentation.Tabs.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomnav.adapters.ProductAdapter
import com.example.bottomnav.databinding.FragmentProductShopBinding
import com.example.bottomnav.data.models.ProductModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProductShopFragment : Fragment() {

    private var binding: FragmentProductShopBinding? = null
    private var productAdapter: ProductAdapter? = null
    private val productViewModel:ProductViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductShopBinding.inflate(inflater,container,false)
        initRecyclerProducts()
        binding?.deleteAllProductBtn?.setOnClickListener(View.OnClickListener {
            productViewModel?.deleteAllProducts()
        })

        return binding?.root
    }

    private fun initRecyclerProducts(){
        binding?.recyclerAllProduct?.layoutManager = LinearLayoutManager(context)
        productAdapter = ProductAdapter({productModel: ProductModel -> deleteProduct(productModel)},
            {productModel: ProductModel -> editProduct(productModel)})
        binding?.recyclerAllProduct?.adapter = productAdapter

        displayProducts()
    }

    private fun displayProducts(){
        productViewModel?.loadProducts()?.observe(viewLifecycleOwner, Observer {
            productAdapter?.setListProduct(it)
            productAdapter?.notifyDataSetChanged()
        })
    }

    private fun deleteProduct(productModel: ProductModel){
        productViewModel?.deleteProduct(productModel)
    }

    private fun editProduct(productModel: ProductModel){
        val panelProduct = PanelEditProductFragment()
        val parameters = Bundle()
        parameters.putString("idProduct", productModel.id.toString())
        parameters.putString("categoryProduct", productModel.category)
        parameters.putString("nameProduct", productModel.name)
        parameters.putString("priceProduct", productModel.price)
        panelProduct.arguments = parameters

        panelProduct.show((context as FragmentActivity).supportFragmentManager, "editProduct")
    }

}
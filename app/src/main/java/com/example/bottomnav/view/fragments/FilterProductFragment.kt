package com.example.bottomnav.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomnav.R
import com.example.bottomnav.adapters.ProductAdapter
import com.example.bottomnav.data.MovieBase
import com.example.bottomnav.databinding.FragmentFilterProductBinding
import com.example.bottomnav.databinding.FragmentProductShopBinding
import com.example.bottomnav.models.ProductModel
import com.example.bottomnav.repositories.ProductRepository
import com.example.bottomnav.viewModels.ProductFactory
import com.example.bottomnav.viewModels.ProductViewModel


class FilterProductFragment(private val nameCategory:String) : Fragment() {

    private var binding: FragmentFilterProductBinding? = null
    private var productRepository: ProductRepository? = null
    private var productViewModel: ProductViewModel? = null
    private var productFactory: ProductFactory? = null
    private var productAdapter: ProductAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilterProductBinding.inflate(inflater,container,false)

        val productDao = MovieBase.getInstance((context as FragmentActivity).application).ProductDAO
        productRepository = ProductRepository(productDao)
        productFactory = ProductFactory(productRepository!!)
        productViewModel = ViewModelProvider(this,productFactory!!).get(ProductViewModel::class.java)

        initRecyclerFilterProducts()

        return binding?.root
    }

    private fun initRecyclerFilterProducts(){
        binding?.recyclerFilterProduct?.layoutManager = LinearLayoutManager(context)
        productAdapter = ProductAdapter({productModel: ProductModel -> deleteProduct(productModel)},
            {productModel: ProductModel -> editProduct(productModel)})
        binding?.recyclerFilterProduct?.adapter = productAdapter

        displayFilterProducts()
    }

    private fun displayFilterProducts(){
        productViewModel?.getFilterCategory(nameCategory)?.observe(viewLifecycleOwner, Observer {
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
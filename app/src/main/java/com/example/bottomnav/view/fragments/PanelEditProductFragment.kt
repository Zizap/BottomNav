package com.example.bottomnav.view.fragments

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.bottomnav.R
import com.example.bottomnav.data.MovieBase
import com.example.bottomnav.databinding.FragmentPanelEditProductBinding
import com.example.bottomnav.repositories.ProductRepository
import com.example.bottomnav.viewModels.ProductFactory
import com.example.bottomnav.viewModels.ProductViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class PanelEditProductFragment : BottomSheetDialogFragment(),View.OnKeyListener {

    private var binding: FragmentPanelEditProductBinding? = null
    private var productRepository: ProductRepository? = null
    private var productViewModel: ProductViewModel? = null
    private var productFactory: ProductFactory? = null
    private var idProduct: Int? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPanelEditProductBinding.inflate(inflater,container,false)

        idProduct = arguments?.getString("idProduct")?.toInt()
        binding?.editProductName?.setText(arguments?.getString("nameProduct").toString())
        binding?.editProductCategory?.setText(arguments?.getString("categoryProduct").toString())
        binding?.editProductPrice?.setText(arguments?.getString("priceProduct").toString())

        val productDao = MovieBase.getInstance((context as FragmentActivity).application).ProductDAO
        productRepository = ProductRepository(productDao)
        productFactory = ProductFactory(productRepository!!)
        productViewModel = ViewModelProvider(this,productFactory!!).get(ProductViewModel::class.java)

        binding?.editProductName?.setOnKeyListener(this)
        binding?.editProductCategory?.setOnKeyListener(this)
        binding?.editProductPrice?.setOnKeyListener(this)

        binding?.editProductBtn?.setOnClickListener(View.OnClickListener {
            productViewModel?.startUpdateProduct(idProduct.toString().toInt(),binding?.editProductName?.text.toString(),binding?.editProductCategory?.text.toString(),
                binding?.editProductPrice?.text.toString())
            binding?.editProductName?.setText("")
            binding?.editProductCategory?.setText("")
            binding?.editProductPrice?.setText("")
            dismiss()
            (context as FragmentActivity).supportFragmentManager.beginTransaction().replace(
                R.id.contentFragment, ProductShopFragment()
            ).commit()
        })

        return binding?.root
    }

    override fun onKey(view: View, keyCode: Int, event: KeyEvent): Boolean {
        when(view.id){
            R.id.editProductName -> {
//                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
//                    productViewModel?.startUpdateProduct(binding?.editProductName?.text.toString(),binding?.editProductCategory?.text.toString(),binding?.editProductPrice?.text.toString())
//                    binding?.editProductName?.setText("")
//                    dismiss()
//                    (context as FragmentActivity).supportFragmentManager.beginTransaction().replace(
//                        R.id.contentFragment,ShopFragment()).commit()
//                    return true
//                }
            }
            R.id.editProductCategory -> {
//                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
//                    productViewModel?.startUpdateProduct(binding?.editProductName?.text.toString(),binding?.editProductCategory?.text.toString(),binding?.editProductPrice?.text.toString())
//                    binding?.editProductCategory?.setText("")
//                    dismiss()
//                    (context as FragmentActivity).supportFragmentManager.beginTransaction().replace(
//                        R.id.contentFragment,ShopFragment()).commit()
//                    return true
//                }
            }
            R.id.editProductPrice -> {
//                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
//                    productViewModel?.startUpdateProduct(binding?.editProductName?.text.toString(),binding?.editProductCategory?.text.toString(),
//                        binding?.editProductPrice?.text.toString())
//                    binding?.editProductPrice?.setText("")
//                    dismiss()
//                    (context as FragmentActivity).supportFragmentManager.beginTransaction().replace(
//                        R.id.contentFragment,ShopFragment()).commit()
//                    return true
//                }
            }
        }
        return false
    }

}
package com.example.bottomnav.fragments

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.bottomnav.R
import com.example.bottomnav.data.MovieBase
import com.example.bottomnav.databinding.FragmentAdminBinding
import com.example.bottomnav.repositories.CategoryRepository
import com.example.bottomnav.repositories.ProductRepository
import com.example.bottomnav.viewModels.CategoryFactory
import com.example.bottomnav.viewModels.CategoryViewModel
import com.example.bottomnav.viewModels.ProductFactory
import com.example.bottomnav.viewModels.ProductViewModel
import com.google.android.material.snackbar.Snackbar

class AdminFragment : Fragment(), View.OnClickListener,View.OnKeyListener {

    private var binding: FragmentAdminBinding? = null
    private var categoryRepository: CategoryRepository? = null
    private var categoryViewModel: CategoryViewModel? = null
    private var categoryFactory: CategoryFactory? = null

    private var productRepository: ProductRepository? = null
    private var productViewModel: ProductViewModel? = null
    private var productFactory: ProductFactory? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdminBinding.inflate(inflater,container,false)

        val categoryDao = MovieBase.getInstance(context as FragmentActivity).CategoryDAO
        categoryRepository = CategoryRepository(categoryDao)
        categoryFactory = CategoryFactory(categoryRepository!!)
        categoryViewModel = ViewModelProvider(this,categoryFactory!!).get(CategoryViewModel::class.java)

        val productDao = MovieBase.getInstance(context as FragmentActivity).ProductDAO
        productRepository = ProductRepository(productDao)
        productFactory = ProductFactory(productRepository!!)
        productViewModel = ViewModelProvider(this,productFactory!!).get(ProductViewModel::class.java)


        binding?.enterCategoryName?.setOnKeyListener(this)
        binding?.enterProductName?.setOnKeyListener(this)
        binding?.enterPriceProduct?.setOnKeyListener(this)

        binding?.addProductBtn?.setOnClickListener(this)

        binding?.addNewCategory?.setOnClickListener(this)


        return binding?.root
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.addProduct_btn ->{
                if (binding?.enterProductName?.text?.length != 0 && binding?.enterCategoryName?.text?.length != 0 &&
                    binding?.enterPriceProduct?.text?.length != 0) {
                   // val checkEmpty = productViewModel?.getFilterCategory(binding?.enterCategoryName?.text.toString())?.value
                    if (productViewModel?.exists(binding?.enterCategoryName?.text.toString())!!) {
                        createNewProduct()
                    } else {
                        Snackbar.make(view,R.string.this_category_does_not_exist,Snackbar.LENGTH_INDEFINITE)
                            .setAction(R.string.create){
                                categoryViewModel?.startInsert(binding?.enterCategoryName?.text.toString())
                                createNewProduct()
                            }
                            .setBackgroundTint(ContextCompat.getColor((context as FragmentActivity),R.color.orange))
                            .setTextColor(ContextCompat.getColor((context as FragmentActivity), R.color.white))
                            .setActionTextColor(ContextCompat.getColor((context as FragmentActivity),R.color.black))
                            .show()
                    }
                }
            }
            R.id.addNewCategory -> {
//                val toast = Toast.makeText((context as FragmentActivity), productViewModel?.exists(binding?.enterCategoryName?.text.toString()).toString(), Toast.LENGTH_SHORT).show()
                if (binding?.enterAddCategoryName?.text?.length != 0) {
                    categoryViewModel?.startInsert(binding?.enterAddCategoryName?.text.toString())
                    binding?.enterAddCategoryName?.setText("")
                }
            }
        }
    }

    private fun createNewProduct(){
        productViewModel?.startInsert(binding?.enterProductName?.text?.toString()!!,
            binding?.enterCategoryName?.text?.toString()!!,binding?.enterPriceProduct?.text?.toString()!!)
        binding?.enterProductName?.setText("")
        binding?.enterCategoryName?.setText("")
        binding?.enterPriceProduct?.setText("")
    }


    override fun onKey(view: View, keyCode: Int, event: KeyEvent): Boolean {
        when(view.id){
            R.id.enter_categoryName -> {
            return true
            }
            R.id.enter_priceProduct -> {
               return true
            }
            R.id.enter_productName -> {

            }
        }
        return false
    }

}
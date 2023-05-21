package com.example.bottomnav.view.fragments

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.example.bottomnav.R
import com.example.bottomnav.databinding.FragmentAdminBinding
import com.example.bottomnav.presentation.Tabs.categories.CategoryViewModel
import com.example.bottomnav.presentation.Tabs.products.ProductViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdminFragment : Fragment(), View.OnClickListener,View.OnKeyListener {

    private var binding: FragmentAdminBinding? = null
    private val categoryViewModel: CategoryViewModel by viewModel()
    private val productViewModel:ProductViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdminBinding.inflate(inflater,container,false)

        binding?.enterCategoryName?.setOnKeyListener(this)
        binding?.enterProductName?.setOnKeyListener(this)
        binding?.enterPriceProduct?.setOnKeyListener(this)

        binding?.addProductBtn?.setOnClickListener(this)

        binding?.addNewCategory?.setOnClickListener(this)

        return binding?.root
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.addProduct_btn -> {
                if (binding?.enterProductName?.text?.length != 0 && binding?.enterCategoryName?.text?.length != 0 &&
                    binding?.enterPriceProduct?.text?.length != 0) {
                    categoryViewModel?.getFilterCategoryName(binding?.enterCategoryName?.text.toString())?.observe(viewLifecycleOwner,
                        Observer {
                            if (it.isNotEmpty()){
                                createNewProduct()
                                    //val toast = Toast.makeText((context as FragmentActivity), it.toString(), Toast.LENGTH_SHORT).show()
                            } else {

                                val builder = MaterialAlertDialogBuilder((context as FragmentActivity),R.style.dialogTheme)
                                    .setTitle(R.string.attention)
                                    .setMessage(R.string.this_category_does_not_exist)
                                    .setPositiveButton(R.string.create) { dialog, which ->
                                        categoryViewModel?.startInsert(binding?.enterCategoryName?.text.toString())
                                        createNewProduct()
                                    }
                                    .setNegativeButton(R.string.Nocreate) { dialog, which ->
                                        binding?.enterProductName?.setText("")
                                        binding?.enterCategoryName?.setText("")
                                        binding?.enterPriceProduct?.setText("")
                                    }
                                    .show()
                            }
                        })
                }
            }
            R.id.addNewCategory -> {
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
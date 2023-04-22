package com.example.bottomnav.view.fragments

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.bottomnav.R
import com.example.bottomnav.data.MovieBase
import com.example.bottomnav.databinding.FragmentPanelEditCategoryBinding
import com.example.bottomnav.repositories.CategoryRepository
import com.example.bottomnav.viewModels.CategoryFactory
import com.example.bottomnav.viewModels.CategoryViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PanelEditCategoryFragment : BottomSheetDialogFragment(),View.OnKeyListener {

    private var binding:FragmentPanelEditCategoryBinding? = null
    private var categoryRepository: CategoryRepository? = null
    private var categoryViewModel: CategoryViewModel? = null
    private var categoryFactory: CategoryFactory? = null
    private var idCategory: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPanelEditCategoryBinding.inflate(inflater,container,false)

        idCategory = arguments?.getString("idCategory")?.toInt()
        binding?.editCategoryName?.setText(arguments?.getString("nameCategory").toString())

        val categoryDao = MovieBase.getInstance((context as FragmentActivity).application).CategoryDAO
        categoryRepository = CategoryRepository(categoryDao)
        categoryFactory = CategoryFactory(categoryRepository!!)
        categoryViewModel = ViewModelProvider(this,categoryFactory!!).get(CategoryViewModel::class.java)

        binding?.editCategoryName?.setOnKeyListener(this)

        return binding?.root
    }

    override fun onKey(view: View, keyCode: Int, event: KeyEvent): Boolean {
        when(view.id){
            R.id.editCategoryName -> {
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    categoryViewModel?.startUpdateCategory(idCategory.toString().toInt(), binding?.editCategoryName?.text.toString())
                    binding?.editCategoryName?.setText("")
                    dismiss()
                    (context as FragmentActivity).supportFragmentManager.beginTransaction().replace(
                        R.id.contentFragment, ShopFragment()
                    ).commit()
                    return true
                }
            }
        }
        return false
    }


}
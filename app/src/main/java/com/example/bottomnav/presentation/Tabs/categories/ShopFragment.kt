package com.example.bottomnav.presentation.Tabs.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomnav.R
import com.example.bottomnav.adapters.CategoryAdapter
import com.example.bottomnav.databinding.FragmentShopBinding
import com.example.bottomnav.data.models.CategoryModel
import com.example.bottomnav.presentation.Tabs.products.FilterProductFragment
import com.example.bottomnav.presentation.Tabs.products.ProductShopFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class ShopFragment : Fragment() {

    private var binding: FragmentShopBinding? = null
    private val categoryViewModel: CategoryViewModel by viewModel()
    private var categoryAdapter: CategoryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopBinding.inflate(inflater,container,false)

        initRecyclerCategories()

        binding?.deleteAllCategoriesBtn?.setOnClickListener(View.OnClickListener {
            categoryViewModel?.deleteAllCategories()
        })
        binding?.productCheckBtn?.setOnClickListener(View.OnClickListener {
            (context as FragmentActivity).supportFragmentManager.beginTransaction().replace(
                R.id.contentFragment, ProductShopFragment()
            ).commit()
        })
//        binding?.productFilterCheckBtn?.setOnClickListener(View.OnClickListener {
//            (context as FragmentActivity).supportFragmentManager.beginTransaction().replace(
//                R.id.contentFragment, FilterProductFragment()).commit()
//        })

        return binding?.root
    }

    private fun initRecyclerCategories(){
        binding?.recyclerCategories?.layoutManager = LinearLayoutManager(context)
        categoryAdapter = CategoryAdapter({categoryModel: CategoryModel -> deleteCategory(categoryModel)},
            {categoryModel: CategoryModel -> editCategory(categoryModel)}, { nameCategory: String -> openProductFromCategory(nameCategory)})
        binding?.recyclerCategories?.adapter = categoryAdapter

        displayCategories()
    }

    private fun displayCategories(){
        categoryViewModel?.loadCategory()?.observe(viewLifecycleOwner, Observer {
            categoryAdapter?.setList(it)
            categoryAdapter?.notifyDataSetChanged()
        })
    }

    private fun deleteCategory(categoryModel: CategoryModel){
        categoryViewModel?.deleteCategory(categoryModel)
    }

    private fun editCategory(categoryModel: CategoryModel){
        val panelCategory = PanelEditCategoryFragment()
        val parameters = Bundle()
        parameters.putString("idCategory", categoryModel.id.toString())
        parameters.putString("nameCategory", categoryModel.name)
        panelCategory.arguments = parameters

        panelCategory.show((context as FragmentActivity).supportFragmentManager, "editCategory")
    }

    private fun openProductFromCategory(nameCategory:String){
        (context as FragmentActivity).supportFragmentManager.beginTransaction().replace(
            R.id.contentFragment, FilterProductFragment(nameCategory)
        ).commit()
    }

}
package com.example.bottomnav.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnav.databinding.CategoriesRecyclerItemBinding
import com.example.bottomnav.models.CategoryModel


class CategoryAdapter(private val deleteCategory:(CategoryModel)->Unit,
                      private val editCategory:(CategoryModel)->Unit,
                      private val openProductFromCategory:(nameCategory:String)->Unit):RecyclerView.Adapter<CategoryHolder>() {

    private var categoryList = ArrayList<CategoryModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val binding: CategoriesRecyclerItemBinding = CategoriesRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return CategoryHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(categoryList[position],deleteCategory,editCategory,openProductFromCategory)

    }

    fun setList(categories: List<CategoryModel>){
        categoryList.clear()
        categoryList.addAll(categories)
    }
}

class CategoryHolder(val binding: CategoriesRecyclerItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(categories: CategoryModel,
             deleteCategory: (CategoryModel) -> Unit,
             editCategory: (CategoryModel) -> Unit,
    openProductFromCategory: (nameCategory: String) -> Unit){

        binding.nameCategory.text = categories.name
        binding.materialCardView.setOnClickListener (View.OnClickListener {
            openProductFromCategory(categories.name)
        })
        binding.deleteCategoryBtn.setOnClickListener(View.OnClickListener {
            deleteCategory(categories)
        })
        binding.editCategoryBtn.setOnClickListener(View.OnClickListener {
            editCategory(categories)
        })
    }
}

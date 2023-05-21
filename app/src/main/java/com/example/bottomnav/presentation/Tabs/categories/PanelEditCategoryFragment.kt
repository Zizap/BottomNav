package com.example.bottomnav.presentation.Tabs.categories

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.example.bottomnav.R
import com.example.bottomnav.databinding.FragmentPanelEditCategoryBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class PanelEditCategoryFragment : BottomSheetDialogFragment(),View.OnKeyListener {

    private var binding:FragmentPanelEditCategoryBinding? = null
    private var idCategory: Int? = null
    private val categoryViewModel: CategoryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPanelEditCategoryBinding.inflate(inflater,container,false)

        idCategory = arguments?.getString("idCategory")?.toInt()
        binding?.editCategoryName?.setText(arguments?.getString("nameCategory").toString())

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
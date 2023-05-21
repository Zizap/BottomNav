package com.example.bottomnav.presentation.Tabs.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.example.bottomnav.R
import com.example.bottomnav.databinding.FragmentPanelEditProductBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class PanelEditProductFragment : BottomSheetDialogFragment() {

    private var binding: FragmentPanelEditProductBinding? = null
    private var idProduct: Int? = null
    private val productViewModel:ProductViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPanelEditProductBinding.inflate(inflater,container,false)

        idProduct = arguments?.getString("idProduct")?.toInt()
        binding?.editProductName?.setText(arguments?.getString("nameProduct").toString())
        binding?.editProductCategory?.setText(arguments?.getString("categoryProduct").toString())
        binding?.editProductPrice?.setText(arguments?.getString("priceProduct").toString())


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



}
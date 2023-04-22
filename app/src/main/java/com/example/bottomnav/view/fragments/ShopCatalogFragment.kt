package com.example.bottomnav.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.example.bottomnav.R
import com.example.bottomnav.databinding.FragmentShopCatalogBinding


class ShopCatalogFragment : Fragment(),View.OnClickListener {

    private var binding: FragmentShopCatalogBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopCatalogBinding.inflate(inflater,container,false)

        binding?.MovieButt?.setOnClickListener(this)
        binding?.productButt?.setOnClickListener(this)

        return binding?.root
    }

    override fun onClick(view: View) {
        when (view.id){
            R.id.MovieButt -> {
                (context as FragmentActivity).supportFragmentManager.beginTransaction().replace(
                    R.id.contentFragment, CatalogFragment()
                ).commit()
            }

            R.id.productButt -> {
                (context as FragmentActivity).supportFragmentManager.beginTransaction().replace(
                    R.id.contentFragment, ShopFragment()
                ).commit()
            }
        }
    }

}
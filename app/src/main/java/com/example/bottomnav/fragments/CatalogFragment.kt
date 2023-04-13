package com.example.bottomnav.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.example.bottomnav.adapters.MoviesItemAdapter
import com.example.bottomnav.R
import com.example.bottomnav.databinding.FragmentCatalogBinding
import com.google.android.material.tabs.TabLayoutMediator


class CatalogFragment : Fragment() {

    private var binding: FragmentCatalogBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatalogBinding.inflate(inflater,container,false)


        binding?.shopButton?.setOnClickListener {
            (context as FragmentActivity).supportFragmentManager.beginTransaction().replace(
                R.id.contentFragment, ShopFragment()).commit()
        }

        binding?.slider?.adapter = MoviesItemAdapter(context as FragmentActivity)

        // Тут все сделано по уроку

        val tabLayoutMediator = binding?.tabSlider?.let {
            binding?.slider?.let {
                it1 -> TabLayoutMediator(
                it,
                it1,
            TabLayoutMediator.TabConfigurationStrategy{tab, position ->
                when(position){
                    0 -> {
                        tab.setIcon(R.drawable.dragon)
                        tab.text = getString(R.string.fantasy)
                    }
                    1 -> {
                        tab.setIcon(R.drawable.startup)
                        tab.text = getString(R.string.fantastic)
                    }
                    2 -> {
                        tab.setIcon(R.drawable.ghost)
                        tab.text = getString(R.string.horror)
                    }
                    3 -> {
                        tab.setIcon(R.drawable.mask)
                        tab.text = getString(R.string.comedy)
                    }
                }
            })
            }
        }

        tabLayoutMediator?.attach()

        return binding?.root



    }




}
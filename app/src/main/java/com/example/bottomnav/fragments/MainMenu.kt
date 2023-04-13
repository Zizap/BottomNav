package com.example.bottomnav.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.example.bottomnav.R
import com.example.bottomnav.databinding.FragmentMainMenuBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class MainMenu : BottomSheetDialogFragment() {

    private var binding: FragmentMainMenuBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainMenuBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment

        binding?.mainMenuNav?.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.homeMenu -> {
                    (context as FragmentActivity).supportFragmentManager.beginTransaction().replace(
                        R.id.contentFragment, HomeFragment()).commit()
                    dismiss()
                }
                R.id.catalogMenu -> {
                    (context as FragmentActivity).supportFragmentManager.beginTransaction().replace(
                        R.id.contentFragment, CatalogFragment()).commit()
                    dismiss()
                }
                R.id.favoritesMenu -> {
                    (context as FragmentActivity).supportFragmentManager.beginTransaction().replace(
                        R.id.contentFragment, FavoriteFragment()).commit()
                    dismiss()
                }
                R.id.accountMenu -> {

                }
                R.id.homeMenu -> {

                }
                R.id.videoMenu -> {

                }
                R.id.lovationMenu -> {

                }
                R.id.settingMenu -> {

                }
                R.id.adminSetting -> {
                    (context as FragmentActivity).supportFragmentManager.beginTransaction().replace(
                        R.id.contentFragment, AdminFragment()
                    ).commit()
                    dismiss()
                }
            }

            true
        }


        return binding?.root
    }


}
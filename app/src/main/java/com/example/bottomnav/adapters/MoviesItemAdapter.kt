package com.example.bottomnav.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bottomnav.fragments.MovieSlider

class MoviesItemAdapter(fragmentActivity:FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> MovieSlider(0)
            1 -> MovieSlider(1)
            2 -> MovieSlider(2)
            3 -> MovieSlider(3)
            else -> MovieSlider(0)

        }
    }
}
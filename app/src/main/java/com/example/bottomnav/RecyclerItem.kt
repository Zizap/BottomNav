package com.example.bottomnav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bottomnav.databinding.FragmentRecyclerItemBinding



class RecyclerItem : Fragment() {

    private var binding: FragmentRecyclerItemBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerItemBinding.inflate(inflater,container,false)
        return binding?.root
    }


}
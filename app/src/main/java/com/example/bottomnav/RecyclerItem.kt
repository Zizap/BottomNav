package com.example.bottomnav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bottomnav.databinding.FragmentResyclerItemBinding


class RecyclerItem : Fragment() {

    private var binding: FragmentResyclerItemBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResyclerItemBinding.inflate(inflater,container,false)
        return binding?.root
    }


}
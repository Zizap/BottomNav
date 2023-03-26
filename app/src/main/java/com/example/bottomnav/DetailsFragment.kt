package com.example.bottomnav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bottomnav.databinding.FragmentCatalogBinding
import com.example.bottomnav.databinding.FragmentDetailsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class DetailsFragment : BottomSheetDialogFragment() {

    private var binding: FragmentDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater,container,false)

        binding?.cartNameDetails?.text = arguments?.getString("nameMovie").toString()
        binding?.cartTimeDetails?.text = arguments?.getString("timeMovie").toString()

        when (binding?.cartNameDetails?.text) {
            getString(R.string.tlotr) -> binding?.imgMovieDetails?.setImageResource(R.drawable.cart1)
            getString(R.string.swep2) -> binding?.imgMovieDetails?.setImageResource(R.drawable.cart3)
        }
        return binding?.root
    }


}
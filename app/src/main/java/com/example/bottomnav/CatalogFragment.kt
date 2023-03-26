package com.example.bottomnav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bottomnav.databinding.FragmentCatalogBinding


class CatalogFragment : Fragment() {

    private var binding: FragmentCatalogBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatalogBinding.inflate(inflater,container,false)

        binding?.MovieButt1?.setOnClickListener { openDetailsFragment(it) }
        binding?.MovieButt2?.setOnClickListener { openDetailsFragment(it) }

        return binding?.root



    }

  private fun openDetailsFragment(item:View){
      val details = DetailsFragment()
      val parameters = Bundle()

      when(item.id) {
          R.id.MovieButt1 -> {
              parameters.putString("nameMovie", binding?.cartName1?.text?.toString())
              parameters.putString("timeMovie", binding?.cartTime1?.text?.toString())
              details.arguments = parameters
              details.show(childFragmentManager,"details")}
          R.id.MovieButt2 -> {
              parameters.putString("nameMovie", binding?.cartName2?.text?.toString())
              parameters.putString("timeMovie", binding?.cartTime2?.text?.toString())
              details.arguments = parameters
              details.show(childFragmentManager,"details")}
      }
  }


}
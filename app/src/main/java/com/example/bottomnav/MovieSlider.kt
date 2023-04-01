package com.example.bottomnav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomnav.databinding.FragmentMainMenuBinding
import com.example.bottomnav.databinding.FragmentMovieRecyclerBinding
import java.text.FieldPosition


class MovieSlider(val position: Int) : Fragment() {

    private var binding: FragmentMovieRecyclerBinding? = null
    private var movieAdapter: MovieAdapter? = null
    private var movies: ArrayList<MovieModel>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieRecyclerBinding.inflate(inflater,container,false)

        // если позиция таба 0, то грузим фентези, если позиция 1, то фантастику и тд

        if (position == 0){
            movies = ArrayList<MovieModel>()
            movies?.add(MovieModel(R.drawable.cart1,getString(R.string.tlotr),getString(R.string.tlotrTime)))
            movies?.add(MovieModel(R.drawable.harry,getString(R.string.harry_potter),getString(R.string.harry_potter_time)))
            movies?.add(MovieModel(R.drawable.pirates,getString(R.string.pirates),getString(R.string.pirates_time)))
            movies?.add(MovieModel(R.drawable.constantine,getString(R.string.constantine),getString(R.string.constantine_time)))

            binding?.recyclerMovie?.layoutManager = LinearLayoutManager(context as FragmentActivity)
            movieAdapter = MovieAdapter({movieModel:MovieModel->openDetailsFragment(movieModel)},movies!!)
            binding?.recyclerMovie?.adapter = movieAdapter
        }
        else if(position == 1) {
            movies = ArrayList<MovieModel>()
            movies?.add(MovieModel(R.drawable.cart3,getString(R.string.swep2),getString(R.string.swep2Time)))

            binding?.recyclerMovie?.layoutManager = LinearLayoutManager(context as FragmentActivity)
            movieAdapter = MovieAdapter({movieModel:MovieModel->openDetailsFragment(movieModel)},movies!!)
            binding?.recyclerMovie?.adapter = movieAdapter
        }
        else if(position == 2) {
            movies = ArrayList<MovieModel>()
            movies?.add(MovieModel(R.drawable.saw,getString(R.string.saw),getString(R.string.sawTime)))

            binding?.recyclerMovie?.layoutManager = LinearLayoutManager(context as FragmentActivity)
            movieAdapter = MovieAdapter({movieModel:MovieModel->openDetailsFragment(movieModel)},movies!!)
            binding?.recyclerMovie?.adapter = movieAdapter
        }
        else if(position == 3) {
            movies = ArrayList<MovieModel>()
            movies?.add(MovieModel(R.drawable.payne,getString(R.string.major),getString(R.string.majorTime)))

            binding?.recyclerMovie?.layoutManager = LinearLayoutManager(context as FragmentActivity)
            movieAdapter = MovieAdapter({movieModel:MovieModel->openDetailsFragment(movieModel)},movies!!)
            binding?.recyclerMovie?.adapter = movieAdapter
        }



        return binding?.root
    }

    private fun openDetailsFragment(movieModel: MovieModel){
        val details = DetailsFragment()
        val parameters = Bundle()

        parameters.putString("nameMovie", movieModel.nameMovie.toString())
        parameters.putString("timeMovie", movieModel.timeMovie.toString())
        details.arguments = parameters
        details.show(childFragmentManager,"details")


    }

}
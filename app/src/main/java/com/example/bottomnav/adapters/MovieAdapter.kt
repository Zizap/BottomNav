package com.example.bottomnav.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnav.MovieModel
import com.example.bottomnav.databinding.FragmentRecyclerItemBinding

// тут все сделано по уроку

class MovieAdapter(private val openDetailsFragment:(MovieModel)->Unit, var movies: ArrayList<MovieModel>): RecyclerView.Adapter<MovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {

        val binding: FragmentRecyclerItemBinding = FragmentRecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(movies[position],openDetailsFragment)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}

class MovieHolder(val binding: FragmentRecyclerItemBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(movieModel: MovieModel, openDetailsFragment:(MovieModel)->Unit){
        binding.cartName1.text = movieModel.nameMovie.toString()
        binding.cartTime1.text = movieModel.timeMovie.toString()
        binding.imgCart.setBackgroundResource(movieModel.banerMovie)

        binding.MovieButt1.setOnClickListener(View.OnClickListener {
            openDetailsFragment(movieModel)
        })
    }

}
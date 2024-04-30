package com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.second.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.anangkur.synrgychapter3.databinding.ItemMovieBinding
import com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.second.data.Movie
import com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.second.viewholder.MovieViewHolder

class MovieAdapter(
    private val movieAdapterListener: MovieAdapterListener,
) : ListAdapter<Movie, MovieViewHolder>(MovieDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            itemViewBinding = ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
            movieAdapterListener = movieAdapterListener,
        )
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
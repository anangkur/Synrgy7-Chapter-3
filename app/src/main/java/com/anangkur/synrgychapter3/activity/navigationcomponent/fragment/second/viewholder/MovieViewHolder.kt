package com.anangkur.synrgychapter3.activity.navigationcomponent.fragment.second.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.anangkur.synrgychapter3.activity.navigationcomponent.fragment.second.adapter.MovieAdapterListener
import com.anangkur.synrgychapter3.activity.navigationcomponent.fragment.second.data.Movie
import com.anangkur.synrgychapter3.databinding.ItemMovieBinding

class MovieViewHolder(
    private val itemViewBinding: ItemMovieBinding,
    private val movieAdapterListener: MovieAdapterListener,
) : RecyclerView.ViewHolder(itemViewBinding.root) {
    fun bind(data: Movie) {
        itemViewBinding.textTitle.text = data.title
        itemViewBinding.imagePoster.load(data.image)
        itemViewBinding.textDescription.text = data.description
        itemViewBinding.root.setOnClickListener { movieAdapterListener.onClickMovie(data) }
    }
}
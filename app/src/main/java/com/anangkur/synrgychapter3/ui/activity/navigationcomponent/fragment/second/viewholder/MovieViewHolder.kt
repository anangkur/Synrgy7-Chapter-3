package com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.second.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.anangkur.synrgychapter3.databinding.ItemMovieBinding
import com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.second.adapter.MovieAdapterListener
import com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.second.data.Movie

class MovieViewHolder(
    private val itemViewBinding: ItemMovieBinding,
    private val movieAdapterListener: MovieAdapterListener,
) : RecyclerView.ViewHolder(itemViewBinding.root) {
    /**
     * Binds movie data to the item view.
     *
     * This function binds the provided movie data to the item view by setting the title,
     * image, and description of the movie. Additionally, it sets a click listener on the
     * root view of the item, which invokes the onClickMovie function of the movie adapter listener
     * when the item is clicked, passing the associated movie data as an argument.
     *
     * @param data The Movie object containing the movie data to be displayed.
     */
    fun bind(data: Movie) {
        // Set the title of the movie to the text view in the item view
        itemViewBinding.textTitle.text = data.title
        // Load the image of the movie into the image view in the item view
        itemViewBinding.imagePoster.load(data.image)
        // Set the description of the movie to the text view in the item view
        itemViewBinding.textDescription.text = data.description
        // Set a click listener on the root view of the item, which invokes the onClickMovie function
        // of the movie adapter listener when the item is clicked, passing the associated movie data
        // as an argument
        itemViewBinding.root.setOnClickListener { movieAdapterListener.onClickMovie(data) }
    }

}
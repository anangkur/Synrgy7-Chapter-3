package com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.second.adapter

import androidx.recyclerview.widget.DiffUtil
import com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.second.data.Movie

class MovieDiffUtil : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.title == newItem.title
    }
    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.title == newItem.title
    }
}
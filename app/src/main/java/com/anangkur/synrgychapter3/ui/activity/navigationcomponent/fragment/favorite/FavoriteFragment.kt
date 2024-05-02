package com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.anangkur.synrgychapter3.databinding.FragmentSecondNavigationBinding
import com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.second.adapter.MovieAdapter
import com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.second.adapter.MovieAdapterListener
import com.anangkur.synrgychapter3.ui.dataclass.Movie

class FavoriteFragment : Fragment(), MovieAdapterListener {

    private val viewModel: FavoriteViewModel by viewModels {
        FavoriteViewModel.provideFactory(this, requireActivity().applicationContext)
    }

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter(this)
    }

    private lateinit var binding: FragmentSecondNavigationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSecondNavigationBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = movieAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.itemAnimator = DefaultItemAnimator()

        viewModel.movies.observe(viewLifecycleOwner) { movie ->
            movieAdapter.submitList(movie)
        }
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
        viewModel.error.observe(viewLifecycleOwner) { throwable ->
            Toast.makeText(context, throwable.message, Toast.LENGTH_SHORT).show()
        }

        viewModel.getMovieFromLocal()
    }

    override fun onClickMovie(data: Movie) {
        goToThirdFragment(data)
    }

    private fun goToThirdFragment(data: Movie) {
        val actionToThirdFragment = FavoriteFragmentDirections.actionFavoriteFragmentToThirdNavigationFragment()
        actionToThirdFragment.title = data.title
        actionToThirdFragment.description = data.description
        actionToThirdFragment.image = data.image
        actionToThirdFragment.id = data.id ?: -1
        findNavController().navigate(actionToThirdFragment)
    }
}
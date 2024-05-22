package com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.second

import android.content.Context
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
import androidx.recyclerview.widget.RecyclerView
import com.anangkur.synrgychapter3.MyApplication
import com.anangkur.synrgychapter3.databinding.FragmentSecondNavigationBinding
import com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.second.adapter.MovieAdapter
import com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.second.adapter.MovieAdapterListener
import com.anangkur.synrgychapter3.domain.model.Movie
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondNavigationFragment : Fragment(), MovieAdapterListener {

    private lateinit var binding: FragmentSecondNavigationBinding
    private val movieAdapter = MovieAdapter(this)

    private val secondNavigationViewModel by viewModel<SecondNavigationViewModel>()

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

        // binding.buttonGoToThird.setOnClickListener { validateName() }

        setupRecyclerView(view.context)

        refreshData()
        binding.swipeRefresh.setOnRefreshListener { refreshData() }

        secondNavigationViewModel.movies.observe(viewLifecycleOwner) { movies ->
            // Submit a new list of movies to the movie adapter
            movieAdapter.submitList(movies)
            binding.swipeRefresh.isRefreshing = false
        }
        secondNavigationViewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
        secondNavigationViewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }

    /**
     * Refreshes the data displayed in the movie adapter.
     *
     * This function updates the list of movies displayed in the movie adapter by submitting
     * a new list retrieved from the `retrieveMovieData()` function. It then disables the
     * swipe-to-refresh indicator by setting `isRefreshing` to false on the binding's swipeRefresh view.
     */
    private fun refreshData() {
        // Disable the swipe-to-refresh indicator
        secondNavigationViewModel.retrieveMovieData()
    }


    /**
     * Sets up the RecyclerView to display movies.
     *
     * This function configures the RecyclerView with the necessary settings to display movies.
     * It sets the movieAdapter as the adapter for the RecyclerView, configures the layout manager
     * to display items vertically, and sets the item animator for view animations.
     */
    private fun setupRecyclerView(context: Context) {
        // Set the movieAdapter as the adapter for the RecyclerView
        binding.recyclerView.adapter = movieAdapter

        // Configure the layout manager to display items vertically
        binding.recyclerView.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false
        )

        // Set the item animator for view animations
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
    }


    /**
     * Navigates from the second fragment to the third fragment, passing data.
     *
     * This function initiates a navigation action from the second fragment to the third fragment
     * within the navigation graph. It constructs a navigation action using the generated NavDirections
     * class (SecondNavigationFragmentDirections) and sets the data to be passed to the third fragment.
     * The data, in this case, is obtained from the EditText component (etName) of the current fragment's binding.
     * Finally, it uses the NavController associated with the current fragment to perform the navigation
     * with the constructed action.
     */
    private fun goToThirdFragment(data: Movie) {
        val actionToThirdFragment = SecondNavigationFragmentDirections.actionSecondNavigationFragmentToThirdNavigationFragment()
        actionToThirdFragment.title = data.title
        actionToThirdFragment.description = data.description
        actionToThirdFragment.image = data.image
        findNavController().navigate(actionToThirdFragment)
    }

    /**
     * Validates the input name field and proceeds accordingly.
     *
     * This function checks whether the name input field is empty or not.
     * If the name field is empty, it sets an error message indicating that the field cannot be empty.
     * Otherwise, if the name field is not empty, it clears any existing error message
     * and proceeds to navigate to the third fragment.
     */
    private fun validateName() {
//        if (binding.etName.text.isNullOrEmpty()) {
//            setErrorName("tidak boleh kosong")
//        } else {
//            setErrorName(null)
//            goToThirdFragment()
//        }
    }

    /**
     * Sets an error message for the name input field.
     *
     * This function enables or disables the error state for the name input field based on
     * whether the provided error message is null or empty. If the error message is not null
     * or not empty, it enables the error state and sets the error message to be displayed.
     * Otherwise, if the error message is null or empty, it disables the error state.
     *
     * @param error The error message to be displayed for the name input field. It can be null or empty
     *              to clear any existing error message and disable the error state.
     */
    private fun setErrorName(error: String?) {
//        binding.tilName.isErrorEnabled = !error.isNullOrEmpty()
//        binding.tilName.error = error
    }

    /**
     * Handles the click event on a movie item.
     *
     * This function is invoked when a movie item is clicked. It navigates to the third fragment,
     * passing the clicked movie data as an argument to the destination.
     *
     * @param data The Movie object representing the clicked movie item.
     */
    override fun onClickMovie(data: Movie) {
        // Navigate to the third fragment, passing the clicked movie data as an argument
        goToThirdFragment(data)
    }
}
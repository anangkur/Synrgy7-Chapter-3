package com.anangkur.synrgychapter3.activity.navigationcomponent.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anangkur.synrgychapter3.databinding.FragmentSecondNavigationBinding
import com.google.android.material.snackbar.Snackbar

class SecondNavigationFragment : Fragment(), MovieAdapterListener {

    private lateinit var binding: FragmentSecondNavigationBinding
    private val movieAdapter = MovieAdapter(this)

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

        binding.recyclerView.adapter = movieAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(
            view.context,
            RecyclerView.VERTICAL,
            false,
        )
        binding.recyclerView.itemAnimator = DefaultItemAnimator()

        movieAdapter.submitList(retrieveMovieData())
        binding.swipeRefresh.setOnRefreshListener {
            movieAdapter.submitList(retrieveMovieData())
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun retrieveMovieData(): List<Movie> {
        return listOf(
            Movie(
                image = "https://media.themoviedb.org/t/p/w440_and_h660_face/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg",
                title = "Dune: Part Two",
                description = "Follow the mythic journey of Paul Atreides as he unites with Chani and the Fremen while on a path of revenge against the conspirators who destroyed his family. Facing a choice between the love of his life and the fate of the known universe, Paul endeavors to prevent a terrible future only he can foresee.",
            ),
            Movie(
                image = "https://media.themoviedb.org/t/p/w440_and_h660_face/AnsSKR9LuK0T9bAOcPVA3PUvyWj.jpg",
                title = "Fallout",
                description = "",
            ),
            Movie(
                image = "https://media.themoviedb.org/t/p/w440_and_h660_face/fdZpvODTX5wwkD0ikZNaClE4AoW.jpg",
                title = "Immaculate",
                description = ""
            ),
            Movie(
                image = "https://media.themoviedb.org/t/p/w440_and_h660_face/d5NXSklXo0qyIYkgV94XAgMIckC.jpg",
                title = "Dune",
                description = "Paul Atreides, a brilliant and gifted young man born into a great destiny beyond his understanding, must travel to the most dangerous planet in the universe to ensure the future of his family and his people. As malevolent forces explode into conflict over the planet's exclusive supply of the most precious resource in existence-a commodity capable of unlocking humanity's greatest potential-only those who can conquer their fear will survive.",
            ),
            Movie(
                image = "https://media.themoviedb.org/t/p/w440_and_h660_face/7O4iVfOMQmdCSxhOg1WnzG1AgYT.jpg",
                title = "Shōgun",
                description = "",
            ),
            Movie(
                image = "https://media.themoviedb.org/t/p/w440_and_h660_face/sh7Rg8Er3tFcN9BpKIPOMvALgZd.jpg",
                title = "Civil War",
                description = "",
            ),
            Movie(
                image = "https://media.themoviedb.org/t/p/w440_and_h660_face/zAxObqiOEooIuQtH338b8zOaFEu.jpg",
                title = "The Sympathizer",
                description = "",
            ),
            Movie(
                image = "https://media.themoviedb.org/t/p/w440_and_h660_face/v9sk7CPhDXJKSkQIegVSBQ5nJnV.jpg",
                title = "Kung Fu Panda 4",
                description = "Po is gearing up to become the spiritual leader of his Valley of Peace, but also needs someone to take his place as Dragon Warrior. As such, he will train a new kung fu practitioner for the spot and will encounter a villain called the Chameleon who conjures villains from the past. movie HD QUALITY, open this link leakedcinema.com",
            ),
            Movie(
                image = "https://media.themoviedb.org/t/p/w440_and_h660_face/6faYaQyiBPhqAizldJKq21mIVaE.jpg",
                title = "Ghostbusters: Frozen Empire",
                description = "",
            ),
            Movie(
                image = "https://media.themoviedb.org/t/p/w440_and_h660_face/8BYT4D0E0f1qFb9WfJPH4YUirL.jpg",
                title = "Woody Woodpecker Goes to Camp",
                description = "",
            )
        )
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

    override fun onClickMovie(data: Movie) {
        goToThirdFragment(data)
    }
}
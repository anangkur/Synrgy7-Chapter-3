package com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.third

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.anangkur.synrgychapter3.MyApplication
import com.anangkur.synrgychapter3.databinding.FragmentThirdNavigationBinding
import com.anangkur.synrgychapter3.di.factory.ViewModelFactory
import org.koin.androidx.viewmodel.ext.android.viewModel

class ThirdNavigationFragment : Fragment() {

    private lateinit var binding: FragmentThirdNavigationBinding

    private val logic by viewModel<ThirdNavigationLogic>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentThirdNavigationBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logic.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
        }

        logic.movieLocal.observe(viewLifecycleOwner) { movie ->
            if (movie != null) {
                binding.buttonFavorite.text = "Hapus dari Favorit"
                binding.buttonFavorite.setOnClickListener { logic.deleteMovieFromFavorite(movie) }
            } else {
                binding.buttonFavorite.text = "Tambahkan ke Favorit"
                binding.buttonFavorite.setOnClickListener {
                    logic.saveMovieToFavorite(
                        name = getName(),
                        image = getImage(),
                        description = getDescription(),
                    )
                }
            }
        }

        logic.insertMovie.observe(viewLifecycleOwner) {
            Toast.makeText(context, "Berhasil ditambahkan ke favorit", Toast.LENGTH_SHORT).show()
            binding.buttonFavorite.text = "Hapus dari Favorit"
        }

        logic.deleteMovie.observe(viewLifecycleOwner) {
            Toast.makeText(context, "Berhasil dihapus dari favorit", Toast.LENGTH_SHORT).show()
            binding.buttonFavorite.text = "Tambahkan ke Favorit"
        }

        binding.tvName.text = getName()
        binding.textDescription.text = getDescription()
        binding.imagePoster.load(getImage())

        logic.title = getName()

        binding.buttonGoogle.setOnClickListener { searchMovie(getName()) }
        logic.loadMovieFromFavorite(getArgId())
    }

    /**
     * Retrieves the name argument passed to the third fragment.
     *
     * This function extracts and returns the name argument passed to the third fragment.
     * It retrieves the argument using the generated NavArgs class (ThirdNavigationFragmentArgs)
     * and the fromBundle method from the fragment's arguments Bundle.
     *
     * @return The name argument passed to the third fragment.
     */
    private fun getName(): String {
        return getArgs().title
    }

    private fun getDescription(): String {
        return getArgs().description
    }

    private fun getImage(): String {
        return getArgs().image
    }

    private fun getArgId(): Int {
        return getArgs().id
    }

    private fun getArgs(): ThirdNavigationFragmentArgs {
        return ThirdNavigationFragmentArgs.fromBundle(
            arguments as Bundle
        )
    }

    private fun searchMovie(title: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse(logic.getUrlGoogle(title)))
        startActivity(intent)
    }
}
package com.anangkur.synrgychapter3.activity.navigationcomponent.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.anangkur.synrgychapter3.databinding.FragmentThirdNavigationBinding

class ThirdNavigationFragment : Fragment() {

    private lateinit var binding: FragmentThirdNavigationBinding
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

        binding.tvName.text = getName()
        binding.textDescription.text = getDescription()
        binding.imagePoster.load(getImage())

        binding.buttonGoogle.setOnClickListener { searchMovie(getName()) }
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

    private fun getArgs(): ThirdNavigationFragmentArgs {
        return ThirdNavigationFragmentArgs.fromBundle(arguments as Bundle)
    }

    private fun searchMovie(title: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse("https://www.google.com/search?q=$title"))
        startActivity(intent)
    }
}
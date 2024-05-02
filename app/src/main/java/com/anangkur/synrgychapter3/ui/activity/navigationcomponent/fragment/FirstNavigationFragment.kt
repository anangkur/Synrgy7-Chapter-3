package com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.anangkur.synrgychapter3.R
import com.anangkur.synrgychapter3.databinding.FragmentFirstNavigationBinding

class FirstNavigationFragment : Fragment() {

    private lateinit var binding: FragmentFirstNavigationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentFirstNavigationBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonGoToSecond.setOnClickListener { goToSecondFragment() }
        binding.buttonGoToFavorite.setOnClickListener { goToFavoriteFragment() }
    }

    /**
     * Navigates to the second fragment from the current fragment.
     *
     * This function uses the NavController to navigate from the current fragment to the second fragment.
     * It specifies the action to navigate from the current destination (firstNavigationFragment) to the second destination (secondNavigationFragment).
     */
    private fun goToSecondFragment() {
        findNavController().navigate(R.id.action_firstNavigationFragment_to_secondNavigationFragment)
    }

    private fun goToFavoriteFragment() {
        findNavController().navigate(R.id.action_firstNavigationFragment_to_favoriteFragment)
    }
}
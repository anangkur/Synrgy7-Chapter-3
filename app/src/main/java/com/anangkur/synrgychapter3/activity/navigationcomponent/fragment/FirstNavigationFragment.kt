package com.anangkur.synrgychapter3.activity.navigationcomponent.fragment

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

        binding.buttonGoToSecond.setOnClickListener {
            findNavController().navigate(R.id.action_firstNavigationFragment_to_secondNavigationFragment)
        }
    }
}
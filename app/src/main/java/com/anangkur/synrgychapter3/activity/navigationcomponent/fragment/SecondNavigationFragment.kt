package com.anangkur.synrgychapter3.activity.navigationcomponent.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.anangkur.synrgychapter3.databinding.FragmentSecondNavigationBinding

class SecondNavigationFragment : Fragment() {

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

        binding.buttonGoToThird.setOnClickListener {
            if (binding.etName.text.isNullOrEmpty()) {
                binding.tilName.isErrorEnabled = true
                binding.tilName.error = "tidak boleh kosong"
            } else {
                binding.tilName.isErrorEnabled = false
                binding.tilName.error = null
                val actionToThirdFragment = SecondNavigationFragmentDirections.actionSecondNavigationFragmentToThirdNavigationFragment()
                actionToThirdFragment.name = binding.etName.text.toString()
                findNavController().navigate(actionToThirdFragment)
            }
        }
    }
}
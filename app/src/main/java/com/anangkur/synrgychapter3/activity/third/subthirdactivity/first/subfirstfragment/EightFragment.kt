package com.anangkur.synrgychapter3.activity.third.subthirdactivity.first.subfirstfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.anangkur.synrgychapter3.R
import com.anangkur.synrgychapter3.databinding.FragmentFirstBinding

class EightFragment : Fragment() {

    private lateinit var viewBinding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentFirstBinding.inflate(layoutInflater, container, false).also {
            viewBinding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.text.text = "ini adalah fragment delapan"
        val bundle = Bundle()
        val data = "ini adalah data dari fragment 8"
        bundle.putString("data", data)
        viewBinding.button.setOnClickListener {
            val action = EightFragmentDirections.actionEightFragmentToNinthFragment(data)
            findNavController().navigate(action)
        }
    }
}
package com.anangkur.synrgychapter3.ui.activity.third.subthirdactivity.first.subfirstfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anangkur.synrgychapter3.databinding.FragmentFirstBinding

class NinthFragment : Fragment() {

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

        val data = arguments?.getString("data")
        val dataSafeArgs = NinthFragmentArgs.fromBundle(arguments as Bundle)
        viewBinding.text.text = "ini adalah fragment sembilan | ${dataSafeArgs.data}"
    }
}
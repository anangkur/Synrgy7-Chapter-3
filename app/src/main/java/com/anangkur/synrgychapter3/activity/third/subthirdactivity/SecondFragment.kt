package com.anangkur.synrgychapter3.activity.third.subthirdactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anangkur.synrgychapter3.databinding.FragmentFirstBinding

class SecondFragment : Fragment() {

    companion object {

        private const val ARGS_STRING = "argsString"
        private const val ARGS_INT = "argsInt"

        fun newInstance(string: String, integer: Int): Fragment {
            return SecondFragment().apply {
                val bundle = Bundle()
                bundle.putString(ARGS_STRING, string)
                bundle.putInt(ARGS_INT, integer)
                arguments = bundle
            }
        }
    }

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

        val string = arguments?.getString(ARGS_STRING)
        val int = arguments?.getInt(ARGS_INT)
        viewBinding.text.text = "ini adalah fragment dua dengan data string: $string integer: $int"
    }
}
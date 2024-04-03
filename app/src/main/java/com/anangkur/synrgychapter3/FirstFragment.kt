package com.anangkur.synrgychapter3

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anangkur.synrgychapter3.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private lateinit var viewBinding: FragmentFirstBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("FirstFragment", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("FirstFragment", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentFirstBinding.inflate(inflater, container, false).also {
            viewBinding = it
            Log.d("FirstFragment", "onCreateView")
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("FirstFragment", "onViewCreated")

        viewBinding.text.text = "contoh manipulasi text di fragment satu"
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("FirstFragment", "onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.d("FirstFragment", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("FirstFragment", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("FirstFragment", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("FirstFragment", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("FirstFragment", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("FirstFragment", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("FirstFragment", "onDetach")
    }
}
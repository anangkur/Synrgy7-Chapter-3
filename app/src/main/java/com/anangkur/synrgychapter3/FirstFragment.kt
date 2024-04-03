package com.anangkur.synrgychapter3

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anangkur.synrgychapter3.databinding.FragmentTabBinding
import com.google.android.material.tabs.TabLayoutMediator

class FirstFragment : Fragment() {

    private lateinit var viewBinding: FragmentTabBinding

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
        return FragmentTabBinding.inflate(inflater, container, false).also {
            viewBinding = it
            Log.d("FirstFragment", "onCreateView")
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("FirstFragment", "onViewCreated")

        viewBinding.viewPager.adapter = ViewPagerAdapter(this, provideListFragment())

        TabLayoutMediator(viewBinding.tab, viewBinding.viewPager) { tab, position ->
            tab.text = "tab ${position+1}"
        }.attach()
    }

    private fun provideListFragment(): List<Fragment> {
        return listOf(
            FifthFragment(),
            SixthFragment(),
            SeventhFragment(),
            EightFragment(),
            NinthFragment(),
        )
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
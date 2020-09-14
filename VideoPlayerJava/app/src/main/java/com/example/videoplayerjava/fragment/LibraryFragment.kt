package com.example.videoplayerjava.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.videoplayerjava.R
import me.yokeyword.fragmentation.SupportFragment

class LibraryFragment : SupportFragment() {
    companion object {
        fun newInstance(): LibraryFragment {
            val args = Bundle()
            val fragment = LibraryFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_library, container, false)
    }
}
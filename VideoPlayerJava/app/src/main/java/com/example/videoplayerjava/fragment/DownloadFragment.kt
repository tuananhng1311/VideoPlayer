package com.example.videoplayerjava.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.videoplayerjava.R
import me.yokeyword.fragmentation.SupportFragment

class DownloadFragment : SupportFragment() {
    companion object {
        fun newInstance(): DownloadFragment {
            val args = Bundle()
            val fragment = DownloadFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dowload, container, false)
    }
}
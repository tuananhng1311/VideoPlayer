package com.example.videoplayerjava

import android.annotation.SuppressLint
import android.app.ActionBar
import android.os.Bundle
import com.example.videoplayerjava.fragment.DownloadFragment
import com.example.videoplayerjava.fragment.LibraryFragment
import com.example.videoplayerjava.fragment.SearchFragment
import com.example.videoplayerjava.fragment.TrendingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.SupportFragment

class MainActivity : SupportActivity() {
    companion object {
        const val FIRST = 0
        const val SECOND = 1
        const val THIRD = 2
        const val FOURTH = 3
    }

    val mFragments = arrayOfNulls<SupportFragment>(4)
    var selectedIndex = 0


    //    private TrendingFragment trendingFragment;
    //    private SupportFragment[] mFragments = new SupportFragment[4];
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.apply {
            setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
            setCustomView(R.layout.custom_toolbar)

        }
        val trendingFragment: SupportFragment? = findFragment(TrendingFragment::class.java)
        if (trendingFragment == null) {
            mFragments[FIRST] = TrendingFragment.newInstance()
            mFragments[SECOND] = SearchFragment.newInstance()
            mFragments[THIRD] = DownloadFragment.newInstance()
            mFragments[FOURTH] = LibraryFragment.newInstance()

            loadMultipleRootFragment(R.id.fl_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH])
        } else {
            mFragments[FIRST] = trendingFragment
            mFragments[SECOND] = findFragment(SearchFragment::class.java)
            mFragments[THIRD] = findFragment(DownloadFragment::class.java)
            mFragments[FOURTH] = findFragment(LibraryFragment::class.java)
        }
        initView()
    }

    private fun initView() {
        bottom_bar_tab.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.fragment_trendings -> {
                    showHideFragment(mFragments[0], mFragments[selectedIndex])
                    selectedIndex = 0
                }
                R.id.fragment_searchs -> {
                    showHideFragment(mFragments[1], mFragments[selectedIndex])
                    selectedIndex = 1
                }
                R.id.fragment_downloads -> {
                    showHideFragment(mFragments[2], mFragments[selectedIndex])
                    selectedIndex = 2
                }
                R.id.fragment_librarys -> {
                    showHideFragment(mFragments[3], mFragments[selectedIndex])
                    selectedIndex = 3
                }
            }
            return@setOnNavigationItemSelectedListener true
        }

    }
}
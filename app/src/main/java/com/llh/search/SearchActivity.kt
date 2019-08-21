package com.llh.search

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import scut.carson_ho.searchview.SearchView

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        initFragment()
    }

    private fun initFragment() {
        addFragment(FragmentSearchPage())
    }

    fun addFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_search, fragment)
        transaction.commit()
    }
    fun switchFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_search, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}

package com.llh.search

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var btnSearchView: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSearchView=btn_search
        btnSearchView.setOnClickListener {
            //切换碎片

            startActivity(Intent(this@MainActivity,SearchActivity::class.java))
        }
    }
}

package com.llh.search

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_search_page.*
import kotlinx.android.synthetic.main.fragment_search_page.view.*
import kotlinx.android.synthetic.main.fragment_search_result.view.*

/**
 * 项目名:    Search
 * 包名:      com.llh.search
 * 文件名:    FragmentSearchPage
 * 创建者:    LLH
 * 创建时间:  2019/8/21 10:18
 * 描述:      TODO
 */
class FragmentSearchResult: Fragment() {
    lateinit var adapter: BookAdapter
    lateinit var mList: List<BookListData>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search_result, container, false)
        initFragment(view)
        return view
    }

    private fun initFragment(view: View?) {
        //创建布局管理器
        val layoutManager = LinearLayoutManager(activity as SearchActivity)
        //设置布局管理

        view!!.rv_result.layoutManager = layoutManager
        //初始化链表
        initList()
        //创建适配器
        adapter = BookAdapter((activity as SearchActivity), mList)
        //设置适配器
        view!!.rv_result.setAdapter(adapter)
    }

    private fun initList() {
        mList = FragmentSearchPage.mList
    }
}
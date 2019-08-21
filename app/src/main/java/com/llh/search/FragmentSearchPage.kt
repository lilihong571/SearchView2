package com.llh.search

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kymjs.rxvolley.RxVolley
import com.kymjs.rxvolley.client.HttpCallback
import com.kymjs.rxvolley.http.VolleyError
import kotlinx.android.synthetic.main.fragment_search_page.view.*
import org.json.JSONException
import org.json.JSONObject
import scut.carson_ho.searchview.ICallBack
import scut.carson_ho.searchview.SearchView
import scut.carson_ho.searchview.bCallBack
import java.io.Serializable
import java.util.ArrayList

/**
 * 项目名:    Search
 * 包名:      com.llh.search
 * 文件名:    FragmentSearchPage
 * 创建者:    LLH
 * 创建时间:  2019/8/21 10:18
 * 描述:      TODO
 */
class FragmentSearchPage: Fragment() {
    companion object{
        var mList = ArrayList<BookListData>()
    }
    //声明控件
    lateinit var searchView: SearchView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search_page, container, false)
        findView(view)
        return view
    }

    private fun findView(view: View) {
        //绑定控件
        searchView = view.search_view
        //设置监听事件
        searchView.setOnClickSearch(ICallBack {
            //Log.d("SearchActivity","收到你的消息了！")
            if (it == "") {
                Toast.makeText((activity as SearchActivity), "输入框不能为空", Toast.LENGTH_SHORT).show()
            } else {
                val catalog_id = Integer.parseInt(it)
                val url =
                    "http://apis.juhe.cn/goodbook/query?key=0095c828443aeb604ca2511b97202c57&catalog_id=$catalog_id&rn=10&rn=10"
                RxVolley.get(url, object : HttpCallback() {
                    override fun onSuccess(t: String?) {
                        Log.d("llhData",t);
                        parsingJson(t!!)
                        //切换碎片，
                        (activity as SearchActivity).switchFragment(FragmentSearchResult())
                    }
                    override fun onFailure(error: VolleyError?) {
                        Log.d("llhData", "请求失败")
                    }
                })
            }
        })
        //设置返回事件
        searchView.setOnClickBack(bCallBack {
            (activity as SearchActivity).finish()
        })
    }
    //解析json
    private fun parsingJson(t: String) {
        mList.clear()
        //Log.d("llhData", "t: "+t);
        try {
            val jsonObject = JSONObject(t)
            val jsonResult = jsonObject.getJSONObject("result")
            val jsonArray = jsonResult.getJSONArray("data")
            for (i in 0 until jsonArray.length()) {
                var json = jsonArray.get(i) as JSONObject
                //获取当前数据
                var imageUrl = json.getString("img")
                //Log.d("llhData", "imageUrl: "+imageUrl);
                var title = json.getString("title")
                var catalog = json.getString("catalog")
                //创建对象
                val data = BookListData()
                data.title = title
                data.catalog = catalog
                data.imageUrl = imageUrl
                mList.add(data)
            }
            //            Log.d("llhData", "SearchAciton: "+mList);
            //            Log.d("llhData", "SearchAciton: "+mList.size());
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }
}
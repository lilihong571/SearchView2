package com.llh.search
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.llh.search.BookListData
import com.llh.search.R
import com.squareup.picasso.Picasso

/**
 * 项目名:    Search
 * 包名:      com.llh.search
 * 文件名:    BookAdapter
 * 创建者:    LLH
 * 创建时间:  2019/8/21 10:06
 * 描述:      TODO
 */
class BookAdapter(mContent: Context, mList: List<BookListData>): RecyclerView.Adapter<BookAdapter.ViewHolder>() {
    lateinit var mList: List<BookListData>
    lateinit var mContent: Context
    init {
        this.mContent = mContent
        this.mList = mList
    }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_title: TextView
        var tv_catalog: TextView
        var imageView: ImageView

        init {
            //绑定控件
            tv_title = view.findViewById(R.id.tv_book_title)
            tv_catalog = view.findViewById(R.id.tv_book_catalog)
            imageView = view.findViewById(R.id.iv_book)
        }
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        //填充视图
        val view = LayoutInflater.from(mContent).inflate(R.layout.item_book, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        //设置数据
        val data = mList.get(p1)
        p0.tv_title.setText(data.title)
        p0.tv_catalog.setText(data.catalog)
        //设置图片
        Picasso.get().load(data.imageUrl).into(p0.imageView)
    }
}

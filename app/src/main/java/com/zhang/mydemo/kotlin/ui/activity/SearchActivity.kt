package com.zhang.mydemo.kotlin.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.zhang.mydemo.R
import com.zhang.mydemo.base.BaseActivity
import com.zhang.mydemo.kotlin.model.bean.User
import com.zhang.mydemo.kotlin.ui.adapter.SearchAdapter
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.layout_title.*

class SearchActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_search

    var seList = mutableListOf<User>()

    lateinit var sAdapter: SearchAdapter

    override fun initView() {
        addData()
        ivPageBack.singleClick { killMyself() }
        tvPageTitle.text = "搜索"
    }

    fun addData() {
        for (i in 0..20) {
            seList.add(
                User(
                    "张三${i}",
                    "https://pic3.zhimg.com/v2-49bf448b03eaaa06e9484ab38b97f33a_b.jpg"
                )
            )
            seList.add(
                User(
                    "李四${i}",
                    "https://pic2.zhimg.com/v2-9bfe125d27920962f31093447c7bfb65_r.jpg"
                )
            )
        }
    }

    override fun initData() {
        sAdapter = SearchAdapter(mutableListOf())
        search_rv.adapter = sAdapter
        sAdapter.setNewInstance(seList)

        et_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                QueryData(s.toString())
            }

        })
    }

    var sList = mutableListOf<User>()
    fun QueryData(search: String) {
        for (i in sAdapter.data.indices) {
            if (sAdapter.data[i].name.contains(search)) {
                sList.add(sAdapter.data[i])
            }
        }
        sAdapter.setNewInstance(sList)
        sAdapter.notifyDataSetChanged()
    }

    override fun setListener() {

    }
}
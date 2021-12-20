package com.zhang.mydemo.kotlin.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elvishew.xlog.XLog
import com.zhang.mydemo.R
import com.zhang.mydemo.base.BaseActivity
import com.zhang.mydemo.kotlin.model.bean.User
import com.zhang.mydemo.kotlin.ui.adapter.SearchAdapter
import com.zhang.mydemo.kotlin.ui.adapter.SearchFilterAdapter
import com.zhang.mydemo.kotlin.ui.widgetkt.popup.SearchFilterPopUpView
import com.zhang.utilslibiary.utils.PopupWindowUtils
import com.zhang.utilslibiary.utils.singleClick
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.layout_title.*
import org.jetbrains.anko.find

class SearchActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_search

    var seList = mutableListOf<User>()

    lateinit var sAdapter: SearchAdapter
    lateinit var pAdapter: SearchFilterAdapter

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

        if (et_search.text.toString().isNotEmpty()) {
            clear.visibility = View.VISIBLE
        }
        clear.singleClick {
            et_search.setText("")
            et_search.visibility = View.INVISIBLE
        }

        et_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                    QueryData(s.toString())
            }
        })
    }

    var sList = arrayListOf<User>()

    private lateinit var sePopup: SearchFilterPopUpView

    fun QueryData(search: String) {
        for (i in sAdapter.data.indices) {
            if (sAdapter.data[i].name.contains(search)) {
                sList.add(sAdapter.data[i])
            }
        }
        sePopup = SearchFilterPopUpView.create(this@SearchActivity, sList, search).apply()
        sePopup.setBackgroundDimEnable(true).setDimView(et_search).setDimValue(0.5F)
        sePopup.setFocusAndOutsideEnable(false)
        sePopup.showAsDropDown(et_search)
        sList.clear()

        /*  val inflate = layoutInflater.inflate(R.layout.item_search_popup, null)
          PopupWindowUtils.DownPopup(
              this,
              inflate,
              ViewGroup.LayoutParams.MATCH_PARENT,
              ViewGroup.LayoutParams.WRAP_CONTENT,
              Gravity.CENTER, et_search
          )

          val poRv = inflate.find<RecyclerView>(R.id.poRV)
          pAdapter = SearchFilterAdapter(mutableListOf(),search)
          poRv.adapter = pAdapter


          for (i in sAdapter.data.indices) {
              if (sAdapter.data[i].name.contains(search)) {
                  sList.add(sAdapter.data[i])
              }
          }

          XLog.e("SearchActivity_102行_2021/12/20_10:52：${sList}")
          pAdapter.setNewInstance(sList)
          pAdapter.notifyDataSetChanged()*/
    }

    override fun setListener() {

    }
}
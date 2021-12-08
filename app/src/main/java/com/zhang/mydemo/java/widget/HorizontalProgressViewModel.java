package com.zhang.mydemo.java.widget;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhang.mydemo.java.ui.adapter.HorizontalProgressListAdapter;
import com.zhang.mydemo.kotlin.ui.widgetkt.Node;

import java.util.List;

/**
 * Description :横向的时间轴
 * Author : Liun
 * Date   : 2016 16/9/8 21:37.
 * Email  : liun_coolman@foxmail.com
 */
public class HorizontalProgressViewModel {
    /**
     * 取消订单
     * @param context
     * @param recyclerView
     * @param list
     */
    public void setViewUp(Context context, RecyclerView recyclerView, List<Node> list) {
        // 设置订单处理进度
        recyclerView.setLayoutManager(
                new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        );

        recyclerView.setAdapter(new HorizontalProgressListAdapter(list));

    }
}

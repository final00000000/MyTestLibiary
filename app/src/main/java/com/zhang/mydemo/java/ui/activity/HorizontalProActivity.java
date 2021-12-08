package com.zhang.mydemo.java.ui.activity;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.zhang.mydemo.R;
import com.zhang.mydemo.base.BaseActivity;
import com.zhang.mydemo.java.widget.HorizontalProgressViewModel;
import com.zhang.mydemo.kotlin.ui.widgetkt.Node;

import java.util.ArrayList;
import java.util.List;

public class HorizontalProActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_horizontal_pro;
    }

    @Override
    protected void initView() {

        findViewById(R.id.ivPageBack).setOnClickListener(v->{killMyself();});
        TextView title = findViewById(R.id.tvPageTitle);
        title.setText("横向物流轴");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // 引用
        HorizontalProgressViewModel model = new HorizontalProgressViewModel();

        model.setViewUp(this, recyclerView, getProgressList());
    }
    /**
     * 模拟节点数据
     * node1.nodeStatus: 0 已完成状态  1正在处理状态  -1待处理状态
     *
     * @return
     */
    private List<Node> getProgressList() {
        List<Node> list = new ArrayList<>();

        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        Node node4 = new Node();
        Node node5 = new Node();

        node1.nodeName = "提交申请";
        node1.nodeStatus = 0;

        node2.nodeName = "等待回寄";
        node2.nodeStatus = 0;

        node3.nodeName = "已回寄";
        node3.nodeStatus = 0;

        node4.nodeName = "等待退款";
        node4.nodeStatus = 1;

        node5.nodeName = "完成";
        node5.nodeStatus = -1;


        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);
        return list;

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }
}
package com.zhang.mydemo.kotlin.ui.widgetkt;

import android.content.Context;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @Author : zhang
 * @Create Time : 2022/1/25
 * @Class Describe : 描述
 * @Project Name : MyDemo
 */
public class MyView  extends View {

    public MyView(Context context) {
        super(context,null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}

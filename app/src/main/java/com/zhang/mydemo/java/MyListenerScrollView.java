package com.zhang.mydemo.java;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class MyListenerScrollView extends ScrollView {

	private OnScrollViewListener mOnScrollViewListener;

	public MyListenerScrollView(Context context) {
		super(context);
	}

	public MyListenerScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyListenerScrollView(Context context, AttributeSet attrs,
                                int defStyle) {
		super(context, attrs, defStyle);
	}

	public interface OnScrollViewListener {
		void onScrollChanged(MyListenerScrollView v, int l, int t, int oldl,
				int oldt);
	}

	public void setOnScrollViewListener(OnScrollViewListener l) {
		this.mOnScrollViewListener = l;
	}

	@Override
	protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX,
			boolean clampedY) {
		super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
	}

	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		mOnScrollViewListener.onScrollChanged(this, l, t, oldl, oldt);
		super.onScrollChanged(l, t, oldl, oldt);
	}

}

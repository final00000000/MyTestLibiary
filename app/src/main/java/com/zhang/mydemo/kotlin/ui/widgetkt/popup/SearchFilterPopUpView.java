package com.zhang.mydemo.kotlin.ui.widgetkt.popup;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;

import androidx.recyclerview.widget.RecyclerView;

import com.zhang.mydemo.R;
import com.zhang.mydemo.kotlin.model.bean.User;
import com.zhang.mydemo.kotlin.ui.adapter.SearchFilterAdapter;
import com.zhang.mydemo.kotlin.ui.widgetkt.popup.basepopup.BasePopup;

import java.util.ArrayList;

/**
 * @author Hugo
 */
public class SearchFilterPopUpView extends BasePopup<SearchFilterPopUpView> {

    private RecyclerView mSearchRv;
    private Context mContext;
    private ArrayList<User> mList;
    private String mSearch;


    public static SearchFilterPopUpView create(Context context, ArrayList<User> mList,String search) {
        return new SearchFilterPopUpView(context, mList,search);
    }

    private SearchFilterPopUpView(Context context, ArrayList<User> mList,String search) {
        setContext(context);
        this.mContext = context;
        this.mList = mList;
        this.mSearch = search;
    }


    @Override
    protected void initAttributes() {
        setContentView(R.layout.item_search_popup,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.PopupAnimaFade)
                .setFocusAndOutsideEnable(true);
    }

    @Override
    protected void initViews(View view, SearchFilterPopUpView popup) {
        mSearchRv = view.findViewById(R.id.poRV);
        SearchFilterAdapter searchFilterAdapter = new SearchFilterAdapter(mList, mSearch);
        searchFilterAdapter.setNewInstance(mList);
        mSearchRv.setAdapter(searchFilterAdapter);
        searchFilterAdapter.notifyDataSetChanged ();
    }
}

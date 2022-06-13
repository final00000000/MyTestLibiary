package com.zhang.mydemo.model.bean;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SecondNode extends BaseNode {

    //private List<BaseNode> childNode;
    private String title;

    public SecondNode(String title) {
        //this.childNode = childNode;
        this.title = title;

        //setExpanded(false);
    }

    public String getTitle() {
        return title;
    }

    @androidx.annotation.Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return null;
    }

    //@Nullable
    //@Override
    //public List<BaseNode> getChildNode() {
    //    return childNode;
    //}
}

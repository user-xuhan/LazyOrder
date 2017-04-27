package com.example.xuhan.lazyorder.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by xuhan on 2017/4/11.
 */

public class ListViewMaxHeight extends ListView {
    private int listViewHeight = 0;

    public int getListViewHeight() {
        return listViewHeight;
    }

    public void setListViewHeight(int listViewHeight) {
        this.listViewHeight = listViewHeight;
    }

    public ListViewMaxHeight(Context context) {
        super(context);
    }

    public ListViewMaxHeight(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewMaxHeight(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(listViewHeight,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}

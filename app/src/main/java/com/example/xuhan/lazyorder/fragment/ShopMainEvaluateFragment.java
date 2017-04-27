package com.example.xuhan.lazyorder.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;

import com.example.xuhan.lazyorder.R;
import com.example.xuhan.lazyorder.adapter.ShopMainEvaluateFragmentCommentsAdapter;
import com.example.xuhan.lazyorder.model.Comment;
import com.example.xuhan.lazyorder.view.ListViewForScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuhan on 2017/3/30.
 */

public class ShopMainEvaluateFragment extends Fragment {

    ScrollView scrollView;
    List<Comment> commentsList = new ArrayList<>();
    ListViewForScrollView evaluateListView;
    ShopMainEvaluateFragmentCommentsAdapter shopMainEvaluateFragmentCommentsAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_main_evaluatefragment_layout, container, false);
        initCommentsList();
        evaluateListView = (ListViewForScrollView) view.findViewById(R.id.shop_main_evaluate_list);
        evaluateListView.setFocusable(false);
        shopMainEvaluateFragmentCommentsAdapter = new ShopMainEvaluateFragmentCommentsAdapter(getContext(), R.layout.shop_main_evaluate_list_item, commentsList);
        evaluateListView.setAdapter(shopMainEvaluateFragmentCommentsAdapter);


        return view;
    }

    private void initCommentsList() {
        commentsList.add(new Comment(R.drawable.example+"", "177****9987", "味道很好吃，送的也很快味道很好吃，送的也很快味道很好吃，送的也很快味道很好吃，送的也很快", "2017-02-28 17:10", "57", "4"));
        commentsList.add(new Comment(R.drawable.example+"", "177****9987", "味道很好吃，送的也很快", "2017-02-28 17:10", "57", "4"));
        commentsList.add(new Comment(R.drawable.example+"", "177****9987", "味道很好吃，送的也很快", "2017-02-28 17:10", "57", "4"));
        commentsList.add(new Comment(R.drawable.example+"", "177****9987", "味道很好吃，送的也很快", "2017-02-28 17:10", "57", "4"));
        commentsList.add(new Comment(R.drawable.example+"", "177****9987", "味道很好吃，送的也很快", "2017-02-28 17:10", "57", "4"));
        commentsList.add(new Comment(R.drawable.example+"", "177****9987", "味道很好吃，送的也很快", "2017-02-28 17:10", "57", "4"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}

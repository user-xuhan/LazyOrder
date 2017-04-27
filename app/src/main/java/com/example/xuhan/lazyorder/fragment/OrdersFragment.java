package com.example.xuhan.lazyorder.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.xuhan.lazyorder.R;
import com.example.xuhan.lazyorder.activity.OrderDetailActivity;
import com.example.xuhan.lazyorder.adapter.OrderAdapter;
import com.example.xuhan.lazyorder.model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuhan on 2017/3/28.
 */

public class OrdersFragment extends Fragment {

    ListView orderFragmentListView;
    List<Order> orderList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ordersfragment_layout, container, false);
        initOrderList();
        orderFragmentListView = (ListView) view.findViewById(R.id.ordersfragment_list);
        orderFragmentListView.setAdapter(new OrderAdapter(getContext(), R.layout.ordersfragment_list_item, orderList));
        orderFragmentListView.setFocusable(true);
        orderFragmentListView.setFocusableInTouchMode(true);
        orderFragmentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent orderDetailIntent = new Intent(getActivity(), OrderDetailActivity.class);
                startActivity(orderDetailIntent);
            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        orderList.clear();
    }

    private void initOrderList() {
        orderList.add(new Order(R.drawable.example+"", "小米粥道", "0", "2017-06-03 17:06:30", "45"));
        orderList.add(new Order(R.drawable.example+"", "小米粥道", "0", "2017-06-03 17:06:30", "45"));
        orderList.add(new Order(R.drawable.example+"", "小米粥道", "3", "2017-06-03 17:06:30", "45"));
        orderList.add(new Order(R.drawable.example+"", "小米粥道", "1", "2017-06-03 17:06:30", "45"));
        orderList.add(new Order(R.drawable.example+"", "小米粥道", "2", "2017-06-03 17:06:30", "45"));
        orderList.add(new Order(R.drawable.example+"", "小米粥道", "1", "2017-06-03 17:06:30", "45"));
    }
}

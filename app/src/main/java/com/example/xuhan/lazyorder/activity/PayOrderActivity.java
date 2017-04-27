package com.example.xuhan.lazyorder.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xuhan.lazyorder.R;
import com.example.xuhan.lazyorder.adapter.PayWayAdapter;
import com.example.xuhan.lazyorder.model.PayWayItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuhan on 2017/4/7.
 */

public class PayOrderActivity extends BaseActivity implements View.OnClickListener {
    ImageView payOrderBack;
    ListView payListView;
    PayWayAdapter payWayAdapter;
    List<PayWayItem> payList = new ArrayList<>();

    ImageView payOrderImage;
    TextView payOrderName;
    TextView payOrderPrice;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_order_layout);
        payOrderBack = (ImageView) findViewById(R.id.pay_order_back_image);
        payOrderBack.setOnClickListener(this);
        payListView = (ListView) findViewById(R.id.pay_order_ways_list);
        initPayList();
        payWayAdapter = new PayWayAdapter(this, R.layout.pay_order_list_item, payList);
        payListView.setAdapter(payWayAdapter);
        Intent getPayIntent = getIntent();
        payOrderImage = (ImageView) findViewById(R.id.pay_order_image);
        payOrderName = (TextView) findViewById(R.id.pay_order_name);
        payOrderName.setText(getPayIntent.getStringExtra("payShopName"));
        payOrderPrice = (TextView) findViewById(R.id.pay_order_price);
        payOrderPrice.setText(getPayIntent.getStringExtra("payPrice"));
    }

    private void initPayList() {
        payList.add(new PayWayItem("支付宝支付", "数亿用户都在用，安全可托付", R.drawable.alipay));
        payList.add(new PayWayItem("微信支付", "微信安全支付", R.drawable.wchat));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pay_order_back_image:
                finish();
                break;
            default:
                break;
        }
    }
}

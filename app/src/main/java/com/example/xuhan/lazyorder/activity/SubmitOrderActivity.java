package com.example.xuhan.lazyorder.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xuhan.lazyorder.R;
import com.example.xuhan.lazyorder.adapter.SubmitOrderGoodAdapter;
import com.example.xuhan.lazyorder.model.Good;
import com.example.xuhan.lazyorder.view.ListViewForScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuhan on 2017/4/7.
 */

public class SubmitOrderActivity extends BaseActivity implements View.OnClickListener {

    ImageView submitOrderBack;
    List<Good> submitGoodList = new ArrayList<>();
    ListViewForScrollView listViewForScrollView;
    SubmitOrderGoodAdapter submitOrderGoodAdapter;
    TextView all_price_text;
    TextView submit_need_price_text;
    TextView submitOrderBtn;
    TextView submitShopName;
    int all_good_price;
    boolean haveLocation = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_order_layout);
        submitOrderBack = (ImageView) findViewById(R.id.submit_order_back_image);
        submitOrderBack.setOnClickListener(this);

        Intent getSubmitIntent = getIntent();
        submitGoodList = getSubmitIntent.getParcelableArrayListExtra("submitList");
        all_good_price = Integer.valueOf(getSubmitIntent.getStringExtra("submitAllPrice"));
        listViewForScrollView = (ListViewForScrollView) findViewById(R.id.submit_order_good_list);
        submitOrderGoodAdapter = new SubmitOrderGoodAdapter(this, R.layout.submit_order_list_item, submitGoodList);
        listViewForScrollView.setAdapter(submitOrderGoodAdapter);
        all_price_text = (TextView) findViewById(R.id.submit_order_all_price_text);
        all_price_text.setText(""+all_good_price);
        submit_need_price_text = (TextView) findViewById(R.id.submit_order_need_money_text);
        submit_need_price_text.setText(all_good_price+"");

        submitOrderBtn = (TextView) findViewById(R.id.submit_order_btn);
        submitOrderBtn.setOnClickListener(this);
        submitShopName = (TextView) findViewById(R.id.submit_order_shop_name);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit_order_back_image:
                finish();
                break;
            case R.id.submit_order_btn:
                if (haveLocation){
                    Intent payIntent = new Intent(SubmitOrderActivity.this, PayOrderActivity.class);
                    payIntent.putExtra("payShopName", submitShopName.getText().toString());
                    payIntent.putExtra("payPrice", all_good_price+"");
                    startActivity(payIntent);
                }else {
                    Toast.makeText(this, "未选中地址", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}

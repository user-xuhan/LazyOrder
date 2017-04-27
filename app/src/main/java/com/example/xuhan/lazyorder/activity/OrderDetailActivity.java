package com.example.xuhan.lazyorder.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.xuhan.lazyorder.R;

/**
 * Created by xuhan on 2017/4/9.
 */

public class OrderDetailActivity extends BaseActivity implements View.OnClickListener {

    ImageView OrderDetailBackImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_detail_layout);
        OrderDetailBackImage = (ImageView) findViewById(R.id.order_detail_back_image);
        OrderDetailBackImage.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.order_detail_back_image:
                finish();
                break;


            default:
                break;
        }
    }
}

package com.example.xuhan.lazyorder.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.xuhan.lazyorder.R;
import com.example.xuhan.lazyorder.adapter.MyCollectShopAdapter;
import com.example.xuhan.lazyorder.model.Shop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuhan on 2017/3/30.
 */

public class MyCollectActivity extends BaseActivity implements View.OnClickListener {
    ImageView myCollectBackImage;
    ListView myCollectListView;
    List<Shop> shopList = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycollect_layout);
        myCollectBackImage = (ImageView) findViewById(R.id.my_collect_back_image);
        myCollectBackImage.setOnClickListener(this);
        initShopList();
        myCollectListView = (ListView) findViewById(R.id.my_collect_list);
        myCollectListView.setAdapter(new MyCollectShopAdapter(this, R.layout.mycollect_list_item, shopList));
    }

    private void initShopList() {
        shopList.add(new Shop("小米粥道", "450", "45", "5",  R.drawable.example+"", "34", "1.7", "4"));
        shopList.add(new Shop("小米粥道", "450", "45", "5",  R.drawable.example+"", "34", "1.7", "4"));
        shopList.add(new Shop("小米粥道", "450", "45", "5",  R.drawable.example+"", "34", "1.7", "5"));
        shopList.add(new Shop("小米粥道", "450", "45", "5",  R.drawable.example+"", "34", "1.7", "5"));
        shopList.add(new Shop("小米粥道", "450", "45", "5",  R.drawable.example+"", "34", "1.7", "5"));
        shopList.add(new Shop("小米粥道", "450", "45", "5",  R.drawable.example+"", "34", "1.7", "5"));
        shopList.add(new Shop("小米粥道", "450", "45", "5",  R.drawable.example+"", "34", "1.7", "5"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_collect_back_image:
                finish();
                break;
            default:
                break;
        }
    }
}

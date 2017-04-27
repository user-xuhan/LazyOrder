package com.example.xuhan.lazyorder.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.xuhan.lazyorder.R;
import com.example.xuhan.lazyorder.adapter.ShopMainViewPageAdapter;
import com.example.xuhan.lazyorder.fragment.ShopMainDetailFragment;
import com.example.xuhan.lazyorder.fragment.ShopMainEvaluateFragment;
import com.example.xuhan.lazyorder.fragment.ShopMainOrderFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuhan on 2017/3/30.
 */

public class ShopMainActivity extends BaseActivity implements View.OnClickListener {

    ViewPager shopMainViewPage;
    List<Fragment> fragmentList = new ArrayList<>();
    ShopMainViewPageAdapter shopMainViewPageAdapter;
    RadioGroup shopMainGroup;
    RadioButton orderRadio;
    RadioButton evaluateRadio;
    RadioButton detailRadio;
    ImageView shopMainBackImage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_main_layout);


        shopMainGroup = (RadioGroup) findViewById(R.id.shop_main_group);
        shopMainGroup.check(R.id.shop_main_order_radio);
        orderRadio = (RadioButton) findViewById(R.id.shop_main_order_radio);
        orderRadio.setTextColor(ContextCompat.getColor(this, R.color.themeColor));
        evaluateRadio = (RadioButton) findViewById(R.id.shop_main_evaluate_radio);
        detailRadio = (RadioButton) findViewById(R.id.shop_main_detail_radio);
        shopMainGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.shop_main_order_radio:
                        orderRadio.setTextColor(ContextCompat.getColor(ShopMainActivity.this, R.color.themeColor));
                        evaluateRadio.setTextColor(ContextCompat.getColor(ShopMainActivity.this, R.color.black));
                        detailRadio.setTextColor(ContextCompat.getColor(ShopMainActivity.this, R.color.black));
                        shopMainViewPage.setCurrentItem(0);
                        break;
                    case R.id.shop_main_evaluate_radio:
                        orderRadio.setTextColor(ContextCompat.getColor(ShopMainActivity.this, R.color.black));
                        evaluateRadio.setTextColor(ContextCompat.getColor(ShopMainActivity.this, R.color.themeColor));
                        detailRadio.setTextColor(ContextCompat.getColor(ShopMainActivity.this, R.color.black));
                        shopMainViewPage.setCurrentItem(1);
                        break;
                    case R.id.shop_main_detail_radio:
                        orderRadio.setTextColor(ContextCompat.getColor(ShopMainActivity.this, R.color.black));
                        evaluateRadio.setTextColor(ContextCompat.getColor(ShopMainActivity.this, R.color.black));
                        detailRadio.setTextColor(ContextCompat.getColor(ShopMainActivity.this, R.color.themeColor));
                        shopMainViewPage.setCurrentItem(2);
                        break;
                    default:
                        break;
                }
            }
        });
        initFragmentList();
        shopMainViewPageAdapter = new ShopMainViewPageAdapter(getSupportFragmentManager(), fragmentList);
        shopMainViewPage = (ViewPager) findViewById(R.id.shop_main_viewpage);
        shopMainViewPage.setAdapter(shopMainViewPageAdapter);
        shopMainViewPage.setOffscreenPageLimit(3);
        shopMainViewPage.setCurrentItem(0);
        shopMainViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        shopMainGroup.check(R.id.shop_main_order_radio);
                        break;
                    case 1:
                        shopMainGroup.check(R.id.shop_main_evaluate_radio);
                        break;
                    case 2:
                        shopMainGroup.check(R.id.shop_main_detail_radio);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        shopMainBackImage = (ImageView) findViewById(R.id.shop_main_back_image);
        shopMainBackImage.setOnClickListener(this);
    }

    private void initFragmentList() {
        fragmentList.add(new ShopMainOrderFragment());
        fragmentList.add(new ShopMainEvaluateFragment());
        fragmentList.add(new ShopMainDetailFragment());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shop_main_back_image:
                finish();
                break;
            default:
                break;
        }
    }
}

package com.example.xuhan.lazyorder.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xuhan.lazyorder.R;
import com.example.xuhan.lazyorder.adapter.ShopcartAdapter;
import com.example.xuhan.lazyorder.model.Good;
import com.example.xuhan.lazyorder.view.ListViewMaxHeight;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by xuhan on 2017/4/10.
 */

public class GoodDetailActivity extends BaseActivity implements View.OnClickListener, ShopcartAdapter.ShopCartCallBack{

    PopupWindow popupWindow;
    ListViewMaxHeight listViewMaxHeight;
    ShopcartAdapter shopcartAdapter;
    ImageView goodDetailBackImage;
    ImageView goodDetailImage;
    TextView goodDetailNameText;
    TextView goodDetailSaleVolumeText;
    TextView goodDetailPriceText;
    Button addShopCartBtn;
    RelativeLayout addGoodRelative;
    Button goodDetailAddBtn;
    Button goodDetailMinusBtn;
    TextView goodDetailNumberText;
    ImageView shopcartImage;
    TextView shopcartText;
    LinearLayout shopcart_linear;
    Good selectGood;
    List<Good> shopcartGoodList;
    int all_good_price = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.good_detail_layout);
        goodDetailBackImage = (ImageView) findViewById(R.id.good_detail_back_image);
        goodDetailBackImage.setOnClickListener(this);
        Intent intent = getIntent();
        selectGood = intent.getParcelableExtra("selectGood");
        shopcartGoodList = intent.getParcelableArrayListExtra("shopcartGoodList");
        all_good_price = Integer.valueOf(intent.getStringExtra("all_good_price"));

        goodDetailImage = (ImageView) findViewById(R.id.good_deatil_good_image);
        goodDetailImage.setImageResource(selectGood.getImageId());
        goodDetailNameText = (TextView) findViewById(R.id.good_deatil_good_name);
        goodDetailNameText.setText(selectGood.getGoodName());
        goodDetailSaleVolumeText = (TextView) findViewById(R.id.good_detail_salesvolume);
        goodDetailSaleVolumeText.setText(selectGood.getGoodSaleVolume());
        goodDetailPriceText = (TextView) findViewById(R.id.good_detail_price);
        goodDetailPriceText.setText(selectGood.getGoodPrice());
        addGoodRelative = (RelativeLayout) findViewById(R.id.good_deatil_add_shopcart_relative);
        addShopCartBtn = (Button) findViewById(R.id.good_deatil_add_shopcart_btn);
        addShopCartBtn.setOnClickListener(this);
        goodDetailAddBtn = (Button) findViewById(R.id.good_deatil_add_btn);
        goodDetailAddBtn.setOnClickListener(this);
        goodDetailMinusBtn = (Button) findViewById(R.id.good_deatil_minus_btn);
        goodDetailMinusBtn.setOnClickListener(this);
        goodDetailNumberText = (TextView) findViewById(R.id.good_deatil_number_text);
        goodDetailNumberText.setText(""+selectGood.getGoodSelectedNumber());
        if (selectGood.getGoodSelectedNumber() > 0){
            addShopCartBtn.setVisibility(View.GONE);
            addGoodRelative.setVisibility(View.VISIBLE);
        }
        shopcart_linear = (LinearLayout) findViewById(R.id.good_detail_shopcart_linear);
        shopcart_linear.setOnClickListener(this);
        shopcartImage = (ImageView) findViewById(R.id.good_detail_shopcart_image);
        shopcartText = (TextView) findViewById(R.id.good_detail_shopcart_price);
        if (all_good_price != 0){
            shopcartText.setText("¥"+all_good_price);
            shopcartText.setTextSize(18);
            shopcartText.setTextColor(ContextCompat.getColor(this, R.color.themeColor));
            shopcartImage.setImageResource(R.drawable.shopcart);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.good_detail_back_image:
                finish();
                break;
            case R.id.good_deatil_add_shopcart_btn:
                addShopCartBtn.setVisibility(View.GONE);
                addGoodRelative.setVisibility(View.VISIBLE);
                selectGood.setGoodSelectedNumber(1);
                goodDetailNumberText.setText(1+"");
                all_good_price = Integer.valueOf(selectGood.getGoodPrice())+ all_good_price;
                shopcartGoodList.add(selectGood);
                if (shopcartGoodList.size() == 1){
                    shopcartText.setText("¥"+all_good_price);
                    shopcartText.setTextSize(18);
                    shopcartText.setTextColor(ContextCompat.getColor(this, R.color.themeColor));
                    shopcartImage.setImageResource(R.drawable.shopcart);
                }
                break;
            case R.id.good_deatil_add_btn:
                int addNumber = Integer.valueOf(goodDetailNumberText.getText().toString());
                goodDetailNumberText.setText(++addNumber+"");
                selectGood.setGoodSelectedNumber(addNumber);
                all_good_price = Integer.valueOf(selectGood.getGoodPrice())+ all_good_price;
                shopcartText.setText("¥"+all_good_price);
                break;
            case R.id.good_deatil_minus_btn:
                int minusNumber = Integer.valueOf(goodDetailNumberText.getText().toString());
                if (minusNumber > 0){
                    goodDetailNumberText.setText(--minusNumber+"");
                    all_good_price = all_good_price - Integer.valueOf(selectGood.getGoodPrice());
                    selectGood.setGoodSelectedNumber(minusNumber);
                }
                if (minusNumber == 0){
                    addGoodRelative.setVisibility(View.GONE);
                    addShopCartBtn.setVisibility(View.VISIBLE);
                    shopcartGoodList.remove(selectGood);
                }
                if (all_good_price > 0){
                    shopcartText.setText("¥"+all_good_price);
                }else {
                    shopcartText.setText("未选中商品");
                    shopcartText.setTextColor(ContextCompat.getColor(this, R.color.noTextColor));
                    shopcartText.setTextSize(15);
                    shopcartImage.setImageResource(R.drawable.emptyshopcart);
                }

                break;
            case R.id.good_detail_shopcart_linear:
                if (shopcartGoodList.size() != 0){
                    showPopupwindow();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("returnSelectGood", selectGood);
        returnIntent.putParcelableArrayListExtra("returnShopcartGoodList", (ArrayList<? extends Parcelable>) shopcartGoodList);
        setResult(RESULT_OK, returnIntent);
    }

    private void showPopupwindow() {
        View v = LayoutInflater.from(this).inflate(R.layout.shop_main_orderfragment_pop_window, null);
        popupWindow = new PopupWindow(v, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        listViewMaxHeight = (ListViewMaxHeight) v.findViewById(R.id.pop_window_shopcart_list);
        listViewMaxHeight.setListViewHeight(600);
        shopcartAdapter = new ShopcartAdapter(this, R.layout.shop_main_orderfragment_pop_window_list_item, shopcartGoodList, this);
        listViewMaxHeight.setAdapter(shopcartAdapter);
        popupWindow.setOutsideTouchable(true);
        ColorDrawable cd = new ColorDrawable(0x000000);
        popupWindow.setBackgroundDrawable(cd);
        findViewById(R.id.good_detail_linear).setAlpha(0.5f);
        findViewById(R.id.good_detail_scroll).setAlpha(0.5f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                findViewById(R.id.good_detail_linear).setAlpha(1.0f);
                findViewById(R.id.good_detail_scroll).setAlpha(1.0f);
                //shopMainOrderFragmentGoodsAdapter.notifyDataSetChanged();

            }
        });
        popupWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        int popHeight = popupWindow.getContentView().getMeasuredHeight();
        int[] position = new int[2];
        shopcart_linear.getLocationOnScreen(position);
        popupWindow.showAtLocation(shopcart_linear, Gravity.NO_GRAVITY, 0, position[1] - popHeight);
        TextView emptyShopcart = (TextView) v.findViewById(R.id.pop_window_empty_shopcart);
        emptyShopcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all_good_price = 0;
                shopcartImage.setImageResource(R.drawable.emptyshopcart);
                shopcartText.setText("未选中商品");
                shopcartText.setTextColor(ContextCompat.getColor(GoodDetailActivity.this, R.color.noTextColor));
                shopcartText.setTextSize(15);
                shopcartGoodList.clear();
                popupWindow.dismiss();
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        for (Good good: goodList
//                                ) {
//                            good.setGoodSelectedNumber(0);
//                            Message message = new Message();
//                            message.what = EMPTY_SHOPCART ;
//                            handler.sendMessage(message);
//                        }
//                    }
//                }).start();

            }
        });
    }

    @Override
    public void shopcartClick(View view) {

    }
}

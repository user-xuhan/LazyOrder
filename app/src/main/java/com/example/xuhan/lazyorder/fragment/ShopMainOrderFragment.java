package com.example.xuhan.lazyorder.fragment;

import android.content.Intent;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xuhan.lazyorder.R;
import com.example.xuhan.lazyorder.activity.GoodDetailActivity;
import com.example.xuhan.lazyorder.activity.SubmitOrderActivity;
import com.example.xuhan.lazyorder.adapter.ShopMainOrderFragmentGoodsAdapter;
import com.example.xuhan.lazyorder.adapter.ShopMainOrderFragmentKindsAdapter;
import com.example.xuhan.lazyorder.adapter.ShopcartAdapter;
import com.example.xuhan.lazyorder.model.Good;
import com.example.xuhan.lazyorder.view.ListViewMaxHeight;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by xuhan on 2017/3/30.
 */

public class ShopMainOrderFragment extends Fragment implements ShopMainOrderFragmentGoodsAdapter.Callback, ShopcartAdapter.ShopCartCallBack {

    PopupWindow popupWindow;
    List<Good> goodList = new ArrayList<Good>();
    List<String> kindList = new ArrayList<>();
    StickyListHeadersListView goodsListView;
    ListView kindsListView;
    LinearLayout shopcart_linear;
    ImageView shopcart_Image;
    TextView shopcart_Text;
    TextView all_price_text;
    ListViewMaxHeight listViewMaxHeight;
    ShopcartAdapter shopcartAdapter;
    List<Good> shopcartGoodList = new ArrayList<>();
    ShopMainOrderFragmentGoodsAdapter shopMainOrderFragmentGoodsAdapter;
    TextView buyBtn;
    int clickPosition;
    int all_good_price = 0;
    int dispatch_price = 45;
    Good goodTemp;
    private final static int EMPTY_SHOPCART = 0;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    changedBuyBtnColor();
                    shopMainOrderFragmentGoodsAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_main_orderfragment_layout, container, false);
        initKindsList();
        final ShopMainOrderFragmentKindsAdapter arrayAdapter = new ShopMainOrderFragmentKindsAdapter(getContext(), R.layout.shop_main_orderfragment_kinds_list_item, kindList);
        kindsListView = (ListView) view.findViewById(R.id.shop_main_orderfragment_kinds_list);
        kindsListView.setAdapter(arrayAdapter);
        kindsListView.setItemChecked(0, true);
        kindsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < goodList.size(); i++){
                    if (goodList.get(i).getGoodProjectTitle().equals(kindList.get(position))){
                        goodsListView.setSelection(i);
                        break;
                    }
                }
            }
        });
        initGoodsList();
        goodsListView = (StickyListHeadersListView) view.findViewById(R.id.shop_main_orderfragment_goods_list);
        shopMainOrderFragmentGoodsAdapter = new ShopMainOrderFragmentGoodsAdapter(getActivity(), goodList, this);
        goodsListView.setAdapter(shopMainOrderFragmentGoodsAdapter);
        goodsListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Good firstGood = goodList.get(firstVisibleItem);
                    kindsListView.setItemChecked(Integer.valueOf(firstGood.getGoodProjectId()) - 1, true);
            }
        });

        goodsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent goodDetailIntent = new Intent(getActivity(), GoodDetailActivity.class);
                goodDetailIntent.putExtra("all_good_price", all_good_price+"");
                goodDetailIntent.putExtra("selectGood", goodList.get(position));
                goodDetailIntent.putParcelableArrayListExtra("shopcartGoodList", (ArrayList<? extends Parcelable>) shopcartGoodList);
                clickPosition = position;
                startActivityForResult(goodDetailIntent, 1);
            }
        });

        shopcart_linear = (LinearLayout) view.findViewById(R.id.shop_main_orderfragment_shopcart_linear);
        shopcart_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shopcartGoodList.size() > 0) {
                    showPopupwindow();
               }
            }
        });
        TextView textView = (TextView) view.findViewById(R.id.shop_main_orderfragment_shopcart_btn);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "ssssss", Toast.LENGTH_SHORT).show();
            }
        });
        shopcart_Image = (ImageView) view.findViewById(R.id.shop_main_orderfragment_shopcart_image);
        shopcart_Text = (TextView) view.findViewById(R.id.shop_main_orderfragment_shopcart_btn);
        all_price_text = (TextView) view.findViewById(R.id.shop_main_orderfragment_shopcart_price);
        if (shopcartGoodList.size() != 0){
            shopcart_Image.setImageResource(R.drawable.shopcart);
            shopcart_Text.setText("¥"+all_good_price);
        }
        buyBtn = (TextView) view.findViewById(R.id.shop_main_orderfragment_shopcart_btn);
        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (all_good_price < dispatch_price){
                    Toast.makeText(getContext(), "未达到起送价", Toast.LENGTH_SHORT).show();
                }else {
                    Intent submitOrderIntent = new Intent(getActivity(), SubmitOrderActivity.class);
                    submitOrderIntent.putParcelableArrayListExtra("submitList", (ArrayList<? extends Parcelable>) shopcartGoodList);
                    submitOrderIntent.putExtra("submitAllPrice", ""+all_good_price);
                    startActivity(submitOrderIntent);
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    Good returnGood = data.getParcelableExtra("returnSelectGood");
                    goodList.remove(clickPosition);
                    goodList.add(clickPosition, returnGood);
                    shopMainOrderFragmentGoodsAdapter.notifyDataSetChanged();
                    shopcartGoodList = data.getParcelableArrayListExtra("returnShopcartGoodList");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void showPopupwindow() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.shop_main_orderfragment_pop_window, null);
        popupWindow = new PopupWindow(v, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        initShopcartList();
        listViewMaxHeight = (ListViewMaxHeight) v.findViewById(R.id.pop_window_shopcart_list);
        listViewMaxHeight.setListViewHeight(600);
        shopcartAdapter = new ShopcartAdapter(getContext(), R.layout.shop_main_orderfragment_pop_window_list_item, shopcartGoodList, this);
        listViewMaxHeight.setAdapter(shopcartAdapter);
        popupWindow.setOutsideTouchable(true);
        ColorDrawable cd = new ColorDrawable(0x000000);
        popupWindow.setBackgroundDrawable(cd);
        getActivity().findViewById(R.id.shop_main_linear).setAlpha(0.5f);
        getActivity().findViewById(R.id.shop_main_group).setAlpha(0.5f);
        getActivity().findViewById(R.id.shop_main_orderfragment_main_linear).setAlpha(0.5f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                getActivity().findViewById(R.id.shop_main_linear).setAlpha(1.0f);
                getActivity().findViewById(R.id.shop_main_group).setAlpha(1.0f);
                getActivity().findViewById(R.id.shop_main_orderfragment_main_linear).setAlpha(1.0f);
                //shopMainOrderFragmentGoodsAdapter.notifyDataSetChanged();

            }
        });

        popupWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popHeight = popupWindow.getContentView().getMeasuredHeight();
        int[] position = new int[2];
        shopcart_linear.getLocationOnScreen(position);
        popupWindow.showAtLocation(shopcart_linear, Gravity.NO_GRAVITY, 0, position[1] - popHeight);
        TextView emptyShopcart = (TextView) v.findViewById(R.id.pop_window_empty_shopcart);
        emptyShopcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all_good_price = 0;
                shopcart_Image.setImageResource(R.drawable.emptyshopcart);
                all_price_text.setText("未选中商品");
                all_price_text.setTextColor(ContextCompat.getColor(getContext(), R.color.noTextColor));
                all_price_text.setTextSize(15);
                shopcartGoodList.clear();
                popupWindow.dismiss();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (Good good: goodList
                                ) {
                            good.setGoodSelectedNumber(0);
                            Message message = new Message();
                            message.what = EMPTY_SHOPCART ;
                            handler.sendMessage(message);
                        }
                    }
                }).start();

            }
        });
    }

    private void initShopcartList() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        kindList.clear();
        goodList.clear();
        shopcartGoodList.clear();
        all_good_price = 0;
    }



    private void initGoodsList() {
        goodList.add(new Good(R.drawable.example, "皮蛋瘦肉粥1", "15", "5", "1", "热销" ));
        goodList.add(new Good(R.drawable.example, "皮蛋瘦肉粥2", "25", "15", "1", "热销" ));
        goodList.add(new Good(R.drawable.example, "皮蛋瘦肉粥3", "35", "25", "1", "热销" ));
        goodList.add(new Good(R.drawable.example, "皮蛋瘦肉粥4", "45", "35", "1", "热销" ));
        goodList.add(new Good(R.drawable.example, "皮蛋瘦肉粥5", "55", "45", "1", "热销" ));

        goodList.add(new Good(R.drawable.example, "皮蛋瘦肉粥6", "65", "55", "2", "促销" ));
        goodList.add(new Good(R.drawable.example, "皮蛋瘦肉粥7", "75", "65", "2", "促销" ));
        goodList.add(new Good(R.drawable.example, "皮蛋瘦肉粥8", "85", "75", "2", "促销" ));
        goodList.add(new Good(R.drawable.example, "皮蛋瘦肉粥9", "95", "85", "2", "促销" ));

        goodList.add(new Good(R.drawable.example, "皮蛋瘦肉粥10", "105", "95", "3", "买一送一" ));
        goodList.add(new Good(R.drawable.example, "皮蛋瘦肉粥11", "115", "105", "3", "买一送一" ));
        goodList.add(new Good(R.drawable.example, "皮蛋瘦肉粥12", "125", "115", "3", "买一送一" ));
        goodList.add(new Good(R.drawable.example, "皮蛋瘦肉粥13", "135", "125", "3", "买一送一" ));
        goodList.add(new Good(R.drawable.example, "皮蛋瘦肉粥14", "145", "135", "3", "买一送一" ));

    }

    private void initKindsList() {
        kindList.add("热销");
        kindList.add("促销");
        kindList.add("买一送一");
    }

    @Override
    public void click(final View v) {
        switch (v.getId()){
            case R.id.shop_main_orderfragment_goods_list_add_btn:
                goodTemp = goodList.get((Integer) v.getTag());
                if (all_good_price == 0){
                    shopcart_Image.setImageResource(R.drawable.shopcart);
                }
                all_good_price = all_good_price + Integer.valueOf(goodTemp.getGoodPrice());
                all_price_text.setText("¥"+all_good_price);
                all_price_text.setTextSize(18);
                all_price_text.setTextColor(ContextCompat.getColor(getContext(), R.color.themeColor));
                if (goodTemp.getGoodSelectedNumber() == 1){
                    shopcartGoodList.add(goodTemp);
                }
                changedBuyBtnColor();
                break;

            case R.id.shop_main_orderfragment_goods_list_minus_btn:
                goodTemp = goodList.get((Integer) v.getTag());
                if (all_good_price > 0){
                    all_good_price = all_good_price - Integer.valueOf(goodTemp.getGoodPrice());
                    all_price_text.setText("¥"+all_good_price);

                }
                if (all_good_price == 0){
                    shopcart_Image.setImageResource(R.drawable.emptyshopcart);
                    all_price_text.setText("未选中商品");
                    all_price_text.setTextColor(ContextCompat.getColor(getContext(), R.color.noTextColor));
                    all_price_text.setTextSize(15);
                }
                if (goodTemp.getGoodSelectedNumber() == 0){
                    shopcartGoodList.remove(goodTemp);
                }
                changedBuyBtnColor();
                break;

            default:
                break;
        }
    }

    @Override
    public void shopcartClick(View v) {
        switch (v.getId()){
            case R.id.shop_main_orderfragment_popwindow_list_add_btn:
                goodTemp = shopcartGoodList.get((Integer) v.getTag());
                all_good_price = all_good_price + Integer.valueOf(goodTemp.getGoodPrice());
                all_price_text.setText("¥"+all_good_price);
                shopMainOrderFragmentGoodsAdapter.notifyDataSetChanged();
                changedBuyBtnColor();
                break;
            case R.id.shop_main_orderfragment_popwindow_list_minus_btn:
                goodTemp = shopcartGoodList.get((Integer) v.getTag());
                all_good_price = all_good_price - Integer.valueOf(goodTemp.getGoodPrice());
                all_price_text.setText("¥" + all_good_price);
                shopMainOrderFragmentGoodsAdapter.notifyDataSetChanged();
                if (goodTemp.getGoodSelectedNumber() == 0){
                    shopcartGoodList.remove(goodTemp);
                    shopcartAdapter.notifyDataSetChanged();
                    popupWindow.dismiss();
                    showPopupwindow();
                }
                if (all_good_price == 0){
                    shopcart_Image.setImageResource(R.drawable.emptyshopcart);
                    all_price_text.setText("未选中商品");
                    all_price_text.setTextColor(ContextCompat.getColor(getContext(), R.color.noTextColor));
                    all_price_text.setTextSize(15);
                    popupWindow.dismiss();
                }
                changedBuyBtnColor();
                break;
            default:

                break;
        }
    }

    private void changedBuyBtnColor(){
        if (all_good_price < dispatch_price){
            int priceTemp = dispatch_price - all_good_price;
            buyBtn.setText("差¥"+ priceTemp +"起送");
            buyBtn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.shopcartbutton));
        }else {
            buyBtn.setText("立即下单");
            buyBtn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.themeColor));
        }
    }
}

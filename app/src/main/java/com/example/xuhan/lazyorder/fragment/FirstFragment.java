package com.example.xuhan.lazyorder.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.xuhan.lazyorder.R;
import com.example.xuhan.lazyorder.activity.ChooseLocationActivity;
import com.example.xuhan.lazyorder.activity.MainActivity;
import com.example.xuhan.lazyorder.activity.ShopMainActivity;
import com.example.xuhan.lazyorder.adapter.FirstFragmentShopsAdapter;
import com.example.xuhan.lazyorder.adapter.ShopcartAdapter;
import com.example.xuhan.lazyorder.model.Shop;
import com.example.xuhan.lazyorder.view.ListViewMaxHeight;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xuhan on 2017/3/28.
 */

public class FirstFragment extends Fragment implements View.OnClickListener, AMapLocationListener {

    PopupWindow popupWindow;
    AMapLocationClient mLocationClient;
    AMapLocationClientOption mLocationOption = null;
    TextView firstLocation_text, firstThreeSpilt;
    ImageView firstLocation_image;
    List<Shop> shopList = new ArrayList<>();
    ListView shopListView;
    FirstFragmentShopsAdapter firstFragmentShopsAdapter;
    String location = null;
    private final static int CHOOSED_STATE = 0;
    private final static int UNCHOOSED_STATE = 1;
    LinearLayout shopKindLinear, intelligentSort_linear, first_screen_linear;
    TextView shopKindText, intelligentSortText, firstScreenText;
    ImageView shopKindImage, intelligentSortImage, firstScreenImage;
    static int shopKindState = 1, intelligentSortState = 1, ScreenState = 1;
    int currentColor;
    TextView mainSpilt;
    RadioGroup mainGroup;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.firstfragment_layout, container, false);
        firstLocation_text = (TextView) view.findViewById(R.id.first_location_text);
        firstLocation_text.setOnClickListener(this);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("location", Context.MODE_PRIVATE);
        location = sharedPreferences.getString("location",null);
        if (savedInstanceState != null){
            location = savedInstanceState.getString("saveLocation");
        }
        if (getArguments() != null){
            Bundle getLocationBundle = getArguments();
            location = getLocationBundle.getString("location");
        }
        if (location == null) {
            if (mLocationClient == null) {
                mLocationClient = new AMapLocationClient(getContext());
                //设置定位回调监听，这里要实现AMapLocationListener接口，AMapLocationListener接口只有onLocationChanged方法可以实现，用于接收异步返回的定位结果，参数是AMapLocation类型。
                mLocationClient.setLocationListener(this);
                //初始化定位参数
                mLocationOption = new AMapLocationClientOption();
                mLocationOption.setLocationCacheEnable(true);
                //设置定位模式为Hight_Accuracy高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
                mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
                //设置是否返回地址信息（默认返回地址信息）
                mLocationOption.setNeedAddress(true);
                //设置是否只定位一次,默认为false
                mLocationOption.setOnceLocation(true);
                //设置是否允许模拟位置,默认为false，不允许模拟位置
                mLocationOption.setMockEnable(false);
                //设置定位间隔,单位毫秒,默认为2000ms
                //mLocationOption.setInterval(2000);
                //给定位客户端对象设置定位参数
                mLocationClient.setLocationOption(mLocationOption);
                //启动定位
                mLocationClient.startLocation();
            }
        }else {
            firstLocation_text.setText(location);
        }


        firstLocation_image = (ImageView) view.findViewById(R.id.first_location_image);
        firstLocation_image.setOnClickListener(this);

        initShopList();
        firstFragmentShopsAdapter = new FirstFragmentShopsAdapter(getContext(), R.layout.first_shop_list_item, shopList);
        shopListView = (ListView) view.findViewById(R.id.first_shop_list);
        shopListView.setAdapter(firstFragmentShopsAdapter);
        shopListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ShopMainActivity.class);
                startActivity(intent);
            }
        });

        firstThreeSpilt = (TextView) view.findViewById(R.id.first_fragment_three_spilt);
        shopKindLinear = (LinearLayout) view.findViewById(R.id.first_shopKind_linear);
        shopKindLinear.setOnClickListener(this);
        intelligentSort_linear = (LinearLayout) view.findViewById(R.id.first_intelligentSort_linear);
        intelligentSort_linear.setOnClickListener(this);
        first_screen_linear = (LinearLayout) view.findViewById(R.id.first_screen_linear);
        first_screen_linear.setOnClickListener(this);
        shopKindText = (TextView) view.findViewById(R.id.first_shopKind_text);
        intelligentSortText = (TextView) view.findViewById(R.id.first_intelligentSort_text);
        currentColor = shopKindText.getCurrentTextColor();
        firstScreenText = (TextView) view.findViewById(R.id.first_screen_text);
        shopKindImage = (ImageView) view.findViewById(R.id.first_shopKind_image);
        intelligentSortImage = (ImageView) view.findViewById(R.id.first_intelligentSort_image);
        firstScreenImage = (ImageView) view.findViewById(R.id.first_screen_image);

        mainGroup = (RadioGroup) getActivity().findViewById(R.id.main_radioGroup);
        mainSpilt = (TextView) getActivity().findViewById(R.id.shop_main_spilt);
        return view;
    }




    private void initShopList() {
        shopList.add(new Shop("小米粥道", "450", "45", "5",  R.drawable.example+"", "34", "1.7", "5"));
        shopList.add(new Shop("小米粥道", "450", "45", "5",  R.drawable.example+"", "34", "1.7", "5"));
        shopList.add(new Shop("小米粥道", "450", "45", "5",  R.drawable.example+"", "34", "1.7", "5"));
        shopList.add(new Shop("小米粥道", "450", "45", "5",  R.drawable.example+"", "34", "1.7", "5"));
        shopList.add(new Shop("小米粥道", "450", "45", "5",  R.drawable.example+"", "34", "1.7", "5"));
        shopList.add(new Shop("小米粥道", "450", "45", "5",  R.drawable.example+"", "34", "1.7", "5"));
        shopList.add(new Shop("小米粥道", "450", "45", "5",  R.drawable.example+"", "34", "1.7", "5"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.first_location_text:
            case R.id.first_location_image:
                Intent intent = new Intent(getActivity(), ChooseLocationActivity.class);
                startActivity(intent);
                break;
            case R.id.first_shopKind_linear:
                if (shopKindState == UNCHOOSED_STATE){
                    shopKindState = CHOOSED_STATE;
                    shopKindText.setTextColor(ContextCompat.getColor(getContext(), R.color.themeColor));
                    shopKindImage.setImageResource(R.drawable.rup);
                    intelligentSortState = UNCHOOSED_STATE;
                    intelligentSortText.setTextColor(currentColor);
                    intelligentSortImage.setImageResource(R.drawable.rdown);
                    ScreenState = UNCHOOSED_STATE;
                    firstScreenText.setTextColor(currentColor);
                    firstScreenImage.setImageResource(R.drawable.rdown);
                    List<String> list1 = new ArrayList<String>();
                    list1.add("美食");
                    list1.add("水果蔬菜");
                    list1.add("早餐");
                    list1.add("夜宵");
                    list1.add("饮品");
                    list1.add("鲜花礼品");
                    list1.add("甜品蛋糕");
                    list1.add("超市");
                    showPopupWindow(R.id.first_shopKind_linear, list1);
                }else {
                    popupWindow.dismiss();
                }
                break;
            case R.id.first_intelligentSort_linear:
                if (intelligentSortState == UNCHOOSED_STATE){
                    intelligentSortState = CHOOSED_STATE;
                    intelligentSortText.setTextColor(ContextCompat.getColor(getContext(), R.color.themeColor));
                    intelligentSortImage.setImageResource(R.drawable.rup);
                    shopKindState = UNCHOOSED_STATE;
                    shopKindText.setTextColor(currentColor);
                    shopKindImage.setImageResource(R.drawable.rdown);
                    ScreenState = UNCHOOSED_STATE;
                    firstScreenText.setTextColor(currentColor);
                    firstScreenImage.setImageResource(R.drawable.rdown);
                    List<String> list2 = new ArrayList<String>();
                    list2.add("综合排序");
                    list2.add("销量最高");
                    list2.add("距离最近");
                    list2.add("评分最高");
                    list2.add("起送价最低");
                    list2.add("配送最快");
                    showPopupWindow(R.id.first_intelligentSort_linear, list2);
                }else {
                    popupWindow.dismiss();
                }
                break;
            case R.id.first_screen_linear:
                if (ScreenState == UNCHOOSED_STATE){
                    ScreenState = CHOOSED_STATE;
                    firstScreenText.setTextColor(ContextCompat.getColor(getContext(), R.color.themeColor));
                    firstScreenImage.setImageResource(R.drawable.rup);
                    intelligentSortState = UNCHOOSED_STATE;
                    intelligentSortText.setTextColor(currentColor);
                    intelligentSortImage.setImageResource(R.drawable.rdown);
                    shopKindState = UNCHOOSED_STATE;
                    shopKindText.setTextColor(currentColor);
                    shopKindImage.setImageResource(R.drawable.rdown);
                    List<String> list3 = new ArrayList<String>();
                    list3.add("美食");
                    list3.add("超市");
                    list3.add("蔬菜水果");
                    list3.add("早餐");
                    list3.add("饮品");
                    list3.add("鲜花礼品");
                    list3.add("甜品蛋糕");
                    list3.add("炒货");
                    showPopupWindow(R.id.first_screen_linear, list3);
                }else {
                    popupWindow.dismiss();
                }
                break;
            default:
                break;
        }
    }

    private void showPopupWindow(final int id, List<String> stringList){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.first_fragment_popupwindow, null);
        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);
        ColorDrawable  cd = new ColorDrawable(0x000000);
        popupWindow.setBackgroundDrawable(cd);
        shopListView.setAlpha(0.5f);
        mainSpilt.setAlpha(0.5f);
        mainGroup.setAlpha(0.5f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                shopListView.setAlpha(1.0f);
                mainSpilt.setAlpha(1.0f);
                mainGroup.setAlpha(1.0f);
                switch (id){
                    case R.id.first_shopKind_linear:
                            shopKindState = UNCHOOSED_STATE;
                            shopKindText.setTextColor(currentColor);
                            shopKindImage.setImageResource(R.drawable.rdown);
                        break;
                    case R.id.first_intelligentSort_linear:
                            intelligentSortState = UNCHOOSED_STATE;
                            intelligentSortText.setTextColor(currentColor);
                            intelligentSortImage.setImageResource(R.drawable.rdown);
                    case R.id.first_screen_linear:
                            ScreenState = UNCHOOSED_STATE;
                            firstScreenText.setTextColor(currentColor);
                            firstScreenImage.setImageResource(R.drawable.rdown);
                        break;
                    default:
                        break;
                }
            }
        });
        ListView listView = (ListView) view.findViewById(R.id.first_fragment_popupwindow_listview);
        listView.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1,stringList));
        popupWindow.showAsDropDown(firstThreeSpilt, 0, 0);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLocationClient != null) {
            if (mLocationClient.isStarted()) {
                mLocationClient.onDestroy();
            }
        }
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("location", Context.MODE_PRIVATE).edit();
        editor.putString("location", firstLocation_text.getText().toString());
        editor.apply();
        if (popupWindow != null) {
            if (popupWindow.isShowing()) {
                popupWindow.dismiss();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("saveLocation", location);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                aMapLocation.getLatitude();//获取纬度
                aMapLocation.getLongitude();//获取经度
                aMapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                df.format(date);//定位时间
                aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                aMapLocation.getCountry();//国家信息
                aMapLocation.getProvince();//省信息
                aMapLocation.getCity();//城市信息
                aMapLocation.getDistrict();//城区信息
                aMapLocation.getStreet();//街道信息
                aMapLocation.getStreetNum();//街道门牌号信息
                aMapLocation.getCityCode();//城市编码
                aMapLocation.getAdCode();//地区编码
                location = aMapLocation.getPoiName();
                firstLocation_text.setText(location);
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("location", Context.MODE_PRIVATE).edit();
                editor.putString("location", firstLocation_text.getText().toString());
                editor.apply();
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
                Toast.makeText(getContext(), "定位失败", Toast.LENGTH_LONG).show();
            }
        }
    }


}

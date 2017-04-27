package com.example.xuhan.lazyorder.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.xuhan.lazyorder.R;
import com.example.xuhan.lazyorder.adapter.ChooseLocationAdapter;
import com.example.xuhan.lazyorder.db.City;
import com.example.xuhan.lazyorder.db.Province;
import com.example.xuhan.lazyorder.model.ChooseLocationItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class ChooseLocationActivity extends BaseActivity implements View.OnClickListener, AMapLocationListener, AdapterView.OnItemClickListener {

    private ProgressDialog progressDialog;
    AMapLocationClient mLocationClient;
    AMapLocationClientOption mLocationOption = null;
    String city = null;
    String location = null;
    ImageView chooseLocation_back;
    TextView local_city_text;
    ImageView down_image;
    TextView addLocation_text, nowLocation_text, nowCity;
    LinearLayout city_linear;
    LinearLayout my_location_linear;
    List<String> searchList = new ArrayList<>();
    StickyListHeadersListView stickyListHeadersListView;
    ChooseLocationAdapter chooseLocationAdapter;
    List<ChooseLocationItem> chooseLocationItemList = new ArrayList<>();
    ListView city_linear_list;
    List<Province> provinceList = new ArrayList<>();
    List<City> cityList = new ArrayList<>();
    List<String> cityNameList = new ArrayList<>();
    ArrayAdapter<String> cityAdapter;

    EditText searchLocationEdit;
    Button searchLocationBtn;
    private int now_level = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_location_layout);
        location();
        local_city_text = (TextView) findViewById(R.id.local_city_text);
        local_city_text.setOnClickListener(this);
        down_image = (ImageView) findViewById(R.id.choose_location_down_image);
        city_linear = (LinearLayout) findViewById(R.id.choose_location_city_linear);
        nowCity = (TextView) findViewById(R.id.now_city_text);
        city_linear_list = (ListView) findViewById(R.id.choose_location_city_linear_list);
        cityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cityNameList);
        city_linear_list.setAdapter(cityAdapter);
        city_linear_list.setOnItemClickListener(this);

        my_location_linear = (LinearLayout) findViewById(R.id.choose_location_my_location_linear);
        chooseLocation_back = (ImageView) findViewById(R.id.choose_location_back_image);
        chooseLocation_back.setOnClickListener(this);
        addLocation_text = (TextView) findViewById(R.id.add_location_text);
        addLocation_text.setOnClickListener(this);
        initChooseLocationList();
        stickyListHeadersListView = (StickyListHeadersListView) findViewById(R.id.choose_location_stickyList);
        chooseLocationAdapter = new ChooseLocationAdapter(this, chooseLocationItemList);
        stickyListHeadersListView.setAdapter(chooseLocationAdapter);

        nowLocation_text = (TextView) findViewById(R.id.choose_location_now_location_text);
        nowLocation_text.setOnClickListener(this);

        searchLocationEdit = (EditText) findViewById(R.id.search_location_edit);
        searchLocationBtn = (Button) findViewById(R.id.choose_locaton_search_btn);
        searchLocationBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.choose_location_back_image:
                finish();
                break;
            case R.id.add_location_text:
                Intent addLocationIntent = new Intent(ChooseLocationActivity.this, AddLocationActivity.class);
                startActivity(addLocationIntent);
                break;
            case R.id.local_city_text:
                if (city_linear.getVisibility() == View.GONE){
                    down_image.setImageResource(R.drawable.rup);
                    city_linear.setVisibility(View.VISIBLE);
                    my_location_linear.setVisibility(View.GONE);
                    stickyListHeadersListView.setVisibility(View.GONE);
                    //getProvinceWithOkHttp();
                    queryProvince();
                }else {
                    down_image.setImageResource(R.drawable.rdown);
                    city_linear.setVisibility(View.GONE);
                    my_location_linear.setVisibility(View.VISIBLE);
                    stickyListHeadersListView.setVisibility(View.VISIBLE);
                    provinceList.clear();
                    cityList.clear();
                    cityNameList.clear();
                    now_level = 0;
                }
                break;
            case R.id.search_location_edit:
                break;

            case R.id.choose_location_now_location_text:
                Intent sendNowLocationIntent = new Intent(ChooseLocationActivity.this, MainActivity.class);
                sendNowLocationIntent.putExtra("nowLocation", location);
                startActivity(sendNowLocationIntent);
                break;

            case R.id.choose_locaton_search_btn:


                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.choose_location_city_linear_list:
                if (now_level == 0){
                    now_level = 1;
                    queryCity(provinceList.get(position).getId()+"");

                }else {
                    now_level = 0;
                    local_city_text.setText(cityNameList.get(position)+"市");
                    down_image.setImageResource(R.drawable.rdown);
                    city_linear.setVisibility(View.GONE);
                    my_location_linear.setVisibility(View.VISIBLE);
                    stickyListHeadersListView.setVisibility(View.VISIBLE);
                    provinceList.clear();
                    cityList.clear();
                    cityNameList.clear();

                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (city_linear.getVisibility() == View.VISIBLE){
            down_image.setImageResource(R.drawable.rdown);
            city_linear.setVisibility(View.GONE);
            my_location_linear.setVisibility(View.VISIBLE);
            stickyListHeadersListView.setVisibility(View.VISIBLE);
            provinceList.clear();
            cityList.clear();
            cityNameList.clear();
            now_level = 0;
        }else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ChooseLocationAdapter.stringList.clear();
        ChooseLocationAdapter.locationList.clear();
        if (mLocationClient.isStarted()) {
            mLocationClient.onDestroy();
        }
    }

    private void initChooseLocationList() {
        chooseLocationItemList.add(new ChooseLocationItem(R.drawable.mylocation + "", "我的收货地址", "0"));
        chooseLocationItemList.add(new ChooseLocationItem(R.drawable.nearlocation + "", "附近地址", "1"));
    }



    private void location(){
        if (mLocationClient == null) {
            mLocationClient = new AMapLocationClient(getApplicationContext());
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
                city = aMapLocation.getCity();
                local_city_text.setText(city);
                nowCity.setText(city);
                location = aMapLocation.getPoiName();
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
                Toast.makeText(this, "定位失败", Toast.LENGTH_LONG).show();
            }
        }
    }


    private void queryProvince(){
        provinceList = DataSupport.findAll(Province.class);
        if (provinceList.size() > 0){
            for (Province province: provinceList
                    ) {
                cityNameList.add(province.getName());

            }
            cityAdapter.notifyDataSetChanged();


        }else{
            getProvinceWithOkHttp();
        }
    }

    private void getProvinceWithOkHttp(){
        showProgressDialog();
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://guolin.tech/api/china").build();
                try {
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseProvinceWithGson(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ChooseLocationActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
                        }
                    });

                }finally {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            closeProgressDialog();
                        }
                    });

                }
            }
        }).start();
    }


    private void parseProvinceWithGson(final String provinceJson){
        Gson gson = new Gson();
        provinceList = gson.fromJson(provinceJson, new TypeToken<List<Province>>(){}.getType());
        now_level = 0;
        for (Province province: provinceList
             ) {
            cityNameList.add(province.getName());
            province.save();
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cityAdapter.notifyDataSetChanged();

            }
        });
    }

    private void queryCity(String id){
        cityList = DataSupport.where("provinceId = ?", id).find(City.class);
        if (cityList.size() > 0){
            cityNameList.clear();
            for (City city: cityList
                    ) {
                cityNameList.add(city.getName());

            }
        }else {
            getCityWithOkHttp(id);
        }
    }

    private void getCityWithOkHttp(final String id){
        showProgressDialog();
        new Thread(new Runnable() {
            @Override
            public void run() {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://guolin.tech/api/china/" + id).build();
                try {
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseCityWithGson(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ChooseLocationActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
                        }
                    });

                }finally {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            closeProgressDialog();
                        }
                    });
                }

                }
        }).start();
    }

    private void parseCityWithGson(String responseData) {
        now_level = 1;
        Gson gson = new Gson();
        cityList = gson.fromJson(responseData, new TypeToken<List<City>>(){}.getType());
        cityNameList.clear();
        for (City city: cityList
             ) {
            cityNameList.add(city.getName());
            city.save();
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cityAdapter.notifyDataSetChanged();
            }
        });
    }

    private void showProgressDialog(){
        if (progressDialog == null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    private void closeProgressDialog(){
        if (progressDialog != null){
            progressDialog.dismiss();
        }
    }
}

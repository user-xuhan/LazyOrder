package com.example.xuhan.lazyorder.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.Nullable;
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
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.example.xuhan.lazyorder.R;
import com.example.xuhan.lazyorder.adapter.ChooseLocationMapAdapter;
import com.example.xuhan.lazyorder.db.City;
import com.example.xuhan.lazyorder.db.Province;
import com.example.xuhan.lazyorder.model.ChooseLocationMapItem;
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

/**
 * Created by xuhan on 2017/4/26.
 */

public class ChooseLocationMapActivity extends BaseActivity implements View.OnClickListener {

    ImageView backImage;
    MapView mapView;
    AMap aMap;
    ListView chooseMapListview;
    LinearLayout mapCityLinear;
    TextView mapLocalCityText, mapNowCityText;
    ImageView mapLocalImage;
    ListView mapCityListView;
    EditText mapSearchLocationEdit;
    Button mapSearchBtn;


    LatLng myLatLng = null;
    Marker marker;
    ChooseLocationMapAdapter chooseLocationMapAdapter;
    List<ChooseLocationMapItem> chooseLocationMapItemsList = new ArrayList<>();

    PoiSearch.Query query;
    PoiSearch poiSearch;
    private AMapLocationClient mlocationClient = null;

    private final static int LOCATION_NUM = 0;
    private final static int QUERY_NUM = 1;

    ProgressDialog progressDialog;
    private static int now_level = 0;
    private List<Province> provinceList = new ArrayList<>();
    private List<City> cityList = new ArrayList<>();
    List<String> stringList = new ArrayList<>();
    ArrayAdapter<String> stringArrayAdapter;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case LOCATION_NUM:
                    myLatLng = msg.getData().getParcelable("latLng");
                    if (marker == null){
                        drawMarker(myLatLng);
                    }
                    query(myLatLng);
                    break;
                case QUERY_NUM:
                    List<PoiItem> poiItems = msg.getData().getParcelableArrayList("poiItems");
                    if (stringList.size() == 0) {
                        for (PoiItem poiItem : poiItems
                                ) {
                            ChooseLocationMapItem chooseLocationMapItem = new ChooseLocationMapItem();
                            chooseLocationMapItem.setPoiName(poiItem.getTitle());
                            chooseLocationMapItem.setDetailAddress(poiItem.getSnippet());
                            chooseLocationMapItem.setLatLng(new LatLng(poiItem.getLatLonPoint().getLatitude(), poiItem.getLatLonPoint().getLongitude()));
                            //stringList.add(poiItem.getTitle());
                            chooseLocationMapItemsList.add(chooseLocationMapItem);
                        }
                        chooseLocationMapAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_location_map_layout);
        backImage = (ImageView) findViewById(R.id.choose_location_map_back_image);
        backImage.setOnClickListener(this);
        mapLocalImage = (ImageView) findViewById(R.id.choose_location_map_down_image);
        mapCityLinear = (LinearLayout) findViewById(R.id.choose_location_map_city_linear);
        mapNowCityText = (TextView) findViewById(R.id.choose_location_map_now_city_text);
        mapCityListView = (ListView) findViewById(R.id.choose_location__map_city_linear_list);

        location();
        mapView = (MapView) findViewById(R.id.choose_location_map);
        mapView.onCreate(savedInstanceState);
        if (aMap == null){
            aMap = mapView.getMap();
            aMap.getUiSettings().setMyLocationButtonEnabled(true);

        }


        chooseMapListview = (ListView) findViewById(R.id.choose_map_location_list);
        //initStringList();
        chooseLocationMapAdapter = new ChooseLocationMapAdapter(this, R.layout.choose_location_map_list_item, chooseLocationMapItemsList);
        chooseMapListview.setAdapter(chooseLocationMapAdapter);

        mapLocalCityText = (TextView) findViewById(R.id.choose_map_local_city_text);
        mapLocalCityText.setOnClickListener(this);

        stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringList);
        mapCityListView.setAdapter(stringArrayAdapter);
        mapCityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (now_level == 0){
                    now_level = 1;
                    queryCity(provinceList.get(position).getId()+"");

                }else {
                    now_level = 0;
                    mapLocalCityText.setText(stringList.get(position)+"市");
                    mapLocalImage.setImageResource(R.drawable.rdown);
                    mapCityLinear.setVisibility(View.GONE);
                    mapView.setVisibility(View.VISIBLE);
                    chooseMapListview.setVisibility(View.VISIBLE);
                    provinceList.clear();
                    cityList.clear();
                    stringList.clear();

                }
            }
        });

        mapSearchLocationEdit = (EditText) findViewById(R.id.chhoose_map_search_location_edit);
        mapSearchBtn = (Button) findViewById(R.id.choose_map_search_btn);
        mapSearchBtn.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.choose_location_map_back_image:
                finish();
                break;
            case R.id.choose_map_local_city_text:
                if (mapCityLinear.getVisibility() == View.GONE){
                    mapCityLinear.setVisibility(View.VISIBLE);
                    mapView.setVisibility(View.GONE);
                    chooseMapListview.setVisibility(View.GONE);
                    mapLocalImage.setImageResource(R.drawable.rup);
                    queryProvince();
                }else {
                    mapCityLinear.setVisibility(View.GONE);
                    mapView.setVisibility(View.VISIBLE);
                    chooseMapListview.setVisibility(View.VISIBLE);
                    mapLocalImage.setImageResource(R.drawable.rdown);
                    provinceList.clear();
                    cityList.clear();
                    stringList.clear();
                    now_level = 0;
                }
                break;
            case R.id.choose_map_search_btn:




                break;
            default:
                break;
        }
    }

    private void location(){
        //声明mLocationOption对象
        AMapLocationClientOption mLocationOption = null;
        mlocationClient = new AMapLocationClient(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位监听
        mlocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        //定位成功回调信息，设置相关消息
                        amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                        //latitude = amapLocation.getLatitude();//获取纬度
                        //longitude = amapLocation.getLongitude();//获取经度
                        amapLocation.getAccuracy();//获取精度信息
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(amapLocation.getTime());
                        df.format(date);//定位时间
                        mapLocalCityText.setText(amapLocation.getCity());
                        mapNowCityText.setText(amapLocation.getCity());
                        myLatLng = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());
                        Message message = new Message();
                        message.what = LOCATION_NUM;
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("latLng", myLatLng);
                        message.setData(bundle);
                        handler.sendMessage(message);
                    } else {
                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError","location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                    }
                }
            }
        });
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
// 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
// 在定位结束后，在合适的生命周期调用onDestroy()方法
// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
//启动定位
        mlocationClient.startLocation();

    }

    private void drawMarker(LatLng latLng){
        marker = aMap.addMarker(new MarkerOptions().draggable(true).position(latLng).visible(true));
        aMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng, 16f, 0, 0)));
        //Toast.makeText(this, ""+latLng.latitude, Toast.LENGTH_SHORT).show();
    }

    private void query(LatLng latLng){

        query = new PoiSearch.Query("", "", "合肥");
        //keyWord表示搜索字符串，
        //第二个参数表示POI搜索类型，二者选填其一，
        //POI搜索类型共分为以下20种：汽车服务|汽车销售|
        //汽车维修|摩托车服务|餐饮服务|购物服务|生活服务|体育休闲服务|医疗保健服务|
        //住宿服务|风景名胜|商务住宅|政府机构及社会团体|科教文化服务|交通设施服务|
        //金融保险服务|公司企业|道路附属设施|地名地址信息|公共设施
        //cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
        query.setPageSize(10);// 设置每页最多返回多少条poiitem
        query.setPageNum(0);//设置查询页码

        poiSearch = new PoiSearch(this, query);
        poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(latLng.latitude, latLng.longitude), 1000));
        poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
            @Override
            public void onPoiSearched(PoiResult poiResult, int i) {
                List<PoiItem> poiItems = poiResult.getPois();
                Message message = new Message();
                message.what = QUERY_NUM;
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("poiItems", (ArrayList<? extends Parcelable>) poiItems);
                message.setData(bundle);
                handler.sendMessage(message);
            }

            @Override
            public void onPoiItemSearched(PoiItem poiItem, int i) {

            }
        });
        poiSearch.searchPOIAsyn();
    }

    private void queryProvince(){
        provinceList = DataSupport.findAll(Province.class);
        if (provinceList.size() > 0){
            for (Province province: provinceList
                    ) {
                stringList.add(province.getName());

            }
            stringArrayAdapter.notifyDataSetChanged();


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
                            Toast.makeText(ChooseLocationMapActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
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
            stringList.add(province.getName());
            province.save();
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                stringArrayAdapter.notifyDataSetChanged();

            }
        });
    }

    private void queryCity(String id){
        cityList = DataSupport.where("provinceId = ?", id).find(City.class);
        if (cityList.size() > 0){
            stringList.clear();
            for (City city: cityList
                    ) {
                stringList.add(city.getName());

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
                            Toast.makeText(ChooseLocationMapActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
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
        stringList.clear();
        for (City city: cityList
                ) {
            stringList.add(city.getName());
            city.save();
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                stringArrayAdapter.notifyDataSetChanged();
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

    @Override
    public void onBackPressed() {
        if (mapCityLinear.getVisibility() == View.VISIBLE){
            mapLocalImage.setImageResource(R.drawable.rdown);
            mapCityLinear.setVisibility(View.GONE);
            mapView.setVisibility(View.VISIBLE);
            chooseMapListview.setVisibility(View.VISIBLE);
            provinceList.clear();
            cityList.clear();
            stringList.clear();
            now_level = 0;
        }else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mlocationClient != null) {
            if (mlocationClient.isStarted()) {
                mlocationClient.onDestroy();
            }
        }
        mapView.onDestroy();
        stringList.clear();
        chooseLocationMapItemsList.clear();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}

package com.example.xuhan.lazyorder.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.example.xuhan.lazyorder.R;

/**
 * Created by xuhan on 2017/4/20.
 */

public class MapActivity extends BaseActivity implements GeocodeSearch.OnGeocodeSearchListener {

    MapView mapView = null;
    AMap aMap;
    MyLocationStyle myLocationStyle;
    GeocodeSearch geocodeSearch;
    LatLng latLng = null;
    String location;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        if (aMap == null){
            aMap = mapView.getMap();
        }
        geocodeSearch = new GeocodeSearch(this);
        geocodeSearch.setOnGeocodeSearchListener(this);
        Intent getMapIntent = getIntent();
        location = getMapIntent.getStringExtra("location");
        GeocodeQuery geocodeQuery = new GeocodeQuery(location, "合肥");
        geocodeSearch.getFromLocationNameAsyn(geocodeQuery);


        myLocationStyle = new MyLocationStyle();//定位样式
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);
        aMap.setMyLocationStyle(myLocationStyle);
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置定位按钮
        aMap.setMyLocationEnabled(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
        if (i == 1000){
            double latitude = geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint().getLatitude();
            double longtitude = geocodeResult.getGeocodeAddressList().get(0).getLatLonPoint().getLongitude();
            latLng = new LatLng(latitude, longtitude);
            Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title(location));
            aMap.moveCamera(
                    CameraUpdateFactory.newCameraPosition(new CameraPosition(
                            latLng,//新的中心点坐标
                            15, //新的缩放级别
                            0, //俯仰角0°~45°（垂直与地图时为0）
                            0  ////偏航角 0~360° (正北方为0)
                    ))
            );
        }
    }
}

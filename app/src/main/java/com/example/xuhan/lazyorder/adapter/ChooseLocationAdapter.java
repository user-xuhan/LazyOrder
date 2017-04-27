package com.example.xuhan.lazyorder.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.example.xuhan.lazyorder.R;
import com.example.xuhan.lazyorder.activity.ChooseLocationActivity;
import com.example.xuhan.lazyorder.activity.MainActivity;
import com.example.xuhan.lazyorder.model.ChooseLocationItem;
import com.example.xuhan.lazyorder.model.Location;
import com.example.xuhan.lazyorder.view.ListViewForScrollView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by xuhan on 2017/4/8.
 */

public class ChooseLocationAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    AMapLocationClient mLocationClient;

    private PoiSearch poiSearch;
    private PoiSearch.Query query;
    private double latitude;
    private double longtitude;
    private String city = null;
    private LayoutInflater inflater;
    private List<ChooseLocationItem> chooseLocationItemList = new ArrayList<>();
    public static List<String> stringList = new ArrayList<>();
    public static List<Location> locationList = new ArrayList<>();
    private final static int LOCATION_MESSAGE = 0;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    ArrayList<PoiItem> poiItems = msg.getData().getParcelableArrayList("poiItem");
                    if (stringList.size() == 0) {
                        if (poiItems.size() > 10) {
                            for (int i = 0; i < 10; i++) {
                                String poiName = poiItems.get(i).getTitle();
                                stringList.add(poiName);
                            }
                        } else {
                            for (int i = 0; i < poiItems.size(); i++) {
                                String poiName = poiItems.get(i).getTitle();
                                stringList.add(poiName);
                            }
                        }
                    }
                    notifyDataSetChanged();
                    mLocationClient.onDestroy();
                    break;
                default:
                    break;
            }
        }
    };
    public ChooseLocationAdapter(Activity context, List<ChooseLocationItem> chooseLocationItems){
        this.inflater = LayoutInflater.from(context);
        this.chooseLocationItemList = chooseLocationItems;
    }
    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        ChooseLocationItem chooseLocationItem = (ChooseLocationItem) getItem(position);
        HeaderViewHolder headerViewHolder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.choose_location_stickylist_header_item, parent, false);
            headerViewHolder = new HeaderViewHolder();
            headerViewHolder.chooseLocationImage = (ImageView) convertView.findViewById(R.id.choose_location_stickyList_header_image);
            headerViewHolder.chooseLocationText = (TextView) convertView.findViewById(R.id.choose_location_stickyList_header_text);
            convertView.setTag(headerViewHolder);
        }else {
            headerViewHolder = (HeaderViewHolder) convertView.getTag();
        }
        headerViewHolder.chooseLocationImage.setImageResource(Integer.parseInt(chooseLocationItem.getHeaderImage()));
        headerViewHolder.chooseLocationText.setText(chooseLocationItem.getHeaderText());
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return Long.parseLong(chooseLocationItemList.get(position).getHeaderId());
    }

    @Override
    public int getCount() {
        return chooseLocationItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return chooseLocationItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ChooseLocationItem chooseLocationItem = (ChooseLocationItem) getItem(position);
        final View view;
        ViewHolder viewHolder;
        if (convertView == null){
            view = inflater.inflate(R.layout.choose_location_stickylist_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.chooseLocationListView = (ListViewForScrollView) view.findViewById(R.id.choose_location_stickyList_item_list);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        if (stringList.size() == 0){
            mLocationClient = new AMapLocationClient(view.getContext());
            mLocationClient.setLocationListener(new AMapLocationListener() {
                @Override
                public void onLocationChanged(AMapLocation aMapLocation) {
                    if (aMapLocation != null) {
                        if (aMapLocation.getErrorCode() == 0) {
                            //定位成功回调信息，设置相关消息
                            aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                            latitude = aMapLocation.getLatitude();//获取纬度
                            longtitude = aMapLocation.getLongitude();//获取经度
                            aMapLocation.getAccuracy();//获取精度信息
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = new Date(aMapLocation.getTime());
                            df.format(date);//定位时间
                            aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                            aMapLocation.getCountry();//国家信息
                            aMapLocation.getProvince();//省信息
                            city = aMapLocation.getCity();//城市信息
                            aMapLocation.getDistrict();//城区信息
                            aMapLocation.getStreet();//街道信息
                            aMapLocation.getStreetNum();//街道门牌号信息
                            aMapLocation.getCityCode();//城市编码
                            aMapLocation.getAdCode();//地区编码

                        } else {
                            //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                            Log.e("AmapError", "location Error, ErrCode:"
                                    + aMapLocation.getErrorCode() + ", errInfo:"
                                    + aMapLocation.getErrorInfo());
                            Toast.makeText(view.getContext(), "定位失败", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
            //初始化定位参数
            AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
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

            new Thread(new Runnable() {
                @Override
                public void run() {
                    query = new PoiSearch.Query("", "", city);
                    //keyWord表示搜索字符串，
                    //第二个参数表示POI搜索类型，二者选填其一，
                    //POI搜索类型共分为以下20种：汽车服务|汽车销售|
                    //汽车维修|摩托车服务|餐饮服务|购物服务|生活服务|体育休闲服务|医疗保健服务|
                    //住宿服务|风景名胜|商务住宅|政府机构及社会团体|科教文化服务|交通设施服务|
                    //金融保险服务|公司企业|道路附属设施|地名地址信息|公共设施
                    //cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
                    query.setPageSize(10);// 设置每页最多返回多少条poiitem
                    query.setPageNum(0);//设置查询页码
                    poiSearch = new PoiSearch(view.getContext(), query);
                    poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(latitude, longtitude), 3000));
                    poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
                        @Override
                        public void onPoiSearched(PoiResult poiResult, int i) {
                            ArrayList<PoiItem> poiItems = poiResult.getPois();
//                        for (PoiItem poiItem: poiItems
//                                ) {
//                            String poiName = poiItem.getTitle();
//                            stringList.add(poiName);
//                        }
                            Message message = new Message();
                            message.what = LOCATION_MESSAGE;
                            Bundle bundle = new Bundle();
                            bundle.putParcelableArrayList("poiItem", poiItems);
                            message.setData(bundle);
                            handler.sendMessage(message);

                        }

                        @Override
                        public void onPoiItemSearched(PoiItem poiItem, int i) {

                        }
                    });
                    poiSearch.searchPOIAsyn();
                }
            }).start();
        }


        if (position == 0 ){
            if (locationList.size() == 0) {
                initLocationList();
                viewHolder.chooseLocationListView.setAdapter(new ChooseLocationItemAdapter(view.getContext(), R.layout.choose_location_item_list_item, locationList));
            }
        }else if (position == 1){
//            if(stringList.size() == 0) {
//                initStringList();
//            }
            viewHolder.chooseLocationListView.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, stringList));
            viewHolder.chooseLocationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    Intent sendLocationIntent = new Intent(view.getContext(), MainActivity.class);
                    sendLocationIntent.putExtra("chooseLocation", stringList.get(position));
                    view.getContext().startActivity(sendLocationIntent);
//                    Toast.makeText(view.getContext(), stringList.get(position), Toast.LENGTH_SHORT).show();
                }
            });
        }


        return view;
    }

    private void initLocationList() {
        locationList.add(new Location("合肥工业大学翡翠湖校区", "17751919987", "徐晗", "先生"));
        locationList.add(new Location("合肥工业大学翡翠湖校区", "17751919987", "徐晗", "先生"));
        locationList.add(new Location("合肥工业大学翡翠湖校区", "17751919987", "徐晗", "先生"));
        locationList.add(new Location("合肥工业大学翡翠湖校区", "17751919987", "徐晗", "先生"));
    }

    private void initStringList() {
        stringList.add("合肥工业大学翡翠湖校区");
        stringList.add("合肥工业大学翡翠湖校区");
        stringList.add("合肥工业大学翡翠湖校区");
        stringList.add("合肥工业大学翡翠湖校区");
        stringList.add("合肥工业大学翡翠湖校区");
        stringList.add("合肥工业大学翡翠湖校区");
    }

    private class HeaderViewHolder{
        ImageView chooseLocationImage;
        TextView chooseLocationText;
    }

    private class ViewHolder{
        ListViewForScrollView chooseLocationListView;
    }

}

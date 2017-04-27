package com.example.xuhan.lazyorder.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.xuhan.lazyorder.R;
import com.example.xuhan.lazyorder.adapter.MyLocationAdapter;
import com.example.xuhan.lazyorder.model.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuhan on 2017/4/8.
 */

public class MyLocationActivity extends BaseActivity implements View.OnClickListener {
    ImageView myLocation_back_image;
    ListView myLocationListView;
    List<Location> locationList = new ArrayList<>();
    Button addLocationBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylocation_layout);
        myLocation_back_image = (ImageView) findViewById(R.id.my_location_back_image);
        myLocation_back_image.setOnClickListener(this);
        myLocationListView = (ListView) findViewById(R.id.my_location_list);
        initLocationList();
        myLocationListView.setAdapter(new MyLocationAdapter(this, R.layout.mylocation_list_item, locationList));
        addLocationBtn = (Button) findViewById(R.id.my_location_add_btn);
        addLocationBtn.setOnClickListener(this);
    }

    private void initLocationList() {
        locationList.add(new Location("合肥工业大学翡翠湖校区六号楼", "17751919987", "徐晗", "先生"));
        locationList.add(new Location("合肥工业大学翡翠湖校区六号楼", "17751919987", "徐晗", "先生"));
        locationList.add(new Location("合肥工业大学翡翠湖校区六号楼", "17751919987", "徐晗", "先生"));
        locationList.add(new Location("合肥工业大学翡翠湖校区六号楼", "17751919987", "徐晗", "先生"));
        locationList.add(new Location("合肥工业大学翡翠湖校区六号楼", "17751919987", "徐晗", "先生"));
        locationList.add(new Location("合肥工业大学翡翠湖校区六号楼", "17751919987", "徐晗", "先生"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_location_back_image:
                finish();
                break;
            case R.id.my_location_add_btn:
                Intent addLocationIntent = new Intent(this, AddLocationActivity.class);
                startActivity(addLocationIntent);
            default:
                break;
        }
    }
}

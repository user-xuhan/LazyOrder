package com.example.xuhan.lazyorder.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xuhan.lazyorder.R;
import com.example.xuhan.lazyorder.activity.MapActivity;

/**
 * Created by xuhan on 2017/3/30.
 */

public class ShopMainDetailFragment extends Fragment implements View.OnClickListener {
    LinearLayout detailPhone;
    TextView phoneText;
    LinearLayout detailLocation;
    TextView locationText;
    LinearLayout detailTime;
    LinearLayout detailMessage;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_main_detailfragment_layout, container, false);
        detailPhone = (LinearLayout) view.findViewById(R.id.shop_main_detail_phone_linear);
        phoneText = (TextView) view.findViewById(R.id.shop_main_detail_phone_text);
        detailPhone.setOnClickListener(this);
        detailLocation = (LinearLayout) view.findViewById(R.id.shop_main_detail_location_linear);
        locationText = (TextView) view.findViewById(R.id.shop_main_detail_location_text);
        detailLocation.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shop_main_detail_phone_linear:
                break;
            case R.id.shop_main_detail_location_linear:
                Intent mapIntent = new Intent(getActivity(), MapActivity.class);
                mapIntent.putExtra("location", locationText.getText().toString());
                startActivity(mapIntent);
                break;
            default:
                break;
        }
    }
}

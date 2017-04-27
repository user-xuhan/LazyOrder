package com.example.xuhan.lazyorder.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xuhan.lazyorder.R;
import com.example.xuhan.lazyorder.activity.LoginActivity;
import com.example.xuhan.lazyorder.activity.MyCollectActivity;
import com.example.xuhan.lazyorder.activity.MyLocationActivity;

/**
 * Created by xuhan on 2017/3/28.
 */

public class MyFragment extends Fragment implements View.OnClickListener {

    ImageView my_person_image;
    TextView login_register_text;
    LinearLayout myLocationLinear;
    LinearLayout myCollectLinear;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myfragment_layout, container, false);
        my_person_image = (ImageView) view.findViewById(R.id.my_person_image);
        my_person_image.setOnClickListener(this);
        login_register_text = (TextView) view.findViewById(R.id.my_login_register_text);
        login_register_text.setOnClickListener(this);

        myLocationLinear = (LinearLayout) view.findViewById(R.id.my_location_linear);
        myLocationLinear.setOnClickListener(this);
        myCollectLinear = (LinearLayout) view.findViewById(R.id.my_collect_linear);
        myCollectLinear.setOnClickListener(this);
        return  view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_person_image:
            case R.id.my_login_register_text:
                Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                startActivity(loginIntent);
                break;
            case R.id.my_location_linear:
                Intent myLocationIntent = new Intent(getActivity(), MyLocationActivity.class);
                startActivity(myLocationIntent);
                break;
            case R.id.my_collect_linear:
                Intent myCollectIntent = new Intent(getActivity(), MyCollectActivity.class);
                startActivity(myCollectIntent);
                break;
            default:
                break;
        }
    }
}

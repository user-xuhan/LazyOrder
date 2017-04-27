package com.example.xuhan.lazyorder.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.xuhan.lazyorder.R;

public class AddLocationActivity extends BaseActivity implements View.OnClickListener {

    ImageView addLocation_back;
    RadioGroup addLocation_sex_group;
    TextView locationBtnText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_location_layout);

        addLocation_back = (ImageView) findViewById(R.id.add_location_back_image);
        addLocation_back.setOnClickListener(this);
        addLocation_sex_group = (RadioGroup) findViewById(R.id.add_location_sex_group);
        addLocation_sex_group.check(R.id.add_location_man_radio);
        locationBtnText = (TextView) findViewById(R.id.location_location_btn_text);
        locationBtnText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_location_back_image:
                finish();
                break;
            case R.id.location_location_btn_text:
                Intent chooseMapIntent = new Intent(AddLocationActivity.this, ChooseLocationMapActivity.class);
                startActivity(chooseMapIntent);
                break;
            default:
                break;
        }
    }
}

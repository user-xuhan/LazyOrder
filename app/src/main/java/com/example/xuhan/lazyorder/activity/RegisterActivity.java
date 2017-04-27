package com.example.xuhan.lazyorder.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.xuhan.lazyorder.R;

/**
 * Created by xuhan on 2017/3/28.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    ImageView back_image;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        back_image = (ImageView) findViewById(R.id.register_back_image);
        back_image.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_back_image:
                finish();
                break;

            default:
                break;
        }
    }
}

package com.example.xuhan.lazyorder.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xuhan.lazyorder.R;

/**
 * Created by xuhan on 2017/3/28.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    TextView register_text;
    ImageView back_image;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        ActionBar actionBar = getSupportActionBar();

        register_text = (TextView) findViewById(R.id.register_text);
        register_text.setOnClickListener(this);
        back_image = (ImageView) findViewById(R.id.login_back_image);
        back_image.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_text:
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
                break;
            case R.id.login_back_image:
                finish();
                break;

            default:
                break;
        }
    }
}

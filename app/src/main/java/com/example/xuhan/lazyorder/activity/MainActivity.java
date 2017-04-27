package com.example.xuhan.lazyorder.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xuhan.lazyorder.R;
import com.example.xuhan.lazyorder.fragment.FirstFragment;
import com.example.xuhan.lazyorder.fragment.MyFragment;
import com.example.xuhan.lazyorder.fragment.OrdersFragment;
import com.example.xuhan.lazyorder.view.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends BaseActivity {

    RadioGroup main_radioGroup;
    public String location = null;
    Intent getLocationIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        //replaceFragment(new FirstFragment());
        main_radioGroup = (RadioGroup) findViewById(R.id.main_radioGroup);

        main_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (main_radioGroup.getCheckedRadioButtonId()){
                    case R.id.first_radio:
                        replaceFragment(new FirstFragment());
                        break;
                    case R.id.orders_radio:
                        replaceFragment(new OrdersFragment());
                        break;
                    case R.id.my_radio:
                        replaceFragment(new MyFragment());
                        break;
                    default:
                        break;
                }
            }
        });
        Intent getNowIntent = getIntent();
        location = getNowIntent.getStringExtra("nowLocation");
        FirstFragment firstFragment = new FirstFragment();
        Bundle firstBundle = new Bundle();
        firstBundle.putString("location", location);
        firstFragment.setArguments(firstBundle);
        replaceFragment(firstFragment);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (main_radioGroup.getCheckedRadioButtonId() == R.id.first_radio){
            Intent getLocationIntent = getIntent();
            location = getLocationIntent.getStringExtra("chooseLocation");
            FirstFragment firstFragment = new FirstFragment();
            Bundle firstBundle = new Bundle();
            firstBundle.putString("location", location);
            firstFragment.setArguments(firstBundle);
            replaceFragment(firstFragment);
            //Toast.makeText(this, location, Toast.LENGTH_SHORT).show();
        }



    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_frameLayout, fragment);
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editor = getSharedPreferences("location", Context.MODE_PRIVATE).edit();
        editor.clear();

    }
}

package com.example.xuhan.lazyorder.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xuhan.lazyorder.R;

/**
 * Created by xuhan on 2017/4/21.
 */

public class EvaluateActivity extends BaseActivity implements View.OnClickListener {

    ImageView evaluate_back_image;
    TextView evaluateShopName;
    EditText evaluateEdit;
    Button submitEvaluateBtn;
    ImageView evaluateShopStar1, evaluateShopStar2, evaluateShopStar3, evaluateShopStar4, evaluateShopStar5;
    ImageView evaluateDispatchStar1, evaluateDispatchStar2, evaluateDispatchStar3, evaluateDispatchStar4, evaluateDispatchStar5;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluate_layout);
        evaluate_back_image = (ImageView) findViewById(R.id.evaluate_back_image);
        evaluate_back_image.setOnClickListener(this);
        evaluateShopName = (TextView) findViewById(R.id.evaluate_shop_name);
        evaluateEdit = (EditText) findViewById(R.id.evaluate_edit);
        submitEvaluateBtn = (Button) findViewById(R.id.evaluate_submit_btn);

        evaluateShopStar1 = (ImageView) findViewById(R.id.evaluate_shop_credit_star1);
        evaluateShopStar2 = (ImageView) findViewById(R.id.evaluate_shop_credit_star2);
        evaluateShopStar3 = (ImageView) findViewById(R.id.evaluate_shop_credit_star3);
        evaluateShopStar4 = (ImageView) findViewById(R.id.evaluate_shop_credit_star4);
        evaluateShopStar5 = (ImageView) findViewById(R.id.evaluate_shop_credit_star5);
        evaluateShopStar1.setOnClickListener(this);
        evaluateShopStar2.setOnClickListener(this);
        evaluateShopStar3.setOnClickListener(this);
        evaluateShopStar4.setOnClickListener(this);
        evaluateShopStar5.setOnClickListener(this);
        evaluateDispatchStar1 = (ImageView) findViewById(R.id.evaluate_dispatch_credit_star1);
        evaluateDispatchStar2 = (ImageView) findViewById(R.id.evaluate_dispatch_credit_star2);
        evaluateDispatchStar3 = (ImageView) findViewById(R.id.evaluate_dispatch_credit_star3);
        evaluateDispatchStar4 = (ImageView) findViewById(R.id.evaluate_dispatch_credit_star4);
        evaluateDispatchStar5 = (ImageView) findViewById(R.id.evaluate_dispatch_credit_star5);
        evaluateDispatchStar1.setOnClickListener(this);
        evaluateDispatchStar2.setOnClickListener(this);
        evaluateDispatchStar3.setOnClickListener(this);
        evaluateDispatchStar4.setOnClickListener(this);
        evaluateDispatchStar5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.evaluate_back_image:
                finish();
                break;
            case R.id.evaluate_shop_credit_star1:
                evaluateShopStar1.setImageResource(R.drawable.fullstar);
                evaluateShopStar2.setImageResource(R.drawable.star);
                evaluateShopStar3.setImageResource(R.drawable.star);
                evaluateShopStar4.setImageResource(R.drawable.star);
                evaluateShopStar5.setImageResource(R.drawable.star);
                break;
            case R.id.evaluate_shop_credit_star2:
                evaluateShopStar1.setImageResource(R.drawable.fullstar);
                evaluateShopStar2.setImageResource(R.drawable.fullstar);
                evaluateShopStar3.setImageResource(R.drawable.star);
                evaluateShopStar4.setImageResource(R.drawable.star);
                evaluateShopStar5.setImageResource(R.drawable.star);
                break;
            case R.id.evaluate_shop_credit_star3:
                evaluateShopStar1.setImageResource(R.drawable.fullstar);
                evaluateShopStar2.setImageResource(R.drawable.fullstar);
                evaluateShopStar3.setImageResource(R.drawable.fullstar);
                evaluateShopStar4.setImageResource(R.drawable.star);
                evaluateShopStar5.setImageResource(R.drawable.star);
                break;
            case R.id.evaluate_shop_credit_star4:
                evaluateShopStar1.setImageResource(R.drawable.fullstar);
                evaluateShopStar2.setImageResource(R.drawable.fullstar);
                evaluateShopStar3.setImageResource(R.drawable.fullstar);
                evaluateShopStar4.setImageResource(R.drawable.fullstar);
                evaluateShopStar5.setImageResource(R.drawable.star);
                break;
            case R.id.evaluate_shop_credit_star5:
                evaluateShopStar1.setImageResource(R.drawable.fullstar);
                evaluateShopStar2.setImageResource(R.drawable.fullstar);
                evaluateShopStar3.setImageResource(R.drawable.fullstar);
                evaluateShopStar4.setImageResource(R.drawable.fullstar);
                evaluateShopStar5.setImageResource(R.drawable.fullstar);
                break;
            case R.id.evaluate_dispatch_credit_star1:
                evaluateDispatchStar1.setImageResource(R.drawable.fullstar);
                evaluateDispatchStar2.setImageResource(R.drawable.star);
                evaluateDispatchStar3.setImageResource(R.drawable.star);
                evaluateDispatchStar4.setImageResource(R.drawable.star);
                evaluateDispatchStar5.setImageResource(R.drawable.star);
                break;
            case R.id.evaluate_dispatch_credit_star2:
                evaluateDispatchStar1.setImageResource(R.drawable.fullstar);
                evaluateDispatchStar2.setImageResource(R.drawable.fullstar);
                evaluateDispatchStar3.setImageResource(R.drawable.star);
                evaluateDispatchStar4.setImageResource(R.drawable.star);
                evaluateDispatchStar5.setImageResource(R.drawable.star);
                break;
            case R.id.evaluate_dispatch_credit_star3:
                evaluateDispatchStar1.setImageResource(R.drawable.fullstar);
                evaluateDispatchStar2.setImageResource(R.drawable.fullstar);
                evaluateDispatchStar3.setImageResource(R.drawable.fullstar);
                evaluateDispatchStar4.setImageResource(R.drawable.star);
                evaluateDispatchStar5.setImageResource(R.drawable.star);
                break;
            case R.id.evaluate_dispatch_credit_star4:
                evaluateDispatchStar1.setImageResource(R.drawable.fullstar);
                evaluateDispatchStar2.setImageResource(R.drawable.fullstar);
                evaluateDispatchStar3.setImageResource(R.drawable.fullstar);
                evaluateDispatchStar4.setImageResource(R.drawable.fullstar);
                evaluateDispatchStar5.setImageResource(R.drawable.star);
                break;
            case R.id.evaluate_dispatch_credit_star5:
                evaluateDispatchStar1.setImageResource(R.drawable.fullstar);
                evaluateDispatchStar2.setImageResource(R.drawable.fullstar);
                evaluateDispatchStar3.setImageResource(R.drawable.fullstar);
                evaluateDispatchStar4.setImageResource(R.drawable.fullstar);
                evaluateDispatchStar5.setImageResource(R.drawable.fullstar);
                break;
            default:
                break;
        }
    }
}

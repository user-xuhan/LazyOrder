package com.example.xuhan.lazyorder.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xuhan.lazyorder.R;
import com.example.xuhan.lazyorder.activity.EvaluateActivity;
import com.example.xuhan.lazyorder.activity.MainActivity;
import com.example.xuhan.lazyorder.model.Order;

import java.util.List;

/**
 * Created by xuhan on 2017/4/9.
 */

public class OrderAdapter extends ArrayAdapter<Order> {

    private int resourceId;
    public OrderAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Order> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Order order = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.orderImage = (ImageView) convertView.findViewById(R.id.ordersfragment_list_image);
            viewHolder.orderName = (TextView) convertView.findViewById(R.id.ordersfragment_list_name);
            viewHolder.orderState = (TextView) convertView.findViewById(R.id.ordersfragment_list_state);
            viewHolder.orderDate = (TextView) convertView.findViewById(R.id.ordersfragment_list_date);
            viewHolder.orderPrice = (TextView) convertView.findViewById(R.id.ordersfragment_list_price);
            viewHolder.leftBtn = (Button) convertView.findViewById(R.id.ordersfragment_list_left_btn);
            viewHolder.rightBtn = (Button) convertView.findViewById(R.id.ordersfragment_list_right_btn);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.orderImage.setImageResource(Integer.valueOf(order.getOrderImage()));
        viewHolder.orderName.setText(order.getOrderName());
        viewHolder.orderDate.setText(order.getOrderDate());
        viewHolder.orderPrice.setText(order.getOrderPrice());
        final View view = convertView;
        switch (Integer.valueOf(order.getOrderState())){
            case 0:
                viewHolder.orderState.setText("待评价");
                viewHolder.leftBtn.setText("再来一单");
                viewHolder.rightBtn.setText("评价");
                viewHolder.rightBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent evaluateIntent = new Intent(view.getContext(), EvaluateActivity.class);
                        view.getContext().startActivity(evaluateIntent);
                    }
                });
                break;
            case 1:
                viewHolder.orderState.setText("已完成");
                viewHolder.leftBtn.setVisibility(View.INVISIBLE);
                viewHolder.rightBtn.setText("再来一单");
                break;
            case 2:
                viewHolder.orderState.setText("待支付");
                viewHolder.leftBtn.setText("取消订单");
                viewHolder.rightBtn.setText("去支付");
                break;
            case 3:
                viewHolder.orderState.setText("已取消");
                viewHolder.leftBtn.setVisibility(View.INVISIBLE);
                viewHolder.rightBtn.setText("再来一单");
                break;
            default:
                break;
        }
        return convertView;
    }

    private class ViewHolder{
        ImageView orderImage;
        TextView orderName, orderDate, orderPrice, orderState;
        Button leftBtn, rightBtn;
    }
}

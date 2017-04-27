package com.example.xuhan.lazyorder.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xuhan.lazyorder.R;
import com.example.xuhan.lazyorder.model.PayWayItem;

import java.util.List;

/**
 * Created by xuhan on 2017/4/14.
 */

public class PayWayAdapter extends ArrayAdapter<PayWayItem> {
    int resourceId;
    public PayWayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<PayWayItem> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PayWayItem payWayItem = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.payImage = (ImageView) convertView.findViewById(R.id.pay_image);
            viewHolder.payName = (TextView) convertView.findViewById(R.id.pay_name);
            viewHolder.payDetail = (TextView) convertView.findViewById(R.id.pay_introduce);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.payImage.setImageResource(payWayItem.getPayImage());
        viewHolder.payName.setText(payWayItem.getPayName());
        viewHolder.payDetail.setText(payWayItem.getPayDetail());
        return convertView;
    }

    private class ViewHolder{
        ImageView payImage;
        TextView payName, payDetail;
    }
}

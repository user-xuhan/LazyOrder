package com.example.xuhan.lazyorder.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.xuhan.lazyorder.R;
import com.example.xuhan.lazyorder.model.Good;

import java.util.List;

/**
 * Created by xuhan on 2017/4/14.
 */

public class SubmitOrderGoodAdapter extends ArrayAdapter<Good> {

    private int resourcId;
    public SubmitOrderGoodAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Good> objects) {
        super(context, resource, objects);
        resourcId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Good good = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(resourcId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.nameText = (TextView) convertView.findViewById(R.id.submit_order_list_name);
            viewHolder.numberText = (TextView) convertView.findViewById(R.id.submit_order_list_number);
            viewHolder.priceText = (TextView) convertView.findViewById(R.id.submit_order_list_price);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.nameText.setText(good.getGoodName());
        viewHolder.numberText.setText(good.getGoodSelectedNumber()+"");
        viewHolder.priceText.setText("¥"+Integer.valueOf(good.getGoodPrice()) * good.getGoodSelectedNumber());
        return convertView;
    }

    class ViewHolder{
        TextView nameText, numberText, priceText;
    }
}

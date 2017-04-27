package com.example.xuhan.lazyorder.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.xuhan.lazyorder.R;
import com.example.xuhan.lazyorder.model.Good;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuhan on 2017/4/11.
 */

public class ShopcartAdapter extends ArrayAdapter<Good> {
    private int resourceId;
    private ShopCartCallBack shopcartCallBack;
    private List<Good> goodList;
    public interface ShopCartCallBack{
        public void shopcartClick(View view);
    }
    public ShopcartAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Good> objects, ShopCartCallBack callBack) {
        super(context, resource, objects);
        resourceId = resource;
        this.goodList = objects;
        this.shopcartCallBack = callBack;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Good good = getItem(position);
            final ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.goodNameText = (TextView) convertView.findViewById(R.id.shop_main_orderfragment_popwindow_list_name);
                viewHolder.goodPrice = (TextView) convertView.findViewById(R.id.shop_main_orderfragment_popwindow_list_price);
                viewHolder.goodNumber = (TextView) convertView.findViewById(R.id.shop_main_orderfragment_popwindow_list_number_text);
                viewHolder.addBtn = (Button) convertView.findViewById(R.id.shop_main_orderfragment_popwindow_list_add_btn);
                viewHolder.minusBtn = (Button) convertView.findViewById(R.id.shop_main_orderfragment_popwindow_list_minus_btn);
                convertView.setTag(viewHolder);

            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
        viewHolder.addBtn.setTag(position);
        viewHolder.minusBtn.setTag(position);
            viewHolder.goodNameText.setText(good.getGoodName());
            viewHolder.goodPrice.setText(""+((Integer.valueOf(good.getGoodPrice()) * Integer.valueOf(good.getGoodSelectedNumber()))));
            viewHolder.goodNumber.setText(good.getGoodSelectedNumber() + "");
            viewHolder.addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int number = good.getGoodSelectedNumber();
                    good.setGoodSelectedNumber(++number);
                    notifyDataSetChanged();
                    shopcartCallBack.shopcartClick(v);
                }
            });
            viewHolder.minusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int number = good.getGoodSelectedNumber();
                    if (number > 0){
                        good.setGoodSelectedNumber(--number);
                        notifyDataSetChanged();
                    }
                    shopcartCallBack.shopcartClick(v);

                }
            });

        return convertView;
    }

    private class ViewHolder{
        TextView goodNameText;
        TextView goodNumber;
        TextView goodPrice;
        Button addBtn;
        Button minusBtn;
    }
}

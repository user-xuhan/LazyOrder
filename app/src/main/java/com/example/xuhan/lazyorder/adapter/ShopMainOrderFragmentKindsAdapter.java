package com.example.xuhan.lazyorder.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.xuhan.lazyorder.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuhan on 2017/4/6.
 */

public class ShopMainOrderFragmentKindsAdapter extends ArrayAdapter<String> {
    int resourceId;
    int lastPosition;
    public ShopMainOrderFragmentKindsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String kind = getItem(position);
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.kindText = (TextView) convertView.findViewById(R.id.shop_main_orderfragment_kinds_list_text);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.kindText.setText(kind);
        return convertView;
    }

    private class ViewHolder{
        TextView kindText;
    }
}

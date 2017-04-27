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
import com.example.xuhan.lazyorder.model.ChooseLocationMapItem;

import java.util.List;

/**
 * Created by xuhan on 2017/4/26.
 */

public class ChooseLocationMapAdapter extends ArrayAdapter<ChooseLocationMapItem> {

    private int resourceId;
    public ChooseLocationMapAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ChooseLocationMapItem> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ChooseLocationMapItem chooseLocationMapItem = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, parent , false);
            viewHolder = new ViewHolder();
            viewHolder.poiNameText = (TextView) convertView.findViewById(R.id.choose_location_map_poi_name);
            viewHolder.detailAddressText = (TextView) convertView.findViewById(R.id.choose_location_map_detail_address);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.poiNameText.setText(chooseLocationMapItem.getPoiName());
        viewHolder.detailAddressText.setText(chooseLocationMapItem.getDetailAddress());
        if (chooseLocationMapItem.getDetailAddress().equals("")){
            viewHolder.detailAddressText.setVisibility(View.GONE);
        }
        return convertView;
    }

    private class ViewHolder{
        TextView poiNameText, detailAddressText;
    }
}

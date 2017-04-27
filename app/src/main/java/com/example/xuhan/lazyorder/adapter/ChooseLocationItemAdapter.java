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
import com.example.xuhan.lazyorder.model.Location;

import java.util.List;

/**
 * Created by xuhan on 2017/4/8.
 */

public class ChooseLocationItemAdapter  extends ArrayAdapter<Location> {
    private int resourceId;
    public ChooseLocationItemAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Location> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Location location = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.nameText = (TextView) convertView.findViewById(R.id.choose_location_list_name);
            viewHolder.sexText = (TextView) convertView.findViewById(R.id.choose_location_list_sex);
            viewHolder.phoneText = (TextView) convertView.findViewById(R.id.choose_location_list_phone);
            viewHolder.locationText = (TextView) convertView.findViewById(R.id.choose_location_list_location);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.nameText.setText(location.getName());
        viewHolder.sexText.setText(location.getSex());
        viewHolder.phoneText.setText(location.getPhone());
        viewHolder.locationText.setText(location.getLocation());
        return convertView;

    }

    private class ViewHolder{
        TextView nameText;
        TextView sexText;
        TextView phoneText;
        TextView locationText;
    }
}

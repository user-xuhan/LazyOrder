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
import com.example.xuhan.lazyorder.model.Shop;

import java.util.List;

/**
 * Created by xuhan on 2017/4/8.
 */

public class MyCollectShopAdapter extends ArrayAdapter<Shop> {
    private int resourceId;
    public MyCollectShopAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Shop> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Shop shop = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.shopImage = (ImageView) convertView.findViewById(R.id.my_collect_list_image);
            viewHolder.shopStar1 = (ImageView) convertView.findViewById(R.id.my_collect_list_star1);
            viewHolder.shopStar2 = (ImageView) convertView.findViewById(R.id.my_collect_list_star2);
            viewHolder.shopStar3 = (ImageView) convertView.findViewById(R.id.my_collect_list_star3);
            viewHolder.shopStar4 = (ImageView) convertView.findViewById(R.id.my_collect_list_star4);
            viewHolder.shopStar5 = (ImageView) convertView.findViewById(R.id.my_collect_list_star5);
            viewHolder.shopName = (TextView) convertView.findViewById(R.id.my_collect_list_name);
            viewHolder.shopDistance = (TextView) convertView.findViewById(R.id.my_collect_list_distance);
            viewHolder.shopSalevolume = (TextView) convertView.findViewById(R.id.my_collect_list_salesvolume);
            viewHolder.shopStartPrice = (TextView) convertView.findViewById(R.id.my_collect_list_start_price);
            viewHolder.shopDisptachPrice = (TextView) convertView.findViewById(R.id.my_collect_list_dispatch_price);
            viewHolder.shopCompleteTime = (TextView) convertView.findViewById(R.id.my_collect_list_time);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.shopImage.setImageResource(Integer.valueOf(shop.getShopImage()));
        viewHolder.shopName.setText(shop.getShopName());
        viewHolder.shopDistance.setText(shop.getShopDistance());
        viewHolder.shopCompleteTime.setText(shop.getShopCompleteTime());
        viewHolder.shopStartPrice.setText(shop.getShopStartPrice());
        viewHolder.shopDisptachPrice.setText(shop.getShopDistachPrice());
        viewHolder.shopSalevolume.setText(shop.getShopSaleVolume());
        switch (Integer.valueOf(shop.getShopStar())){
            case 0:
                viewHolder.shopStar1.setImageResource(R.drawable.star);
                viewHolder.shopStar2.setImageResource(R.drawable.star);
                viewHolder.shopStar3.setImageResource(R.drawable.star);
                viewHolder.shopStar4.setImageResource(R.drawable.star);
                viewHolder.shopStar5.setImageResource(R.drawable.star);
                break;
            case 1:
                viewHolder.shopStar1.setImageResource(R.drawable.fullstar);
                viewHolder.shopStar2.setImageResource(R.drawable.star);
                viewHolder.shopStar3.setImageResource(R.drawable.star);
                viewHolder.shopStar4.setImageResource(R.drawable.star);
                viewHolder.shopStar5.setImageResource(R.drawable.star);
                break;
            case 2:
                viewHolder.shopStar1.setImageResource(R.drawable.fullstar);
                viewHolder.shopStar2.setImageResource(R.drawable.fullstar);
                viewHolder.shopStar3.setImageResource(R.drawable.star);
                viewHolder.shopStar4.setImageResource(R.drawable.star);
                viewHolder.shopStar5.setImageResource(R.drawable.star);
                break;
            case 3:
                viewHolder.shopStar1.setImageResource(R.drawable.fullstar);
                viewHolder.shopStar2.setImageResource(R.drawable.fullstar);
                viewHolder.shopStar3.setImageResource(R.drawable.fullstar);
                viewHolder.shopStar4.setImageResource(R.drawable.star);
                viewHolder.shopStar5.setImageResource(R.drawable.star);
                break;
            case 4:
                viewHolder.shopStar1.setImageResource(R.drawable.fullstar);
                viewHolder.shopStar2.setImageResource(R.drawable.fullstar);
                viewHolder.shopStar3.setImageResource(R.drawable.fullstar);
                viewHolder.shopStar4.setImageResource(R.drawable.fullstar);
                viewHolder.shopStar5.setImageResource(R.drawable.star);
                break;
            case 5:
                viewHolder.shopStar1.setImageResource(R.drawable.fullstar);
                viewHolder.shopStar2.setImageResource(R.drawable.fullstar);
                viewHolder.shopStar3.setImageResource(R.drawable.fullstar);
                viewHolder.shopStar4.setImageResource(R.drawable.fullstar);
                viewHolder.shopStar5.setImageResource(R.drawable.fullstar);
                break;
            default:
                break;
        }

        return convertView;
    }

    private class ViewHolder{
        ImageView shopImage, shopStar1, shopStar2, shopStar3, shopStar4, shopStar5;
        TextView shopName, shopDistance, shopSalevolume, shopCompleteTime, shopStartPrice, shopDisptachPrice;
    }
}

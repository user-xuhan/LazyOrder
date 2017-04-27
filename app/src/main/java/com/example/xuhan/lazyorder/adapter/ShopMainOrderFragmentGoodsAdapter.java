package com.example.xuhan.lazyorder.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xuhan.lazyorder.R;
import com.example.xuhan.lazyorder.model.Good;
import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by xuhan on 2017/4/6.
 */

public class ShopMainOrderFragmentGoodsAdapter extends BaseAdapter implements StickyListHeadersAdapter{

    private LayoutInflater inflater;
    private List<Good> goodList = new ArrayList<>();
    private Callback mCallBack;

    public interface Callback{
        public void click(View v);
    }

    public ShopMainOrderFragmentGoodsAdapter(Activity context, List<Good> goodList, Callback callback) {
        this.inflater = LayoutInflater.from(context);
        this.goodList = goodList;
        this.mCallBack = callback;
    }

    @Override
    public int getCount() {
        return goodList.size();
    }

    @Override
    public Object getItem(int position) {
        return goodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Good good = goodList.get(position);
        final ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.shop_main_orderfragment_goods_list_item, parent, false);
            viewHolder.goodImage = (ImageView) convertView.findViewById(R.id.shop_main_orderfragment_goods_list_image);
            viewHolder.goodName = (TextView) convertView.findViewById(R.id.shop_main_orderfragment_goods_list_name);
            viewHolder.goodSalesVolume = (TextView) convertView.findViewById(R.id.shop_main_orderfragment_goods_list_salesvolume);
            viewHolder.goodPrice = (TextView) convertView.findViewById(R.id.shop_main_orderfragment_goods_list_price);
            viewHolder.goodNumber = (TextView) convertView.findViewById(R.id.shop_main_orderfragment_goods_list_number_text);
            viewHolder.add_btn = (Button) convertView.findViewById(R.id.shop_main_orderfragment_goods_list_add_btn);
            viewHolder.minus_btn = (Button) convertView.findViewById(R.id.shop_main_orderfragment_goods_list_minus_btn);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.add_btn.setTag(position);
        viewHolder.minus_btn.setTag(position);
        if (good.getGoodSelectedNumber() == 0){
            viewHolder.minus_btn.setVisibility(View.INVISIBLE);
            viewHolder.goodNumber.setVisibility(View.INVISIBLE);
        }else {
            viewHolder.minus_btn.setVisibility(View.VISIBLE);
            viewHolder.goodNumber.setVisibility(View.VISIBLE);
            viewHolder.goodNumber.setText(""+good.getGoodSelectedNumber());
        }
        if (good != null){
            viewHolder.goodImage.setImageResource(good.getImageId());
            viewHolder.goodName.setText(good.getGoodName());
            viewHolder.goodSalesVolume.setText(good.getGoodSaleVolume());
            viewHolder.goodPrice.setText(good.getGoodPrice());
            viewHolder.add_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int number = good.getGoodSelectedNumber();
                    good.setGoodSelectedNumber(++number);
                    notifyDataSetChanged();
                    mCallBack.click(v);
                }
            });
            viewHolder.minus_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int number = good.getGoodSelectedNumber();
                    if (number > 0){
                        good.setGoodSelectedNumber(--number);
                        notifyDataSetChanged();
                    }


                    mCallBack.click(v);
                }
            });
        }
        return convertView;
    }


    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder headerViewHolder;
        if (convertView == null){
            headerViewHolder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.shop_main_orderfragment_goods_list_header_item, parent, false);
            headerViewHolder.projectTitle = (TextView) convertView.findViewById(R.id.shop_main_orderfragment_goods_list_header);
            convertView.setTag(headerViewHolder);
        }else {
            headerViewHolder = (HeaderViewHolder) convertView.getTag();
        }
        if (goodList.get(position) != null) {
            headerViewHolder.projectTitle.setText(goodList.get(position).getGoodProjectTitle());
        }
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return Long.parseLong(goodList.get(position).getGoodProjectId());
    }

    class ViewHolder{
        ImageView goodImage;
        TextView goodName;
        TextView goodSalesVolume;
        TextView goodPrice;
        TextView goodNumber;
        Button add_btn;
        Button minus_btn;
    }

    class HeaderViewHolder{
        TextView projectTitle;
    }
}

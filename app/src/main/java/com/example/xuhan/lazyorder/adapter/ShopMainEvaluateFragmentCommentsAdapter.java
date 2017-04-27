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
import com.example.xuhan.lazyorder.model.Comment;

import java.util.List;

/**
 * Created by xuhan on 2017/4/8.
 */

public class ShopMainEvaluateFragmentCommentsAdapter extends ArrayAdapter<Comment> {

    private int resourceId;
    public ShopMainEvaluateFragmentCommentsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Comment> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Comment comment = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.evaluateImage = (ImageView) convertView.findViewById(R.id.shop_main_evaluate_list_image);
            viewHolder.evaluateName = (TextView) convertView.findViewById(R.id.shop_main_evaluate_list_name);
            viewHolder.evaluateDate = (TextView) convertView.findViewById(R.id.shop_main_evaluate_list_date);
            viewHolder.evaluateStar1 = (ImageView) convertView.findViewById(R.id.shop_main_evaluate_list_star1);
            viewHolder.evaluateStar2 = (ImageView) convertView.findViewById(R.id.shop_main_evaluate_list_star2);
            viewHolder.evaluateStar3 = (ImageView) convertView.findViewById(R.id.shop_main_evaluate_list_star3);
            viewHolder.evaluateStar4 = (ImageView) convertView.findViewById(R.id.shop_main_evaluate_list_star4);
            viewHolder.evaluateStar5 = (ImageView) convertView.findViewById(R.id.shop_main_evaluate_list_star5);
            viewHolder.evaluateCompleteTime = (TextView) convertView.findViewById(R.id.shop_main_evaluate_list_time);
            viewHolder.evaluateMessage = (TextView) convertView.findViewById(R.id.shop_main_evaluate_list_message);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.evaluateImage.setImageResource(Integer.valueOf(comment.getUserImage()));
        viewHolder.evaluateName.setText(comment.getUserName());
        viewHolder.evaluateDate.setText(comment.getDate());
        viewHolder.evaluateCompleteTime.setText(comment.getCompleteTime());
        viewHolder.evaluateMessage.setText(comment.getMessage());
        switch (Integer.valueOf(comment.getStar())){
            case 0:
                viewHolder.evaluateStar1.setImageResource(R.drawable.star);
                viewHolder.evaluateStar2.setImageResource(R.drawable.star);
                viewHolder.evaluateStar3.setImageResource(R.drawable.star);
                viewHolder.evaluateStar4.setImageResource(R.drawable.star);
                viewHolder.evaluateStar5.setImageResource(R.drawable.star);
                break;
            case 1:
                viewHolder.evaluateStar1.setImageResource(R.drawable.fullstar);
                viewHolder.evaluateStar2.setImageResource(R.drawable.star);
                viewHolder.evaluateStar3.setImageResource(R.drawable.star);
                viewHolder.evaluateStar4.setImageResource(R.drawable.star);
                viewHolder.evaluateStar5.setImageResource(R.drawable.star);
                break;
            case 2:
                viewHolder.evaluateStar1.setImageResource(R.drawable.fullstar);
                viewHolder.evaluateStar2.setImageResource(R.drawable.fullstar);
                viewHolder.evaluateStar3.setImageResource(R.drawable.star);
                viewHolder.evaluateStar4.setImageResource(R.drawable.star);
                viewHolder.evaluateStar5.setImageResource(R.drawable.star);
                break;
            case 3:
                viewHolder.evaluateStar1.setImageResource(R.drawable.fullstar);
                viewHolder.evaluateStar2.setImageResource(R.drawable.fullstar);
                viewHolder.evaluateStar3.setImageResource(R.drawable.fullstar);
                viewHolder.evaluateStar4.setImageResource(R.drawable.star);
                viewHolder.evaluateStar5.setImageResource(R.drawable.star);
                break;
            case 4:
                viewHolder.evaluateStar1.setImageResource(R.drawable.fullstar);
                viewHolder.evaluateStar2.setImageResource(R.drawable.fullstar);
                viewHolder.evaluateStar3.setImageResource(R.drawable.fullstar);
                viewHolder.evaluateStar4.setImageResource(R.drawable.fullstar);
                viewHolder.evaluateStar5.setImageResource(R.drawable.star);
                break;
            case 5:
                viewHolder.evaluateStar1.setImageResource(R.drawable.fullstar);
                viewHolder.evaluateStar2.setImageResource(R.drawable.fullstar);
                viewHolder.evaluateStar3.setImageResource(R.drawable.fullstar);
                viewHolder.evaluateStar4.setImageResource(R.drawable.fullstar);
                viewHolder.evaluateStar5.setImageResource(R.drawable.fullstar);
                break;
            default:
                break;
        }
        return convertView;
    }

    private class ViewHolder{
        ImageView evaluateImage, evaluateStar1, evaluateStar2, evaluateStar3, evaluateStar4, evaluateStar5;
        TextView evaluateName, evaluateDate, evaluateMessage, evaluateCompleteTime;
    }
}

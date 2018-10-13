package com.example.a14512.imageloader;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.imagelibrary.manager.ImageLoader;

import java.util.ArrayList;

/**
 * @author 14512 on 2018/10/13
 */
public class ListAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> mUrls;

    public ListAdapter(Context context, ArrayList<String> urls) {
        this.mContext = context;
        this.mUrls = urls;
    }

    @Override
    public int getCount() {
        return mUrls.size();
    }

    @Override
    public String getItem(int position) {
        return mUrls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_view_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ImageView imageView = holder.mImageView;
        Log.d("Adapter", "num=" + position);
        ImageLoader.with(mContext).load(getItem(position)).into(imageView);
        return convertView;
    }

    private static class ViewHolder {
        ImageView mImageView;

        ViewHolder(View itemView) {
            mImageView = itemView.findViewById(R.id.itemImg);
        }
    }
}

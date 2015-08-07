package org.eric.commonkit.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter 基类
 *
 * @author 陈坤彬
 */
public abstract class CLBaseAdapter<T> extends BaseAdapter {


    private List<T> mDataCache;
    private LayoutInflater mInflater;
    private ViewCreator mCreator;

    public CLBaseAdapter(LayoutInflater inflater, ViewCreator creator) {
        mInflater = inflater;
        mCreator = creator;
    }

    public void update(List<T> data) {
        mDataCache = data;
        notifyDataSetChanged();
    }

    public void add(List<T> set) {
        if (null == mDataCache)
            mDataCache = new ArrayList<T>();
        mDataCache.addAll(set);
        notifyDataSetChanged();
    }

    public void add(T item) {
        if (null == mDataCache)
            mDataCache = new ArrayList<T>();
        mDataCache.add(item);
        notifyDataSetChanged();
    }

    public int getCount() {
        return null == mDataCache ? 0 : mDataCache.size();
    }

    public T getItem(int position) {
        return null == mDataCache ? null : mDataCache.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        RecyclerView.ViewHolder holder = null;
        if (convertView == null) {
            holder = mCreator.onCreateViewHolder(parent, position);
            convertView = holder.itemView;
            convertView.setTag(holder);
        } else {
            holder = (RecyclerView.ViewHolder) convertView.getTag();
        }
        mCreator.onBindViewHolder(holder, position);
        return convertView;
    }

    public interface ViewCreator {
        RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i);

        void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i);

    }

}
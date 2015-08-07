package org.eric.commonkit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;

/**
 * Created by kunbin.chen on 2015/8/6.
 */
public class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private T datas;

    private HolderCreator creator;

    public BaseRecyclerViewAdapter(Context mContext, HolderCreator creator) {
        this.mContext = mContext;
        this.creator = creator;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
    }

    @Override
    public int getItemCount() {
        if (null == datas) {
            return 0;
        }
        if (datas instanceof List) {
            return ((List) datas).size();
        } else if (datas instanceof Map) {
            return ((Map) datas).size();
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (null != creator) {
            return creator.createHolder();
        }
        return null;
    }

    public interface HolderCreator {
        RecyclerView.ViewHolder createHolder();
    }
}

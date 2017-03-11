package com.travis.demopro.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travis.demopro.R;

import java.util.List;

/**
 * Created by Tenney on 2017/2/8.
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    public CommonAdapter(Context context, int layoutId, List<T> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;
    }
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            View view = mInflater.inflate(mLayoutId, parent, false);
            return new ViewHolder(view);


    }

    @Override
    public int getItemViewType(int position) {
        if(position==getItemCount()-1){
            return  TYPE_FOOTER;
        }else{
            return  TYPE_ITEM;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position<mDatas.size()) {
            convert((ViewHolder) holder, mDatas.get(position));
        }



    }
   public  abstract  void convert(ViewHolder holder ,T data);
    @Override
    public int getItemCount() {
        return mDatas.size()+1;
    }
    //    底部的ViewHolder

}

package com.example.zj.test.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zj on 2016/7/28---15:38.
 */
public class HeadAndFootRecyclerView extends RecyclerView {

    private ArrayList<View> mHeaderViews = new ArrayList<>();
    private ArrayList<View> mFooterViews = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.Adapter mWrapAdapter;
    //item类型
    private static final int TYPE_HEADER = -101;
    private static final int TYPE_FOOTER  = -102;
    private static final int TYPE_LIST_ITEM = - 103;

    public HeadAndFootRecyclerView(Context context) {
        this(context, null);
    }
    public HeadAndFootRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public HeadAndFootRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }
    private void init(Context context){

    }

    @Override
    public void setAdapter(Adapter adapter) {
        mAdapter = adapter;
        mWrapAdapter = new WrapAdapter(mHeaderViews, mFooterViews, adapter);
        super.setAdapter(mWrapAdapter);
        mAdapter.registerAdapterDataObserver(mDataObserver);
    }
    public void addHeaderView(View view){
        mHeaderViews.clear();
        mHeaderViews.add(view);
    }
    public void addFooterView(View view){
        mFooterViews.clear();
        mFooterViews.add(view);
    }
    public int getHeaderViewsCount(){
        return mHeaderViews.size();
    }
    public int getFooterViewsCount(){
        return mFooterViews.size();
    }
    private final RecyclerView.AdapterDataObserver mDataObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            mWrapAdapter.notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            mWrapAdapter.notifyItemRangeChanged(positionStart, itemCount);
        }

//        @Override
//        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
//            mWrapAdapter.notifyItemRangeChanged(positionStart, itemCount, payload);
//        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            mWrapAdapter.notifyItemRangeInserted(positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            mWrapAdapter.notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            mWrapAdapter.notifyItemRangeRemoved(positionStart, itemCount);
        }
    };
    private class WrapAdapter extends RecyclerView.Adapter<ViewHolder>{

        private Adapter mAdapter;
        private List<View> mHeaderViews;
        private List<View> mFooterViews;
        public WrapAdapter(List<View> headerViews,List<View> footerViews,Adapter adapter){
            this.mAdapter = adapter;
            this.mHeaderViews = headerViews;
            this.mFooterViews = footerViews;
        }

        public int getHeaderCount(){
            return this.mHeaderViews.size();
        }
        public int getFooterCount(){
            return this.mFooterViews.size();
        }
        //判断当前item是否是HeadView
        public boolean isHeader(int position){
            return position >= 0 && position < this.mHeaderViews.size();
        }
        //判断当前item是否是FootView
        public boolean isFooter(int position){
            return position < getItemCount() && position >= getItemCount() - this.mFooterViews.size();
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if(viewType == TYPE_HEADER){
                return new CustomViewHolder(this.mHeaderViews.get(0));
            }else if(viewType == TYPE_FOOTER){
                return new CustomViewHolder(this.mFooterViews.get(0));
            }else{
                return this.mAdapter.onCreateViewHolder(parent,viewType);
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if(isHeader(position)) return;
            if(isFooter(position)) return;
            int rePosition = position - getHeaderCount();
            int itemCount = this.mAdapter.getItemCount();
            if(this.mAdapter != null){
                if(rePosition < itemCount){
                    Log.v("czm","rePosition/itemCount="+rePosition+"/"+itemCount);
                    this.mAdapter.onBindViewHolder(holder,rePosition);
                    return;
                }
            }
        }
        @Override
        public long getItemId(int position) {
            if (this.mAdapter != null && position >= getHeaderCount()) {
                int rePosition = position - getHeaderCount();
                int itemCount = this.mAdapter.getItemCount();
                if (rePosition < itemCount) {
                    return this.mAdapter.getItemId(rePosition);
                }
            }
            return -1;
        }
        //判断当前item类型
        @Override
        public int getItemViewType(int position) {
            if(isHeader(position)){
                //头部view
                return TYPE_HEADER;
            }
            if(isFooter(position)){
                //底部view
                return TYPE_FOOTER;
            }
            int rePosition = position - getHeaderCount();
            int itemCount = this.mAdapter.getItemCount();
            if(rePosition < itemCount){
                return this.mAdapter.getItemViewType(position);
            }
            return TYPE_LIST_ITEM;
        }
        @Override
        public int getItemCount() {
            if(this.mAdapter != null){
                return getHeaderCount() + getFooterCount() + this.mAdapter.getItemCount();
            }else{
                return getHeaderCount() + getFooterCount();
            }
        }

        @Override
        public void registerAdapterDataObserver(AdapterDataObserver observer) {
            if(this.mAdapter != null){
                this.mAdapter.registerAdapterDataObserver(observer);
            }
        }

        @Override
        public void unregisterAdapterDataObserver(AdapterDataObserver observer) {
            if(this.mAdapter != null){
                this.mAdapter.unregisterAdapterDataObserver(observer);
            }
        }

        private class CustomViewHolder extends ViewHolder{

            public CustomViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
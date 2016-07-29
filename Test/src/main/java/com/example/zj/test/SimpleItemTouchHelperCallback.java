package com.example.zj.test;

import android.graphics.Canvas;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.zj.test.Interface.onMoveAndSwipedListener;

/**
 * Created by zj on 2016/7/5---13:55.
 */
public class SimpleItemTouchHelperCallback  extends ItemTouchHelper.Callback{
    onMoveAndSwipedListener adapter;
    public SimpleItemTouchHelperCallback( onMoveAndSwipedListener adapter) {
       this.adapter=adapter;
    }

    /**这个方法是用来设置我们拖动的方向以及侧滑的方向的*/
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            //设置拖拽方向：上  下
            final int dragFlags=ItemTouchHelper.UP|ItemTouchHelper.DOWN;
            //设置侧滑方向:  START:左 ,END:右
            final int swipedFlags=ItemTouchHelper.START;//|ItemTouchHelper.END;
            return makeMovementFlags(dragFlags,swipedFlags);
        }
        return 0;
    }
    /**当我们拖动item时会回调此方法*/
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if (viewHolder.getItemViewType() != target.getItemViewType()) {
        return false;
        }
        adapter.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        return true;
    }
    /**当我们侧滑item时会回调此方法*/
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //回调adapter onItemDismiss方法
        System.out.println("direction=:"+direction);
        adapter.onItemDismiss(viewHolder.getAdapterPosition());
    }
/**
 * 设置侧滑item 颜色渐变
 * author:  zj
 * created at @time 2016/7/5 16:31
 */

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {//判断是否是侧滑
            final float alpha=(float) 1.0-Math.abs(dX)/(float) viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(alpha);
            viewHolder.itemView.setTranslationX(dX);
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}

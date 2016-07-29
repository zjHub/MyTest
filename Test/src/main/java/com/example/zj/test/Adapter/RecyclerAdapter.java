package com.example.zj.test.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zj.test.Entity.Item;
import com.example.zj.test.Interface.OnRecyclerViewItemClickListener;
import com.example.zj.test.Interface.onMoveAndSwipedListener;
import com.example.zj.test.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by zj on 2016/7/5---10:21.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> implements onMoveAndSwipedListener  {
    private OnRecyclerViewItemClickListener listener;
    private List<Item> items;
    //设置监听器
    public void setListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }
    //构造函数，将数据赋值给成员变量
    public RecyclerAdapter(List<Item> items) {
        this.items = items;
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //将布局进行绑定
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        Item item = items.get(position);
        holder.img.setImageResource(item.getImg());
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());
        holder.time.setText(item.getTime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            //如果监听器非空，则回调
            @Override
            public void onClick(View v) {
                if (listener!=null) {
                    listener.onClick(holder.itemView, holder.getLayoutPosition());
                }
         }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //ViewHolder，用于缓存，提高效率
    public final static class ItemViewHolder extends RecyclerView.ViewHolder {
        //每一项的四个控件
        ImageView img;
        TextView title;
        TextView description;
        TextView time;

        public ItemViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            time = (TextView) itemView.findViewById(R.id.time);
        }
    }


    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //交换mItems数据的位置
        Collections.swap(items,fromPosition,toPosition);
        //交换RecyclerView列表中item的位置
        notifyItemMoved(fromPosition,toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        //删除mItems数据
        items.remove(position);
        //删除RecyclerView列表对应item
        notifyItemRemoved(position);

      //  Log.e("TAG", "onItemDismiss: size=: " +items.size());
    }
}


package com.example.zj.test;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.zj.test.Adapter.RecyclerAdapter;
import com.example.zj.test.Entity.Item;
import com.example.zj.test.Interface.OnRecyclerViewItemClickListener;
import com.example.zj.test.view.HeadAndFootRecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{


    private RecyclerView recyclerView;
    private ArrayList<Item> items ;
    private View mHeaderView;
    private View mFooterView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);initDatas();
        setContentView(R.layout.test_layout);
        initDatas();//初始化数据
        initViews();//初始化recyclerview
    }
    private void initViews() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //新建适配器
        RecyclerAdapter adapter = new RecyclerAdapter(items);
        //设置监听器
        adapter.setListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {
            //    Log.e("TAG","点击item位置"+position);
               Toast.makeText(getApplicationContext(), items.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
     //   recyclerView.setLayoutManager(new LinearLayoutManager(this));
      //  mHeaderView= LayoutInflater.from(this).inflate(R.layout.head_layout,recyclerView,false);
      //  mFooterView= LayoutInflater.from(this).inflate(R.layout.foot_layout,recyclerView,false);
      //  recyclerView.addHeaderView(mHeaderView);
      //  recyclerView.addFooterView(mFooterView);
        //设置适配器
        recyclerView.setAdapter(adapter);
        //默认动画效果
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置布局管理器，第三个参数为是否逆向布局
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //设置每一项的装饰，这里给它加入分隔线
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            Paint paint = new Paint();
            @Override
            public void onDraw(Canvas c, RecyclerView parent,
                               RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }
            @Override
            public void onDrawOver(Canvas c, RecyclerView parent,
                                   RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
                paint.setColor(Color.LTGRAY);
                for (int i = 0, size = parent.getChildCount(); i < size; i++) {
                    View child = parent.getChildAt(i);
                    c.drawLine(child.getLeft()+20, child.getBottom(),
                            child.getRight()-20, child.getBottom(), paint);
                }
            }
        });
        //可以提高效率
        recyclerView.setHasFixedSize(true);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        ItemTouchHelper  mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }
    private void initDatas() {
        items = new ArrayList<Item>();

        Item item = new Item();
        item.setImg(R.drawable.ic_launcher);
        item.setTitle("微信支付1");
        item.setDescription("微信支付：支付成功通知");
        item.setTime("晚上20:35");
        items.add(item);

        item = new Item();
        item.setImg(R.drawable.ic_launcher);
        item.setTitle("微信支付2");
        item.setDescription("微信支付：支付成功通知");
        item.setTime("晚上20:35");
        items.add(item);

        item = new Item();
        item.setImg(R.drawable.ic_launcher);
        item.setTitle("微信支付3");
        item.setDescription("微信支付：支付成功通知");
        item.setTime("晚上20:35");
        items.add(item);

        item = new Item();
        item.setImg(R.drawable.ic_launcher);
        item.setTitle("微信支付4");
        item.setDescription("微信支付：支付成功通知");
        item.setTime("晚上20:35");
        items.add(item);

        item = new Item();
        item.setImg(R.drawable.ic_launcher);
        item.setTitle("微信支付5");
        item.setDescription("微信支付：支付成功通知");
        item.setTime("晚上20:35");
        items.add(item);

        item = new Item();
        item.setImg(R.drawable.ic_launcher);
        item.setTitle("微信支付6");
        item.setDescription("微信支付：支付成功通知");
        item.setTime("晚上20:35");
        items.add(item);

        item = new Item();
        item.setImg(R.drawable.ic_launcher);
        item.setTitle("微信支付7");
        item.setDescription("微信支付：支付成功通知");
        item.setTime("晚上20:35");
        items.add(item);

        item = new Item();
        item.setImg(R.drawable.ic_launcher);
        item.setTitle("微信支付8");
        item.setDescription("微信支付：支付成功通知");
        item.setTime("晚上20:35");
        items.add(item);

        item = new Item();
        item.setImg(R.drawable.ic_launcher);
        item.setTitle("微信支付9");
        item.setDescription("微信支付：支付成功通知");
        item.setTime("晚上20:35");
        items.add(item);

        item = new Item();
        item.setImg(R.drawable.ic_launcher);
        item.setTitle("微信支付10");
        item.setDescription("微信支付：支付成功通知");
        item.setTime("晚上20:35");
        items.add(item);

    }
}

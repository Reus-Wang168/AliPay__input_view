package com.travis.demopro;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.travis.demopro.adapter.CommonAdapter;
import com.travis.demopro.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.mRecl)
    XRecyclerView mReclylerview;
    @BindView(R.id.srLayout)
    LinearLayout srLayout;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<UserBean> data = new ArrayList<UserBean>();
    private int lastItemPosition;
    CommonAdapter<UserBean> adapter;
    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        initVar();
        // initView();
        setHelper();
        initView(savedInstanceState);


    }


    private void setHelper() {
        ItemTouchHelper itemHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                // 获取触摸响应的方向   包含两个 1.拖动dragFlags 2.侧滑删除swipeFlags
                // 代表只能是向左侧滑删除，当前可以是这样ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT
                int swipeFlags = ItemTouchHelper.LEFT;
                int dragFlags = 0;
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    // GridView 样式四个方向都可以
                    // GridView 样式四个方向都可以
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.LEFT |
                            ItemTouchHelper.DOWN | ItemTouchHelper.RIGHT;
                } else {
                    dragFlags = ItemTouchHelper.UP |
                            ItemTouchHelper.DOWN;
                }

                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //获取原来的位置
                int fromPosition = viewHolder.getAdapterPosition();
                // 得到目标的位置
                int targetPosition = target.getAdapterPosition();
                if (fromPosition > targetPosition) {
                    for (int i = fromPosition; i < targetPosition; i++) {
                        Collections.swap(data, i, i + 1);// 改变实际的数据集
                    }
                } else {
                    for (int i = fromPosition; i > targetPosition; i--) {
                        Collections.swap(data, i, i - 1);// 改变实际的数据集
                    }
                }
                adapter.notifyItemMoved(fromPosition, targetPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                data.remove(position);
                adapter.notifyItemChanged(position);


            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    // ItemTouchHelper.ACTION_STATE_IDLE 看看源码解释就能理解了
                    // 侧滑或者拖动的时候背景设置为灰色
                    viewHolder.itemView.setBackgroundColor(Color.GRAY);
                }
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                viewHolder.itemView.setBackgroundColor(0);
                ViewCompat.setTranslationX(viewHolder.itemView, 0);
            }
        });
        itemHelper.attachToRecyclerView(recyclerView);

    }

    private void initVar() {

        for (int i = 0; i < 20; i++) {
            data.add(new UserBean("jack" + i, i));
        }



    }

    private void initView(Bundle savedInstanceState) {
        if (savedInstanceState != null) {

                    //这个Adapter传进去的UserBean的List
                    //因为是万能的Adapter，所以摆放数据只能在用的时候才写
                    adapter = new CommonAdapter<UserBean>(this, R.layout.item, data) {

                @Override
                public void convert(ViewHolder holder, UserBean data) {
                    holder.setText(R.id.tvAge, data.getAge() + "");
                    holder.setText(R.id.tvName, data.getName());

                }

            };
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mReclylerview.setLayoutManager(layoutManager);
            mReclylerview.setAdapter(adapter);
            mReclylerview.setLoadingListener(new XRecyclerView.LoadingListener() {
                @Override
                public void onRefresh() {
                    initVar();
                   mReclylerview.refreshComplete();
                }

                @Override
                public void onLoadMore() {

                }
            });
            mReclylerview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        }
//       // swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srLayout);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        initVar();
//                        try {
//                            Thread.sleep(3000);
//                        } catch (InterruptedException e) {
//
//                        }
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                swipeRefreshLayout.setRefreshing(false);
//                                adapter.notifyDataSetChanged();
//
//                            }
//                        });
//
//                    }
//                }).start();
//            }
//        });
//        recyclerView = (RecyclerView) findViewById(R.id.mRecl);
//
//        final LinearLayoutManager linearManager = new LinearLayoutManager(this);
//        final GridLayoutManager gridManager = new GridLayoutManager(this, 2);
//        recyclerView.setLayoutManager(gridManager);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setOnScrollListener(new OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                //recyclerView停下来而且可见的item的position是最后一个
//                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastItemPosition + 1 == adapter.getItemCount()) {
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            initVar();
//                            try {
//                                Thread.sleep(3000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    adapter.notifyDataSetChanged();
//                                    Toast.makeText(MainActivity.this, "加载完成", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//
//                        }
//                    }).start();
//                }
//            }
//
//
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                lastItemPosition = gridManager.findLastVisibleItemPosition();
//
//            }
//        });
//

    }

}


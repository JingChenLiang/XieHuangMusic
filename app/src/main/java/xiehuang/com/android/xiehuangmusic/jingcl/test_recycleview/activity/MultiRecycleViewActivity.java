package xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import xiehuang.com.android.xiehuangmusic.R;
import xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview.adapter.CommonAdapter;
import xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview.adapter.EmptyItemAdapter;
import xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview.adapter.HeaderAndFooterItemAdapter;
import xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview.adapter.LoadMoreItemAdapter;
import xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview.base.ViewHolder;
import xiehuang.com.android.xiehuangmusic.jingcl.test_wrapper.ZhangFengXia;
import xiehuang.com.android.xiehuangmusic.jingcl.test_wrapper.zhuangshi.ShuoWrapper;
import xiehuang.com.android.xiehuangmusic.jingcl.test_wrapper.zhuangshi.XuLiWrapper;

public class MultiRecycleViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas = new ArrayList<>();
    private CommonAdapter<String> mNormalAdapter;

    private HeaderAndFooterItemAdapter mHeaderAndFooterItemAdapter;
    private LoadMoreItemAdapter mLoadMoreItemAdapter;
    private EmptyItemAdapter mEmptyItemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_recycle_view);

        for (int i = 'A'; i <= 'z'; i++) {
            mDatas.add((char) i + "");
        }

        testWrapper();

        initView();
    }

    private void testWrapper() {

        ZhangFengXia zhangFengXia = new ZhangFengXia();
        //ZhangFengXia有『说』的功能
        ShuoWrapper shuoWrapper = new ShuoWrapper(zhangFengXia);
        //ZhangFengXia有『跳』的功能
        XuLiWrapper xuLiWrapper = new XuLiWrapper(shuoWrapper);

        //先蓄力 再 攻击
        xuLiWrapper.gongJi();
        //先说 再 隐身
        xuLiWrapper.yinShen();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        OverScrollDecoratorHelper.setUpOverScroll(mRecyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

        //正常的item：view  +  data
        mNormalAdapter = new CommonAdapter<String>(this, R.layout.item_list, mDatas) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                //处理item的数据
                holder.setText(R.id.id_item_list_title, s + " : " + holder.getAdapterPosition() + " , " + holder.getLayoutPosition());
            }
        };
        mNormalAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Toast.makeText(MultiRecycleViewActivity.this, "pos = " + position, Toast.LENGTH_SHORT).show();
                mNormalAdapter.notifyItemRemoved(position);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        initHeaderAndFooter();

        mLoadMoreItemAdapter = new LoadMoreItemAdapter(mHeaderAndFooterItemAdapter);
        mLoadMoreItemAdapter.setLoadMoreView(R.layout.default_loading);
        mLoadMoreItemAdapter.setOnLoadMoreListener(new LoadMoreItemAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                //load data then update
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 5; i++) {
                            mDatas.add("Add:" + i);
                        }
                        mLoadMoreItemAdapter.notifyDataSetChanged();
                    }
                }, 500);
            }
        });

//        mEmptyItemAdapter = new EmptyItemAdapter(mHeaderAndFooterItemAdapter);
//        mEmptyItemAdapter.setEmptyView(R.layout.empty_view);

        mRecyclerView.setAdapter(mLoadMoreItemAdapter);
    }

    private void initHeaderAndFooter() {
        mHeaderAndFooterItemAdapter = new HeaderAndFooterItemAdapter(mNormalAdapter);

        TextView t1 = new TextView(this);
        t1.setText("Header 1");
        TextView t2 = new TextView(this);
        t2.setText("Header 2");
        mHeaderAndFooterItemAdapter.addHeaderView(t1);
        mHeaderAndFooterItemAdapter.addHeaderView(t2);
    }

}

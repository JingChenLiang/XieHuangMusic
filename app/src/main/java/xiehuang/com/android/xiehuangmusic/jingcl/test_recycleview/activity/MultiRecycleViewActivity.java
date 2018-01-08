package xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import xiehuang.com.android.xiehuangmusic.R;
import xiehuang.com.android.xiehuangmusic.jingcl.test_rc_liuj.PullToRefreshRecyclerView;
import xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview.adapter.CommonAdapter;
import xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview.adapter.EmptyItemAdapter;
import xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview.adapter.HeaderAndFooterItemAdapter;
import xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview.adapter.LoadMoreItemAdapter;

public class MultiRecycleViewActivity extends AppCompatActivity {

    private PullToRefreshRecyclerView mPullToRefreshRecyclerView;
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

//        testWrapper();

//        initView();

        initLiuJRecycleView();
    }

    private void initLiuJRecycleView() {
//        mPullToRefreshRecyclerView = (PullToRefreshRecyclerView) findViewById(R.id.id_recyclerview);
//        mPullToRefreshRecyclerView.setRecyclerViewListener(new RecyclerViewListenerImpl());
//        mRecyclerViewAdapter = new RecyclerViewAdapter(mContext,mNewsBeanArrayList);
//        mRecyclerViewAdapter.setOnItemClickListener(mResyslerViewClickListener);
//        mPullToRefreshRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

//    private void testWrapper() {
//
//        XiaoLongNv xiaoLongNv = new XiaoLongNv();
//        //小龙女先对杨过『说』  我恨你  后再暴击
//        ShuoWrapper xLNshuoWrapper = new ShuoWrapper(xiaoLongNv, "小龙女：我恨你");
//        xLNshuoWrapper.gongJi();
//
//        YangGuo yangGuo = new YangGuo();
//        //杨过对小龙女『说』    我爱你  后再暴击
//        //代理:
//        //装饰：保持原有功能的基础上，在前面或后面添加新的功能，甚至可以重写原有功能
//        ShuoWrapper ygShuoWrapper = new ShuoWrapper(yangGuo, "杨过：我爱你");
//        ygShuoWrapper.gongJi();
//
//    }

//    private void initView() {
//        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        OverScrollDecoratorHelper.setUpOverScroll(mRecyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
//
//        //正常的item：view  +  data
//        mNormalAdapter = new CommonAdapter<String>(this, R.layout.item_list, mDatas) {
//            @Override
//            protected void convert(ViewHolder holder, String s, int position) {
//                //处理item的数据
//                holder.setText(R.id.id_item_list_title, s + " : " + holder.getAdapterPosition() + " , " + holder.getLayoutPosition());
//            }
//        };
//        mNormalAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
//                Toast.makeText(MultiRecycleViewActivity.this, "pos = " + position, Toast.LENGTH_SHORT).show();
//                mNormalAdapter.notifyItemRemoved(position);
//            }
//
//            @Override
//            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
//                return false;
//            }
//        });
//
//        initHeaderAndFooter();
//
//        mLoadMoreItemAdapter = new LoadMoreItemAdapter(mHeaderAndFooterItemAdapter);
//        mLoadMoreItemAdapter.setLoadMoreView(R.layout.default_loading);
//        mLoadMoreItemAdapter.setOnLoadMoreListener(new LoadMoreItemAdapter.OnLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                //load data then update
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        for (int i = 0; i < 5; i++) {
//                            mDatas.add("Add:" + i);
//                        }
//                        mLoadMoreItemAdapter.notifyDataSetChanged();
//                    }
//                }, 500);
//            }
//        });
//
////        mEmptyItemAdapter = new EmptyItemAdapter(mHeaderAndFooterItemAdapter);
////        mEmptyItemAdapter.setEmptyView(R.layout.empty_view);
//
////        mEmptyItemAdapter = new EmptyItemAdapter(
////                new LoadMoreItemAdapter(
////                        new HeaderAndFooterItemAdapter(mNormalAdapter)));
////        mEmptyItemAdapter.setEmptyView(R.layout.default_loading);
//
//        //最后装饰完的adapter
//        mRecyclerView.setAdapter(mLoadMoreItemAdapter);
//    }

//    private void initHeaderAndFooter() {
//        mHeaderAndFooterItemAdapter = new HeaderAndFooterItemAdapter(mNormalAdapter);
//
//        TextView t1 = new TextView(this);
//        t1.setText("Header 1");
//        TextView t2 = new TextView(this);
//        t2.setText("Header 2");
//        mHeaderAndFooterItemAdapter.addHeaderView(t1);
//        mHeaderAndFooterItemAdapter.addHeaderView(t2);
//    }

}

package xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview_HY.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import xiehuang.com.android.xiehuangmusic.R;
import xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview_HY.adapter.CommonAdapter;
import xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview_HY.adapter.HeaderAndFooterItemAdapter;
import xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview_HY.adapter.LoadMoreItemAdapter;
import xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview_HY.base.ViewHolder;

public class MultiRecycleViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas = new ArrayList<>();
    private CommonAdapter<String> mNormalAdapter;

    private HeaderAndFooterItemAdapter mHeaderAndFooterItemAdapter;
    private LoadMoreItemAdapter mLoadMoreItemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_recycle_view);

        for (int i = 'A'; i <= 'z'; i++) {
            mDatas.add((char) i + "");
        }

        //        testWrapper();

        initView();

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

        mHeaderAndFooterItemAdapter = new HeaderAndFooterItemAdapter(mNormalAdapter);

        mLoadMoreItemAdapter = new LoadMoreItemAdapter(mHeaderAndFooterItemAdapter);
        mLoadMoreItemAdapter.setLoadMoreView(R.layout.recycleview_load_view);
        mLoadMoreItemAdapter.setOnLoadMoreListener(new LoadMoreItemAdapter.OnLoadMoreListener() {
            @Override
            public boolean onLoadMoreRequested() {
                //load data then update
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 5; i++) {
//                            mDatas.add("Add:" + i);
                        }
                        //有新的数据就刷新  TODO
                        mLoadMoreItemAdapter.notifyDataSetChanged();
                    }
                }, 500);
                return false;
            }
        });

        //最后装饰完的adapter
        mRecyclerView.setAdapter(mLoadMoreItemAdapter);
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
}

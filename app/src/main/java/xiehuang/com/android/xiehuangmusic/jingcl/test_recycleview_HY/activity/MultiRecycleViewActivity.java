package xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview_HY.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import xiehuang.com.android.xiehuangmusic.R;
import xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview_HY.adapter.CommonAdapter;
import xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview_HY.adapter.HeaderAndFooterItemAdapter;
import xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview_HY.adapter.LoadMoreItemAdapter;
import xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview_HY.base.ViewHolder;
import xiehuang.com.android.xiehuangmusic.jingcl.test_wrapper.XiaoLongNv;
import xiehuang.com.android.xiehuangmusic.jingcl.test_wrapper.YangGuo;
import xiehuang.com.android.xiehuangmusic.jingcl.test_wrapper.zhuangshi.ShuoWrapper;

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

        testWrapper();

        getDatasync();

        initView();

    }

    public void getDatasync() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //创建OkHttpClient对象
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://www.baidu.com")//请求接口。如果需要传参拼接到接口后面。
                            .build();//创建Request 对象
                    Response response = null;
                    response = client.newCall(request).execute();//得到Response 对象
                    if (response.isSuccessful()) {
                        Logger.i("response.code()==" + response.code());
                        Logger.i("response.message()==" + response.message());
                        Logger.i("res==" + response.body().string());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
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

    private void testWrapper() {

        XiaoLongNv xiaoLongNv = new XiaoLongNv();

        ShuoWrapper xLNshuoWrapper = new ShuoWrapper(xiaoLongNv, "小龙女：我恨你");

        xLNshuoWrapper.gongJi();//小龙女：先说，再攻击

        //装饰：保持原有功能的基础上，在前面或后面添加新的功能，甚至可以重写原有功能
        YangGuo yangGuo = new YangGuo();
        ShuoWrapper ygShuoWrapper = new ShuoWrapper(yangGuo, "杨过：我爱你");
        ygShuoWrapper.gongJi();//杨过：先说，再攻击

    }

}

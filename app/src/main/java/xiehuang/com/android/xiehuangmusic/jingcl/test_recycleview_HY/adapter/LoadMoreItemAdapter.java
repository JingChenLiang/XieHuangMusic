package xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview_HY.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import xiehuang.com.android.xiehuangmusic.R;
import xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview_HY.base.ViewHolder;
import xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview_HY.utils.AdapterUtils;

/**
 * Created by erliang on 2018/01/05
 */
public class LoadMoreItemAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * 根据数据处理状态
     * <p>
     * mData
     * 1.没有内容了
     * 2.没有网络
     */

    public static final int ITEM_TYPE_LOAD_MORE = Integer.MAX_VALUE - 2;
    private RecyclerView.Adapter mInnerAdapter;
    private int mLoadMoreLayoutId;
    boolean mLoadMoreEntity;

    public LoadMoreItemAdapter(RecyclerView.Adapter adapter) {
        mInnerAdapter = adapter;
    }

    @Override
    public int getItemViewType(int position) {
        if (isShowLoadMore(position)) {
            return ITEM_TYPE_LOAD_MORE;
        }
        return mInnerAdapter.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_LOAD_MORE) {
            ViewHolder holder;
            holder = ViewHolder.createViewHolder(parent.getContext(), parent, mLoadMoreLayoutId);
            TextView loadingText = holder.getView(R.id.loading_text);
            ProgressBar loadingPro = holder.getView(R.id.loading_progressBar);

            //没有网络
//            if (true) {
//                loadingPro.setVisibility(View.GONE);
//                loadingText.setText("网络未连接");
//            }
            //没有更多数据
            if (mLoadMoreEntity == false) {
                loadingPro.setVisibility(View.GONE);
                loadingText.setText("没有更多内容");
            }

            return holder;
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isShowLoadMore(position)) {
            if (mOnLoadMoreListener != null) {
                mLoadMoreEntity = mOnLoadMoreListener.onLoadMoreRequested();
            }
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        AdapterUtils.onAttachedToRecyclerView(mInnerAdapter, recyclerView, new AdapterUtils.SpanSizeCallback() {
            @Override
            public int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager.SpanSizeLookup oldLookup, int position) {
                if (isShowLoadMore(position)) {
                    return layoutManager.getSpanCount();
                }
                if (oldLookup != null) {
                    return oldLookup.getSpanSize(position);
                }
                return 1;
            }
        });
    }


    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        mInnerAdapter.onViewAttachedToWindow(holder);

        if (isShowLoadMore(holder.getLayoutPosition())) {
            setFullSpan(holder);
        }
    }

    @Override
    public int getItemCount() {
        return mInnerAdapter.getItemCount() + (hasLoadMore() ? 1 : 0);
    }

    private boolean hasLoadMore() {
        return mLoadMoreLayoutId != 0;
    }


    private boolean isShowLoadMore(int position) {
        return hasLoadMore() && (position >= mInnerAdapter.getItemCount());
    }

    private void setFullSpan(RecyclerView.ViewHolder holder) {
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();

        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;

            p.setFullSpan(true);
        }
    }

    public interface OnLoadMoreListener {
        boolean onLoadMoreRequested();
    }

    private OnLoadMoreListener mOnLoadMoreListener;

    public LoadMoreItemAdapter setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        if (loadMoreListener != null) {
            mOnLoadMoreListener = loadMoreListener;
        }
        return this;
    }

    public LoadMoreItemAdapter setLoadMoreView(int layoutId) {
        mLoadMoreLayoutId = layoutId;
        return this;
    }

}

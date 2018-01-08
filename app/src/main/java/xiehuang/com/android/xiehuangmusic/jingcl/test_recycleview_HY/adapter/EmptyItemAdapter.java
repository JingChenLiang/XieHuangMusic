package xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview_HY.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview_HY.base.ViewHolder;
import xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview_HY.utils.AdapterUtils;

/**
 * Created by erliang on 2018/01/05
 */
public class EmptyItemAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int ITEM_TYPE_EMPTY = Integer.MAX_VALUE - 1;

    private RecyclerView.Adapter mInnerAdapter;
    private int mEmptyLayoutId;


    public EmptyItemAdapter(RecyclerView.Adapter adapter) {
        mInnerAdapter = adapter;
    }

    @Override
    public int getItemViewType(int position) {
        if (isEmpty()) {
            return ITEM_TYPE_EMPTY;
        }
        return mInnerAdapter.getItemViewType(position);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (isEmpty()) {
            ViewHolder holder;
            holder = ViewHolder.createViewHolder(parent.getContext(), parent, mEmptyLayoutId);
            return holder;
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        AdapterUtils.onAttachedToRecyclerView(mInnerAdapter, recyclerView, new AdapterUtils.SpanSizeCallback() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, GridLayoutManager.SpanSizeLookup oldLookup, int position) {
                if (isEmpty()) {
                    return gridLayoutManager.getSpanCount();
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
        if (isEmpty()) {
            AdapterUtils.setFullSpan(holder);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isEmpty()) {
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        if (isEmpty()) {
            return 1;
        }
        return mInnerAdapter.getItemCount();
    }

    private boolean isEmpty() {
        return (mEmptyLayoutId != 0) && mInnerAdapter.getItemCount() == 0;
    }

    public void setEmptyView(int layoutId) {
        mEmptyLayoutId = layoutId;
    }

}

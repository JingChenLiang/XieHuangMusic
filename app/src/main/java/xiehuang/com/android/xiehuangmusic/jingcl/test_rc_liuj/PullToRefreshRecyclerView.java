package xiehuang.com.android.xiehuangmusic.jingcl.test_rc_liuj;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import xiehuang.com.android.xiehuangmusic.R;


public class PullToRefreshRecyclerView extends FrameLayout {
    private static final String TAG = "PullToRefresh";
    private final int mTouchSlop;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private InfoLinearLayoutManager mLinearLayoutManager;

    private RecyclerViewListener mListener;
    private View mHeaderView;
    private int mFirstCompletelyVisibleItemPosition;
    private int mFirstVisibleItemPosition;
    /**
     * RecyclerView相对于父view的top位置
     */
    private float mRecyclerViewTop;
    /**
     * hintview相对于父view的top位置
     */
    private float mHintViewTop;
    private TextView mRefreshTextView;
    private ProgressBar mRefreshProgressBar;
    private boolean mIsRefreshing = false;//recyclerView是否在刷新中
    private HeaderHintViewContainer mRefreshCountHint;
    private float mDownMotionY;
    private int mHeadHeight;
    private int mHintHeight;
    private float mLastDragY;
    private boolean mIsBeginDragged = false;
    private float mMinRefreshHeight;
    private static final int ANIMATION_DURATION = 300;
    private static final int ANIMATION_DURATION_SCROLL_UP = 250;
    /**
     * recyclerview是否处在滑动状态
     */
    private boolean mIsScrolling = false;
    /**
     * 提示文案view显示时长
     */
    private static final int HINT_VIEW_SHOWN_DURATION = 3000;

    public PullToRefreshRecyclerView(Context context) {
        this(context, null);
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(getContext()).inflate(R.layout.pulltorefresh_recyclerview, this);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        Resources resources = context.getResources();
        mHeadHeight = (int) resources.getDimension(R.dimen.header_hight);
        mMinRefreshHeight = mHeadHeight;
        mHintHeight = (int) resources.getDimension(R.dimen.recyclerview_header_hint_height);
        initView();

}
    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(android.R.id.list);
        mLinearLayoutManager = new InfoLinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.addItemDecoration(new RecyclerViewDecoration(getContext()));
        mRecyclerView.setOnScrollListener(new OnScrollListenerImpl());
        mRecyclerView.addOnItemTouchListener(OnItemTouchListener);

        mHeaderView = findViewById(R.id.refreshing);
        mRefreshTextView = (TextView) mHeaderView.findViewById(R.id.header_text);
        mRefreshProgressBar = (ProgressBar) mHeaderView.findViewById(R.id.header_progressbar);
        mRefreshCountHint = (HeaderHintViewContainer) findViewById(R.id.refresh_count_hint);
    }

    private RecyclerView.OnItemTouchListener OnItemTouchListener = new RecyclerView.OnItemTouchListener() {
        @Override
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            Log.d(TAG,"onInterceptTouchEvent : " + ((ViewGroup)recyclerView).getChildCount());
            int childCount = ((ViewGroup)recyclerView).getChildCount();
            for (int i = 0; i < childCount; i++){
                 View child = recyclerView.getChildAt(i);
                if(child instanceof ImageView){

                }
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            Log.d(TAG,"onTouchEvent : " + ((ViewGroup)recyclerView).getChildCount());
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    };

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
        mRecyclerView.setAdapter(adapter);
    }

    public void setRecyclerViewListener(RecyclerViewListener listener) {
        mListener = listener;
    }

    public void notifyDataSetChanged() {
        mAdapter.notifyDataSetChanged();
    }

    private class OnScrollListenerImpl extends RecyclerView.OnScrollListener {

        public void onScrollStateChanged(int newState) {

            if (newState == RecyclerView.SCROLL_STATE_IDLE) {// RecyclerView滑动停止
                if (mIsScrolling) {
                    // 滑动停止
                    if (mListener != null) {
                        mListener.onScrollEnd();
                    }
                    mIsScrolling = false;
                }
            } else {// 手动拖动 或者 惯性滑动
                if (!mIsScrolling) {
                    // 滑动开始
                    if (mListener != null) {
                        mListener.onScrollBegin();
                    }
                    mIsScrolling = true;
                }
            }
        }

        public void onScrolled(int dx, int dy) {
            mFirstVisibleItemPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
            mFirstCompletelyVisibleItemPosition = mLinearLayoutManager.findFirstCompletelyVisibleItemPosition();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //如果从顶部下拉，当前处理
        //如果其他情况，super
        if (mFirstCompletelyVisibleItemPosition == 0) {//已经到顶部
            switch (ev.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    onTouchDown(ev);
                    break;
                case MotionEvent.ACTION_MOVE:
                    boolean interruptMove = onTouchMove(ev);
                    if (interruptMove) {
                        return true;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    boolean interruptUp = onTouchUp();
                    if (interruptUp) {
                        return true;
                    }
                    break;
                default:
                    break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private void onTouchDown(MotionEvent ev) {
        mIsBeginDragged = false;
        mDownMotionY = ev.getY();
    }

    private boolean isBeingDragged(float motionY) {
        if (mIsBeginDragged) {
            return true;
        }
        float dy = motionY - mDownMotionY;
        if (dy < 0) {// 向上拖动
            if (mRecyclerView.getTop() <= 0) {// 当前recycler不需要上滑，则不设置为drag
                Log.d(TAG, "isBeingDragged, scroll up, Top <= 0 , return false;");
                return false;
            }
        }
        if (Math.abs(dy) > mTouchSlop) {
            mIsBeginDragged = true;
            mLastDragY = motionY;
            Log.d(TAG, "isBeingDragged, test123 motionY:" + motionY);
            return true;
        } else {
            return false;
        }
    }

    private boolean onTouchMove(MotionEvent ev) {
        boolean interruptMove = false;
        float curY = ev.getY();
        boolean isBeingDragged = isBeingDragged(curY);
        if (!isBeingDragged) {
            return false;
        }
        float dy = curY - mLastDragY;
        float dampDy = dy / 2;// 增加阻尼效果
        float tempMoveY = dampDy + mRecyclerViewTop;
        if (tempMoveY > 0) {// 是否还能根据手指继续滑动顶部刷新view
            mRecyclerViewTop = tempMoveY;
        } else {// 如果上滑的距离大于目前剩余的距离只需要需要置为0
            mRecyclerViewTop = 0;
        }

        mHeaderView.setVisibility(VISIBLE);
        if (!mIsRefreshing) {// 如果没有在刷新时，才需要更新文案
            changeHeaderViewState(mRecyclerViewTop);
        }
        requestLayout();

        if (mRecyclerViewTop > 0) {// 还可以上滑或者下滑
            mLastDragY = curY;
            interruptMove = true;
        }
        return interruptMove;
    }

    private void changeHeaderViewState(float moveY) {
        if (moveY > mMinRefreshHeight) {
            mRefreshTextView.setText(getResources().getString(R.string.release_to_refresh));
        } else {
            mRefreshTextView.setText(getResources().getString(R.string.pull_to_refresh));
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int paddingLeft = getPaddingLeft();// 设置从右到左布局时，此值就会返回PaddingEnd的值
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            int moveY = (int) mRecyclerViewTop;
            int childViewId = childView.getId();
            if (childViewId == R.id.refreshing) {// 布局headerview
                childView.layout(paddingLeft, 0, childView.getMeasuredWidth() + paddingLeft, moveY);// 当向下拖动时，header背景要从父view的顶部到拖动的距离都为灰色，所以让它从从父view的顶部开始布局
            } else if (childView instanceof RecyclerView) {// 布局recyclerView
                childView.layout(paddingLeft, moveY, childView.getMeasuredWidth() + paddingLeft, moveY + childView.getMeasuredHeight());
            } else if (childView instanceof HeaderHintViewContainer) {// 显示刷新多少条的view
                int hintViewTop = (int) mHintViewTop;
                childView.layout(paddingLeft, hintViewTop, childView.getMeasuredWidth() + paddingLeft, hintViewTop + childView.getMeasuredHeight());
            }
            Log.d(TAG, "onLayout,test123  layout childid:" + i + "- moveY:" + moveY + "---mHintViewTop:" + mHintViewTop + "-childView:" + childView);
        }
    }

    private boolean onTouchUp() {
        boolean interruptUp = mIsBeginDragged;
        if (mRecyclerViewTop >= mMinRefreshHeight) {//下拉刷新
            setRefreshStatus(null);
            startEnterAnim();
        } else {
            if (!mIsRefreshing) {
                startExitAnim();
            }
        }
        mIsBeginDragged = false;
        return interruptUp;
    }

    private void startExitAnim() {
        ValueAnimator animator = ValueAnimator.ofFloat(mRecyclerViewTop, 0);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                mRecyclerViewTop = (float) arg0.getAnimatedValue();
                requestLayout();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mHeaderView.setVisibility(GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mHeaderView.setVisibility(GONE);
            }
        });
        animator.setDuration(ANIMATION_DURATION_SCROLL_UP);
        animator.start();
    }

    private void startEnterAnim() {
        ValueAnimator animator = ValueAnimator.ofFloat(mRecyclerViewTop, mHeadHeight);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                mRecyclerViewTop = (float) arg0.getAnimatedValue();
                requestLayout();
            }

        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mRecyclerViewTop = mHeadHeight;
                requestLayout();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mRecyclerViewTop = mHeadHeight;
                requestLayout();
            }
        });
        animator.setDuration(ANIMATION_DURATION_SCROLL_UP);
        animator.start();
    }

    private void setRefreshStatus(Object tag) {
        mRefreshTextView.setText(getResources().getString(R.string.refreshing));
        mHeaderView.setVisibility(VISIBLE);
        mRefreshProgressBar.setVisibility(VISIBLE);

        if (mListener != null && !mIsRefreshing) {
            mIsRefreshing = true;
            mListener.pullFromTop(tag);
        }
    }

    private void hideHeaderView() {// 滑动到hint的高度
        ValueAnimator animator = ValueAnimator.ofFloat(mRecyclerViewTop, mHintHeight);
        animator.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                mRecyclerViewTop = (float) arg0.getAnimatedValue();
                requestLayout();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mIsRefreshing = false;
                mRefreshProgressBar.setVisibility(GONE);
                mHeaderView.setVisibility(GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mIsRefreshing = false;
                mRefreshProgressBar.setVisibility(GONE);
                mHeaderView.setVisibility(GONE);
            }
        });
        animator.setDuration(ANIMATION_DURATION);
        animator.start();
    }

    private void showRefreshCountHint() {
        mRefreshCountHint.setVisibility(VISIBLE);
        mHintViewTop = 0;
        requestLayout();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (mIsBeginDragged) {// 如果展示了hint后，还在拖动过程中，则不做收回动画
                    mRefreshCountHint.setVisibility(GONE);
                    return;
                }
                ValueAnimator animator = ValueAnimator.ofFloat(mRecyclerViewTop, 0f);
                animator.addUpdateListener(new AnimatorUpdateListener() {

                    @Override
                    public void onAnimationUpdate(ValueAnimator arg0) {
                        mRecyclerViewTop = (float) arg0.getAnimatedValue();
                        mHintViewTop = mRecyclerViewTop - mHintHeight;
                        requestLayout();
                    }
                });
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mRefreshCountHint.setVisibility(GONE);
                        mHintViewTop = 0;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        mRefreshCountHint.setVisibility(GONE);
                        mHintViewTop = 0;
                    }
                });
                animator.setDuration(ANIMATION_DURATION);
                animator.start();
            }
        };
        postDelayed(runnable, HINT_VIEW_SHOWN_DURATION);
    }

    /**
     * 设置hint文案
     */
    public void setHintText(String text) {
        if (TextUtils.isEmpty(text)) {
            Log.e(TAG, "setHintText, TextUtils.isEmpty(text), return.");
            return;
        }
        if (mRefreshCountHint == null) {
            Log.e(TAG, "setHintText, mRefreshCountHint == null, return.");
            return;
        }
        mRefreshCountHint.setText(text);
    }

    /***
     * 进入顶部刷新状态
     * @param tag 通过{@link RecyclerViewListener#pullFromTop(Object)}返回改值
     */
    public void showRefreshView(Object tag) {
        setRefreshStatus(tag);
        startEnterAnim();
    }

    /**
     * 滑动到RecyclerView顶部 并 进入顶部刷新状态
     *
     * @param tag 通过{@link RecyclerViewListener#pullFromTop(Object)}返回改值
     */
    public void scrollToTopAndShowRefreshView(Object tag) {
        mRecyclerView.scrollToPosition(0);
        showRefreshView(tag);
    }

    /**
     * 获取第一个显示的item的position
     */
    public int getFirstVisibleItemPosition() {
        return mFirstVisibleItemPosition;
    }

    /**
     * 获取RecyclerView中子view的个数
     */
    public int getItemViewCount() {
        return mRecyclerView.getChildCount();
    }

    /**
     * 顶部刷新完成时，调用
     */
    public void setRefreshComplete() {
        hideHeaderView();
        showRefreshCountHint();
    }

    public interface RecyclerViewListener {
        /***
         * 顶部下拉刷新
         * @param tag 手指拖动view下拉刷新该值为null 
         */
        void pullFromTop(Object tag);

        /**
         * 滑动开始
         */
        void onScrollBegin();

        /**
         * 滑动结束
         */
        void onScrollEnd();
    }

    @Override
    public void requestLayout() {
        super.requestLayout();
    }
}

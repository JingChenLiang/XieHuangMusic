package xiehuang.com.android.xiehuangmusic.jingcl.test_rc_liuj;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

public class InfoLinearLayoutManager extends LinearLayoutManager {
    public InfoLinearLayoutManager(Context context) {
        super(context);
    }

    public InfoLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {
            @Override
            public PointF computeScrollVectorForPosition(int targetPosition) {
                return InfoLinearLayoutManager.this.computeScrollVectorForPosition(targetPosition);
            }

            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return 40.0F / (float)displayMetrics.densityDpi;
            }
        };
        linearSmoothScroller.setTargetPosition(position);
        this.startSmoothScroll(linearSmoothScroller);
    }
}

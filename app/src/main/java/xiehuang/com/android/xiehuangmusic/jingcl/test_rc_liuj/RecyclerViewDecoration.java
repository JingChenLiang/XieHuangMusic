package xiehuang.com.android.xiehuangmusic.jingcl.test_rc_liuj;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import xiehuang.com.android.xiehuangmusic.R;


public class RecyclerViewDecoration extends RecyclerView.ItemDecoration {
    private int mDividerHeight;
    private Drawable mDivider;
    private Context mContext;

    public RecyclerViewDecoration(Context context) {
        mContext = context;
        this.mDivider = context.getDrawable(R.drawable.card_recyclerview_divider);
        mDividerHeight = context.getResources().getDimensionPixelOffset(R.dimen.card_item_divider_height);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        int left = mContext.getResources().getDimensionPixelOffset(R.dimen.card_item_margin_left);
        int right = parent.getWidth() - mContext.getResources().getDimensionPixelOffset(R.dimen.card_item_margin_right);
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            final View child = parent.getChildAt(i);
            View nextChild = parent.getChildAt(i + 1);

            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDividerHeight;
            mDivider.setBounds(left, top, right, bottom);
//            //它或者他的下一个View是refresh类型的，就不需要画分界线
//            if (!(child.getId() == R.id.card_item_refresh_background_layout || nextChild.getId() == R.id.card_item_refresh_background_layout)) {
//                mDivider.draw(c);
//            }
        }
    }


    //由于Divider也有宽高，每一个Item需要向下或者向右偏移
    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        outRect.set(0, 0, 0, mDividerHeight);
    }

}

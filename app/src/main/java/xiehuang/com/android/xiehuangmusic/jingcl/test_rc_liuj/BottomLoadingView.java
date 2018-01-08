package xiehuang.com.android.xiehuangmusic.jingcl.test_rc_liuj;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import xiehuang.com.android.xiehuangmusic.R;
import xiehuang.com.android.xiehuangmusic.jingcl.test_rc_liuj.bean.BottomBean;


public class BottomLoadingView extends FrameLayout {

    private final Context mContext;
    private View mBottomLoadingView;
    private View mBottomLoadingNoNet;
    private View mBottomLoadingFail;

    public BottomLoadingView(Context context) {
        this(context, null);
    }

    public BottomLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mBottomLoadingView = findViewById(R.id.bottom_loading_view);
        mBottomLoadingNoNet = findViewById(R.id.bottom_loading_no_net);
        mBottomLoadingFail = findViewById(R.id.bottom_loading_failed);
    }

    public void updateState(int state){
        Log.d("Vincent","updateState : "+ state);
        switch (state){
            case BottomBean.STATE_LOADING:
                mBottomLoadingNoNet.setVisibility(GONE);
                mBottomLoadingView.setVisibility(VISIBLE);
                mBottomLoadingFail.setVisibility(GONE);
                break;
            case BottomBean.STATE_NO_NET:
                mBottomLoadingNoNet.setVisibility(VISIBLE);
                mBottomLoadingView.setVisibility(GONE);
                mBottomLoadingFail.setVisibility(GONE);
                break;
            case BottomBean.STATE_LOAD_FAIL:
                mBottomLoadingNoNet.setVisibility(GONE);
                mBottomLoadingView.setVisibility(GONE);
                mBottomLoadingFail.setVisibility(VISIBLE);
                break;
            default:
                break;
        }

    }
}

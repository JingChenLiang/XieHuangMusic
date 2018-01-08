package xiehuang.com.android.xiehuangmusic.jingcl.test_rc_liuj.bean;


import com.gionee.video.discover.infostream.NewsCardItem;

public class BottomBean implements NewsCardItem {

    public static final int STATE_LOADING = 0;
    public static final int STATE_NO_NET = 1;
    public static final int STATE_LOAD_FAIL = 2;
    private int mState = 0;

    public BottomBean(int state) {
        mState = state;
    }

    @Override
    public int getDataType() {
        return ITEM_TYPE_FOOTER;
    }

    public int getState() {
        return mState;
    }

    public void setState(int state) {
        this.mState = state;
    }
}

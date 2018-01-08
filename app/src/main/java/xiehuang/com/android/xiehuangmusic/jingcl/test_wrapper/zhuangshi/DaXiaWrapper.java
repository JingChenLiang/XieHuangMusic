package xiehuang.com.android.xiehuangmusic.jingcl.test_wrapper.zhuangshi;

import xiehuang.com.android.xiehuangmusic.jingcl.test_wrapper.DaXia;

/**
 * Created by erliang on 2018/1/7.
 */

public class DaXiaWrapper extends DaXia {

    private DaXia mDaXia;

    public DaXiaWrapper() {
    }

    public DaXiaWrapper(DaXia daXia) {
        mDaXia = daXia;
    }

    @Override
    public void gongJi() {
        mDaXia.gongJi();
    }

    @Override
    public void yinShen() {
        mDaXia.yinShen();
    }
}

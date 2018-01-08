package xiehuang.com.android.xiehuangmusic.jingcl.test_wrapper.zhuangshi;

import com.orhanobut.logger.Logger;

import xiehuang.com.android.xiehuangmusic.jingcl.test_wrapper.DaXia;

/**
 * Created by erliang on 2018/1/7.
 */

public class ShuoWrapper extends DaXiaWrapper {

    private String mStr;

    public ShuoWrapper(DaXia daXia, String str) {
        super(daXia);
        mStr = str;
    }

    private void shuo(String str) {
        Logger.i(str);
    }

    @Override
    public void gongJi() {
        shuo(mStr);
        super.gongJi();
    }
}

package xiehuang.com.android.xiehuangmusic.jingcl.test_wrapper.zhuangshi;

import com.orhanobut.logger.Logger;

import xiehuang.com.android.xiehuangmusic.jingcl.test_wrapper.DaXia;

/**
 * Created by erliang on 2018/1/7.
 */

public class ShuoWrapper extends DaXiaWrapper {
    public ShuoWrapper() {
    }

    public ShuoWrapper(DaXia daXia) {
        super(daXia);
    }

    @Override
    public void yinShen() {
        shuo();
        super.yinShen();
    }

    private void shuo() {
        Logger.i("啦啦啦~  你也来打我啊");
    }
}

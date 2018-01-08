package xiehuang.com.android.xiehuangmusic.jingcl.test_wrapper;

import com.orhanobut.logger.Logger;

/**
 * Created by erliang on 2018/1/7.
 */

public class ZhangFengXia extends DaXia {
    public ZhangFengXia() {
    }

    @Override
    public void gongJi() {
        Logger.i("张凤霞开始暴击");
    }

    @Override
    public void yinShen() {
        Logger.i("张凤霞已经隐身");
    }
}

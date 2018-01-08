package xiehuang.com.android.xiehuangmusic.jingcl.test_wrapper.zhuangshi;

import com.orhanobut.logger.Logger;

import xiehuang.com.android.xiehuangmusic.jingcl.test_wrapper.DaXia;

/**
 * Created by erliang on 2018/1/7.
 */

public class XuLiWrapper extends DaXiaWrapper {

    public XuLiWrapper() {
    }

    public XuLiWrapper(DaXia daXia) {
        super(daXia);
    }

    public void xuLi() {
        Logger.i("开始蓄力。。。");
    }

    @Override
    public void gongJi() {
        xuLi();
        super.gongJi();
    }
}

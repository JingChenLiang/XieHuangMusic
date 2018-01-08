package xiehuang.com.android.xiehuangmusic.jingcl.test_wrapper.zhuangshi;

import xiehuang.com.android.xiehuangmusic.jingcl.test_wrapper.DaXia;

/**
 * Created by erliang on 2018/1/7.
 */

public class DaXiaWrapper extends DaXia {

    //为了递归聚合类、不断的包装
    //DaXia类和ZhuangShiDaXia类构成一个环，于是就可以实现类的“递归嵌套”了，而“递归出口”就是不含daxia指针的类
    //shuo->gongJi->tiao
    private DaXia mDaXia;

    public DaXiaWrapper(DaXia daXia) {
        mDaXia = daXia;
    }

    @Override
    public void gongJi() {
        mDaXia.gongJi();
    }

}

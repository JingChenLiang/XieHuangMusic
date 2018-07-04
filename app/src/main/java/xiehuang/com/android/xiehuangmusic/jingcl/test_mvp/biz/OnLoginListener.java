package xiehuang.com.android.xiehuangmusic.jingcl.test_mvp.biz;

import xiehuang.com.android.xiehuangmusic.jingcl.test_mvp.bean.User;

/**
 * Created by zhy on 15/6/19.
 */
public interface OnLoginListener
{
    void loginSuccess(User user);

    void loginFailed();
}

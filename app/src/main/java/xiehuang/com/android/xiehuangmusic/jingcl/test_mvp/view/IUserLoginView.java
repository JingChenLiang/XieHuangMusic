package xiehuang.com.android.xiehuangmusic.jingcl.test_mvp.view;

import xiehuang.com.android.xiehuangmusic.jingcl.test_mvp.bean.User;

/**
 * Created by zhy on 15/6/19.
 */
public interface IUserLoginView
{
    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError();

}

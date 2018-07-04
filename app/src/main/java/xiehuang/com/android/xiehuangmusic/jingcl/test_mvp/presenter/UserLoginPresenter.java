package xiehuang.com.android.xiehuangmusic.jingcl.test_mvp.presenter;

import android.os.Handler;

import xiehuang.com.android.xiehuangmusic.jingcl.test_mvp.bean.User;
import xiehuang.com.android.xiehuangmusic.jingcl.test_mvp.biz.IUserBiz;
import xiehuang.com.android.xiehuangmusic.jingcl.test_mvp.biz.OnLoginListener;
import xiehuang.com.android.xiehuangmusic.jingcl.test_mvp.biz.UserBiz;
import xiehuang.com.android.xiehuangmusic.jingcl.test_mvp.view.IUserLoginView;

/**
 * Created by zhy on 15/6/19.
 */
public class UserLoginPresenter {
    private IUserBiz userBiz;
    private IUserLoginView userLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.userLoginView = userLoginView;
        this.userBiz = new UserBiz();
    }

    public void login() {
        userLoginView.showLoading();
        userBiz.login(userLoginView.getUserName(), userLoginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.toMainActivity(user);
                        userLoginView.hideLoading();
                    }
                });

            }

            @Override
            public void loginFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.showFailedError();
                        userLoginView.hideLoading();
                    }
                });

            }
        });
    }

    public void clear() {
        userLoginView.clearUserName();
        userLoginView.clearPassword();
    }


}

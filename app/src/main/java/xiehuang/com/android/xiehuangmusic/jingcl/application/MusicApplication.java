package xiehuang.com.android.xiehuangmusic.jingcl.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by erliang on 2017/12/19.
 */

public class MusicApplication extends Application {
    private static Context mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = getApplicationContext();
    }

    public static Context getInstance() {
        return mInstance;
    }
}

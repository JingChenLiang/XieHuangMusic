package xiehuang.com.android.xiehuangmusic.jingcl.others.application;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by erliang on 2017/12/19.
 */

public class MusicApplication extends Application {
    private static Context mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = getApplicationContext();
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public static Context getInstance() {
        return mInstance;
    }
}

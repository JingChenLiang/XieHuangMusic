package xiehuang.com.android.xiehuangmusic.homepage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import xiehuang.com.android.xiehuangmusic.R;
import xiehuang.com.android.xiehuangmusic.utils.ToastUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toWenLiangWorkspace(View view) {
        ToastUtils.showToast("去文亮的代码空间实验室");
    }

    public void toChenLiangWorkspace(View view) {
        ToastUtils.showToast("去晨亮的代码空间实验室");
    }
}

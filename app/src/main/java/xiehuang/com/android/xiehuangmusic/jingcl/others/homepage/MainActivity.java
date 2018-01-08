package xiehuang.com.android.xiehuangmusic.jingcl.others.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import xiehuang.com.android.xiehuangmusic.R;
import xiehuang.com.android.xiehuangmusic.jingcl.others.JingclActivity;
import xiehuang.com.android.xiehuangmusic.zhuwl.ZhuwlActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toWenLiangWorkspace(View view) {
        startActivity(new Intent(this, ZhuwlActivity.class));
    }

    public void toChenLiangWorkspace(View view) {
        startActivity(new Intent(this, JingclActivity.class));
    }
}

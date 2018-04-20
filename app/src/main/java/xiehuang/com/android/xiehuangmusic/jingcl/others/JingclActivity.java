package xiehuang.com.android.xiehuangmusic.jingcl.others;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import xiehuang.com.android.xiehuangmusic.R;
import xiehuang.com.android.xiehuangmusic.jingcl.test_okhttp.TestOkHttpActivity;
import xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview_HY.activity.MultiRecycleViewActivity;
import xiehuang.com.android.xiehuangmusic.jingcl.test_scan.ScanMusicActivity;
import xiehuang.com.android.xiehuangmusic.jingcl.test_viewpager_gridview.ViewpagerGridviewActivity;

public class JingclActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jingcl);
    }

    public void toRecycleViewPage(View view) {
        startActivity(new Intent(this, MultiRecycleViewActivity.class));
    }

    public void toScanActivity(View view) {
        startActivity(new Intent(this, ScanMusicActivity.class));
    }

    public void toViewPagerGridView(View view) {
        startActivity(new Intent(this, ViewpagerGridviewActivity.class));
    }

    public void toOkHttpActivity(View view) {
        startActivity(new Intent(this, TestOkHttpActivity.class));
    }
}

package xiehuang.com.android.xiehuangmusic.jingcl.others;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import xiehuang.com.android.xiehuangmusic.R;
import xiehuang.com.android.xiehuangmusic.jingcl.test_linkedlist.TestLinkedListActivity;
import xiehuang.com.android.xiehuangmusic.jingcl.test_mvp.UserLoginActivity;
import xiehuang.com.android.xiehuangmusic.jingcl.test_okhttp.TestOkHttpActivity;
import xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview_HY.activity.MultiRecycleViewActivity;
import xiehuang.com.android.xiehuangmusic.jingcl.test_scan.ScanMusicActivity;
import xiehuang.com.android.xiehuangmusic.jingcl.test_viewpager_gridview.ViewpagerGridviewActivity;

public class JingclActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jingcl);
//        Logger.i("onCreate", "");
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

    public void toLinkedListActivity(View view) {
        startActivity(new Intent(this, TestLinkedListActivity.class));

//        // 创建构建器
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        // 设置参数
//        builder.setTitle("请做出选择").setIcon(R.drawable.play_menu_share)
//                .setMessage("我美不美")
//                .setPositiveButton("美", new DialogInterface.OnClickListener() {// 积极
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(JingclActivity.this, "恭喜你答对了", 0).show();
//                    }
//                }).setNegativeButton("不美", new DialogInterface.OnClickListener() {// 消极
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(JingclActivity.this, "一点也不老实", 0).show();
//            }
//        }).setNeutralButton("不知道", new DialogInterface.OnClickListener() {// 中间级
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(JingclActivity.this, "快睁开眼瞅瞅", 0).show();
//            }
//        });
//        builder.create().show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        Logger.i("onRestart", "");
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Logger.i("onStart", "");
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Logger.i("onResume", "");
    }

    //--------

    @Override
    protected void onPause() {
        super.onPause();
//        Logger.i("onPause", "");
    }

    @Override
    protected void onStop() {
        super.onStop();
//        Logger.i("onStop", "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Logger.i("onDestory", "");
    }

    public void toMvp(View view) {
        startActivity(new Intent(this, UserLoginActivity.class));
    }
}

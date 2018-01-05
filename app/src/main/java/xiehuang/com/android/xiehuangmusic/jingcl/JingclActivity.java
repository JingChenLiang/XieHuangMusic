package xiehuang.com.android.xiehuangmusic.jingcl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import xiehuang.com.android.xiehuangmusic.R;
import xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview.MultiRecycleViewActivity;

public class JingclActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jingcl);
    }

    public void toRecycleViewPage(View view) {
        startActivity(new Intent(this, MultiRecycleViewActivity.class));
    }
}

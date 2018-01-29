package xiehuang.com.android.xiehuangmusic.jingcl.test_scan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import xiehuang.com.android.xiehuangmusic.R;
import xiehuang.com.android.xiehuangmusic.jingcl.others.utils.ToastUtils;
import xiehuang.com.android.xiehuangmusic.jingcl.test_scan.eventbus.ScanEvent;

public class ScanMusicActivity extends AppCompatActivity {

    @BindView(R.id.scan_result_area)
    TextView scanResultDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_music);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
    }

    public void startScan(View view) {
        ToastUtils.showToast("scan");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ScanEvent scanEvent) {
        scanResultDisplay.setText("共扫描到" + scanEvent.getScanResult() + "首歌曲");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

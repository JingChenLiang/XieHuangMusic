package xiehuang.com.android.xiehuangmusic.jingcl.test_scan.eventbus;

/**
 * Created by erliang on 2018/1/16.
 */

public class ScanEvent {
    private String scanResult;

    public ScanEvent(String scanResult){
        this.scanResult = scanResult;
    }

    public String getScanResult(){
        return scanResult;
    }
}

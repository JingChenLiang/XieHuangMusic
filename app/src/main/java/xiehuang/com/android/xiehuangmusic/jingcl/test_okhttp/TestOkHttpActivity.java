package xiehuang.com.android.xiehuangmusic.jingcl.test_okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import xiehuang.com.android.xiehuangmusic.R;

public class TestOkHttpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_ok_http);

        int[] arrs = {2, 4, 6, 8, 10, 22, 34};
        int resutl = test2Search(arrs, 22);
        Logger.i("索引是 = ", resutl);


        new Thread(new Runnable() {
            @Override
            public void run() {
                requestGet("http://japi.juhe.cn/joke/content/text.from?key=ae240f7fba620fc370b803566654949e&page=1&pagesize=10");
            }
        }).start();
    }

    OkHttpClient client = new OkHttpClient();

    String requestGet(String url) {
        try {
            Request request = new Request.Builder()//构建和表示相分离
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseString = response.body().string();
                return response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (Exception e) {
            Logger.i("e = " + e.toString());
        }
        return "null";
    }

    /**
     * 二分法查找
     * @param nums
     * @param num
     * @return
     */
    private int test2Search(int[] nums, int num) {
        //确定数组索引左右边界
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            //与中间值比较确定在左边还是右边区间,以调整区域
            if (num > nums[mid]) {
                //update L
                left = mid + 1;
            } else if (num < nums[mid]) {
                //update R
                right = mid - 1;
            } else {
                //equals，return index
                return mid;
            }
        }


        return -1;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.i("onDestory", "");
    }
}

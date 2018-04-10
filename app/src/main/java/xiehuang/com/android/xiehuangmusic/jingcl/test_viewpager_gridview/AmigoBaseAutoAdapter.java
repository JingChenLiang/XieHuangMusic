package xiehuang.com.android.xiehuangmusic.jingcl.test_viewpager_gridview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class AmigoBaseAutoAdapter extends BaseAdapter {

    //public abstract int getCount(); //返回数据数量

    //public abstract TargetInfo getItem(int position); //当前Item的数据

    public abstract View getItemView(int position, ViewGroup parent); //返回Item的布局

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getItemView(position, parent);
    }
}

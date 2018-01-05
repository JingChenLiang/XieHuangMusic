package xiehuang.com.android.xiehuangmusic.jingcl.test_recycleview.base;


/**
 * Created by erliang on 2018/01/05
 */
public interface ItemViewDelegate<T> {

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(ViewHolder holder, T t, int position);

}

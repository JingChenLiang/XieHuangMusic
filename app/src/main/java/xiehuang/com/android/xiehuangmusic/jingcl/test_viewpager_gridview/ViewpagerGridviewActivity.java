package xiehuang.com.android.xiehuangmusic.jingcl.test_viewpager_gridview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import xiehuang.com.android.xiehuangmusic.R;

public class ViewpagerGridviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_gridview);

        initData();
        initGridView();
    }

    private class Item {
        private int drawabelID;
        private String drawableText;

        public int getDrawabelID() {
            return drawabelID;
        }

        public void setDrawabelID(int drawabelID) {
            this.drawabelID = drawabelID;
        }

        public String getDrawableText() {
            return drawableText;
        }

        public void setDrawableText(String drawableText) {
            this.drawableText = drawableText;
        }
    }

    ArrayList<Item> items = new ArrayList<Item>();
    int[] drawableID = {
            R.drawable.play_menu_collection_small,
            R.drawable.play_menu_collection_small,
            R.drawable.play_menu_collection_small,
            R.drawable.play_menu_collection_small,
            R.drawable.play_menu_collection_small,
            R.drawable.play_menu_collection_small,
            R.drawable.play_menu_collection_small,
            R.drawable.play_menu_collection_small,
            R.drawable.play_menu_collection_small,
            R.drawable.play_menu_collection_small
    };
    private void initData() {
        for(int i=0; i<10; i++) {
            Item item = new Item();
            item.setDrawabelID(drawableID[i]);
            item.setDrawableText((i+1) + "");
            items.add(item);
        }
    }

    private void initGridView() {
        AmigoAutoMaticPageGridView gridView = (AmigoAutoMaticPageGridView) findViewById(R.id.gridview);
        gridView.setAdapter(new AmigoBaseAutoAdapter() {
            @Override
            public View getItemView(int position, ViewGroup viewGroup) {
                LayoutInflater inflater = LayoutInflater.from(ViewpagerGridviewActivity.this);
                View view = inflater.inflate(R.layout.playback_page_menu_gridview_item, null);
                ImageView icon = (ImageView) view.findViewById(R.id.icon);
                TextView iconText = (TextView) view.findViewById(R.id.iconText);
                icon.setBackgroundResource(items.get(position).getDrawabelID());
                iconText.setText(items.get(position).getDrawableText());

                return view;
            }

            @Override
            public int getCount() {
                return items.size();
            }

            @Override
            public Object getItem(int position) {
                return items.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }
        });
    }
}

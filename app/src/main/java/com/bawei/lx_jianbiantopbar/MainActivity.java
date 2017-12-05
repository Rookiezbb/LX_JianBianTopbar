package com.bawei.lx_jianbiantopbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private ScrollViewWithListView mLv;
    private List<String> list = new ArrayList<>();
    /**
     * biaoti
     */
    private Button mLine;
    private ObservableScrollView mScrollview;
    private int imageHeight=300; //设置渐变高度，一般为导航图片高度，自己控制

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getData();
        //搜索框在布局最上面
        mLine.bringToFront();
        //滑动监听
        mScrollview.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {
                    mLine.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
                } else if (y > 0 && y <= imageHeight) {
                    float scale = (float) y / imageHeight;
                    float alpha = (255 * scale);
                    // 只是layout背景透明
                    mLine.setBackgroundColor(Color.argb((int) alpha, 227, 29, 26));
                } else {
                    mLine.setBackgroundColor(Color.argb((int) 255, 227, 29, 26));
                }
            }
        });
    }

    private void getData() {

        for (int i = 0; i < 50; i++) {
            list.add(i + "");
        }
        mLv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int position) {
                return list.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = View.inflate(MainActivity.this, R.layout.item, null);
                TextView tv = (TextView) convertView.findViewById(R.id.tv);
                tv.setText(list.get(position));
                return convertView;
            }
        });
    }

    private void initView() {
        mLv = (ScrollViewWithListView) findViewById(R.id.lv);
        mLine = (Button) findViewById(R.id.line);
        mScrollview = (ObservableScrollView) findViewById(R.id.scrollview);
    }


}

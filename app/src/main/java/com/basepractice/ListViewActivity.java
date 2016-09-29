package com.basepractice;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.basepractice.util.Tag;

/**
 * ListView使用基本技巧：
 *  1 使用ViewHolder提高效率
 *  2 子项之间的分割线可以使用android:divider(@null)和android:dividerHeight设置
 *  3 android:scrollbars="none" 去掉滚动条
 *  4 设置item的点击效果android:listSelector
 *  5 默认显示的第一个item，可指定position的item显示在第一个位置,
 *      设置显示第几个item listview.setSelection(int position)
 *  6 平滑移动还可以这样：
 *      listview.smoothScrollBy(distance,duration)
 *      listview.smoothScrollByOffset(offset)
 *      listview.smoothScrollToPosition(index)
 *  7 通知内容更新 adaoter.notifyDatasetChanged
 *  8 遍历所有的item
 *      for(int i=0;i<listview.getChildCount();i++){
 *          child = listview.getChildAt(i);
 *      }
 *  9 当listview为空时,可设置默认显示
 *      listview.setEmptyView
 */
public class ListViewActivity extends Activity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_optimused);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(new BaseAdapter() {

            @Override
            public int getCount() {
                return 50;
            }

            @Override
            public Object getItem(int position) {
                return ""+position+"";
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null){
                    convertView = new TextView(getBaseContext());
                    Tag.i(Tag.LIST_VIEW," create a new view");
                }
                ((TextView)convertView).setText("Position:"+position);
                ((TextView)convertView).setTextColor((position % 2) == 0 ? Color.RED : Color.BLUE);
                return convertView;
            }
        });
        listView.setSelection(20);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        Log.i("Test","SCROLL_STATE_IDLE");
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        Log.i("Test","SCROLL_STATE_TOUCH_SCROLL");
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                        Log.i("Test","SCROLL_STATE_FLING");
                        break;
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.d("Test","onScroll");
            }
        });
    }

    public void smoothScrollBy(View view) {
        View itemView = listView.getAdapter().getView(0,null,null);
        itemView.measure(0,0);
        listView.smoothScrollBy(5 * (itemView.getMeasuredHeight()+listView.getDividerHeight()),5000);
        Tag.i(Tag.LIST_VIEW,"itemHeight:"+itemView.getMeasuredHeight());
    }
    public void smoothScrollByOffset(View view){
        View itemView = listView.getAdapter().getView(0,null,null);
        itemView.measure(0,0);
        listView.smoothScrollByOffset(2 * (itemView.getMeasuredHeight()+listView.getDividerHeight()));
    }
    public void smoothScrollTo(View view) {
        listView.smoothScrollToPosition(10);
    }
}

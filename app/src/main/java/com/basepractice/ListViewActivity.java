package com.basepractice;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.basepractice.util.Tag;

/**
 * ListView使用基本技巧：
 * 1 使用ViewHolder提高效率
 * 2 子项之间的分割线可以使用android:divider(@null)和android:dividerHeight设置
 * 3 android:scrollbars="none" 去掉滚动条
 * 4 设置item的点击效果android:listSelector
 * 5 默认显示的第一个item，可指定position的item显示在第一个位置,
 * 设置显示第几个item listview.setSelection(int position)
 * 6 平滑移动还可以这样：
 * listview.smoothScrollBy(distance,duration)
 * listview.smoothScrollByOffset(offset)（搞不懂参数的含义）
 * listview.smoothScrollToPosition(index)
 * 7 通知内容更新 adaoter.notifyDatasetChanged
 * 8 遍历所有的item
 * for(int i=0;i<listview.getChildCount();i++){
 * child = listview.getChildAt(i);
 * }
 * 9 当listview为空时,可设置默认显示
 * listview.setEmptyView
 */
public class ListViewActivity extends ActionBarActivity {
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
                return "" + position + "";
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = new TextView(getBaseContext());
                    Tag.i(Tag.LIST_VIEW, " create a new view");
                }
                ((TextView) convertView).setText("Position:" + position);
                ((TextView) convertView).setTextColor((position % 2) == 0 ? Color.RED : Color.BLUE);
                return convertView;
            }
        });
        listView.setSelection(20);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        Log.i("Test", "SCROLL_STATE_IDLE");
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        Log.i("Test", "SCROLL_STATE_TOUCH_SCROLL");
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                        Log.i("Test", "SCROLL_STATE_FLING");
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                Log.d("Test", "onScroll");
            }
        });
        addTouchEvent();
    }

    private void addTouchEvent() {
        //增加一个HeaderView
        View headerView = new View(this);
        headerView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(android.support.design.R.dimen.abc_action_bar_default_height_material)));
        listView.addHeaderView(headerView);

        final View actionBar = this.findViewById(R.id.action_bartest);
        //设置一个最小滑动距离
        final int mTouchMinDistance = 50;//ViewConfiguration.get(this).getScaledTouchSlop();
        listView.setOnTouchListener(new View.OnTouchListener() {
            float mDownY;
            float mCurrY;
            int mDirection = -1;
            boolean mShow = true;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mDownY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mCurrY = event.getY();
                        if (mCurrY - mDownY > mTouchMinDistance) {
                            mDirection = 0;//列表下滑
                            Log.i("ListView","down------------");
                        } else if (mDownY - mCurrY > mTouchMinDistance) {
                            mDirection = 1;//列表上滑
                            Log.i("ListView","up------------");
                        }
                        if (mDirection == 0) {
                            if(!mShow){
                                showOrHideView(mDirection,actionBar);
                                mShow = true;
                                Log.i("ListView","show------------");
                            }
                        } else if (mDirection == 1) {
                            if(mShow){
                                showOrHideView(mDirection,actionBar);
                                mShow = false;
                                Log.i("ListView","hide------------");
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        mDirection = -1;
                        if(!mShow){
                            mShow = true;
                            showOrHideView(0,actionBar);
                        }
                        break;
                }
                return false;
            }
        });
    }

    private void showOrHideView(int showOrHide, View view) {
        Animator animator = null;
        switch (showOrHide) {
            case 0:
                animator = ObjectAnimator.ofFloat(view, "translationY", view.getTranslationY(), 0);
                break;
            case 1:
                animator = ObjectAnimator.ofFloat(view, "translationY", view.getTranslationY(), -view.getHeight());
                break;
        }
        if (animator != null)
            animator.start();
    }

    public void smoothScrollBy(View view) {
        View itemView = listView.getAdapter().getView(0, null, null);
        itemView.measure(0, 0);
        //在当前位置,移动距离
        listView.smoothScrollBy(5 * (itemView.getMeasuredHeight() + listView.getDividerHeight()), 1000);
        Tag.i(Tag.LIST_VIEW, "itemHeight:" + itemView.getMeasuredHeight());
    }

    public void smoothScrollByOffset(View view) {
        View itemView = listView.getAdapter().getView(0, null, null);
        itemView.measure(0, 0);
        Tag.i(Tag.LIST_VIEW, "smoothScrollByOffset:" + 2 * (itemView.getMeasuredHeight() + listView.getDividerHeight()));
        //还没明白
        listView.smoothScrollByOffset(1);
    }

    public void smoothScrollTo(View view) {
        //参数是position,移动到该position的位置,即显示的position是第一个item（稍微会有点偏差）
        listView.smoothScrollToPosition(10);
    }
}

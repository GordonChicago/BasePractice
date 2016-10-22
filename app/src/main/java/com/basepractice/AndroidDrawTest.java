package com.basepractice;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.basepractice.util.Tag;
import com.basepractice.view.SelfView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/10/17.
 */

public class AndroidDrawTest extends FragmentActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private SelfView[] selfViews = new SelfView[4];
    private ViewPager mPager;
    private MViewPagerAdapter mPageAdapter;
    //适配数据
    private String[] mTitles = new String[]{"First Fragment", "Second Fragment", "Third Fragment", "Fourth Fragment"};
    private List<Fragment> mTabFragments = new ArrayList<Fragment>();

    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_draw_layout);
        init();
    }

    private void init() {
        //initView
        SelfView one = (SelfView) findViewById(R.id.seletct_one);
        SelfView two = (SelfView) findViewById(R.id.seletct_two);
        SelfView three = (SelfView) findViewById(R.id.seletct_three);
        SelfView four = (SelfView) findViewById(R.id.seletct_four);
        selfViews[0] = one;
        selfViews[1] = two;
        selfViews[2] = three;
        selfViews[3] = four;
        for (SelfView sv :
                selfViews) {
            sv.setOnClickListener(this);
        }
        mPager = (ViewPager) findViewById(R.id.viewPager_test);

        //initData
        for (String title : mTitles) {
            MFragment mFragment = new MFragment();
            Bundle args = new Bundle();
            args.putString(MFragment.TITLE_KEY, title);
            mFragment.setArguments(args);
            mTabFragments.add(mFragment);
        }

        //adapter data
        mPageAdapter = new MViewPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPageAdapter);
        mPager.setCurrentItem(currentIndex,false);
        selectIndicator(currentIndex);

        Tag.i(Tag.SELF_VIEW, "currentIndex:" + currentIndex);

        //设置监听
        mPager.setOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.seletct_one:
                currentIndex = 0;
                break;
            case R.id.seletct_two:
                currentIndex = 1;
                break;
            case R.id.seletct_three:
                currentIndex = 2;
                break;
            case R.id.seletct_four:
                currentIndex = 3;
                break;
        }
        mPager.setCurrentItem(currentIndex,false);
        selectIndicator(currentIndex);
    }

    private class MViewPagerAdapter extends FragmentPagerAdapter {
        public MViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return mTabFragments.get(position);
        }

        @Override
        public int getCount() {
            return mTabFragments.size();
        }
    }

    public static class MFragment extends Fragment {
        public static final String TITLE_KEY = "title";
        private String mTitle;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            Bundle data = getArguments();
            if (data != null) {
                mTitle = data.getString(TITLE_KEY);
            }

            TextView tv = new TextView(getContext());
            tv.setGravity(Gravity.CENTER);
            tv.setText(mTitle);
            return tv;
        }
    }

    private void selectIndicator(int selectIndex) {
        for (int i = 0; i < selfViews.length; i++) {
            if (selectIndex == i) {
                selfViews[i].setAlpha(1f);
            } else {
                selfViews[i].setAlpha(0f);
            }
        }
    }

    /**
     * @param position
     * @param positionOffset       是指的一个页面的左半部分
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset > 0) {
            float leftAlpha = 1 - positionOffset;
            float rightAlpha = positionOffset;
            selfViews[position].setAlpha(leftAlpha);
            selfViews[position + 1].setAlpha(rightAlpha);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}

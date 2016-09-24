package com.basepractice.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.basepractice.util.ViewUtils;

/**
 * Created by Administrator on 2016/9/24.
 */
public class MeasuredView extends View {
    private int DEFAULT_WIDTH = 100; //数值为dp
    private int DEFAULT_HEIGHT = 100; //数值为dp

    public MeasuredView(Context context) {
        super(context);
    }
    public MeasuredView(Context context, AttributeSet set) {
        super(context,set);
    }
    public MeasuredView(Context context, AttributeSet set,int defStyle) {
        super(context,set,defStyle);
    }

    /**
     * 重写onMeasure方法，目的是当该view的属性为wrap_content的时候，能够给其一个默认值(宽,高)
     * 如果该view的属性为wrap_content,不重写onMeasure，系统就不知道使用多大的尺寸,因此会填充父布局!
     * 例如直接调用super.onMeasure方法
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        setMeasuredDimension(ViewUtils.measureSize(getContext(),widthMeasureSpec,DEFAULT_WIDTH),
                ViewUtils.measureSize(getContext(),heightMeasureSpec,DEFAULT_HEIGHT));
    }


}

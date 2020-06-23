package com.android.selectorview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;

import androidx.appcompat.widget.AppCompatTextView;

public class SelectorTextView extends AppCompatTextView implements Checkable {

    private boolean mChecked = false;   //选中状态

    private int trueImgBackground;//选中背景
    private int falseImgBackground;//未选中背景

    private boolean openClickChecked;//是否开启点击切换功能

    private int trueTextColor;
    private int falseTextColor;

    public SelectorTextView(Context context) {
        this(context, null);
    }

    public SelectorTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectorTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    //初始化
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SelectorTextView);
        if (ta != null) {
            mChecked = ta.getBoolean(R.styleable.SelectorTextView_checked, false);

            openClickChecked = ta.getBoolean(R.styleable.SelectorTextView_openClickChecked, false);

            trueTextColor = ta.getColor(R.styleable.SelectorTextView_trueTextColor, 0);

            falseTextColor = ta.getColor(R.styleable.SelectorTextView_falseTextColor, 0);

            trueImgBackground = ta.getResourceId(R.styleable.SelectorTextView_trueImgBackground, 0);

            falseImgBackground = ta.getResourceId(R.styleable.SelectorTextView_falseImgBackground, 0);
            //获取属性完毕 释放资源
            ta.recycle();
        }
        if (openClickChecked) {
            setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    setChecked(!mChecked);
                }
            });
        }
        setCheckedState(mChecked);
        setChecked(mChecked);
    }

    private void setCheckedState(boolean checked) {
        if (checked) {
            if (trueImgBackground != 0) {
                setBackgroundResource(trueImgBackground);
            }
            if (trueTextColor != 0) {
                setTextColor(trueTextColor);
            }
        } else {
            if (falseImgBackground != 0) {
                setBackgroundResource(falseImgBackground);
            }
            if (falseTextColor != 0) {
                setTextColor(falseTextColor);
            }
        }
    }

    /**
     * 创建控件状态类型
     *
     * @param extraSpace 当前数组里的状态数据量
     * @return 返回状态数组
     */
    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        //调用父类的本方法可以获得到在父类添加过状态的数组,我们给数组加1然后添加我们的状态到状态数组中
        final int[] drawableState = super.onCreateDrawableState(extraSpace + SelectorManager.STATE_LENGTH);
        if (isChecked()) {//判断当前的状态是不是true 如果是就添加状态到数组中
            mergeDrawableStates(drawableState, SelectorManager.CHECKED_STATE_SET);//调用view自带的合并功能添加状态
        }
        return drawableState;//把添加好的数组返回给父类
    }

    /**
     * 设置选中状态并刷新当前控件的状态
     *
     * @param checked
     */
    @Override
    public void setChecked(boolean checked) {
        if (mChecked != checked) {
            mChecked = checked;
            setCheckedState(checked);
            refreshDrawableState();
        }
    }

    /**
     * 获取当前状态
     */
    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }
}

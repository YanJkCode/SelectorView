package com.android.selectorview;

public class SelectorManager {
    //可选中的状态码
    public static int[] CHECKED_STATE_SET = new int[]{android.R.attr.state_checked};
    //可选中的状态码 数量
    public static int STATE_LENGTH = 1;

    public static void setCheckedStateSet(int[] checkedStateSet) {
        if (checkedStateSet != null && checkedStateSet.length > 0) {
            CHECKED_STATE_SET = checkedStateSet;
            STATE_LENGTH = checkedStateSet.length;
        }
    }
}

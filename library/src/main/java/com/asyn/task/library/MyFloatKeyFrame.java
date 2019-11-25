package com.asyn.task.library;

/**
 * Created by ThinkPad on 2019/11/22.
 * 关键帧
 */

public class MyFloatKeyFrame {

    public MyFloatKeyFrame(float mFraction, float mValue) {
        this.mFraction = mFraction;
        this.mValue = mValue;
    }

    float mFraction;//当前动画的百分比

    Class mValueType;//float  返回什么类型的值 默认是float

    float mValue;//具体值

    public float getmFraction() {
        return mFraction;
    }

    public void setmFraction(float mFraction) {
        this.mFraction = mFraction;
    }

    public float getmValue() {
        return mValue;
    }

    public void setmValue(float mValue) {
        this.mValue = mValue;
    }
}

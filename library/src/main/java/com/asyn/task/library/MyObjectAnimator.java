package com.asyn.task.library;

import android.animation.ObjectAnimator;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Created by ThinkPad on 2019/11/22.
 */

public class MyObjectAnimator  implements VSYNCManager.AnimationFrameCallback{

    private static final String LOG_TAG = "MyObjectAnimator";
    long mStartTime =-1;//动画开始时间
    private long mDuration =0;

    private WeakReference<View> mTarget;



    /**
     *
     * @param target   需要执行动画的view
     * @param propertyName  执行动画的名字
     * @param values  关键帧
     * @return
     */
    //属性管理器
    MyPropertyValuesHodler myPropertyValuesHodler;
    private float index = 0;
    private TimeInterpolator interpolator;

    public MyObjectAnimator(View target, String propertyName, float[] values) {
        mTarget = new WeakReference<View>(target);
        myPropertyValuesHodler = new MyPropertyValuesHodler(propertyName,values);


    }

    public void setDuration(long mDuration) {
        this.mDuration = mDuration;
    }

    public void setInterpolator(TimeInterpolator interpolator) {
        this.interpolator = interpolator;
    }


    public static MyObjectAnimator ofFloat(View target, String propertyName, float... values) {

      MyObjectAnimator animator = new MyObjectAnimator(target,propertyName,values);

        return animator;
    }

    /**
     * 每隔16毫米调用
     * @param currntTime
     * @return
     */

    @Override
    public boolean doAnimationFrame(long currntTime) {
        //需要计算当前的百分比
        float total = mDuration /16;//一共分成多少分
        //动画执行的百分比(index ++ )/total
        float  fraction =(index ++ )/ total;
        if (interpolator != null){
            fraction = interpolator.getInterpolator(fraction);

        }
        if (index >= total){
            index = 0;
        }

        myPropertyValuesHodler.setAnimatedValue(mTarget.get(),fraction);
        return false;


    }


    public void start(){
        myPropertyValuesHodler.setupSetter();
        mStartTime =System.currentTimeMillis();
        VSYNCManager.getInstance().add(this);

    }
}

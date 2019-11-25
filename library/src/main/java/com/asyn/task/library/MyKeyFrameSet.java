package com.asyn.task.library;

import android.animation.FloatEvaluator;
import android.animation.TypeEvaluator;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ThinkPad on 2019/11/22.
 *关键帧管理类
 */

public class MyKeyFrameSet {


    //存储关键帧
    List<MyFloatKeyFrame> mykeyFrames;

    //类型估值其
    TypeEvaluator mEvaluator;


    public MyKeyFrameSet(MyFloatKeyFrame... keyFrame){
        mykeyFrames = Arrays.asList(keyFrame);
        mEvaluator =new FloatEvaluator();

    }
    public static MyKeyFrameSet ofFloat(float[] values) {
        if (values.length <= 0){
            return null;
        }

        int numKeyframes = values.length;
        //关键帧初始化 for
        MyFloatKeyFrame keyFrame[] =new MyFloatKeyFrame[numKeyframes];
        keyFrame[0] = new MyFloatKeyFrame(0,values[0]);
        for (int i=0;i<numKeyframes;i++){
            //计算
            keyFrame[i] = new MyFloatKeyFrame((float) i/(numKeyframes -1 ),values[i]);
        }

        return new MyKeyFrameSet(keyFrame);

    }



    public Object getValues(float fraction){
        MyFloatKeyFrame prevKeyframe = mykeyFrames.get(0);
        for (int i=0;i<mykeyFrames.size();i++){
            MyFloatKeyFrame nextKeyframe =mykeyFrames.get(i);
            if (fraction<nextKeyframe.getmFraction()){
            //关键部分  间隔百分比===两帧之间的百分比  就是1f  到  2f  的 之间走了多少百分比
                float intervalFraction = (fraction - prevKeyframe.getmFraction())/
                        (nextKeyframe.getmFraction() - prevKeyframe.getmFraction());

                return mEvaluator == null ?
                        prevKeyframe.getmValue() + intervalFraction *(nextKeyframe.getmValue()):
                mEvaluator.evaluate(intervalFraction,prevKeyframe.getmValue(),nextKeyframe.getmValue());
            }

            prevKeyframe = nextKeyframe;//切帧操作
        }

        //完成===返回最后一帧的具体的值
        return mykeyFrames.get(mykeyFrames.size() - 1 ).getmValue();
    }

}

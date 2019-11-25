package com.asyn.task.library;

import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 关键帧管理类
 */

public class MyPropertyValuesHodler {

    //属性名字
  String mProperName;

  Class mValueType;
  //反射
  Method mSeeter = null;

  //关键帧管理类
    MyKeyFrameSet myKeyFrameSet;




   public MyPropertyValuesHodler(String propertyName ,float... values){


       this.mProperName = propertyName;
       mValueType =float.class;
       myKeyFrameSet =MyKeyFrameSet.ofFloat(values);


    }


    public void setupSetter(){
       char fierstLetter =Character.toUpperCase(mProperName.charAt(0));
       String theRest =mProperName.substring(1);
       String methodName ="set"+fierstLetter+theRest;
       //反射
        try {
            mSeeter = View.class.getMethod(methodName,float.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    /**
     * 通过反射方式进行属性赋值
     * @param viw
     * @param fraction
     */
    public void setAnimatedValue (View viw ,float fraction){
     Object values=myKeyFrameSet.getValues(fraction);

     //通过反射方法进行赋值操作
        try {
            mSeeter.invoke(viw,values);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}

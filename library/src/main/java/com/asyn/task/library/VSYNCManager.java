package com.asyn.task.library;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2019/11/22.
 */

public class VSYNCManager {
    private static final VSYNCManager ourInstance =new VSYNCManager();

    private List<AnimationFrameCallback> list =new ArrayList<>();

    public static VSYNCManager getInstance(){
        return ourInstance;
    }


    private VSYNCManager(){

        new Thread(runnable).start();
    }


    Runnable runnable =new Runnable() {
        @Override
        public void run() {
            //循环执行
            while (true) {
                try {

                    Thread.sleep(16);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                for (AnimationFrameCallback animationFrameCallback : list) {
                    animationFrameCallback.doAnimationFrame(System.currentTimeMillis());
                }
            }
        }

    };


    interface  AnimationFrameCallback{
        boolean doAnimationFrame(long currntTime);
    }


    public void add(AnimationFrameCallback animationFrameCallback){
        list.add(animationFrameCallback);
    }


}

package com.asyn.task.neteaseevent;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.asyn.task.library.LineInterpolator;
import com.asyn.task.library.MyObjectAnimator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttton =findViewById(R.id.btn);
        MyObjectAnimator objectAnimator=MyObjectAnimator.ofFloat(buttton,"ScaleX",1f,2f,3f,1f);
        objectAnimator.setInterpolator(new LineInterpolator());
        objectAnimator.setDuration(3000);
        objectAnimator.start();



    }
}

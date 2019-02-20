package com.bytetrade.bytehub.byteluckywheel;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class PointView extends ImageView {
    public PointView(Context context) {
        super(context);
    }

    public PointView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PointView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PointView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setPointState() {
        this.setImageResource(R.drawable.lucky_node);
    }


    float mAngle = 15;
    private int lastPosition;
    private float currAngle = (float) 7.5;
    private int mMinTimes = 3;
    private int mVarTime = 30;

    public void startRotate(final int pos) {
        //最低圈数是mMinTimes圈
        int newAngle = (int) (360 * mMinTimes + (pos - 1) * mAngle + currAngle - (lastPosition == 0 ? 0 : ((lastPosition - 1) * mAngle)));
        //计算目前的角度划过的扇形份数
        int num = (int) ((newAngle - currAngle) / mAngle);
        ObjectAnimator anim = ObjectAnimator.ofFloat(this, "rotation", currAngle, newAngle);
        currAngle = newAngle;
        lastPosition = pos;
        // 动画的持续时间，执行多久？
        anim.setDuration(num * mVarTime);
//        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                //将动画的过程态回调给调用者
//                if (rotateListener != null)
//                    rotateListener.rotating(animation);
//            }
//        });
        anim.start();
    }
}

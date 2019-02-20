package com.bytetrade.bytehub.byteluckywheel;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LuckyWheelMainActivity extends AppCompatActivity {

    private List<RecyclerViewData> recyclerViewDataList = new ArrayList<>();

    LuckySwapLoadingView mLuckySwapLoadingView;
    LuckySwapImageView mLuckySwapView;
    LuckySwapBottomView nLuckySwapView;
    CircleView mCircleView;
    PointView nPointView;

    private RelativeLayout mtextview;
    private RelativeLayout ntextview;
    private View buttonview;

    int mNum = 0;
    int i = 0;
    float mHorizentalMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky_wheel_main);

        mHorizentalMove = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.dp_142), getResources().getDisplayMetrics());

        mtextview = (RelativeLayout) findViewById(R.id.record);
        ntextview = (RelativeLayout) findViewById(R.id.gift);
        buttonview = (View) findViewById(R.id.button_view);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        nLuckySwapView = (LuckySwapBottomView) this.findViewById(R.id.pointview);

        mLuckySwapView = (LuckySwapImageView) this.findViewById(R.id.luckyswapview);

        mLuckySwapLoadingView = (LuckySwapLoadingView) this.findViewById(R.id.luckyloadingview);

        nLuckySwapView.uodateBackgroundState(LuckySwapBottomView.BACKGROUNDRED);

        mLuckySwapView.uodateCurrentState(LuckySwapImageView.STATEFREEBLING);

        mCircleView = (CircleView) this.findViewById(R.id.circleView);

        nPointView = (PointView) this.findViewById(R.id.circlepan);

        nLuckySwapView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//                RotateAnimation mRotateAnimation = new RotateAnimation(0, 3600, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//                mRotateAnimation.setDuration(10000);
//                mRotateAnimation.setInterpolator(new LinearInterpolator());
//                mRotateAnimation.setRepeatCount(Animation.INFINITE);
//                nPointView.startAnimation(mRotateAnimation);

                int position = new Random().nextInt(23) + 1;
                nPointView.startRotate(position);

                RotateAnimation nRotateAnimation = new RotateAnimation(0, 3600, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                nRotateAnimation.setDuration(10000);
                nRotateAnimation.setInterpolator(new LinearInterpolator());
                nRotateAnimation.setRepeatCount(Animation.INFINITE);

                if (mNum == 0) {
                    nLuckySwapView.uodateBackgroundState(LuckySwapBottomView.BACKGROUNDRED);
                    mLuckySwapLoadingView.startAnimation(nRotateAnimation);
                    if (mLuckySwapLoadingView.getVisibility() == View.GONE)
                        mLuckySwapLoadingView.setVisibility(View.VISIBLE);
                    mNum = 1;
                    i++;
                } else if (mNum == 1) {

                    nRotateAnimation.cancel();
                    Log.v("rotate", "rotate animation cancel");

                    mLuckySwapLoadingView.clearAnimation();
                    Log.v("rotate", "rotate clear animation");

                    if (mLuckySwapLoadingView.getVisibility() == View.VISIBLE)
                        mLuckySwapLoadingView.setVisibility(View.GONE);
                    nLuckySwapView.uodateBackgroundState(LuckySwapBottomView.BACKGROUNDGREY);
                    mLuckySwapView.uodateCurrentState(LuckySwapImageView.STATEFREERUNNING);
                    mNum = 2;

                } else if (mNum == 2) {
                    nLuckySwapView.uodateBackgroundState(LuckySwapBottomView.BACKGROUNDRED);
                    mLuckySwapView.uodateCurrentState(LuckySwapImageView.STATECOSTBLING);
                    mNum = 3;

                } else if (mNum == 3) {
                    nLuckySwapView.uodateBackgroundState(LuckySwapBottomView.BACKGROUNDRED);
                    mLuckySwapLoadingView.startAnimation(nRotateAnimation);
                    if (mLuckySwapLoadingView.getVisibility() == View.GONE)
                        mLuckySwapLoadingView.setVisibility(View.VISIBLE);
                    mNum = 4;

                } else if (mNum == 4) {

                    mLuckySwapLoadingView.clearAnimation();
                    nLuckySwapView.uodateBackgroundState(LuckySwapBottomView.BACKGROUNDGREY);
                    mLuckySwapView.uodateCurrentState(LuckySwapImageView.STATECOSTRUNNING);
                    if (mLuckySwapLoadingView.getVisibility() == View.VISIBLE)
                        mLuckySwapLoadingView.setVisibility(View.GONE);
                    mNum = 2;
                }
            }
        });

        ntextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonview.animate().translationX(mHorizentalMove).setDuration(150).setInterpolator(new AccelerateDecelerateInterpolator());
            }
        });

        mtextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonview.animate().translationX(0).setDuration(150).setInterpolator(new AccelerateDecelerateInterpolator());
                ;
            }
        });

        //RecyclerView第一次尝试

        initRecyclerViewData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view_1);
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(recyclerViewDataList);
        recyclerView.setAdapter(adapter);

    }

    private void initRecyclerViewData() {
        RecyclerViewData first = new RecyclerViewData("Liuhuang333…", "0.01 ETH", "2018.12.28 10:30:28");
        recyclerViewDataList.add(first);
        RecyclerViewData first1 = new RecyclerViewData("Liuhuang333…", "100 KCASH", "2018.12.28 10:33:09");
        recyclerViewDataList.add(first1);
        RecyclerViewData first2 = new RecyclerViewData("Liuhuang333…", "100 IOST", "2018.12.29 24:30:44");
        recyclerViewDataList.add(first2);
        RecyclerViewData first3 = new RecyclerViewData("Liuhuang333…", "1000 MT", "2018.12.29 24:30:56");
        recyclerViewDataList.add(first3);
        RecyclerViewData first4 = new RecyclerViewData("Liuhuang333…", "0.1 DGD", "2018.12.30 08:42:06");
        recyclerViewDataList.add(first4);
    }

}
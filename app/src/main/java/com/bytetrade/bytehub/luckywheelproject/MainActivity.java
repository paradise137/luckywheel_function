package com.bytetrade.bytehub.luckywheelproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.bytetrade.bytehub.byteluckywheel.LuckyWheelMainActivity;

public class MainActivity extends Activity {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky_wheel_main);

        mContext = MainActivity.this;
        Intent mIntent = new Intent(mContext, LuckyWheelMainActivity.class);
        mContext.startActivity(mIntent);

    }

}

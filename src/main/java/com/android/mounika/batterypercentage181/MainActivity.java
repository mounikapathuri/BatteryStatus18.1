package com.android.mounika.batterypercentage181;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tvBattery);


        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(broadcastReceiver,intentFilter);
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int mBatteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
            int mscale = intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
            int mLevel = -1;
            if (mBatteryLevel >=0 && mscale>0) {
                mLevel = (mBatteryLevel * 100) / mscale;
            }
            textView.setText("Battery Status : "+mLevel + "%");
        }
    };
}

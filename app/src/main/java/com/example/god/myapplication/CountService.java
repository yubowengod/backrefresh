package com.example.god.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by GOD on 2016/9/8.
 */
public class CountService extends Service {
    private int count = 0;
    private boolean threadDisable=false;

    @Override
    public void onCreate() {
        super.onCreate();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!threadDisable) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                    Log.v("CountService", "Count is " + count);

                    //发送广播
                    Intent intent=new Intent();
                    intent.putExtra("count", count);
                    intent.setAction("com.ljq.activity.CountService");
                    sendBroadcast(intent);
                }
            }
        }).start();

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        count=0;
        threadDisable = true;
        Log.v("CountService", "on destroy");
    }


}
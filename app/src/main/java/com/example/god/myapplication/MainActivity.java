package com.example.god.myapplication;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    private EditText editText=null;
    private MyReceiver receiver=null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        editText=(EditText)findViewById(R.id.editText);

        //启动服务
        startService(new Intent(MainActivity.this, CountService.class));

        //注册广播接收器
        receiver=new MyReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction("com.ljq.activity.CountService");
        MainActivity.this.registerReceiver(receiver,filter);
    }

    @Override
    protected void onDestroy() {
        //结束服务
        stopService(new Intent(MainActivity.this, CountService.class));
        super.onDestroy();
    }

    /**
     * 获取广播数据
     *
     * @author jiqinlin
     *
     */
    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle=intent.getExtras();
            int count=bundle.getInt("count");
            editText.setText(count+"");
        }
    }




}
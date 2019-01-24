package com.owen.demo.sensordemo.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.owen.demo.sensordemo.R;
import com.owen.demo.sensordemo.utils.LogUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = "MainActivity";
    private LogUtils logUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logUtils = new LogUtils(TAG);
        initView();
    }

    private void initView() {
        TextView tvDispaly = findViewById(R.id.tv_dispaly);
        Button btnStartToTimeActivity = findViewById(R.id.btn_start_to_time_activity);
        btnStartToTimeActivity.setOnClickListener(this);
        Button btnStart = findViewById(R.id.btn_start);
        btnStart.setOnClickListener(this);
        Button btnPause = findViewById(R.id.btn_pause);
        btnPause.setOnClickListener(this);
        Button btnStop = findViewById(R.id.btn_stop);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                logUtils.i("开始的点击事件");
                break;
            case R.id.btn_pause:
                logUtils.i("暂停的点击事件");
                break;
            case R.id.btn_stop:
                logUtils.i("停止的点击事件");
                break;
            case R.id.btn_start_to_time_activity:
                startToTimeActivity();
                break;
            default:
                logUtils.i("未定义的点击事件");
                break;
        }
    }

    private void startToTimeActivity() {
        Intent intent = new Intent(MainActivity.this,TimeActivity.class);
        startActivity(intent);
    }
}

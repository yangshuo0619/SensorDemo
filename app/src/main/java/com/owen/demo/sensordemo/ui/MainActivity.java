package com.owen.demo.sensordemo.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        Button btnStartToUtilsActivity = findViewById(R.id.btn_start_to_utils_activity);
        btnStartToUtilsActivity.setOnClickListener(this);
        Button btnStart = findViewById(R.id.btn_start);
        btnStart.setOnClickListener(this);
        Button btnPause = findViewById(R.id.btn_pause);
        btnPause.setOnClickListener(this);
        Button btnStop = findViewById(R.id.btn_stop);
        btnStop.setOnClickListener(this);
        Button btnGyroscopeAndAcceleration = findViewById(R.id.btn_gyroscope_and_acceleration);
        btnGyroscopeAndAcceleration.setOnClickListener(this);
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
            case R.id.btn_start_to_utils_activity:
                startToUtilsActivity();
                break;
                case R.id.btn_gyroscope_and_acceleration:
                    Intent intent = new Intent(MainActivity.this,SensorActivity.class);
                    startActivity(intent);
                    this.finish();
                    break;
            default:
                logUtils.i("未定义的点击事件");
                break;
        }
    }

    private void startToUtilsActivity() {
        Intent intent = new Intent(MainActivity.this,UtilsActivity.class);
        startActivity(intent);
    }
}

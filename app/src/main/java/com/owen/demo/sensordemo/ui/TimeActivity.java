package com.owen.demo.sensordemo.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.owen.demo.sensordemo.R;
import com.owen.demo.sensordemo.utils.LogUtils;
import com.owen.demo.sensordemo.utils.TimeUtils;

public class TimeActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = "TimeActivity";

    private LogUtils logUtils;
    private EditText editTimeStamp;
    private TextView textFormatDate;
    private TextView textNowDateContent;
    private CheckBox checkUnixStamp;

    private UpdateDateHandler handler = new UpdateDateHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("时间戳格式化");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        logUtils = new LogUtils(TAG);
        initView();
        startUpdateNowDate();
    }

    private void startUpdateNowDate() {
        Thread updateData = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message message = new Message();
                    message.what = handler.WHAT_UPDATE_DATE;
                    handler.sendMessage(message);
                }
            }
        });
        updateData.start();
    }

    private void updateNowDate() {
        long nowStamp = System.currentTimeMillis();
        String strNowDate = TimeUtils.stampToDate(false,nowStamp);
        textNowDateContent.setText(strNowDate);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        textNowDateContent = findViewById(R.id.text_now_date_content);
        textFormatDate = findViewById(R.id.text_format_date);
        editTimeStamp = findViewById(R.id.edit_time_stamp);
        checkUnixStamp = findViewById(R.id.check_unix_stamp);
        Button btnFormat = findViewById(R.id.btn_format);
        btnFormat.setOnClickListener(this);
    }

    private void formatTime() {
        String strStamp = editTimeStamp.getText().toString();
        boolean unixFlag = checkUnixStamp.isChecked();
        String strDate = null;
        try {
            strDate = TimeUtils.stampToDate(unixFlag, strStamp);
        }catch (Exception e){
            logUtils.e(e.getMessage());
        }
        logUtils.d("时间戳："+strStamp+", 格式化后："+strDate);
        textFormatDate.setText(strDate);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_format:
                formatTime();
                break;
            default:
                logUtils.w("未处理的点击事件");
                break;
        }
    }

    public class UpdateDateHandler extends Handler{
        static final int WHAT_UPDATE_DATE = 1;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case WHAT_UPDATE_DATE:
                    updateNowDate();
                    break;
                default:
                    break;
            }
        }
    }
}

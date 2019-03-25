package com.owen.demo.sensordemo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.owen.demo.sensordemo.R;

public class UtilsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnTimeUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utils);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("常用工具");
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        initView();
    }

    private void initView() {
        btnTimeUtils = findViewById(R.id.btn_time_utils);
        btnTimeUtils.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_time_utils:
                startTimeActivity();
                break;
            default:
                Toast.makeText(this,"未定义的点击事件",Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void startTimeActivity() {
        Intent intent = new Intent(this,TimeActivity.class);
        startActivity(intent);
        finish();
    }
}

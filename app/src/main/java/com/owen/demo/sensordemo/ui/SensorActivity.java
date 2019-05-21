package com.owen.demo.sensordemo.ui;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.owen.demo.sensordemo.R;
import com.owen.demo.sensordemo.utils.LogUtils;

public class SensorActivity extends AppCompatActivity implements View.OnClickListener, SensorEventListener {

    private final static String TAG = "SensorActivity";
    private Button btnStartSensor;
    private TextView tvSensorX;
    private TextView tvSensorY;
    private TextView tvSensorZ;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Sensor.TYPE_ORIENTATION:
                    Bundle sensorBundle = msg.getData();
                    chargeSensorData(sensorBundle);
                    break;
                default:
                    break;
            }
        }
    };

    private void chargeSensorData(Bundle sensorBundle) {
        tvSensorX.setText("x:"+sensorBundle.getFloat("x"));
        tvSensorY.setText("y:"+sensorBundle.getFloat("y"));
        tvSensorZ.setText("z:"+sensorBundle.getFloat("z"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        initView();
        initSensor();
    }

    private void initSensor() {
        SensorManager manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //指定监听的传感器类型
        //all为全部，ACCELEROMETER为加速度，ORIENTATION为方向
        Sensor orientsensor = manager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        manager.registerListener(SensorActivity.this, orientsensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    private void initView() {
        btnStartSensor = findViewById(R.id.btn_sensor_start);
        btnStartSensor.setOnClickListener(this);
        tvSensorX = findViewById(R.id.tv_sensor_data_x);
        tvSensorY = findViewById(R.id.tv_sensor_data_y);
        tvSensorZ = findViewById(R.id.tv_sensor_data_z);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sensor_start:
                Toast.makeText(this,"传感器数据",Toast.LENGTH_SHORT).show();
            default:
                break;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ORIENTATION){
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            Message message = new Message();
            message.what = Sensor.TYPE_ORIENTATION;
            Bundle bundle = new Bundle();
            bundle.putFloat("x",x);
            bundle.putFloat("y",y);
            bundle.putFloat("z",z);
            message.setData(bundle);
            handler.sendMessage(message);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

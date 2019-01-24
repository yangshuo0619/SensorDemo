package com.owen.demo.sensordemo.utils;

import android.util.Log;

public class LogUtils {
    private boolean logFlag = true;
    private String tag;
    public LogUtils(String tag){
        this.tag = tag;
    }

    public void i(String content){
        if (logFlag){
            Log.i(tag,content);
        }
    }

    public void d(String content){
        if (logFlag){
            Log.d(tag,content);
        }
    }

    public void w(String content){
        if (logFlag){
            Log.w(tag,content);
        }
    }

    public void e(String content){
        if (logFlag){
            Log.e(tag,content);
        }
    }
}

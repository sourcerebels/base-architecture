package com.sourcerebels.basearchitecture.logger;

import android.util.Log;

public class AndroidLogger implements Logger {

    @Override
    public void debug(String tag, String message) {
        Log.d(tag, message);
    }

    @Override
    public void info(String tag, String message) {
        Log.i(tag, message);
    }

    @Override
    public void warn(String tag, String message) {
        Log.w(tag, message);
    }

    @Override
    public void warn(String tag, String message, Throwable tr) {
        Log.w(tag, message, tr);
    }

    @Override
    public void error(String tag, String message) {
        Log.e(tag, message);
    }

    @Override
    public void error(String tag, String message, Throwable tr) {
        Log.e(tag, message, tr);
    }
}

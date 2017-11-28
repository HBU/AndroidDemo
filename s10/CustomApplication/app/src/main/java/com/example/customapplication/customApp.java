package com.example.customapplication;

import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.Log;

/**
 * Created by David on 2017/11/5.
 * ref:http://blog.csdn.net/pi9nc/article/details/11200969
 */

public class customApp extends Application {
        private String textData = "default";
        private String TAG = "David_Application:";

        public void setTextData(String textData) {
            this.textData = textData;
        }
        public String getTextData() {
            return textData;
        }

        @Override
        public void onCreate() {
            // 程序创建的时候执行
            Log.d(TAG, "onCreate");
            super.onCreate();
        }
        @Override
        public void onTerminate() {
            // 程序终止的时候执行
            Log.d(TAG, "onTerminate");
            super.onTerminate();
        }
        @Override
        public void onLowMemory() {
            // 低内存的时候执行
            Log.d(TAG, "onLowMemory");
            super.onLowMemory();
        }
        @Override
        public void onTrimMemory(int level) {
            // 程序在内存清理的时候执行
            Log.d(TAG, "onTrimMemory");
            super.onTrimMemory(level);
        }
        @Override
        public void onConfigurationChanged(Configuration newConfig) {
            Log.d(TAG, "onConfigurationChanged");
            super.onConfigurationChanged(newConfig);
        }

        @Override
        public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
            Log.d(TAG, "registerActivityLifecycleCallbacks");
            super.registerActivityLifecycleCallbacks(callback);
        }

        @Override
        public void registerComponentCallbacks(ComponentCallbacks callback) {
            Log.d(TAG, "registerComponentCallbacks");
            super.registerComponentCallbacks(callback);
        }

        @Override
        public void unregisterActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
            Log.d(TAG, "unregisterActivityLifecycleCallbacks");
            super.unregisterActivityLifecycleCallbacks(callback);
        }

        @Override
        public void unregisterComponentCallbacks(ComponentCallbacks callback) {
            Log.d(TAG, "unregisterComponentCallbacks");
            super.unregisterComponentCallbacks(callback);
        }


}

package wanandroid.com.javamvpdemo.app;

import android.app.Application;

import wanandroid.com.javamvpdemo.utils.Preferences;



public class MyApplication extends Application {


    private static MyApplication instance;
    public static boolean isFirstRun = true;//是否是第一次启动

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Preferences.init(getApplicationContext());
    }

    public synchronized static MyApplication getInstance(){
        return instance;
    }
}

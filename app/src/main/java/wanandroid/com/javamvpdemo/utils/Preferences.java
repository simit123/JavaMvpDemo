package wanandroid.com.javamvpdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * sharePreferences 工具类
 */

public class Preferences {

    private static final String PREFS_NAME = "PrefsFile";

    private static Preferences instance = null;

    private Context mContext;

    private SharedPreferences sp;

    public static void init(Context mContext){
        if (instance == null) {
            instance = new Preferences(mContext);
        }
    }

    public static Preferences getInstance(){
        return instance;
    }

    private Preferences(Context mContext){
        this.mContext = mContext;
        sp = mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

}

package wanandroid.com.javamvpdemo.scheduler;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2018/12/20 ADD
 */

public class SchedulerUtils {

    public  static <T> IoMainScheduler<T> ioToMain(){
        return new IoMainScheduler<T>(Schedulers.io(), AndroidSchedulers.mainThread());
    }
}

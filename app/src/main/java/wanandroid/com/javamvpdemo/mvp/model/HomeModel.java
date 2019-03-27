package wanandroid.com.javamvpdemo.mvp.model;


import io.reactivex.Observable;
import wanandroid.com.javamvpdemo.mvp.model.bean.HomeDataBean;
import wanandroid.com.javamvpdemo.net.RetrofitManager;
import wanandroid.com.javamvpdemo.scheduler.SchedulerUtils;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2018/12/20 ADD
 */

public class HomeModel {

    public Observable<HomeDataBean> getHomeData() {
        //SchedulerUtils 一个 Utils为什么要泛型参数？？？？
        return RetrofitManager.service.getHomeData("https://www.baidu.com/").compose(SchedulerUtils.<HomeDataBean>ioToMain());
    }
}

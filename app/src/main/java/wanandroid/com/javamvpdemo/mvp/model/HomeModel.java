package wanandroid.com.javamvpdemo.mvp.model;


import io.reactivex.Observable;
import wanandroid.com.javamvpdemo.mvp.model.bean.HomeDataBean;
import wanandroid.com.javamvpdemo.net.RetrofitManager;
import wanandroid.com.javamvpdemo.scheduler.SchedulerUtils;



public class HomeModel {

    public Observable<HomeDataBean> getHomeData() {
        //SchedulerUtils 一个 Utils为什么要泛型参数？？？？
        return RetrofitManager.service.getHomeData("https://www.baidu.com/").compose(SchedulerUtils.ioToMain());
    }
}

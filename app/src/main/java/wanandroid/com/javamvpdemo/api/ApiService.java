package wanandroid.com.javamvpdemo.api;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;
import wanandroid.com.javamvpdemo.mvp.model.bean.HomeDataBean;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2018/12/20 ADD
 */

public interface ApiService {

    @GET
    Observable<HomeDataBean> getHomeData(@Url String url);
}

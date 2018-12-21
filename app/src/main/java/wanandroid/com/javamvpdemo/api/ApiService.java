package wanandroid.com.javamvpdemo.api;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;
import wanandroid.com.javamvpdemo.mvp.model.bean.HomeDataBean;


public interface ApiService {

    @GET
    Observable<HomeDataBean> getHomeData(@Url String url);
}

package wanandroid.com.javamvpdemo.mvp.contract;

import wanandroid.com.javamvpdemo.base.IBasePresenter;
import wanandroid.com.javamvpdemo.base.IBaseView;
import wanandroid.com.javamvpdemo.mvp.model.bean.HomeDataBean;

/**
 * 契约类 用来定义View 和 presenter 的子接口
 */
public class HomeContract {

   public interface IView extends IBaseView{

        /**
         * 拿到请求的数据 并 设置给View 加载
         */
        void setData(HomeDataBean homeDataBean);
    }


   public interface IPresenter extends IBasePresenter<IView>{

        /**
         * 请求数据
         */
        void getHomeData();
    }
}

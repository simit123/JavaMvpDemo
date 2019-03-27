package wanandroid.com.javamvpdemo.mvp.presenter;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import wanandroid.com.javamvpdemo.base.BasePresenter;
import wanandroid.com.javamvpdemo.mvp.contract.HomeContract;
import wanandroid.com.javamvpdemo.mvp.model.HomeModel;
import wanandroid.com.javamvpdemo.mvp.model.bean.BaseResponse;
import wanandroid.com.javamvpdemo.mvp.model.bean.HomeDataBean;
import wanandroid.com.javamvpdemo.net.BaseObserver;


public class HomePresenter extends BasePresenter<HomeContract.IView> implements HomeContract.IPresenter{


    private HomeModel homeModel = new HomeModel();

    @Override
    public void getHomeData() {
        checkViewAttach();//请求数据之前先检查View 是否Attached
//        mRootViw.showLoading();//转圈圈


        Disposable disposable = homeModel.getHomeData()
                .subscribeWith(new BaseObserver<HomeDataBean>(mRootViw){

                    @Override
                    public void onNext(HomeDataBean homeDataBean) {
                        mRootViw.setData(homeDataBean);
                    }
                });


        //一定要add 防止Rxjava内存泄漏
        addSubscription(disposable);

    }
}

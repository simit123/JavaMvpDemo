package wanandroid.com.javamvpdemo.mvp.presenter;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import wanandroid.com.javamvpdemo.base.BasePresenter;
import wanandroid.com.javamvpdemo.mvp.contract.HomeContract;
import wanandroid.com.javamvpdemo.mvp.model.HomeModel;
import wanandroid.com.javamvpdemo.mvp.model.bean.BaseResponse;
import wanandroid.com.javamvpdemo.mvp.model.bean.HomeDataBean;


public class HomePresenter extends BasePresenter<HomeContract.IView> implements HomeContract.IPresenter{


    private HomeModel homeModel = new HomeModel();

    @Override
    public void getHomeData() {
        checkViewAttach();//请求数据之前先检查View 是否Attached
        mRootViw.showLoading();//转圈圈


        Disposable disposable = homeModel.getHomeData()
                .subscribe(new Consumer<HomeDataBean>() {
                    @Override
                    public void accept(HomeDataBean homeDataBean) throws Exception {
                        mRootViw.dismissLoading();
                        mRootViw.setData(homeDataBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mRootViw.dismissLoading();
                    }
                });


        //一定要add 防止Rxjava内存泄漏
        addSubscription(disposable);

    }
}

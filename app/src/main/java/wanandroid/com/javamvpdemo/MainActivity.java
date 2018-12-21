package wanandroid.com.javamvpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import wanandroid.com.javamvpdemo.mvp.contract.HomeContract;
import wanandroid.com.javamvpdemo.mvp.model.bean.HomeDataBean;
import wanandroid.com.javamvpdemo.mvp.presenter.HomePresenter;

public class MainActivity extends AppCompatActivity implements HomeContract.IView{

    private TextView tv;

    private HomePresenter homePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = ((TextView) findViewById(R.id.tv));
        homePresenter = new HomePresenter();
        homePresenter.attachView(this);
        homePresenter.getHomeData();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        homePresenter.detachView();
    }

    @Override
    public void setData(HomeDataBean homeDataBean) {

    }
}

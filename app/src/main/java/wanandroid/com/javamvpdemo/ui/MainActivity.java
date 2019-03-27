package wanandroid.com.javamvpdemo.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.TextView;

import wanandroid.com.javamvpdemo.R;
import wanandroid.com.javamvpdemo.base.BaseRootActivity;
import wanandroid.com.javamvpdemo.mvp.contract.HomeContract;
import wanandroid.com.javamvpdemo.mvp.model.bean.HomeDataBean;
import wanandroid.com.javamvpdemo.mvp.presenter.HomePresenter;

public class MainActivity extends BaseRootActivity<HomePresenter> implements HomeContract.IView{

    private TextView tv;
    private static final String TAG = "MainActivity";

    @Override
    public void showLoading() {

    }

    @Override
    public HomePresenter getP() {
        return new HomePresenter();
    }


    @Override
    protected void onViewCreated() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolbar() {


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initData() {
        super.initData();
        tv = ((TextView) findViewById(R.id.tv));
        mPresenter.getHomeData();
    }

    @Override
    public void setData(HomeDataBean homeDataBean) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"onRestart");

    }
}

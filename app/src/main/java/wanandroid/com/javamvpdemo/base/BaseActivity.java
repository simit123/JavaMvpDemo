package wanandroid.com.javamvpdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import wanandroid.com.javamvpdemo.R;
import wanandroid.com.javamvpdemo.app.Constants;
import wanandroid.com.javamvpdemo.ui.MainActivity;
import wanandroid.com.javamvpdemo.utils.ActivityCollector;
import wanandroid.com.javamvpdemo.utils.CommonUtils;

/**
 * 修改番号 INLS-NEW-201811-002 修改简介 wuy 2019/3/12 ADD
 */

public abstract class BaseActivity<T extends BasePresenter> extends AbstractBaseActivity implements IBaseView{
    public void showToast(String message) {
        CommonUtils.showMessage(this, message);
    }

    private long clickTime;

    private static final String TAG = "BaseActivity";

    public T mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("life",TAG);
        mPresenter = getP();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void showLoading() {

    }


    @Override
    public void showErrorMsg(String errorMsg) {
        CommonUtils.showMessage(this, errorMsg);
    }

    @Override
    public void showError() {

    }

    @Override
    public void reload() {

    }

    @Override
    public void onBackPressed() {

        if (this instanceof MainActivity) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - clickTime > Constants.DOUBLE_INTERVAL_TIME) {
                CommonUtils.showMessage(this, getString(R.string.double_click_exit_tint));
                clickTime = System.currentTimeMillis();
            }else {
                ActivityCollector.getInstance().exitApp();
            }
        }else {
            finish();
        }
    }


    @Override
    public void showNormal() {

    }

    @Override
    protected void initData() {

    }

    public abstract T getP();
}

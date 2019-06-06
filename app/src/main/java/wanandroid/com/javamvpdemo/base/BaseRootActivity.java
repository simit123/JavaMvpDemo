package wanandroid.com.javamvpdemo.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;

import wanandroid.com.javamvpdemo.R;

/**
 * 如果Activity有数据请求的话 继承此Activity
 */
public abstract class BaseRootActivity<T extends BasePresenter> extends BaseActivity {

    private static final int NORMAL_STATE = 0;
    private static final int LOADING_STATE = 1;
    public static final int ERROR_STATE = 2;

    private LottieAnimationView mLoadingAnimation;
    private View mErrorView;
    private View mLoadingView;
    private ViewGroup mNormalView;
    private int currentState = NORMAL_STATE;
    public T mPresenter;

    @Override
    protected void initData() {
         mPresenter = getP();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }




        mNormalView = (ViewGroup) findViewById(R.id.normal_view);
        if (mNormalView == null) {
            throw new IllegalStateException(
                    "The subclass of RootActivity must contain a View named 'mNormalView'.");
        }
        if (!(mNormalView.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException(
                    "mNormalView's ParentView should be a ViewGroup.");
        }
        ViewGroup mParent = (ViewGroup) mNormalView.getParent();
        View.inflate(this, R.layout.loading_view, mParent);
        View.inflate(this, R.layout.error_view, mParent);
        mLoadingView = mParent.findViewById(R.id.loading_group);
        mErrorView = mParent.findViewById(R.id.error_group);
        TextView reloadTv = mErrorView.findViewById(R.id.error_reload_tv);
        reloadTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reload();
            }
        });
        mLoadingAnimation = mLoadingView.findViewById(R.id.loading_animation);
        mErrorView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.GONE);
        mNormalView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        if (mLoadingAnimation != null) {
            mLoadingAnimation.cancelAnimation();
        }
        super.onDestroy();

        if (mPresenter != null) {
            mPresenter.detachView();
        }

    }

    @Override
    public void showLoading() {
        if (currentState == LOADING_STATE) {
            return;
        }
        hideCurrentView();
        currentState = LOADING_STATE;
        mLoadingView.setVisibility(View.VISIBLE);
        mLoadingAnimation.setAnimation("loading_bus.json");
        mLoadingAnimation.loop(true);
        mLoadingAnimation.playAnimation();
    }

    @Override
    public void showError() {
        if (currentState == ERROR_STATE) {
            return;
        }
        hideCurrentView();
        currentState = ERROR_STATE;
        mErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNormal() {
        if (currentState == NORMAL_STATE) {
            return;
        }
        hideCurrentView();
        currentState = NORMAL_STATE;
        mNormalView.setVisibility(View.VISIBLE);
    }

    private void hideCurrentView() {
        switch (currentState) {
            case NORMAL_STATE:
                mNormalView.setVisibility(View.GONE);
                break;
            case LOADING_STATE:
                mLoadingAnimation.cancelAnimation();
                mLoadingView.setVisibility(View.GONE);
                break;
            case ERROR_STATE:
                mErrorView.setVisibility(View.GONE);
            default:
                break;
        }
    }

    public abstract T getP();
}
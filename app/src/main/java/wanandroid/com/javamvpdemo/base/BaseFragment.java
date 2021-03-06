package wanandroid.com.javamvpdemo.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import wanandroid.com.javamvpdemo.R;



public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements IBaseView {

    private Unbinder unBinder;
    private ViewGroup mNormalView;
    private View mLoading;
    private View mError;
    private LottieAnimationView mLoadingAnimation;

    private static final int NORMAL_STATE = 0;//加载成功展示列表View
    private static final int LOADING_STATE = 1;//加载完成，dismiss
    public static final int ERROR_STATE = 2;//加载失败
    private int currentState = NORMAL_STATE;//当前所处的状态

    public T mPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unBinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = getP();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }

        if (getView() == null) {
            return;
        }

        mNormalView = getView().findViewById(R.id.normal_view);
        if (mNormalView == null) {
            throw new IllegalStateException(
                    "The subclass of RootActivity must contain a View named 'mNormalView'.");
        }
        if (!(mNormalView.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException(
                    "mNormalView's ParentView should be a ViewGroup.");
        }

        ViewGroup parent = (ViewGroup) mNormalView.getParent();
        View.inflate(getActivity(), R.layout.loading_view, parent);
        View.inflate(getActivity(), R.layout.error_view, parent);
        mLoading = parent.findViewById(R.id.loading_group);
        mError = parent.findViewById(R.id.error_group);
        TextView reloadText = (TextView) mError.findViewById(R.id.error_reload_tv);
        reloadText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reload();
            }
        });
        mLoadingAnimation = mLoading.findViewById(R.id.loading_animation);
        mError.setVisibility(View.GONE);
        mLoading.setVisibility(View.GONE);
        mNormalView.setVisibility(View.VISIBLE);
        initView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unBinder != null && unBinder != Unbinder.EMPTY) {
            unBinder.unbind();
            unBinder = null;
        }
        if (mLoadingAnimation != null) {
            mLoadingAnimation.cancelAnimation();
        }

        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract T getP();

    @Override
    public void showErrorMsg(String errorMsg) {

    }

    @Override
    public void reload() {

    }

    @Override
    public void showLoading() {
        if (currentState == LOADING_STATE || mNormalView == null) {
            return;
        }
        hideCurrentView();
        currentState = LOADING_STATE;
        mLoading.setVisibility(View.VISIBLE);
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
        mError.setVisibility(View.VISIBLE);
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


    //currentState 赋值之前调用，也就是此方法中的currentState是上一个的状态
    private void hideCurrentView() {
        switch (currentState) {
            case NORMAL_STATE:
                if (mNormalView == null) {
                    return;
                }
                mNormalView.setVisibility(View.INVISIBLE);
                break;
            case LOADING_STATE:
                mLoadingAnimation.cancelAnimation();
                mLoading.setVisibility(View.GONE);
                break;
            case ERROR_STATE:
                mError.setVisibility(View.GONE);
            default:
                break;
        }
    }

}

package wanandroid.com.javamvpdemo.ui;
import android.support.v4.app.Fragment;


import wanandroid.com.javamvpdemo.R;
import wanandroid.com.javamvpdemo.base.BaseFragment;
import wanandroid.com.javamvpdemo.base.BasePresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends BaseFragment {


    public MyFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView() {

    }

    @Override
    public BasePresenter getP() {
        return null;
    }

}

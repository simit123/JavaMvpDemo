package wanandroid.com.javamvpdemo.base;

/**
 *  view 接口的基类 统一的view ui 处理
 */
public interface IBaseView {
    /**
     * Show error message
     *
     * @param errorMsg error message
     */
    void showErrorMsg(String errorMsg);

    /**
     * showNormal
     */
    void showNormal();

    /**
     * Show error
     */
    void showError();

    /**
     * Show loading
     */
    void showLoading();

    /**
     * Reload
     */
    void reload();
}

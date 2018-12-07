package com.smallsnailtech.smallsnail.base;

/**
 * @author luzhaosheng
 * @date 2018年11月06日
 */
public interface BaseView {

    void showLoading();

    void hideLoading();

    void showNetError();

    default void showShortToast(String text) {
//        ToastUtils.showShort(SupportApplication.getContext(), text);
    }

    default void showShortToast(int resId) {
//        ToastUtils.showShort(SupportApplication.getContext(), SupportApplication.getContext().getString(resId));
    }

    default void showShortToast(CharSequence charSequence) {
//        ToastUtils.showShort(SupportApplication.getContext(), charSequence);
    }

}

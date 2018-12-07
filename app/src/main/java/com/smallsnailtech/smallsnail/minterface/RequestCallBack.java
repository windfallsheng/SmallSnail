package com.smallsnailtech.smallsnail.minterface;

public interface RequestCallBack<T> {

    void onStart();

    void onSuccess(T t);

    void onFailed(Throwable t);

    void onCancel();

}

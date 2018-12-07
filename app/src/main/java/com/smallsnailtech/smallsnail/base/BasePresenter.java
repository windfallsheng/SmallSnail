package com.smallsnailtech.smallsnail.base;

import com.smallsnailtech.smallsnail.entity.Param;

import java.util.List;

/**
 * @author luzhaosheng
 * @date 2018年11月06日
 */
public interface BasePresenter {

    void attachView();

    default void loadData(List<Param> params) {

    }

    default void refreshData(List<Param> params) {

    }

    /**
     * 处理一些断开引用的操作，通知Model层关闭子线程等的后台任务
     */
    void detachView();

}

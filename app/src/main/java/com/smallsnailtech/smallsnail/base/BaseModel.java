package com.smallsnailtech.smallsnail.base;

import com.smallsnailtech.smallsnail.entity.Param;

import java.util.List;

/**
 * @author luzhaosheng
 * @date 2018年11月06日
 */
public interface BaseModel {

    default void loadData(List<Param> params) {

    }

    /**
     * 页面关闭时，取消网络请求、其它子线程等任务
     */
    void cancelTasks();

}

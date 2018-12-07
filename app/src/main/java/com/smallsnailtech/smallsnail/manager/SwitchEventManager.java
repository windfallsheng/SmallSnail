package com.smallsnailtech.smallsnail.manager;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.smallsnailtech.smallsnail.base.SupportApplication;


/**
 * @Author: luzhaosheng
 * @Date: 2018/4/16
 * <p>
 * Description: 监听应用前后台的切换，同时作为被观察者，将前后台切换的相关数据通知给各观察者，以便各观察者判断相关数据，进行业务处理
 * <p>
 * 在 {@link SupportApplication#onCreate()}方法中注册（调用registerActivityLifecycleCallbacks()方法）这个事件，
 * 之后就可以实现监听应用前后台的切换。
 * <p>
 * Version:
 */
public class SwitchEventManager implements Application.ActivityLifecycleCallbacks {

    private static SwitchEventManager instance = null;
    private int mActivityCount;


    public static SwitchEventManager getInstance() {
        if (instance == null) {
            instance = new SwitchEventManager();
        }
        return instance;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        String activityName = activity.getClass().getName();
    }

    @Override
    public void onActivityStarted(Activity activity) {
        mActivityCount++;
        String activityName = activity.getClass().getName();
        if (mActivityCount > 0) {// 此时表明应用在前台
            //
            if (!SuspendedWindowOperate.getInstance().isSuspendedWindowShowing()){
                SuspendedWindowOperate.getInstance().showWindow(SupportApplication.getContext());
            }
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
        String activityName = activity.getClass().getName();
    }

    @Override
    public void onActivityPaused(Activity activity) {
        String activityName = activity.getClass().getName();
    }

    @Override
    public void onActivityStopped(Activity activity) {
        mActivityCount--;
        String activityName = activity.getClass().getName();
        if (mActivityCount == 0) {// 此时表明应用在后台
            SuspendedWindowOperate.getInstance().dismissWindow();
            SuspendedWindowPageHistory.getInstance().dismissWindow();
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        String activityName = activity.getClass().getName();
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        String activityName = activity.getClass().getName();
    }
}

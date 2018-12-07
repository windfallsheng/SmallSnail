package com.smallsnailtech.smallsnail.base;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.smallsnailtech.smallsnail.command.Constants;
import com.smallsnailtech.smallsnail.entity.PageHistoryEntity;
import com.smallsnailtech.smallsnail.entity.UserInfoEntity;
import com.smallsnailtech.smallsnail.manager.SwitchEventManager;
import com.smallsnailtech.smallsnail.util.SharedPrefUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author luzhaosheng
 * @date 2018年11月06日
 */
public class SupportApplication extends Application {

    // 退出整个应用可以用到
    private static Stack<Activity> activityStack;
    private List<PageHistoryEntity> mPageHistoryList;
    private List<PageHistoryEntity> mClosedPageList; // 关闭页面的集合
    private Map<String, Class<? extends Activity>> mActivityMap; // 所有打开过的页面的class对象集合集合
    private static SupportApplication singleton;
    //
    private static Context mContext;
    private static Thread mMainThread;
    private static long mMainTreadId;
    private static Looper mMainLooper;
    private static Handler mHandler;
    private UserInfoEntity mGlobalUserInfo = null;

    public static Handler getHandler() {
        return mHandler;
    }

    public static Context getContext() {
        return mContext;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static long getMainTreadId() {
        return mMainTreadId;
    }

    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    public static Stack<Activity> getActivityStack() {
        return activityStack;
    }

    public List<PageHistoryEntity> getPageHistoryList() {
        return mPageHistoryList;
    }

    public List<PageHistoryEntity> getClosedPageList() {
        return mClosedPageList;
    }

    // 退出整个应用可以用到
    // Returns the application instance
    public static SupportApplication getInstance() {
        return singleton;
    }

    /**
     * add Activity 添加Activity到栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
//        Class<? extends Activity> aClass = activity.getClass();
//        String activityName = aClass.getName();
//        Log.d("SupportApplication", "addActivity==>" + activityName);
        activityStack.add(activity);
    }

    public void addPageHistory(PageHistoryEntity pageHistoryEntity) {
        if (mPageHistoryList == null) {
            mPageHistoryList = new LinkedList<>();
        }
        mPageHistoryList.add(pageHistoryEntity);
    }

    public void removePageHistory(PageHistoryEntity pageHistoryEntity) {
        if (mPageHistoryList != null) {
            mPageHistoryList.remove(pageHistoryEntity);
        }
    }

    public void clearPageHistory(PageHistoryEntity pageHistoryEntity) {
        if (mPageHistoryList != null) {
            mPageHistoryList.clear();
        }
    }

    public PageHistoryEntity getTargetClosedPage(int index) {
        if (mClosedPageList != null && mPageHistoryList.size() > 0) {
            return mClosedPageList.get(index);
        }
        return null;
    }

    public void addClosedPage(int index, PageHistoryEntity pageHistoryEntity) {
        if (mClosedPageList == null) {
            mClosedPageList = new LinkedList<>();
        }
        mClosedPageList.add(index, pageHistoryEntity);
    }

    public void removeClosedPage(int index) {
        if (mPageHistoryList != null && mPageHistoryList.size() > 0) {
            mPageHistoryList.remove(index);
        }
    }

    public void addActivityClass(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        String activityName = aClass.getName();
        if (mActivityMap == null) {
            mActivityMap = new LinkedHashMap<>();
        }
        mActivityMap.put(activityName, aClass);
    }

    public Class<? extends Activity> getTargeActivityClass(String activityName) {
        if (mActivityMap != null && mActivityMap.size() > 0 && mActivityMap.containsKey(activityName)) {
            return mActivityMap.get(activityName);
        }
        return null;
    }

    /**
     * get current Activity 获取当前Activity（栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            Log.d("SupportApplication", "finishActivity==>" + activity.getClass().toString());
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                Log.d("SupportApplication", "finishActivity==>" + activity.getClass().toString());
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                Log.d("SupportApplication", "finishAllActivity==>" + activityStack.get(i).getClass().toString());
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void exitApplication() {
        try {
            finishAllActivity();
        } catch (Exception e) {
        }
    }

    /**
     * 将当前登录用户信息保存在内存和本地持久化文件中
     * </P>
     * 将每个登录用户信息保存在一个单独的SharedPreferences文件中
     *
     * @param userInfoEntity 此参数为null，时就可以清除内存和本地持久化文件中缓存的用户登录信息
     */
    public void initGlobalUserInfo(UserInfoEntity userInfoEntity) {
        this.mGlobalUserInfo = userInfoEntity;
        // 将每个登录用户信息保存在一个单独的SharedPreferences文件中
        // 用户登录成功后的token是存储在名字为 {@link C#COMMON_CONFIG_SP}的SharedPreferences中
        if (userInfoEntity != null) {
            String userAccount = userInfoEntity.getUserAccount();
            SharedPrefUtils sharedUserInfo = SharedPrefUtils.init(this, Constants.USER_INFO_SP + Constants.DECOLLATOR + userAccount);
            sharedUserInfo.putString(Constants.USER_ACCOUNT, userAccount);
            String username = userInfoEntity.getUsername();
            if (username != null && !"".equals(username)) {
                sharedUserInfo.putString(Constants.USERNAME, username);
            }
            String userId = userInfoEntity.getUserId();
            if (userId != null && !"".equals(userId)) {
                sharedUserInfo.putString(Constants.USER_ID, userId);
            }
            String headIconUrl = userInfoEntity.getHeadIconUrl();
            if (headIconUrl != null && !"".equals(headIconUrl)) {
                sharedUserInfo.putString(Constants.HEAD_ICON_URL, headIconUrl);
            }
        } else {
            clearGlobalUserInfo();
        }
    }

    /**
     * 刷新当前登录用户信息的某些可变字段，包括内存和本地持久化文件
     * </P>
     *
     * @param userInfoEntity 此参数为null时不做任何操作
     */
    public boolean refreshGlobalUserInfos(UserInfoEntity userInfoEntity) {
        if (userInfoEntity != null) {
            if (mGlobalUserInfo == null) {
                mGlobalUserInfo = getGlobalUserInfo();
            }
            if (mGlobalUserInfo != null) {
                String userAccount = mGlobalUserInfo.getUserAccount();
                String token = userInfoEntity.getToken();
                String username = userInfoEntity.getUsername();
                String headIconUrl = userInfoEntity.getHeadIconUrl();
                SharedPrefUtils sharedUserInfo = SharedPrefUtils.init(this, Constants.USER_INFO_SP + Constants.DECOLLATOR + userAccount);
                if (token != null && !"".equals(token)) {
                    SharedPrefUtils sharedCommonConfig = SharedPrefUtils.init(this, Constants.COMMON_CONFIG_SP);
                    sharedCommonConfig.putString(Constants.TOKEN, token);
                    this.mGlobalUserInfo.setToken(token);
                }
                if (username != null && !"".equals(username)) {
                    sharedUserInfo.putString(Constants.USERNAME, username);
                    this.mGlobalUserInfo.setUsername(username);
                }
                if (headIconUrl != null && !"".equals(headIconUrl)) {
                    sharedUserInfo.putString(Constants.HEAD_ICON_URL, headIconUrl);
                    this.mGlobalUserInfo.setHeadIconUrl(headIconUrl);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 获取当前登录用户信息，先从内存中获取，如果没有再从持久化文件中读取到内存
     * </P>
     */
    public UserInfoEntity getGlobalUserInfo() {
        if (mGlobalUserInfo == null) {
            // 将通用信息保存在一个单独的SharedPreferences文件中
            SharedPrefUtils sharedCommonConfig = SharedPrefUtils.init(this, Constants.COMMON_CONFIG_SP);
            String userAccount = sharedCommonConfig.getString(Constants.LOGIN_SUCCESSED_ACCOUNT, "");
            if (!"".equals(userAccount)) {
                String token = sharedCommonConfig.getString(Constants.TOKEN);
                // 将每个登录用户信息保存在一个单独的SharedPreferences文件中
                SharedPrefUtils sharedUserInfo = SharedPrefUtils.init(this,
                        Constants.USER_INFO_SP + Constants.DECOLLATOR + userAccount);
                String userId = sharedUserInfo.getString(Constants.USER_ID, "");
                String account = sharedUserInfo.getString(Constants.USER_ACCOUNT, "");
                if (!"".equals(userId) && !"".equals(account)) {// 必须要判断文件中是否已经存储的有值
                    mGlobalUserInfo = new UserInfoEntity();
                    mGlobalUserInfo.setToken(token);
                    mGlobalUserInfo.setUserId(userId);
                    mGlobalUserInfo.setUserAccount(account);
                    mGlobalUserInfo.setUsername(sharedUserInfo.getString(Constants.USERNAME));
                    mGlobalUserInfo.setHeadIconUrl(sharedUserInfo.getString(Constants.HEAD_ICON_URL));
                    return mGlobalUserInfo;
                }
            }
        }
        return mGlobalUserInfo;
    }

    /**
     * 使用Instrumentation接口：对于非自行编译的安卓系统，无法获取系统签名，只能在前台模拟按键，不能后台模拟
     * 注意:调用Instrumentation的sendKeyDownUpSync方法必须另起一个线程，否则无效
     *
     * @param keyCode 按键事件(KeyEvent)的按键值
     */
    public void sendKeyCode(final int keyCode) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 创建一个Instrumentation对象
                    Instrumentation inst = new Instrumentation();
                    // 调用inst对象的按键模拟方法
                    inst.sendKeyDownUpSync(keyCode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // private RefWatcher refWatcher;

    @Override
    public void onCreate() { // 程序的入口
        // 初始化化一些.常用属性.然后放到盒子里面来
        // 上下文
        mContext = getApplicationContext();
        // 主线程
        mMainThread = Thread.currentThread();
        // 主线程Id
        mMainTreadId = android.os.Process.myTid();
        // tid thread
        // uid user
        // pid process
        // 主线程Looper对象
        mMainLooper = getMainLooper();
        // 定义一个handler
        mHandler = new Handler();
        // 退出整个应用可以用到
        singleton = this;
        super.onCreate();
        // 初始化Crash
//		CrashHandler crashHandler = CrashHandler.getInstance();
//		crashHandler.init(this);
        // 应用启动后先移除之前缓存的一些数据，防止非正常退出应用时未清除的本地数据
        clearGlobalUserInfo();
        //
        registerActivityLifecycleCallbacks(SwitchEventManager.getInstance());
    }

    /**
     * 清除某个账号登录成功后缓存在本地持久化文件中的用户信息数据
     */
    private void clearGlobalUserInfo() {
        SharedPrefUtils sharedCommonConfig = SharedPrefUtils.init(this, Constants.COMMON_CONFIG_SP);
        if (sharedCommonConfig != null && sharedCommonConfig.contains(Constants.LOGIN_SUCCESSED_ACCOUNT)) {
            String userAccount = sharedCommonConfig.getString(Constants.LOGIN_SUCCESSED_ACCOUNT);
            // 删除登录用户信息数据
            SharedPrefUtils sharedUserInfo = SharedPrefUtils.init(this, Constants.USER_INFO_SP + Constants.DECOLLATOR + userAccount);
            if (sharedUserInfo != null) {
                sharedUserInfo.clear();
            }
            // 或者直接删除xml文件
            // File file = new File("/data/data/" + getPackageName().toString() +
            // "/shared_prefs",
            // C.USER_INFO_SP + C.DECOLLATOR + userAccount + ".xml");
            // if (file.exists()) {
            // file.delete();
            // Toast.makeText(this, "删除成功", Toast.LENGTH_LONG).show();
            // }
            // (每次启动应用时清除这个标识，防止用户之前非正常退出应用时还保留这个标识)
            sharedCommonConfig.remove(Constants.LOGIN_SUCCESSED_ACCOUNT);
        }
    }
}

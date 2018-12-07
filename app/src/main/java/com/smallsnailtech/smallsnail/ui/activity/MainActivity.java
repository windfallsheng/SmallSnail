package com.smallsnailtech.smallsnail.ui.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.smallsnailtech.smallsnail.R;
import com.smallsnailtech.smallsnail.base.BaseActivity;
import com.smallsnailtech.smallsnail.base.BasePresenter;
import com.smallsnailtech.smallsnail.base.SupportApplication;
import com.smallsnailtech.smallsnail.entity.PageHistoryEntity;
import com.smallsnailtech.smallsnail.entity.Param;
import com.smallsnailtech.smallsnail.manager.SuspendedWindowOperate;
import com.smallsnailtech.smallsnail.presenter.contract.MainActivityContract;
import com.smallsnailtech.smallsnail.presenter.model.MainActivityModel;
import com.smallsnailtech.smallsnail.presenter.presenter.MainActivityPresenter;
import com.smallsnailtech.smallsnail.ui.fragment.BrowseFragment;
import com.smallsnailtech.smallsnail.ui.fragment.HomeFragment;
import com.smallsnailtech.smallsnail.ui.fragment.MessageFragment;
import com.smallsnailtech.smallsnail.ui.fragment.PersonalCenterFragment;
import com.smallsnailtech.smallsnail.ui.fragment.dialogfragment.SplashPageDialogFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Test Description
 * <p> 应用的启动界面和主界面
 *
 * @author luzhaosheng
 * @date 2018年11月06日
 * @since
 */
public class MainActivity extends BaseActivity<MainActivityContract.IMainActivityPresenter> implements MainActivityContract.IMainActivityView, View.OnClickListener {

    private FragmentManager mFragmentManager;
    private Fragment mFragmentHome, mFragmentBrowse, mFragmentMessage, mFragmentPersonalCenter;

    private FrameLayout mFLayoutFragmentContainer;
    private RelativeLayout mRLayoutHome, mRLayoutBrawse, mRLayoutMessage, mRLayoutPersonalCenter;
    private SplashPageDialogFragment mDialogFragmentSplashPage;
    private long lastExitTime;
    private BasePresenter mMainActivityPresenter;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }

    @Override
    protected int getContentViewResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariables() {
        mMainActivityPresenter = new MainActivityPresenter(this, new MainActivityModel());
        SuspendedWindowOperate.getInstance().showWindow(this);
    }

    /**
     * {@link android.view.Window} or {@link android.support.v4.view.WindowCompat}.
     *
     * @return Returns true if the requested feature is supported and now enabled.
     * @see Activity#requestWindowFeature
     */
    @Override
    protected void initViewsEvent() {
        mFLayoutFragmentContainer = findViewById(R.id.frameLayout_fragment_container_main_activity);
        mRLayoutHome = findViewById(R.id.rlayout_nav_home);
        mRLayoutBrawse = findViewById(R.id.rlayout_nav_browse);
        mRLayoutMessage = findViewById(R.id.rlayout_nav_message);
        mRLayoutPersonalCenter = findViewById(R.id.rlayout_nav_personal_center);
        // 创建主界面的所有的Fragment页面，并添加显示
        mFragmentManager = getFragmentManager();
        mFragmentHome = HomeFragment.newInstance();
        mFragmentBrowse = BrowseFragment.newInstance();
        mFragmentMessage = MessageFragment.newInstance();
        mFragmentPersonalCenter = PersonalCenterFragment.newInstance();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayout_fragment_container_main_activity, mFragmentHome, mFragmentHome.getClass().getName());
        fragmentTransaction.add(R.id.frameLayout_fragment_container_main_activity, mFragmentBrowse, mFragmentBrowse.getClass().getName());
        fragmentTransaction.add(R.id.frameLayout_fragment_container_main_activity, mFragmentMessage, mFragmentMessage.getClass().getName());
        fragmentTransaction.add(R.id.frameLayout_fragment_container_main_activity, mFragmentPersonalCenter, mFragmentPersonalCenter.getClass().getName());
        fragmentTransaction.commit();
        // 设置控件的监听事件
        mRLayoutHome.setOnClickListener(this);
        mRLayoutBrawse.setOnClickListener(this);
        mRLayoutMessage.setOnClickListener(this);
        mRLayoutPersonalCenter.setOnClickListener(this);
        // 让首页在启动应用时默认显示出来
        mRLayoutHome.performClick();
        // 创建并显示引导对话框
        mDialogFragmentSplashPage = new SplashPageDialogFragment();
        mDialogFragmentSplashPage.show(mFragmentManager, SplashPageDialogFragment.class.getName());
//        ViewTreeObserver viewTreeObserver = mTitleGridLayout.getViewTreeObserver();
//        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//
//            public void onGlobalLayout() {
//                mTitleBgImage.setLayoutParams(
//                        new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, mTitleGridLayout.getHeight()));
//                // ���Ҫˢ��һ�½���
//                mTitleBgImage.invalidate();
//                Log.i("CategoryDetailActivity", "mTitleBgImage=" + mTitleBgImage.getHeight());
//                // Log.i("CategoryDetailActivity", "details_url=" +
//                // details_url);
//                ImageLoader.getInstance().displayImage(Constants.HTTP_URL_IMAGE + details_url, mTitleBgImage,
//                        new DisplayImageOptions.Builder().cacheOnDisc(true).cacheInMemory(true)
//                                .showStubImage(R.drawable.theme_01).showImageForEmptyUri(R.drawable.theme_01)
//                                .showImageOnFail(R.drawable.theme_01).imageScaleType(ImageScaleType.EXACTLY).build());
//            }
//        });
    }

    @Override
    protected void addPageHistory() {
        String activityName = this.getClass().getName();// 这个activityName需要拼接上当时页面数据的唯一标识，以实现多个相同界面可以加入集合中，如像多个不同数据的详情
        Log.d("MainActivity", "MainActivity_onResume_title==>" + this.getTitle());
        PageHistoryEntity historyEntity = new PageHistoryEntity();
        historyEntity.setPageName("主页");
        historyEntity.setPageFlag(activityName);
        SupportApplication.getInstance().addPageHistory(historyEntity);
    }

    @Override
    protected void addClosedPage() {
        String activityName = this.getClass().getName();// 这个activityName需要拼接上当时页面数据的唯一标识，以实现多个相同界面可以加入集合中，如像多个不同数据的详情
        Log.d("MainActivity", "MainActivity_onResume_title==>" + this.getTitle());
        PageHistoryEntity historyEntity = new PageHistoryEntity();
        historyEntity.setPageFlag(activityName);
        historyEntity.setPageName("主页");
        SupportApplication.getInstance().addClosedPage(0,historyEntity);
    }

    @Override
    protected void loadData() {
        List<Param> params = new ArrayList<>();
        params.add(new Param("K", "V"));
        mMainActivityPresenter.loadData(params);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Stack<Activity> activityStack = SupportApplication.getInstance().getActivityStack();
        for (Activity activity : activityStack) {
            String activityName = activity.getClass().getName();
            Log.d("MainActivity", "MainActivity_onResume_activityName==>" + activityName);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        int statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
            Log.d("MainActivity", "statusBarHeight==" + statusBarHeight);
        }
        int contentTop = getWindow().findViewById(android.view.Window.ID_ANDROID_CONTENT).getTop();
        //statusBarHeight是上面状态栏的高度
        Log.d("MainActivity", "contentTop==" + contentTop);
        int titleBarHeight = contentTop - statusBarHeight;
        Log.d("MainActivity", "titleBarHeight==" + titleBarHeight);
    }

    @Override
    public void loadDataStart() {

    }

    @Override
    public void loadDataSuccess(String text) {

    }

    @Override
    public void loadDataFailed(String text) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlayout_nav_home:
                showTargetFragment(mFragmentHome.getClass().getSimpleName());
                break;
            case R.id.rlayout_nav_browse:
                showTargetFragment(mFragmentBrowse.getClass().getSimpleName());
                break;
            case R.id.rlayout_nav_message:
                showTargetFragment(mFragmentMessage.getClass().getSimpleName());
                break;
            case R.id.rlayout_nav_personal_center:
                showTargetFragment(mFragmentPersonalCenter.getClass().getSimpleName());
                break;
            default:
                break;
        }
    }

    private void showTargetFragment(String fragmentName) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        hideAllFragments(fragmentTransaction);
        switch (fragmentName) {
            case "HomeFragment":
                fragmentTransaction.show(mFragmentHome);
                break;
            case "BrowseFragment":
                fragmentTransaction.show(mFragmentBrowse);
                break;
            case "MessageFragment":
                fragmentTransaction.show(mFragmentMessage);
                break;
            case "PersonalCenterFragment":
                fragmentTransaction.show(mFragmentPersonalCenter);
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }

    private void hideAllFragments(FragmentTransaction fragmentTransaction) {
        if (mFragmentHome != null) {
            fragmentTransaction.hide(mFragmentHome);
        }
        if (mFragmentBrowse != null) {
            fragmentTransaction.hide(mFragmentBrowse);
        }
        if (mFragmentMessage != null) {
            fragmentTransaction.hide(mFragmentMessage);
        }
        if (mFragmentPersonalCenter != null) {
            fragmentTransaction.hide(mFragmentPersonalCenter);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastExitTime > 2000) {
                Toast.makeText(MainActivity.this, getResources().getString(R.string.prompt_when_exit_the_app),
                        Toast.LENGTH_SHORT).show();
                lastExitTime = currentTime;
            } else {
                // 必须清除内存中缓存的登录用户数据，否则只执行MyApplication.getInstance().exitApplication();Application不一定销毁
//                SupportApplication.getInstance().initGlobalUserInfo(null);
                SupportApplication.getInstance().exitApplication();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}

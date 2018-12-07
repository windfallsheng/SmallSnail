package com.smallsnailtech.smallsnail.base;

import android.app.Dialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;

import com.smallsnailtech.smallsnail.manager.SuspendedWindowOperate;
import com.smallsnailtech.smallsnail.ui.fragment.dialogfragment.PageHistoryDialogFragment;
import com.smallsnailtech.smallsnail.ui.fragment.dialogfragment.SplashPageDialogFragment;

/**
 * @author luzhaosheng
 * @date 2018年11月06日
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    protected T mPresenter;
    private PageHistoryDialogFragment mPageHistoryDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //
        setContentView(getContentViewResID());
        //
        SupportApplication.getInstance().addActivity(this);
        SupportApplication.getInstance().addActivityClass(this);
//        String activityName = this.getClass().getName();// 这个activityName需要拼接上当时页面数据的唯一标识，以实现多个相同界面可以加入集合中，如像多个不同数据的详情
//        PageHistoryEntity historyEntity = new PageHistoryEntity();
//        historyEntity.setPageName();
//        SupportApplication.getInstance().addPageHistory(activityName, historyEntity);
        initVariables();
        initViewsEvent();
        addPageHistory();
        loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SupportApplication.getInstance().finishActivity(this);
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showNetError() {

    }

    public boolean isPageHistoryDialogShowing() {
        if (mPageHistoryDialogFragment == null) {
            return false;
        } else {
            Dialog dialog = mPageHistoryDialogFragment.getDialog();
            boolean isShowing;
            if (dialog == null) {
                isShowing = false;
                Log.e("BaseActivity", "get_dialog == null--isShowing==" + isShowing);
            } else {
                isShowing = dialog.isShowing();
                Log.e("BaseActivity", "get_dialog = " + dialog.toString() + "--isShowing==" + isShowing);
            }
            return isShowing;
        }
    }

    public void notifyPageHistoryDialog() {
        if (mPageHistoryDialogFragment == null) {
            mPageHistoryDialogFragment = new PageHistoryDialogFragment();
        }
        Dialog dialog = mPageHistoryDialogFragment.getDialog();
        boolean isShowing;
        if (dialog == null) {
            isShowing = false;
            Log.e("BaseActivity", "dialog == null--isShowing==" + isShowing);
        } else {
            isShowing = dialog.isShowing();
            Log.e("BaseActivity", "dialog = " + dialog.toString() + "--isShowing==" + isShowing);
        }
        if (!isShowing) {
            FragmentManager fragmentManager = getFragmentManager();
            mPageHistoryDialogFragment.show(fragmentManager, SplashPageDialogFragment.class.getName());
        } else {
            if (mPageHistoryDialogFragment != null) {
                mPageHistoryDialogFragment.dismiss();
            }
        }
    }

    /**
     * 设置布局
     */
    protected abstract int getContentViewResID();

    /**
     * 初始化变量
     */
    protected abstract void initVariables();

    /**
     * 初始化控件
     */
    protected abstract void initViewsEvent();

    /**
     * 添加到浏览历史记录集合
     */
    protected abstract void addPageHistory();
    /**
     * 添加到关闭页面的记录集合
     */
    protected abstract void addClosedPage();

    /**
     * 获取数据
     */
    protected abstract void loadData();

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Log.d(this.getClass().getName(), "onKeyDown");
            // 保存关闭的页面到一个集合中
            addClosedPage();
            SuspendedWindowOperate.getInstance().setGoForwardPageIndex(0);// 如果界面关闭就要将向前跳转的index标识重置，可以将这个方法放在onDestroy()方法里
            finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

}

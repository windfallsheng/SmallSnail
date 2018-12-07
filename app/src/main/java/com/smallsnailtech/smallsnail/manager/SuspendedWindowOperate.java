package com.smallsnailtech.smallsnail.manager;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.smallsnailtech.smallsnail.R;
import com.smallsnailtech.smallsnail.base.BaseActivity;
import com.smallsnailtech.smallsnail.base.SupportApplication;
import com.smallsnailtech.smallsnail.entity.PageHistoryEntity;
import com.smallsnailtech.smallsnail.ui.activity.PublishInformationActivity;
import com.smallsnailtech.smallsnail.ui.activity.SearchInformationsActivity;

import java.util.List;

import static android.content.Context.WINDOW_SERVICE;


public class SuspendedWindowOperate {

    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;
    private View mContentView;
    private boolean isSuspendedWindowShowing;
    private boolean isOperateViewsShowing;
    private ImageView mIVOperateCenter, mIVGoBack, mIVGoForward, mIVGoToSearch, mIVGoToPublish;
    private AnimatorSet mAnimatorSetBottom, mAnimatorSetTop, mAnimatorSetRight, mAnimatorSetLeft, mAnimatorSetCenter;
    private AnimatorSet mAnimatorSetBottomHide, mAnimatorSetTopHide, mAnimatorSetRightHide, mAnimatorSetLeftHide;
    private ObjectAnimator mAnimAlphaContentViewWhenShow, mAnimatorContentViewAlpha1, mAnimatorContentViewAlpha0;
    private int mGoForwardPageIndex;
    int downRawX;
    int downRawY;

    private SuspendedWindowOperate() {

    }

    private static class SingletonInstance {
        @SuppressLint("StaticFieldLeak")
        private static final SuspendedWindowOperate INSTANCE = new SuspendedWindowOperate();
    }

    public static SuspendedWindowOperate getInstance() {
        return SingletonInstance.INSTANCE;
    }

    public boolean isSuspendedWindowShowing() {
        return isSuspendedWindowShowing;
    }

    public void setGoForwardPageIndex(int goForwardPageIndex) {
        mGoForwardPageIndex = goForwardPageIndex;
    }

    private void initAnimations() {
        mAnimatorContentViewAlpha1 = ObjectAnimator.ofFloat(mContentView, "alpha", 1.0F);
        mAnimatorContentViewAlpha1.setDuration(500);
        mAnimatorContentViewAlpha0 = ObjectAnimator.ofFloat(mContentView, "alpha", 1.0F, 0.5F, 0.2F);
        mAnimatorContentViewAlpha0.setDuration(5000);
        mAnimAlphaContentViewWhenShow = ObjectAnimator.ofFloat(mContentView, "alpha", 0.2F, 1.0F, 0.2F, 1.0F, 0.85F, 0.45F, 0.2f);
        mAnimAlphaContentViewWhenShow.setInterpolator(new AccelerateDecelerateInterpolator());
        mAnimAlphaContentViewWhenShow.setDuration(5000);
        Log.e("SuspendedWindow", "initAnimations_mAnimatorSetCenter==" + mAnimatorSetCenter);
        // 中心操作控件的旋转动画
        mAnimatorSetCenter = new AnimatorSet();
        ObjectAnimator rotationCenter = ObjectAnimator.ofFloat(mIVOperateCenter, "rotation", 0F, 720F);
        mAnimatorSetCenter.setInterpolator(new AccelerateInterpolator());
        mAnimatorSetCenter.setDuration(270);
        mAnimatorSetCenter.playTogether(rotationCenter);
//        //展开动画
        mAnimatorSetBottom = new AnimatorSet();
        mAnimatorSetTop = new AnimatorSet();
        mAnimatorSetRight = new AnimatorSet();
        mAnimatorSetLeft = new AnimatorSet();
        ObjectAnimator alphaBottom = ObjectAnimator.ofFloat(mIVGoToPublish, "alpha", 0.2F, 1.0F);
        ObjectAnimator alphaRight = ObjectAnimator.ofFloat(mIVGoForward, "alpha", 0.2F, 1.0F);
        ObjectAnimator alphaTop = ObjectAnimator.ofFloat(mIVGoBack, "alpha", 0.2F, 1.0F);
        ObjectAnimator alphaLeft = ObjectAnimator.ofFloat(mIVGoToSearch, "alpha", 0.2F, 1.0F);
        //
        ObjectAnimator scaleXBottom = ObjectAnimator.ofFloat(mIVGoToPublish, "scaleX", 0.0F, 1.0F);
        ObjectAnimator scaleXRight = ObjectAnimator.ofFloat(mIVGoForward, "scaleX", 0.0F, 1.0F);
        ObjectAnimator scaleXTop = ObjectAnimator.ofFloat(mIVGoBack, "scaleX", 0.0F, 1.0F);
        ObjectAnimator scaleXLeft = ObjectAnimator.ofFloat(mIVGoToSearch, "scaleX", 0.0F, 1.0F);
        //
        ObjectAnimator scaleYBottom = ObjectAnimator.ofFloat(mIVGoToPublish, "scaleY", 0.0F, 1.0F);
        ObjectAnimator scaleYRight = ObjectAnimator.ofFloat(mIVGoForward, "scaleY", 0.0F, 1.0F);
        ObjectAnimator scaleYTop = ObjectAnimator.ofFloat(mIVGoBack, "scaleY", 0.0F, 1.0F);
        ObjectAnimator scaleYLeft = ObjectAnimator.ofFloat(mIVGoToSearch, "scaleY", 0.0F, 1.0F);
        //
        ObjectAnimator rotationBottom = ObjectAnimator.ofFloat(mIVGoToPublish, "rotation", 0F, 720F);
        ObjectAnimator rotationRight = ObjectAnimator.ofFloat(mIVGoForward, "rotation", 0F, 720F);
        ObjectAnimator rotationTop = ObjectAnimator.ofFloat(mIVGoBack, "rotation", 0F, 720F);
        ObjectAnimator rotationLeft = ObjectAnimator.ofFloat(mIVGoToSearch, "rotation", 0F, 720F);
        //
        ObjectAnimator translationToBottom = ObjectAnimator.ofFloat(mIVGoToPublish, "translationY", 0, 150);
        ObjectAnimator translationToRight = ObjectAnimator.ofFloat(mIVGoForward, "translationX", 0, 150);
        ObjectAnimator translationToTop = ObjectAnimator.ofFloat(mIVGoBack, "translationY", 0, -150);
        ObjectAnimator translationToLeft = ObjectAnimator.ofFloat(mIVGoToSearch, "translationX", 0, -150);
        //
        mAnimatorSetBottom.setInterpolator(new AccelerateInterpolator());
        mAnimatorSetBottom.playTogether(alphaBottom, translationToBottom, scaleXBottom, scaleYBottom, rotationBottom);
        mAnimatorSetBottom.setDuration(270);
        //
        mAnimatorSetTop.setInterpolator(new AccelerateInterpolator());
        mAnimatorSetTop.playTogether(alphaTop, translationToTop, scaleXTop, scaleYTop, rotationTop);
        mAnimatorSetTop.setDuration(270);
        //
        mAnimatorSetRight.setInterpolator(new AccelerateInterpolator());
        mAnimatorSetRight.playTogether(alphaRight, translationToRight, scaleXRight, scaleYRight, rotationRight);
        mAnimatorSetRight.setDuration(270);
        //
        mAnimatorSetLeft.setInterpolator(new AccelerateInterpolator());
        mAnimatorSetLeft.playTogether(alphaLeft, translationToLeft, scaleXLeft, scaleYLeft, rotationLeft);
        mAnimatorSetLeft.setDuration(270);
        //收起动画
        mAnimatorSetBottomHide = new AnimatorSet();
        mAnimatorSetTopHide = new AnimatorSet();
        mAnimatorSetRightHide = new AnimatorSet();
        mAnimatorSetLeftHide = new AnimatorSet();
        ObjectAnimator alphaBottomHide = ObjectAnimator.ofFloat(mIVGoToPublish, "alpha", 1.0F, 0.2F);
        ObjectAnimator alphaRightHide = ObjectAnimator.ofFloat(mIVGoForward, "alpha", 1.0F, 0.2F);
        ObjectAnimator alphaTopHide = ObjectAnimator.ofFloat(mIVGoBack, "alpha", 1.0F, 0.2F);
        ObjectAnimator alphaLeftHide = ObjectAnimator.ofFloat(mIVGoToSearch, "alpha", 1.0F, 0.2F);
        //
        ObjectAnimator scaleXBottomHide = ObjectAnimator.ofFloat(mIVGoToPublish, "scaleX", 1.0F, 0.0F);
        ObjectAnimator scaleXRightHide = ObjectAnimator.ofFloat(mIVGoForward, "scaleX", 1.0F, 0.0F);
        ObjectAnimator scaleXTopHide = ObjectAnimator.ofFloat(mIVGoBack, "scaleX", 1.0F, 0.0F);
        ObjectAnimator scaleXLeftHide = ObjectAnimator.ofFloat(mIVGoToSearch, "scaleX", 1.0F, 0.0F);
        //
        ObjectAnimator scaleYBottomHide = ObjectAnimator.ofFloat(mIVGoToPublish, "scaleY", 1.0F, 0.0F);
        ObjectAnimator scaleYRightHide = ObjectAnimator.ofFloat(mIVGoForward, "scaleY", 1.0F, 0.0F);
        ObjectAnimator scaleYTopHide = ObjectAnimator.ofFloat(mIVGoBack, "scaleY", 1.0F, 0.0F);
        ObjectAnimator scaleYLeftHide = ObjectAnimator.ofFloat(mIVGoToSearch, "scaleY", 1.0F, 0.0F);
        //
        ObjectAnimator translationToBottomHide = ObjectAnimator.ofFloat(mIVGoToPublish, "translationY", 150, 0);
        ObjectAnimator translationToRightHide = ObjectAnimator.ofFloat(mIVGoForward, "translationX", 150, 0);
        ObjectAnimator translationToTopHide = ObjectAnimator.ofFloat(mIVGoBack, "translationY", -150, 0);
        ObjectAnimator translationToLeftHide = ObjectAnimator.ofFloat(mIVGoToSearch, "translationX", -150, 0);
        //
        mAnimatorSetBottomHide.setInterpolator(new AccelerateInterpolator());
        mAnimatorSetBottomHide.playTogether(alphaBottomHide, translationToBottomHide, scaleXBottomHide, scaleYBottomHide, rotationBottom);
        mAnimatorSetBottomHide.setDuration(270);
        //
        mAnimatorSetTopHide.setInterpolator(new AccelerateInterpolator());
        mAnimatorSetTopHide.playTogether(alphaTopHide, translationToTopHide, scaleXTopHide, scaleYTopHide, rotationTop);
        mAnimatorSetTopHide.setDuration(270);
        //
        mAnimatorSetRight.setInterpolator(new AccelerateInterpolator());
        mAnimatorSetRightHide.playTogether(alphaRightHide, translationToRightHide, scaleXRightHide, scaleYRightHide, rotationRight);
        mAnimatorSetRightHide.setDuration(270);
        //
        mAnimatorSetLeftHide.setInterpolator(new AccelerateInterpolator());
        mAnimatorSetLeftHide.playTogether(alphaLeftHide, translationToLeftHide, scaleXLeftHide, scaleYLeftHide, rotationLeft);
        mAnimatorSetLeftHide.setDuration(270);
    }

    @SuppressLint("CheckResult")
    public void showWindow(Context context) {
        if (isSuspendedWindowShowing) {
            return;
        }
        mWindowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        mContentView = LayoutInflater.from(context).inflate(R.layout.layout_window_suspended_operate, null);
        mIVOperateCenter = mContentView.findViewById(R.id.imageview_operate_center);
        mIVGoBack = mContentView.findViewById(R.id.imageview_operate_go_back);
        mIVGoToPublish = mContentView.findViewById(R.id.imageview_operate_go_to_publish);
        mIVGoForward = mContentView.findViewById(R.id.imageview_operate_go_forward);
        mIVGoToSearch = mContentView.findViewById(R.id.imageview_operate_go_to_search);
        initAnimations();
        if (mAnimAlphaContentViewWhenShow != null) {
            mAnimAlphaContentViewWhenShow.start();
        }
        if (isOperateViewsShowing) {
            mAnimatorSetBottom.start();
            mAnimatorSetTop.start();
            mAnimatorSetRight.start();
            mAnimatorSetLeft.start();
            mAnimatorSetCenter.start();
        }
        mLayoutParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
        mLayoutParams.format = PixelFormat.RGBA_8888;   //窗口透明
        mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;  //窗口位置
        mLayoutParams.x = mWindowManager.getDefaultDisplay().getWidth();// 以屏幕左上角为原点，设置x、y初始值
        mLayoutParams.y = mWindowManager.getDefaultDisplay().getHeight();
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT; //窗口的宽
        mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT; //窗口的高
//        mLayoutParams.width = 400; //窗口的宽
//        mLayoutParams.height = 400; //窗口的高
        mLayoutParams.x = (int) (mWindowManager.getDefaultDisplay().getWidth() * 0.7);//0.618黄金分割点
        mLayoutParams.y = (int) (mWindowManager.getDefaultDisplay().getHeight() * 0.618);
        mWindowManager.addView(mContentView, mLayoutParams);
        isSuspendedWindowShowing = true;
        initViewsListener(context);
    }

    public void dismissWindow() {
        if (mWindowManager != null && mContentView != null) {
            mWindowManager.removeViewImmediate(mContentView);
            mWindowManager = null;
            mContentView = null;
        }
        isSuspendedWindowShowing = false;
    }


    private void initViewsListener(final Context context) {
        mIVGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float alphaContentView = mContentView.getAlpha();
                if (alphaContentView <= 1.0F) {// 开始淡入动画
                    mAnimatorContentViewAlpha0.start();
                }
                // 模拟返回键功能
                SupportApplication.getInstance().sendKeyCode(KeyEvent.KEYCODE_BACK);
                //
                // mGoForwardPageIndex = 0; // 如果点击了回退按钮，将向前跳转的index标识重置，因为下面的代码模拟了返回按钮事件，所在在onKeyDown()方法里处理
                // 模拟返回键功能
//                Runtime runtime = Runtime.getRuntime();
//                try {
//                    runtime.exec("input keyevent " + KeyEvent.KEYCODE_BACK);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });
        mIVGoToPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float alphaContentView = mContentView.getAlpha();
                if (alphaContentView <= 1.0F) {// 开始淡入动画
                    mAnimatorContentViewAlpha0.start();
                }
                context.startActivity(new Intent(context, PublishInformationActivity.class));
            }
        });
        mIVGoForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float alphaContentView = mContentView.getAlpha();
                if (alphaContentView <= 1.0F) {// 开始淡入动画
                    mAnimatorContentViewAlpha0.start();
                }
                List<PageHistoryEntity> closedPageList = SupportApplication.getInstance().getClosedPageList();
                if (closedPageList == null || (closedPageList != null && closedPageList.size() <= 0)) {
                    Toast.makeText(context, "还没有可以向前跳转的界面", Toast.LENGTH_SHORT).show();
                } else if (mGoForwardPageIndex >= closedPageList.size()) {
                    Toast.makeText(context, "已经跳转到最前面的界面", Toast.LENGTH_SHORT).show();
                } else {
                    PageHistoryEntity pageHistoryEntity = closedPageList.get(mGoForwardPageIndex);
                    String activityName = pageHistoryEntity.getPageFlag();
                    Class<? extends Activity> aClass = SupportApplication.getInstance().getTargeActivityClass(activityName);
                    if (aClass != null) {
                        context.startActivity(new Intent(context, aClass));
                    } else {
                        Toast.makeText(context, "没有获取到目标界面", Toast.LENGTH_SHORT).show();
                    }
                    mGoForwardPageIndex++;
                }
            }
        });
        mIVGoToSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float alphaContentView = mContentView.getAlpha();
                if (alphaContentView <= 1.0F) {// 开始淡入动画
                    mAnimatorContentViewAlpha0.start();
                }
                context.startActivity(new Intent(context, SearchInformationsActivity.class));
            }
        });
        mIVOperateCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isPageHistoryDialogShowing = ((BaseActivity) SupportApplication.getInstance().currentActivity()).isPageHistoryDialogShowing();
                if (isPageHistoryDialogShowing) {
                    ((BaseActivity) SupportApplication.getInstance().currentActivity()).notifyPageHistoryDialog();
                } else {
                    isOperateViewsShowing = !isOperateViewsShowing;
                    if (isOperateViewsShowing) {
                        mAnimatorSetBottom.start();
                        mAnimatorSetTop.start();
                        mAnimatorSetRight.start();
                        mAnimatorSetLeft.start();
                        mAnimatorSetCenter.start();
                        mLayoutParams.width = 400; //窗口的宽
                        mLayoutParams.height = 400; //窗口的高
                        mWindowManager.updateViewLayout(mContentView, mLayoutParams);
                    } else {
                        mAnimatorSetBottomHide.start();
                        mAnimatorSetTopHide.start();
                        mAnimatorSetRightHide.start();
                        mAnimatorSetLeftHide.start();
                        mAnimatorSetCenter.start();

                        mAnimatorSetLeftHide.addListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {
                                if (null != mContentView && null != mLayoutParams) {
//                                    if (isOperateViewsShowing) {
//                                        mLayoutParams.width = 400;
//                                        mLayoutParams.height = 400;
//                                    } else {
//                mLayoutParams.x = mLayoutParams.x + mRLayoutPageHistory.getWidth()/2;
//                mLayoutParams.y = mLayoutParams.y + mRLayoutPageHistory.getHeight()/2;
                                    mLayoutParams.x = downRawX;
                                    mLayoutParams.y = downRawY;
                                    mLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT; //窗口的宽
                                    mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT; //窗口的高
//                                    }
                                    mWindowManager.updateViewLayout(mContentView, mLayoutParams);
                                }
                            }

                            @Override
                            public void onAnimationCancel(Animator animator) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animator) {

                            }
                        });
                    }
                }
            }
        });
        mIVOperateCenter.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                boolean isPageHistoryDialogShowing = ((BaseActivity) SupportApplication.getInstance().currentActivity()).isPageHistoryDialogShowing();
                if (!isPageHistoryDialogShowing) {
                    ((BaseActivity) SupportApplication.getInstance().currentActivity()).notifyPageHistoryDialog();
                }
                return true;
            }
        });
        //设置触摸滑动事件
        mIVOperateCenter.setOnTouchListener(new View.OnTouchListener() {


            int startX, startY;  //起始点
            boolean isSuspendedWindowMoving;  //是否在移动
            int finalMoveX;  //最后通过动画将mView的X轴坐标移动到finalMoveX
            int statusBarHeight;
            int widthDisplay = mWindowManager.getDefaultDisplay().getWidth();
            int heightDisplay = mWindowManager.getDefaultDisplay().getHeight();

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mAnimAlphaContentViewWhenShow != null && mAnimAlphaContentViewWhenShow.isRunning()) {
                            mAnimAlphaContentViewWhenShow.cancel();
                        }
                        float alphaContentView = mContentView.getAlpha();
                        if (alphaContentView <= 1.0F) {// 开始淡入动画
                            mAnimatorContentViewAlpha0.start();
                        }
                        startX = (int) event.getX();
                        startY = (int) event.getY();
                        Log.e("SuspendedWindow", "startX---->" + startX);
                        Log.e("SuspendedWindow", "startY---->" + startY);
                        downRawX = (int) event.getRawX();
                        downRawY = (int) event.getRawY();
                        Log.e("SuspendedWindow", "downRawX---->" + downRawX);
                        Log.e("SuspendedWindow", "downRawY---->" + downRawY);
                        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
                        if (resourceId > 0) {
                            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
                            Log.e("SuspendedWindow", "statusBarHeight---->" + statusBarHeight);
                        }
                        isSuspendedWindowMoving = false;
                        return false;
                    case MotionEvent.ACTION_MOVE:
                        int moveRawX = (int) event.getRawX();
                        int moveRawY = (int) event.getRawY();
                        Log.e("SuspendedWindow", "moveRawX---->" + moveRawX);
                        Log.e("SuspendedWindow", "moveRawY---->" + moveRawY);
                        if (Math.abs(moveRawX - downRawX) > 0 || Math.abs(moveRawY - downRawY) > 0) {
                            mLayoutParams.x = (int) (moveRawX - startX);
                            //这里修复了刚开始移动的时候，悬浮窗的y坐标是不正确的，要减去状态栏的高度，可以将这个去掉运行体验一下
                            mLayoutParams.y = (int) (moveRawY - startY - statusBarHeight - 90);
                            Log.e("SuspendedWindow", "y---->" + mLayoutParams.y);
                            updateViewLayout();   //更新mView 的位置
                            if (isSuspendedWindowMoving == false) {
                                isSuspendedWindowMoving = true;
                            }
                        } else {
                            isSuspendedWindowMoving = false;
                        }
                        if (isSuspendedWindowMoving) {
                            return false;
                        } else {
                            return true;
                        }
                    case MotionEvent.ACTION_UP:
                        int upRawX = (int) event.getRawX();
                        int upRawY = (int) event.getRawY();
                        Log.e("SuspendedWindow", "upRawX---->" + upRawX);
                        Log.e("SuspendedWindow", "upRawY---->" + upRawY);
                        Log.e("SuspendedWindow", "widthDisplay---->" + widthDisplay);
                        Log.e("SuspendedWindow", "heightDisplay---->" + heightDisplay);
//                        int borderX = widthDisplay - upRawX;
//                        int borderY = heightDisplay - upRawY;
//                        if (borderX < 50) {
//                            finalMoveX = borderX;
//                        }
//                        if (borderY < 150){
//                            finalMoveY =  borderY;
//                        }
                        // 开始淡出动画
                        mAnimatorContentViewAlpha0.start();
                        //判断mView是在Window中的位置，以中间为界
                        if (mLayoutParams.x + mContentView.getMeasuredWidth() / 2 >= mWindowManager.getDefaultDisplay().getWidth() / 2) {
                            finalMoveX = mWindowManager.getDefaultDisplay().getWidth() - mContentView.getMeasuredWidth();
                        } else {
                            finalMoveX = 0;
                        }
                        //使用动画移动mView
                        ValueAnimator animator = ValueAnimator.ofInt(mLayoutParams.x, finalMoveX).setDuration(Math.abs(mLayoutParams.x - finalMoveX));
                        animator.addUpdateListener((ValueAnimator animation) -> {
                            mLayoutParams.x = (int) animation.getAnimatedValue();
                            updateViewLayout();
                        });
                        animator.start();
                        if (isSuspendedWindowMoving) {
                            return true;
                        } else {
                            return false;
                        }
                }
                return true;
            }
        });
    }

    private void updateViewLayout() {
        if (null != mContentView && null != mLayoutParams) {
            mWindowManager.updateViewLayout(mContentView, mLayoutParams);
        }
    }
}
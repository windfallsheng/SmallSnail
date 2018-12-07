package com.smallsnailtech.smallsnail.manager;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.smallsnailtech.smallsnail.R;

import java.lang.reflect.Field;

import static android.content.Context.WINDOW_SERVICE;


public class SuspendedWindowPageHistory {

    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;
    private View mView;
    private ImageView mIVClose;
    private RelativeLayout mRLayoutPageHistory, mRLayoutTitle;

    private static final String ROM360 = "rom360";
    private static final String HUAWEI = "huawei";
    private static final String MEIZU = "meizu";
    private static final String MIUI = "miui";
    private static final String OPPO = "oppo";
    private static final String COMMON_ROM = "common_rom";

    private OnPermissionListener mOnPermissionListener;


    private SuspendedWindowPageHistory() {

    }

    private static class SingletonInstance {
        @SuppressLint("StaticFieldLeak")
        private static final SuspendedWindowPageHistory INSTANCE = new SuspendedWindowPageHistory();
    }

    public static SuspendedWindowPageHistory getInstance() {
        return SingletonInstance.INSTANCE;
    }

    @SuppressLint("CheckResult")
    public void showWindow(Context context) {
        mWindowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        mView = LayoutInflater.from(context).inflate(R.layout.dialog_fragment_page_history, null);
//        mRLayoutPageHistory = mView.findViewById(R.id.rlayout_page_history);
//        mRLayoutPageHistory.setVisibility(View.GONE);
        mRLayoutTitle = mView.findViewById(R.id.rlayout_title);
        mIVClose = mView.findViewById(R.id.imageview_close);
//        RecyclerView rvPageHistory = mView.findViewById(R.id.recyclerview_page_history);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false);
//        rvPageHistory.setLayoutManager(gridLayoutManager);
//        rvPageHistory.setAdapter(new PageHistoryAdapter(context));
        initListener(context);
        mLayoutParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
        mLayoutParams.format = PixelFormat.RGBA_8888;   //窗口透明
        mLayoutParams.gravity = Gravity.CENTER;  //窗口位置
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mLayoutParams.width = 700;
        mLayoutParams.height = 750;
//        mLayoutParams.x = mWindowManager.getDefaultDisplay().getWidth() - 200;
        mLayoutParams.x = 0;
        mLayoutParams.y = 0;
        mWindowManager.addView(mView, mLayoutParams);
    }

    public void dismissWindow() {
        if (mWindowManager != null && mView != null) {
            mWindowManager.removeViewImmediate(mView);
            mWindowManager = null;
            mView = null;
        }
    }

    private void initListener(final Context context) {
        mIVClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismissWindow();
            }
        });
        //设置触摸滑动事件
        mView.setOnTouchListener(new View.OnTouchListener() {
            int startX, startY;  //起始点
            boolean isMove;  //是否在移动
            long startTime;
            int finalMoveX;  //最后通过动画将mView的X轴坐标移动到finalMoveX
            int statusBarHeight;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = (int) event.getX();
                        startY = (int) event.getY();
                        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
                        if (resourceId > 0) {
                            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
                            Log.e("TAG", "statusBarHeight---->" + statusBarHeight);
                        }
                        startTime = System.currentTimeMillis();
                        isMove = false;
                        return false;
                    case MotionEvent.ACTION_MOVE:
                        mLayoutParams.x = (int) (event.getRawX() - startX);
                        //这里修复了刚开始移动的时候，悬浮窗的y坐标是不正确的，要减去状态栏的高度，可以将这个去掉运行体验一下
                        mLayoutParams.y = (int) (event.getRawY() - startY - statusBarHeight);
                        Log.e("TAG", "y---->" + mLayoutParams.y);
                        updateViewLayout();   //更新mView 的位置
                        return true;
                    case MotionEvent.ACTION_UP:
                        long curTime = System.currentTimeMillis();
                        isMove = curTime - startTime > 100;

                        //判断mView是在Window中的位置，以中间为界
                        if (mLayoutParams.x + mView.getMeasuredWidth() / 2 >= mWindowManager.getDefaultDisplay().getWidth() / 2) {
                            finalMoveX = mWindowManager.getDefaultDisplay().getWidth() - mView.getMeasuredWidth();
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
                        return isMove;
                }
                return false;
            }
        });
    }

    private void updateViewLayout() {
        if (null != mView && null != mLayoutParams) {
            mWindowManager.updateViewLayout(mView, mLayoutParams);
        }
    }


    public void commonROMPermissionApplyInternal(Context context) throws NoSuchFieldException, IllegalAccessException {
        Class clazz = Settings.class;
        Field field = clazz.getDeclaredField("ACTION_MANAGE_OVERLAY_PERMISSION");

        Intent intent = new Intent(field.get(null).toString());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        context.startActivity(intent);
    }

    interface OnPermissionListener {
        void result(boolean isSuccess);
    }
}
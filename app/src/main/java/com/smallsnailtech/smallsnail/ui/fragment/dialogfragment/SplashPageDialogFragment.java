package com.smallsnailtech.smallsnail.ui.fragment.dialogfragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.smallsnailtech.smallsnail.R;
import com.smallsnailtech.smallsnail.ui.adapter.SplashPageAdapter;
import com.smallsnailtech.smallsnail.util.DisplayUtil;
import com.smallsnailtech.smallsnail.widget.ItemDecorationSplash;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luzhaosheng
 * @date 2018年11月06日
 * <p>
 * 首次安装应用时，引导页从本地资源文件中预置的图片中读数据，同时应用会在后台进行网络请求，获取下一次的数据，以便在下次打开应用时加载
 * 生命周期执行次序onCreate-->onCreateDialog-->onCreateView-->onStart-->onResume-->onStart-->onResume-->onStart-->onResume
 */
public class SplashPageDialogFragment extends DialogFragment {

    private View mViewContent;
    private RecyclerView mRVSplashPage;
    private ImageView mIVSplashClose;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(this.getClass().getName(), "onCreate");
//        setStyle(android.app.DialogFragment.STYLE_NORMAL, R.style.Fragment_Dialog_Style);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d(this.getClass().getName(), "onCreateDialog");
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(this.getClass().getName(), "onCreateView");
        mViewContent = inflater.inflate(R.layout.dialog_fragment_splash_page, container, false);
        mRVSplashPage = mViewContent.findViewById(R.id.recyclerview_splash_page);
        mIVSplashClose = mViewContent.findViewById(R.id.imageview_splash_close);
        initParams();
        initData();
        return mViewContent;
    }

    @Override
    public void onStart() {
        super.onStart();
        // 比如当前后台切换时会多次调用onStart方法
        Log.d(this.getClass().getName(), "onStart");
//        initParams();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(this.getClass().getName(), "onResume");
    }

    private void initParams() {
        Window window = getDialog().getWindow();
//        //设置dialog宽度
//        int width = DisplayUtil.getScreenWidth(getActivity()) - 2 * DisplayUtil.dp2px(getActivity(), 55);
//        //设置dialog高度
//        int height = DisplayUtil.getScreenHeight(getActivity()) - 2 * DisplayUtil.dp2px(getActivity(), 120);
//        window.setLayout(width, height);
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.gravity = Gravity.CENTER;
            //设置dialog宽度
//            layoutParams.width = DisplayUtil.getScreenWidth(getActivity()) - 2 * DisplayUtil.dp2px(getActivity(), 500);
            //设置dialog高度
//            layoutParams.height =  DisplayUtil.getScreenHeight(getActivity()) - 2 * DisplayUtil.dp2px(getActivity(), 600);
            //设置dialog进入、退出的动画
//            window.setWindowAnimations(animStyle);
            window.setAttributes(layoutParams);
        }
//        setCancelable(outCancel);
    }

    private void initData() {
        final List<String> urls = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            urls.add("");
        }
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRVSplashPage.setLayoutManager(layoutManager);
        mRVSplashPage.setHasFixedSize(true);
        // 通过自定义分割线类 添加分割线
//        mRVSplashPage.addItemDecoration(new ItemDecorationSplash());
        mRVSplashPage.setAdapter(new SplashPageAdapter(getActivity(), urls));
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mRVSplashPage);
        mRVSplashPage.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int i = layoutManager.findFirstVisibleItemPosition() % urls.size();
                    //得到指示器红点的位置
                    Log.d(this.getClass().getName(), "position = " + i);
                }
            }
        });
        mIVSplashClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}

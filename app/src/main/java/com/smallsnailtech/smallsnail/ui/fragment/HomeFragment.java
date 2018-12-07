package com.smallsnailtech.smallsnail.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.smallsnailtech.smallsnail.R;
import com.smallsnailtech.smallsnail.entity.InfoCategoryEntity;
import com.smallsnailtech.smallsnail.minterface.OnRecyclerViewItemClick;
import com.smallsnailtech.smallsnail.ui.activity.InformationDetailsActivity;
import com.smallsnailtech.smallsnail.ui.adapter.HomeBackgroundAdapter;
import com.smallsnailtech.smallsnail.ui.adapter.HomeFragmentAdapter;
import com.smallsnailtech.smallsnail.ui.adapter.InfoCategoryAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * @author luzhaosheng
 * @date 2018年11月06日
 */
public class HomeFragment extends Fragment {

    private View mViewContent;
    private RecyclerView mRVInfoList, mRVInfoCategoryList, mRVBackground;
    private RelativeLayout mRLayoutContainerTitle;
    private HomeFragmentAdapter mHomeFragmentAdapter;
    private InfoCategoryAdapter mInfoCategoryAdapter;
    private HomeBackgroundAdapter mHomeBackgroundAdapter;
    private Context mContext;

    public static HomeFragment newInstance() {
//        Bundle bundle = new Bundle();
//        bundle.putString("", "");
        HomeFragment browseFragment = new HomeFragment();
//        functionSettings.setArguments(bundle);
        return browseFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mViewContent = inflater.inflate(R.layout.fragment_home, null);
        Log.d(this.getClass().getName(), "onCreateView");
        return mViewContent;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
        mRLayoutContainerTitle = mViewContent.findViewById(R.id.llayout_container_title);
        mRVInfoCategoryList = mViewContent.findViewById(R.id.recyclerview_information_category);
        mRVBackground = mViewContent.findViewById(R.id.recyclerview_background);

//        LinearLayoutManager layoutManagerBg = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//        mRVBackground.setLayoutManager(layoutManagerBg);
//        List<String> imageUrls = new ArrayList<>();
//        imageUrls.add("http://atjp.hipad.com:9010/mdm_hpoa/Uploads/img/bybannert/2016-12-09/584a0c4ab8f44.jpg");
//        imageUrls.add("http://atjp.hipad.com:9010/mdm_hpoa/Uploads/img/bybannert/2016-12-09/584a0b89512d4.jpg");
//        imageUrls.add("http://atjp.hipad.com:9010/mdm_hpoa/Uploads/img/bybannert/2016-12-09/584a0be83240f.jpg");
//        mHomeBackgroundAdapter = new HomeBackgroundAdapter(getActivity(), imageUrls);
//        mRVBackground.setAdapter(mHomeBackgroundAdapter);
        //
        LinearLayoutManager layoutManagerInfoCategory = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRVInfoCategoryList.setLayoutManager(layoutManagerInfoCategory);
        List<InfoCategoryEntity> mInfoCategoryList = new ArrayList<>();
        InfoCategoryEntity ategory10 = new InfoCategoryEntity();
        ategory10.setCategoryName("关注");
        InfoCategoryEntity ategory11 = new InfoCategoryEntity();
        ategory11.setCategoryName("推荐");
        InfoCategoryEntity ategory1 = new InfoCategoryEntity();
        ategory1.setCategoryName("美食");
        InfoCategoryEntity ategory2 = new InfoCategoryEntity();
        ategory2.setCategoryName("电影");
        InfoCategoryEntity ategory3 = new InfoCategoryEntity();
        ategory3.setCategoryName("超市");
        InfoCategoryEntity ategory4 = new InfoCategoryEntity();
        ategory4.setCategoryName("娱乐");
        mInfoCategoryList.add(ategory10);
        mInfoCategoryList.add(ategory11);
        mInfoCategoryList.add(ategory1);
        mInfoCategoryList.add(ategory2);
        mInfoCategoryList.add(ategory3);
        mInfoCategoryList.add(ategory4);
        for (int i = 0; i < 10; i++) {
            InfoCategoryEntity ategory5 = new InfoCategoryEntity();
            ategory5.setCategoryName("数码" + i);
            mInfoCategoryList.add(ategory5);
        }
        mInfoCategoryAdapter = new InfoCategoryAdapter(getActivity(), mInfoCategoryList);
        mRVInfoCategoryList.setAdapter(mInfoCategoryAdapter);

        mRVInfoList = mViewContent.findViewById(R.id.recyclerview_info_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRVInfoList.setLayoutManager(layoutManager);
        mHomeFragmentAdapter = new HomeFragmentAdapter(getActivity(), null);
        mRVInfoList.setAdapter(mHomeFragmentAdapter);
        mHomeFragmentAdapter.setOnRecyclerViewItemClick(new OnRecyclerViewItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                mContext.startActivity(new Intent(mContext, InformationDetailsActivity.class));
            }
        });
        mRVInfoList.addOnScrollListener(new RecyclerView.OnScrollListener() {

            int totalY = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d(this.getClass().getName(), "newState==" + newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                Log.d(this.getClass().getName(), "dx==" + dx);
                Log.d(this.getClass().getName(), "dy==" + dy);
                totalY += dy;
                mRVBackground.smoothScrollBy(0, dy / 2);
                Log.d(this.getClass().getName(), "totalY==" + totalY);
            }
        });
        int heightTitle = mRLayoutContainerTitle.getHeight();
        Log.d(this.getClass().getName(), "heightTitle==" + heightTitle);
        ViewTreeObserver viewTreeObserver = mRLayoutContainerTitle.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            public void onGlobalLayout() {
//                mRLayoutContainerTitle.setLayoutParams(
//                        new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, mRLayoutContainerTitle.getHeight()));
//                mRLayoutContainerTitle.invalidate();
                Log.i(this.getClass().getName(), "heightTitle=" + mRLayoutContainerTitle.getHeight());
            }
        });
    }
}

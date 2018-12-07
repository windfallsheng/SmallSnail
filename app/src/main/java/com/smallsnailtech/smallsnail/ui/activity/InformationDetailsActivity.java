package com.smallsnailtech.smallsnail.ui.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.smallsnailtech.smallsnail.R;
import com.smallsnailtech.smallsnail.base.BaseActivity;
import com.smallsnailtech.smallsnail.base.SupportApplication;
import com.smallsnailtech.smallsnail.entity.PageHistoryEntity;

import java.util.Stack;

public class InformationDetailsActivity extends BaseActivity {

    private Button mBtn;
    private FragmentManager mFragmentManager;

    @Override
    protected int getContentViewResID() {
        return R.layout.activity_information_details;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViewsEvent() {
        mFragmentManager = getFragmentManager();
        mBtn = findViewById(R.id.button);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Stack<Activity> activityStack = SupportApplication.getInstance().getActivityStack();
                for (Activity activity : activityStack) {
                    String activityName = activity.getClass().getName();
                    Log.d("SupportApplication", "NewsDetailActivity_Stack<Activity>==>" + activityName);
                    if (activityName.equals(MainActivity.class.getName())){
                        startActivity(new Intent(InformationDetailsActivity.this, MainActivity.class));
                    }
                }
            }
        });
    }

    @Override
    protected void addPageHistory() {
        String activityName = this.getClass().getName();// 这个activityName需要拼接上当时页面数据的唯一标识，以实现多个相同界面可以加入集合中，如像多个不同数据的详情
        PageHistoryEntity historyEntity = new PageHistoryEntity();
        historyEntity.setPageName("新闻详情");
        historyEntity.setPageFlag(activityName);
        SupportApplication.getInstance().addPageHistory(historyEntity);
    }

    @Override
    protected void addClosedPage() {
        String activityName = this.getClass().getName();// 这个activityName需要拼接上当时页面数据的唯一标识，以实现多个相同界面可以加入集合中，如像多个不同数据的详情
        Log.d("MainActivity", "MainActivity_onResume_title==>" + this.getTitle());
        PageHistoryEntity historyEntity = new PageHistoryEntity();
        historyEntity.setPageFlag(activityName);
        historyEntity.setPageName("新闻详情");
        SupportApplication.getInstance().addClosedPage(0,historyEntity);
    }

    @Override
    protected void loadData() {

    }

}

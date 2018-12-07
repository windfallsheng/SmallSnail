package com.smallsnailtech.smallsnail.ui.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.smallsnailtech.smallsnail.R;
import com.smallsnailtech.smallsnail.base.BaseActivity;
import com.smallsnailtech.smallsnail.base.SupportApplication;
import com.smallsnailtech.smallsnail.entity.PageHistoryEntity;

public class LoginActivity extends BaseActivity {

    private Button mBtn;
    private FragmentManager mFragmentManager;

    @Override
    protected int getContentViewResID() {
        return R.layout.activity_login;
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
                startActivity(new Intent(LoginActivity.this, InformationDetailsActivity.class));
            }
        });
    }

    @Override
    protected void addPageHistory() {
        String activityName = this.getClass().getName();// 这个activityName需要拼接上当时页面数据的唯一标识，以实现多个相同界面可以加入集合中，如像多个不同数据的详情
        PageHistoryEntity historyEntity = new PageHistoryEntity();
        historyEntity.setPageName("登录页");
        historyEntity.setPageFlag(activityName);
        SupportApplication.getInstance().addPageHistory(historyEntity);
    }

    @Override
    protected void addClosedPage() {
        String activityName = this.getClass().getName();// 这个activityName需要拼接上当时页面数据的唯一标识，以实现多个相同界面可以加入集合中，如像多个不同数据的详情
        Log.d("MainActivity", "MainActivity_onResume_title==>" + this.getTitle());
        PageHistoryEntity historyEntity = new PageHistoryEntity();
        historyEntity.setPageFlag(activityName);
        historyEntity.setPageName("登录页");
        SupportApplication.getInstance().addClosedPage(0,historyEntity);
    }

    @Override
    protected void loadData() {

    }

}

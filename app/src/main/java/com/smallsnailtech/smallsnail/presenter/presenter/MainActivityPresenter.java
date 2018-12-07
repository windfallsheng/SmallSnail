package com.smallsnailtech.smallsnail.presenter.presenter;

import android.util.Log;

import com.smallsnailtech.smallsnail.base.BaseView;
import com.smallsnailtech.smallsnail.entity.ResponseEntity;
import com.smallsnailtech.smallsnail.entity.UserInfoEntity;
import com.smallsnailtech.smallsnail.minterface.RequestCallBack;
import com.smallsnailtech.smallsnail.presenter.contract.MainActivityContract;

import java.lang.ref.WeakReference;

/**
 * @author luzhaosheng
 * @date 2018年11月06日
 * 主要职责:完成完整的业务逻辑操作；完成必要的与页面的交互逻辑。
 */
public class MainActivityPresenter implements MainActivityContract.IMainActivityPresenter {

    private MainActivityContract.IMainActivityView mMainActivityView;
    private WeakReference<BaseView> mReferenceMainActivityView;
    private MainActivityContract.IMainActivityModel mMainActivityModel;

    public MainActivityPresenter(MainActivityContract.IMainActivityView MainActivityView, MainActivityContract.IMainActivityModel MainActivityModel) {
        mReferenceMainActivityView = new WeakReference<BaseView>(MainActivityView);
        mMainActivityModel = MainActivityModel;
    }

    @Override
    public void saveLastLoginAccount(String account) {

    }

    @Override
    public void getLastLoginAccount() {

    }

    @Override
    public void login(String account, String password) {
        mMainActivityModel.login(account, password, new RequestCallBack<ResponseEntity<UserInfoEntity>>() {

            @Override
            public void onStart() {
                mMainActivityView.loadDataStart();
            }

            @Override
            public void onSuccess(ResponseEntity<UserInfoEntity> responseEntity) {
                Log.d("MainActivityPresenter", "onNext");
                int resultCode = responseEntity.getCode();
                String resultMsg = responseEntity.getMsg();
                Log.d("MainActivityPresenter", "Main()_onNext()_resultCode=" + resultCode + " resultMsg==" + resultMsg);
                if (resultCode == 0) {

                    mMainActivityView.loadDataSuccess(resultMsg);
                } else {
                    mMainActivityView.loadDataFailed(resultMsg);
                    if (resultCode == 2) {

                    } else if (resultCode == 17) {

                    } else {
                        Log.d("MainActivityPresenter", "Get data failed.");
                    }
                }
            }

            @Override
            public void onFailed(Throwable t) {
                mMainActivityView.showNetError();
            }

            @Override
            public void onCancel() {

            }
        });
    }

    @Override
    public void attachView() {

    }

    @Override
    public void detachView() {
        if (mReferenceMainActivityView != null) {
            mReferenceMainActivityView.clear();
            mReferenceMainActivityView = null;
            Log.d("MainActivityPresenter", "detachView()_mReferenceMainActivityView=" + mReferenceMainActivityView);
        }
        if (mMainActivityModel != null) {
            mMainActivityModel.cancelTasks();
        }
    }
}

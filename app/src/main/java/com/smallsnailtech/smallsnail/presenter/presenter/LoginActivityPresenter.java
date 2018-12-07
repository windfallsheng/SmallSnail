package com.smallsnailtech.smallsnail.presenter.presenter;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.smallsnailtech.smallsnail.base.BaseView;
import com.smallsnailtech.smallsnail.base.SupportApplication;
import com.smallsnailtech.smallsnail.command.Constants;
import com.smallsnailtech.smallsnail.entity.ResponseCode;
import com.smallsnailtech.smallsnail.entity.ResponseEntity;
import com.smallsnailtech.smallsnail.entity.UserInfoEntity;
import com.smallsnailtech.smallsnail.minterface.RequestCallBack;
import com.smallsnailtech.smallsnail.presenter.contract.LoginActivityContract;
import com.smallsnailtech.smallsnail.util.SharedPrefUtils;

import java.lang.ref.WeakReference;

/**
 * @author luzhaosheng
 * @date 2018年11月06日
 * 主要职责:完成完整的业务逻辑操作；完成必要的与页面的交互逻辑。
 */
public class LoginActivityPresenter implements LoginActivityContract.ILoginPresenter {

    private LoginActivityContract.ILoginView mLoginView;
    private WeakReference<BaseView> mReferenceLoginView;
    private LoginActivityContract.ILoginModel mLoginModel;

    public LoginActivityPresenter(LoginActivityContract.ILoginView loginView, LoginActivityContract.ILoginModel loginModel) {
        mReferenceLoginView = new WeakReference<BaseView>(loginView);
        mLoginModel = loginModel;
    }

    @Override
    public void saveLastLoginAccount(String account) {
        SharedPrefUtils sharedCommonConfig = SharedPrefUtils.init(SupportApplication.getContext(), Constants.COMMON_CONFIG_SP);
        String lastLoginAccount = sharedCommonConfig.getString(Constants.LAST_LOGIN_ACCOUNT);
        if (!sharedCommonConfig.contains(Constants.LAST_LOGIN_ACCOUNT) || !lastLoginAccount.equals(account)) {
            sharedCommonConfig.put(Constants.LAST_LOGIN_ACCOUNT, account);
        }
    }

    @Override
    public void getLastLoginAccount() {
        // 如果上次用户登录过账号，本地就会缓存这个账号，在这里赋值
        SharedPrefUtils sharedCommonConfig = SharedPrefUtils.init(SupportApplication.getContext(), Constants.COMMON_CONFIG_SP);
        String lastLoginAccount = sharedCommonConfig.getString(Constants.LAST_LOGIN_ACCOUNT, "");
    }

    @Override
    public void login(String account, String password) {
        mLoginModel.login(account, password, new RequestCallBack<ResponseEntity<UserInfoEntity>>() {

            @Override
            public void onStart() {
                mLoginView.loginStart();
            }

            @Override
            public void onSuccess(ResponseEntity<UserInfoEntity> responseEntity) {
                Log.d("LoginActivityPresenter", "onNext");
                int resultCode = responseEntity.getCode();
                String resultMsg = responseEntity.getMsg();
                Log.d("LoginActivityPresenter", "login()_onNext()_resultCode=" + resultCode + " resultMsg==" + resultMsg);
                if (resultCode == 0) {
                    Log.d("LoginActivityPresenter", ResponseCode.SUCCESS.getMsg());
                    UserInfoEntity userInfoEntity = responseEntity.getData();
                    // 将通用信息保存在一个单独的SharedPreferences文件中
                    SharedPrefUtils sharedCommonConfig = SharedPrefUtils.init(SupportApplication.getContext(), Constants.COMMON_CONFIG_SP);
                    String userAccount = userInfoEntity.getUserAccount();
                    sharedCommonConfig.put(Constants.TOKEN, userInfoEntity.getToken());
                    sharedCommonConfig.put(Constants.LOGIN_SUCCESSED_ACCOUNT, userAccount);
                    SupportApplication.getInstance().initGlobalUserInfo(userInfoEntity);
                    LocalBroadcastManager.getInstance(SupportApplication.getContext())
                            .sendBroadcast(new Intent(Constants.ACTION_NORMAL_LOGIN_SUCCESSED));
                    mLoginView.loginSuccess(resultMsg);
                } else {
                    mLoginView.loginFailed(resultMsg);
                    if (resultCode == 2) {

                    } else if (resultCode == 17) {

                    } else {
                        Log.d("LoginActivityPresenter", "Get data failed.");
                    }
                }
            }

            @Override
            public void onFailed(Throwable t) {
                mLoginView.showNetError();
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
        if (mReferenceLoginView != null) {
            mReferenceLoginView.clear();
            mReferenceLoginView = null;
            Log.d("LoginActivityPresenter", "detachView()_mReferenceLoginView=" + mReferenceLoginView);
        }
        if (mLoginModel != null) {
            mLoginModel.cancelTasks();
        }
    }
}

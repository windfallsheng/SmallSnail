package com.smallsnailtech.smallsnail.presenter.presenter;

import android.util.Log;

import com.smallsnailtech.smallsnail.entity.Param;
import com.smallsnailtech.smallsnail.entity.ResponseEntity;
import com.smallsnailtech.smallsnail.entity.UserInfoEntity;
import com.smallsnailtech.smallsnail.minterface.RequestCallBack;
import com.smallsnailtech.smallsnail.presenter.contract.RegisteAccountActivityContract;

import java.util.List;

public class RegisteAccountActivityPresenter implements RegisteAccountActivityContract.IRegisteAccountPresenter {

    private RegisteAccountActivityContract.IRegisteAccountView mRegisteAccountView;
    private RegisteAccountActivityContract.IRegisteAccountModel mRegisteAccountModel;

    public RegisteAccountActivityPresenter(RegisteAccountActivityContract.IRegisteAccountView registeAccountView,
                                           RegisteAccountActivityContract.IRegisteAccountModel registeAccountModel) {
        mRegisteAccountView = registeAccountView;
        this.mRegisteAccountModel = registeAccountModel;
    }

    @Override
    public void verifyUserAccount(List<Param> params) {
        mRegisteAccountModel.verifyUserAccount(params, new RequestCallBack<ResponseEntity<String>>() {

            @Override
            public void onStart() {
//                mRegisteAccountView.registeStart();
            }

            @Override
            public void onSuccess(ResponseEntity<String> responseEntity) {
                int resultCode = responseEntity.getCode();
                String resultMsg = responseEntity.getMsg();
                Log.d("RegisteAccountPresenter", "verifyAccount()_resultCode=" + resultCode + " resultMsg==" + resultMsg);
                if (resultCode == 1) {
                    mRegisteAccountView.verifyUserAccountResult(true);
                } else {
                    mRegisteAccountView.verifyUserAccountResult(false);
                    if (resultCode == 26) {
//                        mRegisteAccountView.showShortToast(R.string.phoneNumber_exist);
                    } else {
//                        mRegisteAccountView.showShortToast(R.string.please_input_conrrect_phoneNumber);
                    }
                }
            }

            @Override
            public void onFailed(Throwable t) {
                mRegisteAccountView.verifyUserAccountResult(false);
            }

            @Override
            public void onCancel() {

            }
        });
    }

    @Override
    public void verifyUsername(List<Param> params) {
        mRegisteAccountModel.verifyUsername(params, new RequestCallBack<ResponseEntity<String>>() {
            @Override
            public void onStart() {
//                mRegisteAccountView.registeStart();
            }

            @Override
            public void onSuccess(ResponseEntity<String> responseEntity) {
                int resultCode = responseEntity.getCode();
                String resultMsg = responseEntity.getMsg();
                Log.d("RegisteAccountPresenter", "verifyAccount()_resultCode=" + resultCode + " resultMsg==" + resultMsg);
                if (resultCode == 1) {
                    mRegisteAccountView.verifyUsernameResult(true);
                } else {
                    mRegisteAccountView.verifyUsernameResult(false);
                    if (resultCode == 30) {
//                        mRegisteAccountView.showShortToast(R.string.username_exist);
                    } else {
//                        mRegisteAccountView.showShortToast(R.string.please_input_conrrect_username);
                    }
                }
            }

            @Override
            public void onFailed(Throwable t) {
                mRegisteAccountView.verifyUsernameResult(false);
            }

            @Override
            public void onCancel() {

            }
        });
    }

    @Override
    public void registeUserInfo(final List<Param> params) {
        mRegisteAccountModel.registeUserInfo(params, new RequestCallBack<ResponseEntity<UserInfoEntity>>() {
            @Override
            public void onStart() {
                mRegisteAccountView.registeStart();
            }

            @Override
            public void onSuccess(ResponseEntity<UserInfoEntity> responseEntity) {
                int resultCode = responseEntity.getCode();
                String resultMsg = responseEntity.getMsg();
                Log.d("RegisteAccountPresenter", "registeUserInfo()_onNext()_resultCode=" + resultCode + " resultMsg==" + resultMsg);
                if (resultCode == 1) {
                    String userAccount = "";
                    String userPassword = "";
                    for (int i = 0; i < params.size(); i++) {
                        Param param = params.get(i);
                        String key = param.getKey();
                        String value = param.getValue();
                        if ("telephoneNumber".equals(key)) {
                            userAccount = value;
                        } else if ("userPassword".equals(key)) {
                            userPassword = value;
                        }
                    }
                    mRegisteAccountView.registeSuccess(resultMsg, userAccount, userPassword);
                } else {
                    if (resultCode == 26) {
//                        mRegisteAccountView.showShortToast(R.string.phoneNumber_exist);
                    } else if (resultCode == 30) {
//                        mRegisteAccountView.showShortToast(R.string.username_exist);
                    } else {
                        mRegisteAccountView.registeFailed(resultMsg);
                    }
                }
            }

            @Override
            public void onFailed(Throwable t) {
                mRegisteAccountView.showNetError();
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
        // todo 切断引用关系
    }
}

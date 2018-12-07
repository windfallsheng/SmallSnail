package com.smallsnailtech.smallsnail.presenter.contract;

import com.smallsnailtech.smallsnail.base.BaseModel;
import com.smallsnailtech.smallsnail.base.BasePresenter;
import com.smallsnailtech.smallsnail.base.BaseView;
import com.smallsnailtech.smallsnail.entity.ResponseEntity;
import com.smallsnailtech.smallsnail.entity.UserInfoEntity;
import com.smallsnailtech.smallsnail.minterface.RequestCallBack;

/**
 * @author luzhaosheng
 * @date 2018年11月06日
 * 定义各层的接口
 */
public interface LoginActivityContract {

    interface ILoginPresenter extends BasePresenter {

        void saveLastLoginAccount(String account);

        void getLastLoginAccount();

        void login(String account, String password);

    }

    interface ILoginView extends BaseView {

        void loginStart();

        void loginSuccess(String text);

        void loginFailed(String text);

    }

    interface ILoginModel extends BaseModel {

        void login(String account, String password, RequestCallBack<ResponseEntity<UserInfoEntity>> callBack);

    }
}

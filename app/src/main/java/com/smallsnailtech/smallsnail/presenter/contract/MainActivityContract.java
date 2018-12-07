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
public interface MainActivityContract {

    interface IMainActivityPresenter extends BasePresenter {

        void saveLastLoginAccount(String account);

        void getLastLoginAccount();

        void login(String account, String password);

    }

    interface IMainActivityView extends BaseView {

        void loadDataStart();

        void loadDataSuccess(String text);

        void loadDataFailed(String text);

    }

    interface IMainActivityModel extends BaseModel {

        void login(String account, String password, RequestCallBack<ResponseEntity<UserInfoEntity>> callBack);

    }
}

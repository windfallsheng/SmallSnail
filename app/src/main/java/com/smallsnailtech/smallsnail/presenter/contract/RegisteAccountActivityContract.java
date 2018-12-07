package com.smallsnailtech.smallsnail.presenter.contract;

import com.smallsnailtech.smallsnail.base.BaseModel;
import com.smallsnailtech.smallsnail.base.BasePresenter;
import com.smallsnailtech.smallsnail.base.BaseView;
import com.smallsnailtech.smallsnail.entity.Param;
import com.smallsnailtech.smallsnail.entity.ResponseEntity;
import com.smallsnailtech.smallsnail.entity.UserInfoEntity;
import com.smallsnailtech.smallsnail.minterface.RequestCallBack;
import java.util.List;

public interface RegisteAccountActivityContract {

    interface IRegisteAccountPresenter extends BasePresenter {

        void verifyUserAccount(List<Param> params);

        void verifyUsername(List<Param> params);

        void registeUserInfo(List<Param> params);

    }

    interface IRegisteAccountView extends BaseView {

        void verifyUserAccountResult(boolean pass);

        void verifyUsernameResult(boolean pass);

        void registeStart();

        void registeSuccess(String text, String userAccount, String password);

        void registeFailed(String text);

    }

    interface IRegisteAccountModel extends BaseModel {

        void verifyUserAccount(List<Param> params, RequestCallBack<ResponseEntity<String>> callBack);

        void verifyUsername(List<Param> params, RequestCallBack<ResponseEntity<String>> callBack);

        void registeUserInfo(List<Param> params, RequestCallBack<ResponseEntity<UserInfoEntity>> callBack);

    }
}

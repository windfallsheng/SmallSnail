package com.smallsnailtech.smallsnail.presenter.model;

import android.util.Log;
import com.smallsnailtech.smallsnail.base.MobileAPIService;
import com.smallsnailtech.smallsnail.command.Constants;
import com.smallsnailtech.smallsnail.entity.ResponseEntity;
import com.smallsnailtech.smallsnail.entity.UserInfoEntity;
import com.smallsnailtech.smallsnail.minterface.RequestCallBack;
import com.smallsnailtech.smallsnail.presenter.contract.LoginActivityContract;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author luzhaosheng
 * @date 2018年11月06日
 * 主要职责:增强数据操作功能的内聚性,
 * 假如登录操作包含了若干个网络请求去完成，就可以在本类下的{@link LoginActivityModel#login(String, String, RequestCallBack)}
 * 方法里完成，将结果返回给上一次。
 * todo 可以结合切面编程的思想，实现对网络请求的返回结果进行逻辑处理，包括各类异常的不同处理，及返回数据不同code码的处理逻辑
 */
public class LoginActivityModel implements LoginActivityContract.ILoginModel {

    @Override
    public void login(String account, String password, final RequestCallBack<ResponseEntity<UserInfoEntity>> callBack) {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Constants.MOBILE_API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MobileAPIService mobileAPIService = retrofit.create(MobileAPIService.class);
        mobileAPIService.login(account, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseEntity<UserInfoEntity>>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("LoginActivityModel", "onSubscribe_login");
                        if (callBack != null) {
                            callBack.onStart();
                        }
                    }

                    @Override
                    public void onNext(ResponseEntity<UserInfoEntity> responseEntity) {
                        Log.d("LoginActivityModel", "onNext_login" + responseEntity);
                        if (callBack != null) {
                            callBack.onSuccess(responseEntity);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d("LoginActivityModel", "onError_login" + t.toString());
                        if (callBack != null) {
                            callBack.onFailed(t);
                        }
//                            if (t instanceof HttpException) { //获取对应statusCode和Message
//                                HttpException exception = (HttpException) t;
//                                String message = exception.response().message();
//                                int code = exception.response().code();
//                            } else if (t instanceof SSLHandshakeException) {
//                                //接下来就是各种异常类型判断
//                                //...
//                                }else if(e instanceof ...){
//
//                            }... ... }
//
//                                mLoginView.showError();
//                                Log.i("LoginActivityPresenter", "onError" + t.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.i("LoginActivityModel", "onComplete");
                    }
                });
    }

    @Override
    public void cancelTasks() {

    }
}

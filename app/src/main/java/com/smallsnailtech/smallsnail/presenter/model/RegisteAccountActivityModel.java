package com.smallsnailtech.smallsnail.presenter.model;

import android.util.Log;

import com.smallsnailtech.smallsnail.base.MobileAPIService;
import com.smallsnailtech.smallsnail.command.Constants;
import com.smallsnailtech.smallsnail.entity.Param;
import com.smallsnailtech.smallsnail.entity.ResponseEntity;
import com.smallsnailtech.smallsnail.entity.UserInfoEntity;
import com.smallsnailtech.smallsnail.minterface.RequestCallBack;
import com.smallsnailtech.smallsnail.presenter.contract.RegisteAccountActivityContract;
import com.smallsnailtech.smallsnail.util.RetrofitRequestBodyUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisteAccountActivityModel implements RegisteAccountActivityContract.IRegisteAccountModel {

    @Override
    public void verifyUserAccount(List<Param> params, final RequestCallBack<ResponseEntity<String>> callBack) {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Constants.MOBILE_API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MobileAPIService mobileAPIService = retrofit.create(MobileAPIService.class);
        Map paramsMap = new HashMap();
        for (int i = 0; i < params.size(); i++) {
            Param param = params.get(i);
            String key = param.getKey();
            String value = param.getValue();
            paramsMap.put(key, value);
        }
        mobileAPIService.verifyUserAccount(paramsMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseEntity<String>>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        callBack.onStart();
                        Log.d("RegisteAccountActivityModel", "onSubscribe");
                    }

                    @Override
                    public void onNext(ResponseEntity<String> responseEntity) {
                        callBack.onSuccess(responseEntity);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d("RegisteAccountActivityModel", "onError" + t.toString());
                        callBack.onFailed(t);
                    }

                    @Override
                    public void onComplete() {
                        Log.i("RegisteAccountActivityModel", "onComplete");
                    }
                });
    }

    @Override
    public void verifyUsername(List<Param> params, final RequestCallBack<ResponseEntity<String>> callBack) {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Constants.MOBILE_API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MobileAPIService mobileAPIService = retrofit.create(MobileAPIService.class);
        Map paramsMap = new HashMap();
        for (int i = 0; i < params.size(); i++) {
            Param param = params.get(i);
            String key = param.getKey();
            String value = param.getValue();
            paramsMap.put(key, value);
        }
        mobileAPIService.verifyUsername(paramsMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseEntity<String>>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        callBack.onStart();
                        Log.d("RegisteAccountActivityModel", "onSubscribe");
                    }

                    @Override
                    public void onNext(ResponseEntity<String> responseEntity) {
                        callBack.onSuccess(responseEntity);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d("RegisteAccountActivityModel", "onError" + t.toString());
                        callBack.onFailed(t);
                    }

                    @Override
                    public void onComplete() {
                        Log.i("RegisteAccountActivityModel", "onComplete");
                    }
                });
    }

    @Override
    public void registeUserInfo(List<Param> params,
                                final RequestCallBack<ResponseEntity<UserInfoEntity>> callBack) {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Constants.MOBILE_API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MobileAPIService mobileAPIService = retrofit.create(MobileAPIService.class);
        Map paramsMap = new HashMap();
        for (int i = 0; i < params.size(); i++) {
            Param param = params.get(i);
            String key = param.getKey();
            String value = param.getValue();
            if (!"displayPhoto".equals(key)) {
                paramsMap.put(key, value);
            } else {
                if (value != null && !"".equals(value)) {
                    File headIconFile = new File(value);
                    paramsMap.put("displayPhoto", headIconFile);
                }
            }
        }
        Map<String, RequestBody> requestBodyMap = RetrofitRequestBodyUtils.getRequestBodyMap(paramsMap);
        mobileAPIService.registeUserInfo(requestBodyMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseEntity<UserInfoEntity>>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        callBack.onStart();
                        Log.d("RegisteAccountActivityModel", "onSubscribe");
                    }

                    @Override
                    public void onNext(ResponseEntity<UserInfoEntity> responseEntity) {
                        callBack.onSuccess(responseEntity);
                    }

                    @Override
                    public void onError(Throwable t) {
                        callBack.onFailed(t);
                        Log.d("RegisteAccountActivityModel", "onError" + t.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.i("RegisteAccountActivityModel", "onComplete");
                    }
                });
    }

    @Override
    public void cancelTasks() {
// todo 切断引用关系
    }

}

package com.smallsnailtech.smallsnail.base;

import com.smallsnailtech.smallsnail.entity.ResponseEntity;
import com.smallsnailtech.smallsnail.entity.UserInfoEntity;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

/**
 * @author luzhaosheng
 * @date 2018年11月06日
 */
public interface MobileAPIService {

    @FormUrlEncoded
    @POST("mobileuser/login")
    Observable<ResponseEntity<UserInfoEntity>> login(@Field("telephoneNumber") String account,
                                                     @Field("userPassword") String password);

    @FormUrlEncoded
    @POST("mobileuser/checkmobile")
    Observable<ResponseEntity<String>> verifyUserAccount(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST("mobileuser/checkuser")
    Observable<ResponseEntity<String>> verifyUsername(@FieldMap Map<String, String> params);

    @Multipart
    @POST("mobileuser/register")
    Observable<ResponseEntity<UserInfoEntity>> registeUserInfo(@PartMap Map<String, RequestBody> multipartMap);

    @Multipart
    @POST("mobileuser/modifyUserInfo")
    Observable<ResponseEntity<UserInfoEntity>> updateUserInfo(@PartMap Map<String, RequestBody> multipartMap);

}

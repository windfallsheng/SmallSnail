package com.smallsnailtech.smallsnail.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @Author: luzhaosheng
 * @Description: 网络请求的返回值实体类, 使用新版本Gson的jar包，修改注解 @SerializedName(value = "val",
 * alternate = {"alt1", "alt2"})
 * @Version:
 */
public class ResponseEntity<T> implements Serializable {

    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 返回码
     */
    @SerializedName(value = "code", alternate = {"rt", "result"})
    private int code;
    /**
     * 返回信息
     */
    @SerializedName(value = "msg", alternate = {"message", "content"})
    private String msg;
    /**
     * 返回数据
     */
    @SerializedName("result")
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseEntity{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}

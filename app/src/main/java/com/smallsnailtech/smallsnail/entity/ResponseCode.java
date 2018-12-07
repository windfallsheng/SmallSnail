package com.smallsnailtech.smallsnail.entity;

public enum ResponseCode {
    SUCCESS(200,"查询成功"),
    NODATA(201,"查询成功无记录"),
    FEAILED(202,"查询失败"),
    ACCOUNT_ERROR(500, "账户不存在或被禁用"),
    API_NOT_EXISTS(400, "请求的接口不存在"),
    API_NOT_PER(401, "没有该接口的访问权限"),
    PARAMS_ERROR(500, "参数为空或格式错误"),
    SIGN_ERROR(501, "数据签名错误"),
    AMOUNT_NOT_QUERY(502, "余额不够，无法进行查询"),
    API_DISABLE(503, "查询权限已被限制"),
    UNKNOWN_IP(504, "非法IP请求"),
    SYSTEM_ERROR(505, "系统异常");

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

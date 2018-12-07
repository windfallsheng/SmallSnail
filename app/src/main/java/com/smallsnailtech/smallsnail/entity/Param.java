package com.smallsnailtech.smallsnail.entity;

/**
 * @author luzhaosheng 键值对，可以用于请求参数的
 * @date 2018年11月06日
 */
public class Param {

    private String key;
    private String value;

    public Param() {
    }

    public Param(String key, String value) {
        super();
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Param [key=" + key + ", value=" + value + "]";
    }

}

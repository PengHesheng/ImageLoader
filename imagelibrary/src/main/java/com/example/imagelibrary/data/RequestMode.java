package com.example.imagelibrary.data;

/**
 * 弃用
 * @author 14512 on 2018/10/12
 */
@Deprecated
public class RequestMode {
    private Object mValue;
    private Class mCls;

    public RequestMode(Class cls, Object obj) {
        this.mCls = cls;
        this.mValue = obj;
    }

    public Class getCls() {
        return mCls;
    }

    public Object getValue() {
        return mValue;
    }
}

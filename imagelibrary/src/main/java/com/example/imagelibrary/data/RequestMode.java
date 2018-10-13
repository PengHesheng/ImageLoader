package com.example.imagelibrary.data;

/**
 * @author 14512 on 2018/10/12
 */
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

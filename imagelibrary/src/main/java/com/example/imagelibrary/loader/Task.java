package com.example.imagelibrary.loader;

import android.os.Handler;

import com.example.imagelibrary.config.LoaderConfig;

/**
 * 每一个请求视为一个任务，包装了所有需要的信息
 * @author 14512 on 2018/10/10
 */
public class Task {
    private Handler mHandler;
    private LoaderConfig mConfig;
    private LoaderResult mResult;

    public Task(Handler handler, LoaderConfig config, LoaderResult result) {
        this.mHandler = handler;
        this.mConfig = config;
        this.mResult = result;
    }

    public Handler getHandler() {
        return mHandler;
    }

    public LoaderConfig getConfig() {
        return mConfig;
    }

    public LoaderResult getLoaderResult() {
        return mResult;
    }
}

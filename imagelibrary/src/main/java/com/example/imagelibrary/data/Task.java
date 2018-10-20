package com.example.imagelibrary.data;

import android.os.Handler;

import com.example.imagelibrary.config.LoaderConfiguration;

/**
 * 每一个请求视为一个任务，包装了所有需要的信息
 * @author 14512 on 2018/10/10
 */
public class Task {
    private Handler mHandler;
    private LoaderResult mResult;
    private LoaderConfiguration mConfiguration;

    public Task(Handler handler, LoaderConfiguration configuration, LoaderResult result) {
        this.mHandler = handler;
        this.mConfiguration = configuration;
        this.mResult = result;
    }

    public Handler getHandler() {
        return mHandler;
    }

    public LoaderResult getLoaderResult() {
        return mResult;
    }

    public LoaderConfiguration getConfiguration() {
        return mConfiguration;
    }

}

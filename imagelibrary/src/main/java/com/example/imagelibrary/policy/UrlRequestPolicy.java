package com.example.imagelibrary.policy;

import android.util.Log;

import com.example.imagelibrary.data.Task;
import com.example.imagelibrary.manager.HttpManager;

/**
 * @author 14512 on 2018/10/20
 */
public final class UrlRequestPolicy implements RequestPolicy {
    private static final String TAG = "UrlRequestPolicy";

    @Override
    public void request(Task task) {
        Log.d(TAG, "load from http");
        HttpManager.INSTANCE.download(task);
    }
}

package com.example.imagelibrary.policy;

import android.util.Log;

import com.example.imagelibrary.data.LoaderResult;
import com.example.imagelibrary.data.Task;

/**
 * @author 14512 on 2018/10/20
 */
public final class ResRequestPolicy implements RequestPolicy {
    private static final String TAG = "ResRequestPolicy";

    @Override
    public void request(Task task) {
        Log.d(TAG, "load from resource");
        task.getHandler().obtainMessage(LoaderResult.RESULT_SUCCESS_FROM_RESOURCE,
                task.getLoaderResult()).sendToTarget();
    }
}

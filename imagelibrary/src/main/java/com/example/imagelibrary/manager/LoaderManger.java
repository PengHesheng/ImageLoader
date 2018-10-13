package com.example.imagelibrary.manager;

import android.net.Uri;
import android.util.Log;

import com.example.imagelibrary.data.LoaderResult;
import com.example.imagelibrary.data.RequestMode;
import com.example.imagelibrary.data.Task;

/**
 * @author 14512 on 2018/10/10
 */
final class LoaderManger {
    private static final String TAG = "LoaderManager";

    static LoaderManger get() {
        LoaderManger instance = (LoaderManger) SingletonManager.getService(
                SingletonManager.LOADER_MANAGER);
        if (instance == null) {
            instance = new LoaderManger();
            SingletonManager.registerService(SingletonManager.LOADER_MANAGER, instance);
        }
        return instance;
    }

    private LoaderManger() {
    }

    void load(Task task) {
        LoaderResult result = task.getLoaderResult();
        RequestMode requestMode = result.getRequestMode();
        //根据加载方式的不同进行不同的加载
        if (requestMode.getCls() == String.class) {
            Log.d(TAG, "load from http");
            HttpManager.INSTANCE.download(task);
        } else if (requestMode.getCls() == Uri.class) {

        } else if (requestMode.getCls() == Integer.class) {
            Log.d(TAG, "load from resource");
            task.getHandler().obtainMessage(LoaderResult.RESULT_SUCCESS_FROM_RESOURCE, result)
                    .sendToTarget();
        }
    }



}

package com.example.imagelibrary.manager;

import com.example.imagelibrary.data.Task;

/**
 * @author 14512 on 2018/10/10
 */
@Deprecated
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
    }



}

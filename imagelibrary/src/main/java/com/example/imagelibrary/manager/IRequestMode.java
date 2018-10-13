package com.example.imagelibrary.manager;

import android.net.Uri;

/**
 * @author 14512 on 2018/10/13
 */
public interface IRequestMode<T> {

    /**
     * @param url
     * @return
     */
    T load(String url);

    /**
     * @param uri
     * @return
     */
    T load(Uri uri);

    /**
     * @param resId
     * @return
     */
    T load(int resId);
}

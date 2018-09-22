package com.example.imagelibrary;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * @author 14512 on 2018/9/22
 */
public interface LoadListener {
    /**
     * 完成时调用
     * @param imageView
     * @param bitmap
     * @param uri
     */
    void onComplete(ImageView imageView, Bitmap bitmap, String uri);

    /**
     * 失败时
     */
    void onFailed();
}

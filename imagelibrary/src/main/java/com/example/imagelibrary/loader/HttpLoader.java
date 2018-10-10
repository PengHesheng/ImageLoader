package com.example.imagelibrary.loader;

import android.graphics.Bitmap;

/**
 * @author 14512 on 2018/10/10
 */
public interface HttpLoader {
    /**
     * 下载通用接口
     * @param url
     * @return
     */
    Bitmap download(String url);
}

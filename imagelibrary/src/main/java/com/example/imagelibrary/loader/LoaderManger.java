package com.example.imagelibrary.loader;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.example.imagelibrary.config.LoaderConfig;

/**
 * @author 14512 on 2018/10/10
 */
public final class LoaderManger {
    private static final String TAG = "LoaderManager";
    private static LoaderManger INSTANCE;
    private ResultHandler mHandler;
    private LoaderConfig mConfig;
    private LoaderResult mResult = new LoaderResult();

    public static LoaderManger get(LoaderConfig config) {
        if (INSTANCE == null) {
            synchronized (LoaderManger.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LoaderManger(config);
                }
            }
        }
        return INSTANCE;
    }

    private LoaderManger(LoaderConfig config) {
        this.mHandler = new ResultHandler(Looper.getMainLooper());
        this.mConfig = config;
    }

    public LoaderManger load(String url) {
        mResult.mUrl = url;
        return this;
    }


    public void into(ImageView imageView) {
        mResult.mImageView = imageView;
        HttpManager.INSTANCE.download(new Task(mHandler, mConfig, mResult));
    }


    private static class ResultHandler extends Handler {

        ResultHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LoaderResult.RESULT_SUCCESS_FROM_CACHE:
                    handleResult((LoaderResult) msg.obj, false);
                    break;
                case LoaderResult.RESULT_SUCCESS_FROM_HTTP:
                    Log.d(TAG, "handleMessage http");
                    handleResult((LoaderResult) msg.obj, true);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }

    }

    private static void handleResult(LoaderResult result, boolean isCache) {
        Bitmap bitmap = result.mBitmap;
        if (bitmap == null) {
            Log.d(TAG, "bitmap is null!");
            return;
        }
        if (isCache) {


        }
        ImageView imageView = result.mImageView;
        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

}

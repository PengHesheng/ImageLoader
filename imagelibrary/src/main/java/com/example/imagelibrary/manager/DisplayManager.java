package com.example.imagelibrary.manager;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.example.imagelibrary.config.LoaderConfiguration;
import com.example.imagelibrary.data.LoaderResult;
import com.example.imagelibrary.data.Task;

/**
 * @author 14512 on 2018/10/12
 */
public class DisplayManager {
    private static final String TAG = "DisplayManager";
    private LoaderConfiguration mConfiguration;
    private ResultHandler mHandler;

    private DisplayManager() {
    }

    static DisplayManager get() {
        DisplayManager displayManager = (DisplayManager) SingletonManager.getService(
                SingletonManager.DISPLAY_MANAGER);
        if (displayManager == null) {
            displayManager = new DisplayManager();
            SingletonManager.registerService(SingletonManager.DISPLAY_MANAGER, displayManager);
        }
        return displayManager;
    }

    DisplayManager init(LoaderConfiguration configuration) {
        this.mConfiguration = configuration;
        this.mHandler = new ResultHandler(mConfiguration.getContext().getMainLooper());
        return this;
    }

    public void into(ImageView imageView) {
        LoaderResult result = mConfiguration.getResult();
        Log.d(TAG, result.getRequestMode().getValue().toString());
        result.mImageView = imageView;
        Task task = new Task(mHandler, mConfiguration, mConfiguration.getResult());
        LoaderManger.get().load(task);
    }

    private static class ResultHandler extends Handler {

        ResultHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LoaderResult.RESULT_SUCCESS_FROM_HTTP:
                    Log.d(TAG, "handleMessage http");
                    handleResultFromHttp((LoaderResult) msg.obj);
                    break;
                case LoaderResult.RESULT_SUCCESS_FROM_RESOURCE:
                    Log.d(TAG, "handleMessage resource");
                    handleResultFromResource((LoaderResult) msg.obj);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    private static void handleResultFromResource(LoaderResult result) {
        ImageView imageView = result.mImageView;
        if (imageView != null) {
            imageView.setImageResource((Integer) result.getRequestMode().getValue());
        } else {
            throwNullException();
        }
    }


    private static void handleResultFromHttp(LoaderResult result) {
        Bitmap bitmap = result.mBitmap;
        if (bitmap == null) {
            Log.d(TAG, "bitmap is null!");
            return;
        }
        ImageView imageView = result.mImageView;
        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            throwNullException();
        }
    }


    private static void throwNullException() {
        throw new NullPointerException("ImageView is null");
    }
}

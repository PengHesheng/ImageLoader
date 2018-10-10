package com.example.imagelibrary.loader;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.example.imagelibrary.cache.DefaultCache;
import com.example.imagelibrary.cache.ImageCache;

/**
 * @author 14512 on 2018/10/10
 */
public final class LoaderManger {
    private static final String TAG = "LoaderManager";
    private static LoaderManger INSTANCE;
    private Context mContext;
    private ResultHandler mHandler;
    private ImageCache mCache;
    private HttpLoader mDefaultHttpLoader;
    private LoaderResult mResult = new LoaderResult();

    public static LoaderManger get(Context context) {
        if (INSTANCE == null) {
            synchronized (LoaderManger.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LoaderManger(context);
                }
            }
        }
        return INSTANCE;
    }

    private LoaderManger(Context context) {
        this.mContext = context;
        mHandler = new ResultHandler(context.getMainLooper());
        mCache = new DefaultCache(context);
        mDefaultHttpLoader = new HttpURLConnectionLoader();
    }

    public LoaderManger load(String url) {
        mResult.mUrl = url;
        return this;
    }

    public LoaderManger setImageCache(ImageCache imageCache) {
        mCache = imageCache;
        return this;

    }

    public LoaderManger setPlaceholder(int resId) {
        return this;
    }

    public LoaderManger setLoader(HttpLoader httpLoader) {
        mDefaultHttpLoader = httpLoader;
        return this;
    }

    public void into(ImageView imageView) {
        mResult.mImageView = imageView;
        HttpManager.INSTANCE.download(new Task(mContext, mCache, mDefaultHttpLoader, mResult));
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

# ImageLoader

一个小巧的图片加载图，主要用途是学习（毕竟主流的图片加载库很强大）

## 使用说明  

`ImageLoader.with(Context context).load(String url).into(ImageView imageView);`  
有点类似于Glide的链式加载（就是模仿的这个链式加载），大部分模仿Glide最后的效果

同样可以通过`ImageLoader.with(Context context).load(String url).setCache(ImageCache imageCache).setHttpLoader(HttpLoader httpLoader).into(ImageView imageView);`这样来进行一些加载的配置，例如缓存机制、网络加载方式等等。

## 类说明

- ImageLoader  
    最外层的调用，通过ImageLoader来进行加载，整个链式的开始

- SingletonManager  
    采用容器进行单例管理

- RequestManager  
    请求管理类，统一方法，用来进行图片加载的自定义配置，如缓存策略、网络请求方式等

- DisplayManager  
    图片展示管理类，在这里将之前对图片的配置进行设置（圆角、模糊）等，但目前尚未加入。同时这也是最后回调展示图片的地方

- LoaderManager  
    加载管理类，不同的请求参数，进行不同的加载。例如String url的加载、resource文件的加载、本地的加载等

- HttpManager  
    网络请求管理类，不同的网络请求方式管理。先从缓存中获取，没有再进行网络请求

- HttpUrlConnectionLoader
    采用原生的HttpURLConnection，单纯的进行网络请求处理

- LoaderConfiguration  
    参数配置类，所有的设置项

- LoaderResult  
    对最后请求的结果的封装类

- ImageCache  
    缓存接口，可以是内存缓存，可以是磁盘缓存（采用DiskLruCache，也可以不采用），默认双缓存  
    自定义缓存时需实现该接口

- HttpLoader  
    网络请求接口，自定义网络请求需实现该接口

## 注意

仅提供参考，后续会进一步完善

## 特别鸣谢

- Android开发艺术探索  
- Android源码设计模式
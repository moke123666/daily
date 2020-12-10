package club.mokeblog.diary.presenter.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import club.mokeblog.diary.model.domain.Picture;
import club.mokeblog.diary.presenter.IPicturePresenter;
import club.mokeblog.diary.utils.AppUtil;
import club.mokeblog.diary.view.IPictureCallback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PicturePresenterImpl implements IPicturePresenter {
    private final String TAG = "PicturePresenterImpl";
    private IPictureCallback mCallback = null;
    private static Handler mHandler;
    private List<Bitmap> mList = new ArrayList<>();
    private Gson gson = new Gson();
    private GetDataDoing get = GetDataDoing.NONE;
    private Context context;
    private Thread mThread;

    private enum GetDataDoing {
        NONE, REFRESH, LOADMORE
    }

    @SuppressLint("HandlerLeak")
    private void initHandler() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        if (mList.size() < 6) {
                            getPicture();
                        } else {
                            if (get == GetDataDoing.REFRESH) {
                                mCallback.onRefreshData(mList);
                            } else {
                                if (get == GetDataDoing.LOADMORE) {
                                    mCallback.onLoadMoreData(mList);
                                }
                            }
                        }
                        break;
                    case 2:
                        if (get == GetDataDoing.REFRESH) {
                            mCallback.onErrorRefresh();
                        } else {
                            if (get == GetDataDoing.LOADMORE) {
                                mCallback.onErrorLoadMore();
                            }
                        }
                        break;
                    case 3:
                        //mCallback.onLoadMoreData(mList);
                        break;
                    default:
                }
            }
        };
    }

//    private void getPicture() {
//        mList.clear();
//
//        if (mCallback != null) {
//            if (get == GetDataDoing.REFRESH) {
//                mCallback.onLoading();
//            }
//        }
//        initHandler();
//        final String url = "https://bing.ioliu.cn/v1/rand?type=json";
//        new Thread(() -> {
//            try {
//                OkHttpClient client = new OkHttpClient();
//                for (int i = 0; i < 10; ++i) {
//                    Request request = new Request.Builder().url(url).header("Proxy-Connection", "keep-alive")
//                            .header("Cache-Control", "max-age=0")
//                            .header("Upgrade-Insecure-Requests", "1")
//                            .header("User-Agent", "Mozilla/5.0 (Linux; U; Android 8.1.0; en-US; 16th Build/OPM1.171019.026) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.108 Quark/2.5.0.937 Mobile Safari/537.36")
//                            .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
//                            .header("Accept-Encoding", "gzip, deflate")
//                            .header("Accept-Language", "en-US")
//                            .header("X-UCBrowser-UA", "dv(HTC 802t);pr(UCBrowser/10.2.0.535);ov(Android 5.0.2);ss(360*640);pi(1080*1920);bt(UC);pm(1);bv(1);nm(0);im(0);sr(0);nt(2);")
//                            .build();
//                    Response response = client.newCall(request).execute();
//                    int responseCode = response.code();
//                    String responseData = response.body().string();
//                    if (responseCode == 200) {
//                        Picture picture = gson.fromJson(responseData, Picture.class);
//                        mList.add(picture.getData().getUrl());
//                    }
//                }
//
//                if (mList.isEmpty()) {
//                    mHandler.sendEmptyMessage(2);
//                } else {
//                    mHandler.sendEmptyMessage(1);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();
//    }


    private void getPicture() {
        if (mCallback != null) {
            if (get == GetDataDoing.REFRESH) {
                mCallback.onLoading();
            }
        }
        initHandler();
        mThread = new NetThread();
        mThread.start();
//        new Thread(() -> {
//            try {
//                OkHttpClient client = new OkHttpClient();
//                for (int i = 0; i < 8; ++i) {
//                    Request request = new Request.Builder().url(url).header("Proxy-Connection", "keep-alive")
//                            .header("Cache-Control", "max-age=0")
//                            .header("Upgrade-Insecure-Requests", "1")
//                            .header("User-Agent", "Mozilla/5.0 (Linux; U; Android 8.1.0; en-US; 16th Build/OPM1.171019.026) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.108 Quark/2.5.0.937 Mobile Safari/537.36")
//                            .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
//                            .header("Accept-Encoding", "gzip, deflate")
//                            .header("Accept-Language", "en-US")
//                            .header("X-UCBrowser-UA", "dv(HTC 802t);pr(UCBrowser/10.2.0.535);ov(Android 5.0.2);ss(360*640);pi(1080*1920);bt(UC);pm(1);bv(1);nm(0);im(0);sr(0);nt(2);")
//                            .build();
//                    Response response = client.newCall(request).execute();
//                    int responseCode = response.code();
//                    String responseData = response.body().string();
//                    if (responseCode == 200) {
//                        Picture picture = gson.fromJson(responseData, Picture.class);
//                        Glide.with(context).asBitmap().load(picture.getData().getUrl()).into(new SimpleTarget<Bitmap>() {
//                            @Override
//                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                                mList.add(resource);
//                            }
//                        });
//                    }
//                }
//
//                if (mList.isEmpty()) {
//                    mHandler.sendEmptyMessage(2);
//                } else {
//                    mHandler.sendEmptyMessage(1);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();
    }

    public class NetThread extends Thread {
        @Override
        public void run() {
            try {
                Random random = new Random();
                int skip = random.nextInt(5000);
                while (skip >= 4980) {
                    skip = random.nextInt(5000);
                }

                StringBuilder url = new StringBuilder("http://service.picasso.adesk.com/v1/vertical/vertical");
                url.append("?limit=10");
                url.append("&skip=");
                url.append(skip);
                url.append("&adult=false");
                url.append("&first=1");
                url.append("&order=hot");

//                OkHttpClient client = new OkHttpClient().newBuilder()
//                        .callTimeout(2, TimeUnit.SECONDS)
//                        .connectTimeout(2, TimeUnit.MILLISECONDS)
//                        .readTimeout(2, TimeUnit.MILLISECONDS)
//                        .writeTimeout(2, TimeUnit.MILLISECONDS)
//                        .build();

                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder().url(url.toString()).header("Proxy-Connection", "keep-alive")
                        .header("Cache-Control", "max-age=0")
                        .header("Upgrade-Insecure-Requests", "1")
                        .header("User-Agent", String.format("%s/%s (Linux; Android %s; %s Build/%s)", AppUtil.getMyAppName(), AppUtil.getMyAppVersion(), AppUtil.getBuildVersionRelease(), AppUtil.getBuildManufacturer(), AppUtil.getBuildId()))
                        //.header("User-Agent", "Mozilla/5.0 (Linux; U; Android 8.1.0; en-US; 16th Build/OPM1.171019.026) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.108 Quark/2.5.0.937 Mobile Safari/537.36")
                        .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                        //.header("Accept-Encoding", "gzip, deflate")
                        .header("Accept-Language", "en-US")
                        .build();


                Response response = client.newCall(request).execute();
                int responseCode = response.code();
                String responseData = response.body().string();
                if (responseCode == 200) {
                    Picture picture = gson.fromJson(responseData, Picture.class);

                    for (int j = 0; j < 10; ++j) {
                        //isWait = true;
                        Log.d(TAG, "run12313: " + picture.getRes().getVertical().get(j).getImg());
//                        SimpleTarget<Bitmap> simpleTarget = new SimpleTarget<Bitmap>() {
//                            @Override
//                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                                mList.add(resource);
//                            }
//                        };
//                        Glide.with(context).asBitmap().load(picture.getRes().getVertical().get(j).getImg()).into(simpleTarget);

                        FutureTarget<Bitmap> futureBitmap = Glide.with(context)
                                .asBitmap()
                                .load(picture.getRes().getVertical().get(j).getImg())
                                .submit();
                        Bitmap myBitmap = futureBitmap.get();
                        mList.add(myBitmap);
                    }

                }


                if (mList.isEmpty()) {
                    mHandler.sendEmptyMessage(2);
                } else {
                    mHandler.sendEmptyMessage(1);
                }
            } catch (Exception e) {
                mHandler.sendEmptyMessage(2);
                e.printStackTrace();
            }
        }
    }


    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void registerViewCallback(IPictureCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public void unRegisterViewCallback(IPictureCallback callback) {
        this.mCallback = null;
    }

    public void refreshData() {
        mList.clear();

//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                if (mList.size() < 3) {
//                    mThread.interrupt();
//                    mHandler.sendEmptyMessage(2);
//                }
//            }
//        }, 4000);

        this.get = GetDataDoing.REFRESH;
        getPicture();
    }

    public void loadMoreData() {
        mList.clear();

        this.get = GetDataDoing.LOADMORE;
        getPicture();
    }

}

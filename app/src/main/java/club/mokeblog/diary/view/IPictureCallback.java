package club.mokeblog.diary.view;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.List;

import club.mokeblog.diary.base.IBaseCallback;

public interface IPictureCallback extends IBaseCallback, Serializable {

    /**
     *数据刷新
     */
    void onRefreshData(List<Bitmap> mData);

    /**
     * 数据加载更多
     * @param mData
     */
    void onLoadMoreData(List<Bitmap> mData);
    
    void onErrorRefresh();
    
    void onErrorLoadMore();
}

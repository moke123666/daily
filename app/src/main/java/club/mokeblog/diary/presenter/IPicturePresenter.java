package club.mokeblog.diary.presenter;

import club.mokeblog.diary.base.IBasePresenter;
import club.mokeblog.diary.view.IPictureCallback;

public interface IPicturePresenter extends IBasePresenter<IPictureCallback> {

    /**
     *刷新图片
     */
    void refreshData();

    /**
     * 加载更多图片
     */
    void loadMoreData();
}

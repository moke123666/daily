package club.mokeblog.diary.view;


/**
 * viewPage销毁时回调
 */
public interface IPicturePageCallback {
    void onLoadMoreData();
    void onPageChange(int position);
}

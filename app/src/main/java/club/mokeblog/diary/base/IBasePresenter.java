package club.mokeblog.diary.base;

public interface IBasePresenter<T> {

    /**
     * 注册UI通知
     * @param callback
     */
    void registerViewCallback(T callback);

    /**
     *取消UI通知
     * @param callback
     */
    void unRegisterViewCallback(T callback);
}

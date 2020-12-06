package club.mokeblog.diary.utils;

import club.mokeblog.diary.presenter.impl.PicturePresenterImpl;

public class PresenterManager {
    public PresenterManager()
    {
        mPicturePresenter = new PicturePresenterImpl();
    }
    
    private static final PresenterManager outInstance = new PresenterManager();
    private final PicturePresenterImpl mPicturePresenter;
    
    public static PresenterManager getOutInstance() {
        return outInstance;
    }

    public PicturePresenterImpl getPicturePresenter() {
        return mPicturePresenter;
    }
}

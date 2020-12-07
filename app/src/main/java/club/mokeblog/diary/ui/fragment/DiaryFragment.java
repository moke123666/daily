package club.mokeblog.diary.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import club.mokeblog.diary.R;
import club.mokeblog.diary.base.BaseFragment;

public class DiaryFragment extends BaseFragment {
    
    @Override
    protected int getRootViewId() {
        return R.layout.fragment_diary;
    }

    @Override
    protected View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_diary, container, false);
    }
}

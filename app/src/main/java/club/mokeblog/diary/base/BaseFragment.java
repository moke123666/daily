package club.mokeblog.diary.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import club.mokeblog.diary.R;


public abstract class BaseFragment extends Fragment {

    private State currentState = State.NONE;
    private View mLoadingView;
    private View mErrorView;
    private View mSuccessView;

    private FrameLayout mFrameLayout;
    private Unbinder mBind;

    public enum State {
        NONE, LOADING, EMPTY, SUCCESS, ERROR
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = loadRootView(inflater, container);
        mFrameLayout = view.findViewById(R.id.base_container);
        loadStateView(inflater, container);
        mBind = ButterKnife.bind(this, view);
        initView(view);
        initListener();
        initPresenter();
        loadData();
        return view;
    }

    private void loadStateView(LayoutInflater inflater, ViewGroup container) {
        mLoadingView = loadLoadingView(inflater, container);
        mFrameLayout.addView(mLoadingView);

        mErrorView = loadErrorView(inflater, container);
        mFrameLayout.addView(mErrorView);

        mSuccessView = loadSuccessView(inflater, container);
        mFrameLayout.addView(mSuccessView);

        setUpState(State.NONE);
    }

    protected void setUpState(State state) {
        this.currentState = state;
        
        mSuccessView.setVisibility(currentState == State.SUCCESS ? View.VISIBLE : View.GONE);
        mLoadingView.setVisibility(currentState == State.LOADING ? View.VISIBLE : View.GONE);
        mErrorView.setVisibility(currentState == State.ERROR ? View.VISIBLE : View.GONE);
    }

    private View loadSuccessView(LayoutInflater inflater, ViewGroup container) {
        int resId = getRootViewId();
        return inflater.inflate(resId, container, false);
    }

    protected abstract int getRootViewId();

    protected View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.base_fragment_layout, container, false);
    }
    
    private View loadErrorView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_error, container, false);
    }

    private View loadLoadingView(LayoutInflater inflater, ViewGroup container) {
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.loading);
        View view = inflater.inflate(R.layout.fragment_loading, container, false);
        view.findViewById(R.id.loading_image).startAnimation(animation);
        return  view;
    }


    protected void loadData() {
    }

    protected void initPresenter() {
    }

    protected void initView(View rootView) {

    }

    protected void initListener() {

    }

}

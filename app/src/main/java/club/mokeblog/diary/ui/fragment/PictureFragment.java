package club.mokeblog.diary.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import club.mokeblog.diary.R;
import club.mokeblog.diary.base.BaseFragment;
import club.mokeblog.diary.presenter.impl.PicturePresenterImpl;
import club.mokeblog.diary.ui.adapter.PictureContentAdapter;
import club.mokeblog.diary.utils.PresenterManager;
import club.mokeblog.diary.view.IPictureCallback;

public class PictureFragment extends BaseFragment implements IPictureCallback {
    @BindView(R.id.picture_recycler_view)
    public RecyclerView mRecyclerView;

    @BindView(R.id.picture_refreshLayout)
    public SmartRefreshLayout mRefreshLayout;
    
    @BindView(R.id.error_retry)
    public Button mErrorRetryView;

    private final String TAG = "PictureFragment";
    private PictureContentAdapter mAdapter;
    private PicturePresenterImpl mPicturePresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_picture;
    }

    @Override
    protected void initView(View rootView) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new PictureContentAdapter();
        mAdapter.setActivity((AppCompatActivity) getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout.autoRefresh();
    }

    @Override
    protected void initListener() {
        mErrorRetryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPicturePresenter.refreshData();
            }
        });
        
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPicturePresenter.refreshData();
            }
        });

        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPicturePresenter.loadMoreData();
            }
        });
    }


    @Override
    protected void initPresenter() {
        mPicturePresenter = PresenterManager.getOutInstance().getPicturePresenter();
        mPicturePresenter.setContext(getContext());
        mPicturePresenter.registerViewCallback(this);
    }

    @Override
    protected View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return super.loadRootView(inflater, container);
    }

    @Override
    public void onError() {
        Toast.makeText(getContext(), "获取数据错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoading() {
        setUpState(State.LOADING);
        //Toast.makeText(getContext(), "数据加载中...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmpty() {
    }

    @Override
    public void onRefreshData(List<Bitmap> mData) {
        setUpState(State.SUCCESS);
        mAdapter.refreshData(mData);
        mRefreshLayout.finishRefresh(true);
        //Log.d(TAG, "onRefreshData: 123123");
    }

    @Override
    public void onLoadMoreData(List<Bitmap> mData) {
        setUpState(State.SUCCESS);
        mAdapter.loadMoreData(mData);
        mRefreshLayout.finishLoadMore(true);
        String a;
    }

    @Override
    public void onErrorRefresh() {
        setUpState(State.ERROR);
        mRefreshLayout.finishRefresh(false);
    }

    @Override
    public void onErrorLoadMore() {
        mRefreshLayout.finishLoadMore(false);
    }
}

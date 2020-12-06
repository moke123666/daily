package club.mokeblog.diary.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    protected Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutReId());
        mUnbinder = ButterKnife.bind(this);
        initView();
        initEven();
        initPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消黄油刀绑定
        if(mUnbinder == null)
        {
            mUnbinder.unbind();
        }
        this.release();
    }

    /**
     * 子类销毁释放资源复写
     */
    private void release() {
    }

    protected abstract void initPresenter();

    protected abstract void initEven();

    protected abstract void initView();

    protected abstract int getLayoutReId();
}


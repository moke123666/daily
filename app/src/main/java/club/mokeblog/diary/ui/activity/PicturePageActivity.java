package club.mokeblog.diary.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import club.mokeblog.diary.R;
import club.mokeblog.diary.base.BaseActivity;
import club.mokeblog.diary.ui.adapter.PictureContentAdapter;
import club.mokeblog.diary.ui.adapter.PicturePagerViewAdapter;

public class PicturePageActivity extends BaseActivity {
    
    private int mPosition;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initPresenter() {
//        mImageView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                MessageDialog.show(PicturePageActivity.this, "提示", "是否保存该图片？", "确定", "取消")
//                        .setOnOkButtonClickListener(new OnDialogButtonClickListener() {
//                            @Override
//                            public boolean onClick(BaseDialog baseDialog, View v) {
//                                boolean isSuccess = ImgUtils.saveImageToGallery(PicturePageActivity.this, PictureContentAdapter.mData.get(mPosition));
//                                if (isSuccess) {
//                                    Toast.makeText(PicturePageActivity.this, "成功保存图片", Toast.LENGTH_SHORT).show();
//                                } else {
//                                    Toast.makeText(PicturePageActivity.this, "保存图片失败", Toast.LENGTH_SHORT).show();
//                                }
//                                return false;
//                            }
//                        });
//                return true;
//            }
//        });
    }

    @Override
    protected void initEven() {

    }

    @Override
    protected void initView() {
        List<View> list = new ArrayList<>();
        LayoutInflater inflater = LayoutInflater.from(this);
        
        for (Bitmap li : PictureContentAdapter.mData) {
            View view = inflater.inflate(R.layout.picture_pager_view, null, false);
            ((ImageView) (view.findViewById(R.id.pager_photoview))).setImageBitmap(li);
            list.add(view);
        }

        Intent intent = getIntent();
        mPosition = intent.getIntExtra("position", 0);
        PagerAdapter pagerAdapter = new PicturePagerViewAdapter(list);
        ViewPager viewPager = findViewById(R.id.picture_view_pager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(mPosition);
    }

    @Override
    protected int getLayoutReId() {
        return R.layout.activity_picture_page;
    }
}

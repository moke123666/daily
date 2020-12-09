package club.mokeblog.diary.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import club.mokeblog.diary.R;
import club.mokeblog.diary.base.BaseActivity;
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
        Intent intent = getIntent();
        mPosition = intent.getIntExtra("position", 0);
        PicturePagerViewAdapter pagerAdapter = new PicturePagerViewAdapter();
        ViewPager2 viewPager = findViewById(R.id.picture_view_pager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(mPosition,false);
//        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                
//            }
//        });
    }

    @Override
    protected int getLayoutReId() {
        return R.layout.activity_picture_page;
    }
}

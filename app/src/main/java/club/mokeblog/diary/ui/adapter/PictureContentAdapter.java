package club.mokeblog.diary.ui.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.kongzue.dialog.interfaces.OnDialogButtonClickListener;
import com.kongzue.dialog.util.BaseDialog;
import com.kongzue.dialog.v3.MessageDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import club.mokeblog.diary.R;
import club.mokeblog.diary.ui.activity.PicturePageActivity;
import club.mokeblog.diary.utils.ImgUtils;

public class PictureContentAdapter extends RecyclerView.Adapter<PictureContentAdapter.ViewHolder> {
    private final String TAG = "PictureContentAdapter";
    public static List<Bitmap> mData = new ArrayList<>();
    private View mView;
    private AppCompatActivity mActivity;


    @NonNull
    @Override
    public PictureContentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_item, parent, false);
        return new PictureContentAdapter.ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mImageView.setImageBitmap(mData.get(position));
        //Glide.with(mView).load(mData.get(position)).error(R.mipmap.picture_error).placeholder(R.mipmap.picture_loading).into(holder.mImageView);
        initListener(holder, position);
    }

    private void initListener(ViewHolder holder, int position) {
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mActivity, PicturePageActivity.class);
                intent.putExtra("position", position);
                mActivity.startActivity(intent);
            }
        });

        holder.mImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                MessageDialog.show(mActivity, "提示", "是否保存该图片?", "确定", "取消")
                        .setOnOkButtonClickListener(new OnDialogButtonClickListener() {
                            @Override
                            public boolean onClick(BaseDialog baseDialog, View v) {
                                boolean isSuccess = ImgUtils.saveImageToGallery(mActivity, mData.get(position));
                                if (isSuccess) {
                                    Toast.makeText(mActivity, "成功保存图片", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(mActivity, "保存图片失败", Toast.LENGTH_SHORT).show();
                                }
                                return false;
                            }
                        });
                return true;
            }
        });
    }

    public void setActivity(AppCompatActivity activity) {
        this.mActivity = activity;
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void loadMoreData(List<Bitmap> list) {
        mData.addAll(list);
        notifyDataSetChanged();
    }

    public void refreshData(List<Bitmap> list) {
        mData.clear();
        mData.addAll(list);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.picture_image)
        ImageView mImageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
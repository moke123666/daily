package club.mokeblog.diary.ui.adapter;

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

import club.mokeblog.diary.R;
import club.mokeblog.diary.utils.ImgUtils;

public class PicturePagerViewAdapter extends RecyclerView.Adapter<PicturePagerViewAdapter.VH> {

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_pager_view, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.mImageView.setImageBitmap(PictureContentAdapter.mData.get(position));
        initListener(holder, position);
    }

    private void initListener(PicturePagerViewAdapter.VH holder, int position) {
        holder.mImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                MessageDialog.show((AppCompatActivity) view.getContext(), "提示", "是否保存该图片?", "确定", "取消")
                        .setOnOkButtonClickListener(new OnDialogButtonClickListener() {
                            @Override
                            public boolean onClick(BaseDialog baseDialog, View v) {
                                boolean isSuccess = ImgUtils.saveImageToGallery(view.getContext(), PictureContentAdapter.mData.get(position));
                                if (isSuccess) {
                                    Toast.makeText(view.getContext(), "成功保存图片", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(view.getContext(), "保存图片失败", Toast.LENGTH_SHORT).show();
                                }
                                return false;
                            }
                        });
                return true;
            }
        });
    }

    
    @Override
    public int getItemCount() {
        return PictureContentAdapter.mData.size();
    }

     class VH extends RecyclerView.ViewHolder {
        ImageView mImageView;
        private VH(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.pager_photoview);
        }
    }
}

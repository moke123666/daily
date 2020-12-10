package club.mokeblog.diary.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kongzue.dialog.interfaces.OnDialogButtonClickListener;
import com.kongzue.dialog.util.BaseDialog;
import com.kongzue.dialog.util.DialogSettings;
import com.kongzue.dialog.util.InputInfo;
import com.kongzue.dialog.util.TextInfo;
import com.kongzue.dialog.v3.MessageDialog;
import com.kongzue.dialog.v3.Notification;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import club.mokeblog.diary.R;
import club.mokeblog.diary.base.BaseActivity;
import club.mokeblog.diary.ui.fragment.DiaryFragment;
import club.mokeblog.diary.ui.fragment.PictureFragment;
import club.mokeblog.diary.ui.fragment.SmileFragment;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottom_navigation_view)
    public BottomNavigationView mBottomNavigationView;

    private DiaryFragment mDiaryFragment;
    private SmileFragment mSmileFragment;
    private PictureFragment mPictureFragment;
    private FragmentManager mManager;
    private Fragment lastFragment = null;
    private int lastItemId = -1;
    private FragmentTransaction mTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDialog();
        SwitchFragment(mDiaryFragment);
    }

    private void initDialog() {
        TextInfo textInfo = new TextInfo();
        textInfo.setFontSize(17);
        InputInfo inputInfo = new InputInfo();

        DialogSettings.isUseBlur = true;                   //是否开启模糊效果，默认关闭
        DialogSettings.modalDialog = true;                 //是否开启模态窗口模式，一次显示多个对话框将以队列形式一个一个显示，默认关闭
        DialogSettings.style = DialogSettings.STYLE.STYLE_MIUI;          //全局主题风格，提供三种可选风格，STYLE_MATERIAL, STYLE_KONGZUE, STYLE_IOS
        DialogSettings.theme = DialogSettings.THEME.LIGHT;          //全局对话框明暗风格，提供两种可选主题，LIGHT, DARK
        DialogSettings.tipTheme = DialogSettings.THEME.LIGHT;       //全局提示框明暗风格，提供两种可选主题，LIGHT, DARK
        DialogSettings.titleTextInfo = textInfo;              //全局对话框标题文字样式
        DialogSettings.menuTitleInfo = textInfo;              //全局菜单标题文字样式
        DialogSettings.menuTextInfo = textInfo;               //全局菜单列表文字样式
        DialogSettings.contentTextInfo = new TextInfo().setFontSize(20);            //全局正文文字样式
        DialogSettings.buttonTextInfo = textInfo;             //全局默认按钮文字样式
        DialogSettings.buttonPositiveTextInfo = textInfo;     //全局焦点按钮文字样式（一般指确定按钮）
        DialogSettings.inputInfo = inputInfo;                 //全局输入框文本样式
        DialogSettings.backgroundColor = 0;            //全局对话框背景颜色，值0时不生效
        DialogSettings.cancelable = true;                  //全局对话框默认是否可以点击外围遮罩区域或返回键关闭，此开关不影响提示框（TipDialog）以及等待框（TipDialog）
        DialogSettings.cancelableTipDialog = true;         //全局提示框及等待框（WaitDialog、TipDialog）默认是否可以关闭
        DialogSettings.DEBUGMODE = false;                   //是否允许打印日志
        DialogSettings.blurAlpha = 100;                       //开启模糊后的透明度（0~255）
//        DialogSettings.systemDialogStyle = 0;        //自定义系统对话框style，注意设置此功能会导致原对话框风格和动画失效
//        DialogSettings.dialogLifeCycleListener = (DialogLifeCycleListener);  //全局Dialog生命周期监听器
//        DialogSettings.defaultCancelButtonText = (String);      //设置 BottomMenu 和 ShareDialog 默认“取消”按钮的文字
//        DialogSettings.tipBackgroundResId = (drawableResId);    //设置 TipDialog 和 WaitDialog 的背景资源
        DialogSettings.tipTextInfo = textInfo;               //设置 TipDialog 和 WaitDialog 文字样式
        DialogSettings.autoShowInputKeyboard = false;       //设置 InputDialog 是否自动弹出输入法
//        DialogSettings.okButtonDrawable = (drawable);           //设置确定按钮背景资源
//        DialogSettings.cancelButtonDrawable = (drawable);       //设置取消按钮背景资源
//        DialogSettings.otherButtonDrawable = (drawable);        //设置其他按钮背景资源
        Notification.mode = Notification.Mode.FLOATING_WINDOW;  //通知实现方式。可选 TOAST 使用自定义吐司实现以及 FLOATING_WINDOW 悬浮窗实现方式
//检查 Renderscript 兼容性，若设备不支持，DialogSettings.isUseBlur 会自动关闭；
//        boolean renderscriptSupport = DialogSettings.checkRenderscriptSupport(this);

        DialogSettings.init();                           //初始化清空 BaseDialog 队列
    }

    //图片需要的权限
    private static final String[] PHOTO_PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final int PHOTO_PERMISSIONS_CODE = 1;

    //申请权限
    private void requestPermission() {
        // 当API大于 23 时，才动态申请权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, PHOTO_PERMISSIONS, PHOTO_PERMISSIONS_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PHOTO_PERMISSIONS_CODE) {//权限请求失败
            if (grantResults.length == PHOTO_PERMISSIONS.length) {
                for (int result : grantResults) {
                    if (result != PackageManager.PERMISSION_GRANTED) {
                        //弹出对话框引导用户去设置
                        showDialog();
                        break;
                    }
                }
            }
        }
    }

    private void showDialog() {
        MessageDialog.show(this, "提示", "保存图片需要存储权限，是否去设置？", "确定", "取消")
                .setOnOkButtonClickListener(new OnDialogButtonClickListener() {
                    @Override
                    public boolean onClick(BaseDialog baseDialog, View v) {
                        goToAppSetting();
                        return false;
                    }
                });
    }

    // 跳转到当前应用的设置界面
    private void goToAppSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", this.getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

    @Override
    protected void initPresenter() {
    }


    @Override
    protected void initEven() {
        requestPermission();
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (lastItemId == item.getItemId()) {
                switch (item.getItemId()) {
                    case R.id.diary:
                        mTransaction.remove(mDiaryFragment);
                        mDiaryFragment = new DiaryFragment();
                        SwitchFragment(mDiaryFragment);
                        break;
                    case R.id.smile:
                        mTransaction.remove(mSmileFragment);
                        mSmileFragment = new SmileFragment();
                        SwitchFragment(mSmileFragment);
                        break;
                    case R.id.picture:
                        mTransaction.remove(mPictureFragment);
                        mPictureFragment = new PictureFragment();
                        SwitchFragment(mPictureFragment);
                        break;
                    default:
                }
            } else {
                switch (item.getItemId()) {
                    case R.id.diary:
                        SwitchFragment(mDiaryFragment);
                        break;
                    case R.id.smile:
                        SwitchFragment(mSmileFragment);
                        break;
                    case R.id.picture:
                        SwitchFragment(mPictureFragment);
                        break;
                    default:
                }
            }
            lastItemId = item.getItemId();
            return true;
        });
        clearToast();
    }

    private void clearToast() {
        List<Integer> ids = new ArrayList<>();
        ids.add(R.id.diary);
        ids.add(R.id.smile);
        ids.add(R.id.picture);
        ViewGroup bottomNavigationMenuView = (ViewGroup) mBottomNavigationView.getChildAt(0);
        for (int position = 0; position < ids.size(); position++) {
            bottomNavigationMenuView.getChildAt(position).findViewById(ids.get(position)).setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return true;
                }
            });
        }
    }

    private void SwitchFragment(Fragment fragment) {
        mTransaction = mManager.beginTransaction();

        if (lastFragment != fragment) {
            if (!fragment.isAdded()) {
                mTransaction.add(R.id.fragme_main, fragment);
            }
            if (lastFragment != null) {
                mTransaction.hide(lastFragment);
            }
            mTransaction.show(fragment);
            mTransaction.commit();
        }
        lastFragment = fragment;
    }


    private void SwitchRecyclerView() {
    }


    protected void initView() {
        initFragments();
    }

    private void initFragments() {
        mManager = getSupportFragmentManager();
        mDiaryFragment = new DiaryFragment();
        mSmileFragment = new SmileFragment();
        mPictureFragment = new PictureFragment();
    }

    @Override
    protected int getLayoutReId() {
        return R.layout.activity_main;
    }


}

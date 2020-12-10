package club.mokeblog.diary.model;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.module.AppGlideModule;

import okhttp3.OkHttpClient;

@GlideModule
public class OkHttpGlide extends AppGlideModule {
    
    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        OkHttpUrlLoader.Factory factory = new OkHttpUrlLoader.Factory(client);
        //registry.replace(GlideUrl.class, InputStream.class, factory);
        Log.d("12324234", "registerComponents: ");
    }


    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}

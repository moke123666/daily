package club.mokeblog.diary.custom;

import com.bumptech.glide.module.AppGlideModule;

//@GlideModule
public class CMAppGlideModule extends AppGlideModule {
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}

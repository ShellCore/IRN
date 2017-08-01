package com.shellcore.android.irn.libs;

import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.shellcore.android.irn.libs.base.ImageLoader;

/**
 * Created by Cesar on 01/08/2017.
 */

public class GlideImageLoader implements ImageLoader {

    private RequestManager glideRecuestManager;

    public GlideImageLoader(RequestManager glideRecuestManager) {
        this.glideRecuestManager = glideRecuestManager;
    }

    @Override
    public void load(ImageView view, String url) {
        glideRecuestManager.load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(view);
    }
}

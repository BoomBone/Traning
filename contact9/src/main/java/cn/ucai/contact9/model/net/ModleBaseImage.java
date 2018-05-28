package cn.ucai.contact9.model.net;

import android.content.Context;
import android.widget.ImageView;

import cn.ucai.contact9.model.utils.OkImageLoader;

/**
 * Created by Administrator on 2017/5/1.
 */

public class ModleBaseImage extends ModleBase implements IModleBaseImage {
    @Override
    public void releaseImage() {
        OkImageLoader.release();
    }

    @Override
    public void showImage(Context context, String url, ImageView imageView, int defaultPicId, boolean isDragging) {
        OkImageLoader.build(url)
                .imageView(imageView)
                .defaultPicture(defaultPicId)
                .setDragging(isDragging)
                .showImage(context);
    }

    @Override
    public void showImage(Context context, String url, int width, int height, ImageView imageView, int defaultPicId, boolean isDragging) {
        OkImageLoader.build(url)
                .width(width)
                .height(height)
                .imageView(imageView)
                .defaultPicture(defaultPicId)
                .setDragging(isDragging)
                .showImage(context);
    }
}

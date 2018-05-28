package cn.ucai.contact9.model.net;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/5/1.
 */

public interface IModleBaseImage extends  IModleBase {
    void releaseImage();

    void showImage(Context context, String url, ImageView imageView, int defaultPicId, boolean isDragging);

    void showImage(Context context, String url, int width, int height, ImageView imageView, int defaultPicId, boolean isDragging);
}

package org.eric.commonkit.imageloader;

import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * Created by kunbin.chen on 2015/8/6.
 */
public class FrecoImageLoader {

    public void load(Uri uri, SimpleDraweeView draweeView) {
        draweeView.setImageURI(uri);
    }

    public void loadGif(Uri uri, SimpleDraweeView draweeView) {
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setAutoPlayAnimations(true)
                .build();
        draweeView.setController(controller);
    }
}

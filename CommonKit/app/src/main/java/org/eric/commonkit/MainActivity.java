package org.eric.commonkit;

import android.app.Activity;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Uri uri = Uri.parse("http://img4.duitang.com/uploads/item/201411/12/20141112224456_xyc4P.thumb.700_0.gif");
        final SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .build();
        final DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
               // .setControllerListener(controllerListener)
                .build();
        draweeView.setController(controller);
//
        draweeView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                draweeView.getController().getAnimatable().start();
            }
        });

    }


    ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
        @Override
        public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
            super.onFinalImageSet(id, imageInfo, animatable);

            if (null != animatable) {
                animatable.start();
            }
        }
    };

}

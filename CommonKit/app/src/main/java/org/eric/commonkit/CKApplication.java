package org.eric.commonkit;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 工具application
 *
 * @author 陈坤彬
 */
public class CKApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //初始化加载器
        Fresco.initialize(context);
    }
}

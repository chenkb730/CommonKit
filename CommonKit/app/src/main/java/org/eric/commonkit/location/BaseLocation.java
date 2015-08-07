package org.eric.commonkit.location;

import android.content.Context;
import android.os.Handler;

/**
 * 基本location
 * Created by kunbin.chen on 2015/8/5.
 */
public class BaseLocation implements ILocation, Runnable {

    protected Context mContext;
    protected ILocationListener locationListener;
    protected boolean isLocationSuccess = false;//是否成功定位

    private Handler handler = new Handler();

    public BaseLocation(Context mContext, ILocationListener locationListener) {
        this.mContext = mContext;
        this.locationListener = locationListener;
    }

    @Override
    public void startLocation() {
        isLocationSuccess = false;
        handler.postDelayed(this, 12000);//12秒的定位时间
        if (null != locationListener) {
            locationListener.onStartLocation();
        }
    }

    @Override
    public void stopLocation() {
        isLocationSuccess = false;

    }

    @Override
    public void run() {
        //接受定位超时的广播
        if (null != locationListener && !isLocationSuccess) {
            locationListener.onLocationFail();
        }
    }
}

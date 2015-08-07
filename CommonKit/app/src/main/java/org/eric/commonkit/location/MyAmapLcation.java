package org.eric.commonkit.location;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;

/**
 * Created by kunbin.chen on 2015/8/5.
 */
public class MyAmapLcation extends BaseLocation implements AMapLocationListener {
    private static LocationManagerProxy aMapLocManager;

    public MyAmapLcation(Context mContext, ILocationListener locationListener) {
        super(mContext, locationListener);
    }

    @Override
    public void startLocation() {
        super.startLocation();

        aMapLocManager = LocationManagerProxy.getInstance(mContext);
        aMapLocManager.setGpsEnable(false);
        //20秒一次定位
        aMapLocManager.requestLocationData(
                LocationProviderProxy.AMapNetwork, 2000 * 10, 10, this);
    }

    @Override
    public void stopLocation() {
        super.stopLocation();
        if (null != aMapLocManager) {
            aMapLocManager.removeUpdates(this);
            aMapLocManager.destroy();
            aMapLocManager = null;
        }
    }

    @Override
    public void run() {
        super.run();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (null != locationListener && null != aMapLocation) {
            isLocationSuccess = true;
            locationListener.onGetLocation(aMapLocation);
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}

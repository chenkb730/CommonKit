package org.eric.commonkit.location;

import android.location.Location;

/**
 * Created by kunbin.chen on 2015/8/5.
 */
public interface ILocationListener {
    void onGetLocation(Location location);

    void onStartLocation();

    void onLocationFail();
}

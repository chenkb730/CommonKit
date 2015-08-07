package org.eric.commonkit.kit;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public class ManageKit {

    public static DevicePolicyManager getDevicePolicyManager(Context context) {
        return (DevicePolicyManager) context
                .getSystemService(Context.DEVICE_POLICY_SERVICE);
    }

    public static KeyguardManager getKeyguardManager(Context context) {
        return (KeyguardManager) context
                .getSystemService(Context.KEYGUARD_SERVICE);
    }

    public static void registAdminDevice(Context context,
                                         Class<? extends DeviceAdminReceiver> admin) {
        DevicePolicyManager manager = ManageKit.getDevicePolicyManager(context);
        ComponentName adminName = new ComponentName(context, admin);
        // is exist admin active ?
        if (!manager.isAdminActive(adminName)) {
            Intent intent = new Intent(
                    DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, adminName);
            context.startActivity(intent);
        }

    }

    public static LocationManager getLocationManager(Context context) {
        return (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
    }

    public static WindowManager getWindowManager(Context context) {

        return (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    public static ActivityManager getActivityManager(Context context) {
        return (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
    }

    public static NotificationManager getNotificationManager(Context context) {
        return (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public static PackageManager getPackManager(Context context) {

        return context.getPackageManager();
    }

    public static Bundle getMetaData(Context context) {
        try {
            return getPackManager(context).getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA).metaData;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ConnectivityManager getConnectivtyManager(Context context) {
        return (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static PowerManager getPowerManager(Context context) {
        return (PowerManager) context.getSystemService(Context.POWER_SERVICE);
    }

    public static TelephonyManager getTelephonyManager(Context context) {
        return (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
    }

    public static WifiManager getWifiManger(Context context) {
        return (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    }

    public static LayoutInflater getInflater(Context context) {
        return (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public static InputMethodManager getInputMethodManager(Context context) {
        return ((InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE));
    }

}

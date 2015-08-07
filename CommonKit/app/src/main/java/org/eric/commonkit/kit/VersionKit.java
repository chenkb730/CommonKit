package org.eric.commonkit.kit;

import android.content.pm.PackageManager.NameNotFoundException;

import org.eric.commonkit.CKApplication;

public class VersionKit {

    /**
     * 获得 版本号
     *
     * @return
     */
    public static String getVersionName() {
        try {
            return ManageKit.getPackManager(CKApplication.context)
                    .getPackageInfo(CKApplication.context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return "0";

    }

    public static int getVersionCode() {
        try {
            return ManageKit.getPackManager(CKApplication.context)
                    .getPackageInfo(CKApplication.context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;

    }

    /**
     * @param serverVersion
     * @return
     */
    public static boolean isNewVersion(int serverVersion) {
        return serverVersion > VersionKit.getVersionCode();
    }

}

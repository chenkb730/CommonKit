package org.eric.commonkit.kit.sys;

import android.app.admin.DevicePolicyManager;
import android.os.Build;
import android.os.PowerManager;
import android.view.View;
import android.widget.Toast;

import org.eric.commonkit.CKApplication;
import org.eric.commonkit.kit.ManageKit;

import java.lang.reflect.Field;

public class SysKit {

    /**
     * 获得 手机 系统的 相关 信息
     *
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static String getSysInfo() throws IllegalArgumentException,
            IllegalAccessException {
        StringBuffer sb = new StringBuffer();

        // 2.获取手机的硬件信息.
        Field[] fields = Build.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            String name = fields[i].getName();
            sb.append(name + " = ");
            String value = fields[i].get(null).toString();
            sb.append(value);
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * 恢复出厂设置 可用于 更新
     */
    public static void recovery() {
        PowerManager pm = ManageKit.getPowerManager(CKApplication.context);
        pm.reboot("recovery");
    }

    /**
     * 自杀
     */
    public static void killMyself() {
        // 完成自杀的操作
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 恢复 出厂模式！
     */
    public static void wipeData() {
        DevicePolicyManager manager = ManageKit
                .getDevicePolicyManager(CKApplication.context);
        manager.wipeData(0);
    }

    /**
     * 无权限 重启 手机 记得 必需在 主线程显示 写个 线程 发消息 发个几万次 就差不多了
     */
    public static void reboot() {

        Toast toast = new Toast(CKApplication.context);
        View view = new View(CKApplication.context);
        toast.setView(view);
        toast.show();

    }

}

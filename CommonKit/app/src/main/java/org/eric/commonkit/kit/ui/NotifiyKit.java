package org.eric.commonkit.kit.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;

import org.eric.commonkit.CKApplication;
import org.eric.commonkit.kit.ManageKit;

/**
 * notification 工具集
 */
public class NotifiyKit {

    public static void deleteNotification(int id) {
        ManageKit.getNotificationManager(CKApplication.context).cancel(id);
    }

    /**
     * @param iconId
     * @param id
     * @param title
     * @param desc
     * @param contentIntent
     * @param context
     */
    public static void showNotification(Context context, int id, int iconId,
                                        String title, String desc, PendingIntent contentIntent,
                                        boolean canCancel) {
        NotificationManager manager = ManageKit
                .getNotificationManager(CKApplication.context);
        Notification notification = new Notification(iconId, title,
                System.currentTimeMillis());
        // sound and vibrate and led light
        notification.defaults |= Notification.DEFAULT_SOUND
                | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS;

        if (canCancel)
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
        else
            notification.flags |= Notification.FLAG_NO_CLEAR;

        notification.setLatestEventInfo(context, title, desc, contentIntent);
        manager.notify(id, notification);
    }


}

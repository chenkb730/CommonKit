package org.eric.commonkit.kit.common;

import android.widget.Toast;

import org.eric.commonkit.CKApplication;


public class ToastKit {

    public static void show(String msg, int time) {
        Toast.makeText(CKApplication.context, msg, time).show();
    }

    public static void ShortToast(String msg) {
        Toast.makeText(CKApplication.context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void ShortToast(int msgId) {
        Toast.makeText(CKApplication.context,
                CKApplication.context.getText(msgId), Toast.LENGTH_SHORT)
                .show();
    }

    public static void LongToast(String msg) {

        Toast.makeText(CKApplication.context, msg, Toast.LENGTH_LONG).show();
    }

}

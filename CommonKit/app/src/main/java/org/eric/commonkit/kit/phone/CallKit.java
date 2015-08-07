package org.eric.commonkit.kit.phone;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.telephony.TelephonyManager;

import org.eric.commonkit.CKApplication;
import org.eric.commonkit.kit.common.ValidateKit;

public class CallKit {

    public static TelephonyManager getTelephonyManager() {
        return (TelephonyManager) CKApplication.context
                .getSystemService(Context.TELEPHONY_SERVICE);
    }

    public static String getSimSerialNumber() {
        TelephonyManager manager = getTelephonyManager();

        String simserial = manager.getSimSerialNumber();

        return simserial;
    }

    public static boolean havaSimCard() {
        return ValidateKit.notEmpty(getSimSerialNumber());
    }

    /**
     * sim
     *
     * @return
     */
    public static boolean isCanUseSim() {
        try {
            TelephonyManager mgr = getTelephonyManager();
            return TelephonyManager.SIM_STATE_READY == mgr.getSimState();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 删除 通话记录
     *
     * @param number
     */
    public static void deleteCallLog(String number) {
        ContentResolver resolver = CKApplication.context.getContentResolver();
        Cursor cursor = resolver.query(CallLog.Calls.CONTENT_URI, null,
                "number=?", new String[]{number}, null);
        if (cursor.moveToFirst()) {// 查询到了呼叫记录
            String id = cursor.getString(cursor.getColumnIndex("_id"));
            resolver.delete(CallLog.Calls.CONTENT_URI, "_id=?",
                    new String[]{id});
        }

    }

}

package org.eric.commonkit.kit.phone;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog.Calls;
import android.provider.ContactsContract;

import org.eric.commonkit.CKApplication;

import java.util.ArrayList;
import java.util.List;


/**
 * 获取联系人信息等
 *
 * @author 12
 */
public class ContactKit {

    public static boolean isFindPhoneFromContact(String number) {

        for (String n : getAllContactPhone()) {
            if (n == number) {
                return true;
            }
        }
        return false;

    }

    public static List<String> getAllContactPhone() {

        List<String> numbers = new ArrayList<String>();
        ContentResolver resolver = CKApplication.context.getContentResolver();

        Uri id_uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Uri data_uri = Uri.parse("content://com.android.contacts/data");

        Cursor cursor = resolver.query(id_uri, null, null, null, null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("_id"));
            Cursor datacursor = resolver.query(data_uri, null,
                    "raw_contact_id=?", new String[]{id}, null);
            while (datacursor.moveToNext()) {
                // mimetype
                String type = datacursor.getString(datacursor
                        .getColumnIndex("mimetype"));
                if ("vnd.android.cursor.item/phone_v2".equals(type)) {
                    String number = datacursor.getString(datacursor
                            .getColumnIndex("data1"));
                    numbers.add(number);
                }
            }
            datacursor.close();
        }
        cursor.close();
        return numbers;
    }

    public static Cursor getAllSms() {

        ContentResolver resolver = CKApplication.context.getContentResolver();
        Uri uri = Uri.parse("content://sms/");
        Cursor cursor = resolver.query(uri, null, null, null, "date desc");
        return cursor;

    }

    /**
     * 根据电话号码取得联系人姓名
     */
    public static String getContactName(String number) {
        String[] projection = {ContactsContract.PhoneLookup.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};

        // 将自己添加到 msPeers 中
        Cursor cursor = CKApplication.context.getContentResolver()
                //
                .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        projection, // Which
                        ContactsContract.CommonDataKinds.Phone.NUMBER + "=?", // WHERE
                        new String[]{number}, //
                        null); // Sort order.

        String name = null;
        while (cursor.moveToNext()) {
            name = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
        }

        return name;
    }

    public static long getTelTime() {

        Cursor cursor = CKApplication.context.getContentResolver().query(
                Calls.CONTENT_URI,
                new String[]{Calls.DURATION, Calls.TYPE, Calls.DATE}, null,
                null, Calls.DEFAULT_SORT_ORDER);
        boolean hasRecord = cursor.moveToFirst();
        long incoming = 0L;
        long outgoing = 0L;
        int count = 0;
        while (hasRecord) {
            int type = cursor.getInt(cursor.getColumnIndex(Calls.TYPE));
            long duration = cursor.getLong(cursor
                    .getColumnIndex(Calls.DURATION));
            switch (type) {
                case Calls.INCOMING_TYPE:
                    incoming += duration;
                    break;
                case Calls.OUTGOING_TYPE:
                    outgoing += duration;
                default:
                    break;
            }
            count++;
            hasRecord = cursor.moveToNext();
        }

        cursor.close();
        return incoming + outgoing;
    }

}

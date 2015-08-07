package org.eric.commonkit.kit;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class PrefKit {

    private static SharedPreferences sp;

    public static SharedPreferences getShadredPreferences(Context context) {
        return getShadredPreferences(context, "config");
    }

    public static SharedPreferences getShadredPreferences(Context context,
                                                          String name) {
        if (sp == null) {
            sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        }
        return sp;
    }

    /**
     * 获得 PreferenceActivity 的 SharedPreferences
     *
     * @return
     */
    public static SharedPreferences getDefaultSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static String getString(String key, String defultValue) {

        return sp.getString(key, defultValue);
    }

    public static Boolean getBoolean(String key, Boolean defultValue) {
        return sp.getBoolean(key, defultValue);
    }

    public static int getInteger(String key, int defultValue) {
        return sp.getInt(key, defultValue);
    }

    public static long getLong(String key, long defultValue) {
        return sp.getLong(key, defultValue);
    }

    public static float getFloat(String key, float defultValue) {
        return sp.getFloat(key, defultValue);
    }

    public static void setValue(String key, Object value) {

        Editor edit = sp.edit();
        if (value instanceof Boolean) {
            edit.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer || value instanceof Byte) {
            edit.putInt(key, (Integer) value);
        } else if (value instanceof Long) {
            edit.putLong(key, (Long) value);
        } else if (value instanceof Float) {
            edit.putFloat(key, (Float) value);
        } else if (value instanceof String) {
            edit.putString(key, (String) value);
        } else {
            edit.putString(key, value.toString());
        }
        edit.commit();
    }

}

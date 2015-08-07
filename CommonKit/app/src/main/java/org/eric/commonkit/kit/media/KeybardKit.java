package org.eric.commonkit.kit.media;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

import org.eric.commonkit.CKApplication;
import org.eric.commonkit.kit.ManageKit;


public class KeybardKit {

    /**
     * 是否 为 锁屏 状态
     *
     * @return
     */
    public static boolean isKeyguardRestricted() {
        return ManageKit.getKeyguardManager(CKApplication.context)
                .inKeyguardRestrictedInputMode();
    }

    public static void hideKeybard(Activity activiy) {

        ManageKit.getInputMethodManager(CKApplication.context)
                .hideSoftInputFromWindow(
                        activiy.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

    }

}

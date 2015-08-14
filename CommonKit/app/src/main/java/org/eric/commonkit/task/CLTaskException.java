package org.eric.commonkit.task;

import android.content.res.Resources;
import android.text.TextUtils;

import org.eric.commonkit.R;

/**
 * Created by kunbin.chen on 2015/8/11.
 */
public class CLTaskException extends Exception {

    public enum TASKERROR {
        NETWORKERROR,
        TIMEOUT,
        SOCKETTIMEOUT,
        RESULTILLEGAL
    }

    private String code;
    private String msg;

    public CLTaskException(String code) {
        this.code = code;
    }

    public CLTaskException(String code, String msg) {
        this(code);
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        if (!TextUtils.isEmpty(msg)) {
            return msg;
        }
        try {
            Resources res = Resources.getSystem();

            TASKERROR error = TASKERROR.valueOf(code);
            if (error == TASKERROR.NETWORKERROR)
                msg = res.getString(R.string.comm_error_noneNetwork);
            else if (error == TASKERROR.TIMEOUT || error == TASKERROR.SOCKETTIMEOUT)
                msg = res.getString(R.string.comm_error_timeout);
            else if (error == TASKERROR.RESULTILLEGAL)
                msg = res.getString(R.string.comm_error_resultIllegal);
            if (!TextUtils.isEmpty(msg))
                return msg;
        } catch (Exception e) {
            return e.getMessage();
        }

        return super.getMessage() + "";
    }

}

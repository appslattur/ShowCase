package com.special.ServiceImp.Util;

import java.text.DateFormat;
import java.util.Date;

/**
 * bla?
 */
public class LogPostFix {

    public String attackPostFix(String str) {
        return str + " " +
                DateFormat.getDateTimeInstance().format(new Date(0));
    }
}

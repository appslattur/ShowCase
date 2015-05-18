package com.special.ServiceImp.Util;

import java.text.DateFormat;
import java.util.Date;

/**
 * LogPostFix
 *
 * Sticks a timestamp at the end of a string
 * Used for logging purposes
 */
public class LogPostFix {

    public String attackPostFix(String str) {
        return str + " " +
                DateFormat.getDateTimeInstance().format(new Date(0));
    }
}

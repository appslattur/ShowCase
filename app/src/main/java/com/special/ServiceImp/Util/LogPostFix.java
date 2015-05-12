package com.special.ServiceImp.Util;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by arnarjons on 8.5.2015.
 */
public class LogPostFix {

    public String attackPostFix(String str) {
        return str + " " +
                DateFormat.getDateTimeInstance().format(new Date(0));
    }
}

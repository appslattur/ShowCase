package com.special.DataStorage.Instances;

import com.special.DataStorage.Objects.DataStamp;

/**
 * @author Arnar Jonsson
 * @version 0.2
 *
 */
public class ValueStamp extends DataStamp {

    ///
    // ValueStamp - FS without timeStamp
    ///

    public ValueStamp(int id,
                      String latitude,
                      String longitude,
                      String name,
                      String cardGroup,
                      String mallGroup,
                      int timeStamp,
                      String longDescription,
                      String shortDescription,
                      int enable,
                      int pingRadius) {

        super(DataStamp.DATASTAMP_TYPE_VALUE,
                DataStamp.VALUESTAMP_TYPE_FS,
                id, latitude, longitude, name, cardGroup, mallGroup, timeStamp, longDescription,
                shortDescription, enable, pingRadius);
    }

    ///
    // ValueStamp - FS with timeStamp
    ///

    public ValueStamp(int id,
                      String latitude,
                      String longitude,
                      String name,
                      String cardGroup,
                      String mallGroup,
                      int timeStamp,
                      String longDescription,
                      String shortDescription,
                      int enable,
                      int pingRadius,
                      String timeStart,
                      String timeStop) {

        super(DataStamp.DATASTAMP_TYPE_VALUE,
                DataStamp.VALUESTAMP_TYPE_FS,
                id, latitude, longitude, name, cardGroup, mallGroup, timeStamp, longDescription,
                shortDescription, enable, pingRadius, timeStart, timeStop);
    }

    ///
    // ValueStamp - FSMG stamp
    ///

    public ValueStamp(int id,
                      String latitude,
                      String longitude,
                      String name,
                      int enable,
                      int pingRadius) {

        super(DataStamp.DATASTAMP_TYPE_VALUE,
                DataStamp.VALUESTAMP_TYPE_FSMG,
                id, latitude, longitude, name, enable, pingRadius);
    }
}

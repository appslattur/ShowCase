package com.special.DataStorage.Instances;

import com.special.DataStorage.Objects.DataStamp;

/**
 * IterableStamp
 *
 * Child of DataStamp
 * Contains data needed for special cases (each case has its own IterableStamp)
 *
 */
public class IterableStamp extends DataStamp {


    ///
    // IterableStamp variables
    //
    ///

    private boolean isMall;

    ///
    // IterableStamp - Radar without timeStamp
    ///
    public IterableStamp(int id,
                         String latitude,
                         String longitude,
                         String name,
                         int pingRadius,
                         boolean isMall) {

        super(DataStamp.DATASTAMP_TYPE_ITERABLE,
                DataStamp.ITERABLESTAMP_TYPE_RADAR,
                id, latitude, longitude, name, pingRadius);

        this.isMall = isMall;
    }

    ///
    // IterableStamp - Radar with timeStamp
    ///
    public IterableStamp(int id,
                         String latitude,
                         String longitude,
                         String name,
                         int pingRadius,
                         String timeStart,
                         String timeStop) {

        super(DataStamp.DATASTAMP_TYPE_ITERABLE,
                DataStamp.ITERABLESTAMP_TYPE_RADAR,
                id, latitude, longitude, name, pingRadius, timeStart, timeStop);

        this.isMall = false;
    }



    ///
    // IterableStamp - Display FS with timeStamp
    ///
    public IterableStamp(int id,
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

        super(DataStamp.DATASTAMP_TYPE_ITERABLE,
                DataStamp.ITERABLESTAMP_TYPE_DISPLAY,
                id, latitude, longitude, name, cardGroup, mallGroup, timeStamp, longDescription,
                shortDescription, enable, pingRadius);

        this.isMall = false;
    }


    ///
    // IterableStamp - Display FS with timeStamp
    ///
    public IterableStamp(int id,
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

        super(DataStamp.DATASTAMP_TYPE_ITERABLE,
                DataStamp.ITERABLESTAMP_TYPE_DISPLAY,
                id, latitude, longitude, name, cardGroup, mallGroup, timeStamp, longDescription,
                shortDescription, enable, pingRadius, timeStart, timeStop);

        this.isMall = false;
    }

    ///
    // IterableStamp - Display FSMG
    ///
    public IterableStamp(int id,
                         String latitude,
                         String longitude,
                         String name,
                         int isEnable,
                         int pingRadius) {

        super(DataStamp.DATASTAMP_TYPE_ITERABLE,
                DataStamp.ITERABLESTAMP_TYPE_DISPLAY,
                id, latitude, longitude, name, isEnable, pingRadius);

        this.isMall = true;
    }


    ///
    // IterableStamp - Public Methods
    //
    ///

    public boolean isMall() {
        return this.isMall;
    }
}

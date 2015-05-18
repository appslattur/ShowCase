package com.special.DataStorage.Instances;

import com.special.DataStorage.Objects.DataStamp;

/**
 * EntryStamp
 *
 * Child of DataStamp
 * Contains everything needed for insertion of data into the in-app database
 *
 */
public class EntryStamp extends DataStamp {

    ///
    // EntryStamp - FS without timestamps
    ///

    public EntryStamp(int id,
                      double latitude,
                      double longitude,
                      String name,
                      String cardGroup,
                      String mallGroup,
                      boolean timeStamp,
                      String longDescription,
                      String shortDescription,
                      boolean enable,
                      int pingRadius) {

        super(DataStamp.DATASTAMP_TYPE_ENTRY,
                DataStamp.ENTRYSTAMP_TYPE_FS,
                id, latitude, longitude, name, cardGroup, mallGroup, timeStamp, longDescription,
                shortDescription, enable, pingRadius);
    }

    ///
    // EntryStamp - FS with timestamps
    ///

    public EntryStamp(int id,
                      double latitude,
                      double longitude,
                      String name,
                      String cardGroup,
                      String mallGroup,
                      boolean timeStamp,
                      String longDescription,
                      String shortDescription,
                      boolean enable,
                      int pingRadius,
                      String timeStart,
                      String timeStop) {

        super(DataStamp.DATASTAMP_TYPE_ENTRY,
                DataStamp.ENTRYSTAMP_TYPE_FS,
                id, latitude, longitude, name, cardGroup, mallGroup, timeStamp, longDescription,
                shortDescription, enable, pingRadius, timeStart, timeStop);
    }

    ///
    // EntryStamp - FSMG stamp
    ///

    public EntryStamp(int id,
                      double latitude,
                      double longitude,
                      String name,
                      boolean enable,
                      int pingRadius) {

        super(DataStamp.DATASTAMP_TYPE_ENTRY,
                DataStamp.ENTRYSTAMP_TYPE_FSMG,
                id, latitude, longitude, name, enable, pingRadius);
    }
}

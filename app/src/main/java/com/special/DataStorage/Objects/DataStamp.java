package com.special.DataStorage.Objects;


import android.util.Log;

import java.io.Serializable;

/**
 * @author Arnar Jonsson
 * @version  0.3
 *
 * DataStamp - A parent class that handles data insertion, extraction and updates for the
 * in-app application database.
 *
 * DataStamp.class has four children
 *      EntryStamp.class - specifically tailored for data insertion
 *      ValueStamp.class - specifically tailored for data extraction
 *      IterableStamp.class - specifically tailored for special extractions
 *      UpdateStamp.class - specifically tailored for database updates
 *
 * DataStamp.class handles all logic for the above four classes. (with few exceptions)
 * They are created by calling super() on a specific DataStamp constructors
 *
 */
public class DataStamp implements Serializable {

    ///
    // DataStamp Error Handling
    //
    ///

    // Integers
    public static final String ERROR_TYPE_ID = "ErrorID";
    public static final String ERROR_TYPE_PINGRADIUS = "ErrorPingRadius";
    public static final String ERROR_TYPE_TIMESTAMP = "ErrorTimeStamp";
    public static final String ERROR_TYPE_ENABLED = "ErrorEnabled";
    // Doubles
    public static final String ERROR_TYPE_LATITUDE = "ErrorLatitude";
    public static final String ERROR_TYPE_LONGITUDE = "ErrorLongitude";
    // Strings
    public static final String ERROR_TYPE_TIMESTART = "ErrorTimeStart";
    public static final String ERROR_TYPE_TIMESTOP = "ErrorTimeStop";

    ///
    // DataStamp Control Flow
    //
    ///

    ///
    // DataStamp Types
    ///

    public static final String DATASTAMP_TYPE_ENTRY = "ENTRY";
    public static final String DATASTAMP_TYPE_VALUE = "VALUE";
    public static final String DATASTAMP_TYPE_ITERABLE = "ITERABLE";
    public static final String DATASTAMP_TYPE_UPDATE = "UPDATE";

    ///
    // DataStamp SubTypes
    ///

    public static final String ENTRYSTAMP_TYPE_FS = "EntryFS";
    public static final String ENTRYSTAMP_TYPE_FSMG = "EntryFSMG";

    public static final String VALUESTAMP_TYPE_FS = "ValueFS";
    public static final String VALUESTAMP_TYPE_FSMG = "ValueFSMG";

    public static final String ITERABLESTAMP_TYPE_RADAR = "IterableRADAR";
    public static final String ITERABLESTAMP_TYPE_DISPLAY = "IterableDisplay";

    public static final String UPDATESTAMP_TYPE_ENABLEBYID = "UpdateENABLEid";
    public static final String UPDATESTAMP_TYPE_ENABLEBYNAME = "UpdateENABLEname";
    public static final String UPDATESTAMP_TYPE_COUNT = "UpdateCount";

    ///
    // DataStamp Variables
    //
    ///

    private String DATASTAMP_TYPE;
    private String DATASTAMP_SUBTYPE;

    private int id;
    private double latitude;
    private double longitude;
    private String name;
    private String cardGroup;
    private String mallGroup;
    private boolean timeStamp;
    private String longDescription;
    private String shortDescription;
    private boolean enable;
    private int pingRadius;

    private String timeStart;
    private String timeStop;

    private int newEntity;
    private String newEntityString;

    private String DATASTAMP_ERROR;
    private boolean isReady = true;

    private int version;

    ///
    // DataStamp Constructors
    //
    ///

    ///
    // EntryStamp - without TimeStamp
    ///
    public DataStamp(String type, String subType,
                     int id,
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

        setTypes(type,subType);

        setId(id);
        setLatitude(latitude);
        setLongitude(longitude);
        setName(name);
        setCardGroup(cardGroup);
        setMallGroup(mallGroup);
        setTimeStamp(timeStamp);
        setLongDescription(longDescription);
        setShortDescription(shortDescription);
        setEnable(enable);
        setPingRadius(pingRadius);

    }

    ///
    // EntryStamp - with TimeStamp
    ///
    public DataStamp(String type, String subType,
                     int id,
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

        setTypes(type,subType);

        setId(id);
        setLatitude(latitude);
        setLongitude(longitude);
        setName(name);
        setCardGroup(cardGroup);
        setMallGroup(mallGroup);
        setTimeStamp(timeStamp);
        setLongDescription(longDescription);
        setShortDescription(shortDescription);
        setEnable(enable);
        setPingRadius(pingRadius);
        setTimeStart(timeStart);
        setTimeStop(timeStop);

    }

    ///
    // EntryStamp - mallStamp
    ///
    public DataStamp(String type, String subType,
                     int id,
                     double latitude,
                     double longitude,
                     String name,
                     boolean enable,
                     int pingRadius) {

        setTypes(type,subType);

        setId(id);
        setLatitude(latitude);
        setLongitude(longitude);
        setName(name);
        setEnable(enable);
        setPingRadius(pingRadius);
    }

    ///
    // ValueStamp - without TimeStamp
    // IterableStamp - Display FS without timeStamp
    ///
    public DataStamp(String type, String subType,
                     int id,
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

        setTypes(type,subType);

        setId(id);
        setLatitude(latitude);
        setLongitude(longitude);
        setName(name);
        setCardGroup(cardGroup);
        setMallGroup(mallGroup);
        setTimeStamp(timeStamp);
        setLongDescription(longDescription);
        setShortDescription(shortDescription);
        setEnable(enable);
        setPingRadius(pingRadius);

    }

    ///
    // ValueStamp - with TimeStamp
    // IterableStamp - Display FS with timeStamp
    ///
    public DataStamp(String type, String subType,
                     int id,
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

        setTypes(type,subType);

        setId(id);
        setLatitude(latitude);
        setLongitude(longitude);
        setName(name);
        setCardGroup(cardGroup);
        setMallGroup(mallGroup);
        setTimeStamp(timeStamp);
        setLongDescription(longDescription);
        setShortDescription(shortDescription);
        setEnable(enable);
        setPingRadius(pingRadius);
        setTimeStart(timeStart);
        setTimeStop(timeStop);

    }

    ///
    // ValueStamp - mallStamp
    // IterableStamp - Display FSMG
    ///
    public DataStamp(String type, String subType,
                     int id,
                     String latitude,
                     String longitude,
                     String name,
                     int enable,
                     int pingRadius) {

        setTypes(type,subType);

        setId(id);
        setLatitude(latitude);
        setLongitude(longitude);
        setName(name);
        setEnable(enable);
        setPingRadius(pingRadius);
    }

    ///
    // IterableStamp - Radar without TimeStamp
    ///
    public DataStamp(String type, String subType,
                     int id,
                     String latitude,
                     String longitude,
                     String name,
                     int pingRadius) {

        setTypes(type,subType);

        setId(id);
        setLatitude(latitude);
        setLongitude(longitude);
        setName(name);
        setPingRadius(pingRadius);


        this.timeStamp = false;

    }

    ///
    // IterableStamp - Radar with TimeStamp
    ///
    public DataStamp(String type, String subType,
                     int id,
                     String latitude,
                     String longitude,
                     String name,
                     int pingRadius,
                     String timeStart,
                     String timeStop) {

        setTypes(type,subType);

        setId(id);
        setLatitude(latitude);
        setLongitude(longitude);
        setName(name);
        setPingRadius(pingRadius);
        setTimeStart(timeStart);
        setTimeStop(timeStop);

        this.timeStamp = true;

    }

    ///
    // DataStamp Private Methods - variable declaration
    //
    ///

    private void setTypes(String type, String subType) {
        this.DATASTAMP_TYPE = type;
        this.DATASTAMP_SUBTYPE = subType;
    }

    private void setId(int id) {
        if(id < 0) {
            Log.d("DataStamp", DataStamp.ERROR_TYPE_ID);
            setError(DataStamp.ERROR_TYPE_ID);
            return;
        }

        this.id = id;
    }

    private void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    private void setLatitude(String latitude) {
        try {
            this.latitude = Double.parseDouble(latitude);
        }
        catch (NumberFormatException e) {
            Log.d("DataStamp", DataStamp.ERROR_TYPE_LATITUDE);
            setError(DataStamp.ERROR_TYPE_LATITUDE);
        }
    }

    private void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    private void setLongitude(String longitude) {
        try {
            this.longitude = Double.parseDouble(longitude);
        }
        catch (NullPointerException e) {
            Log.d("DataStamp", DataStamp.ERROR_TYPE_LONGITUDE);
            setError(DataStamp.ERROR_TYPE_LONGITUDE);
        }
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setCardGroup(String cardGroup) {
        this.cardGroup = cardGroup;
    }

    private void setMallGroup(String mallGroup) {
        this.mallGroup = mallGroup;
    }

    private void setTimeStamp(boolean timeStamp) {
        this.timeStamp = timeStamp;
    }

    private void setTimeStamp(int timeStamp) {
        if(timeStamp == 1) {
            this.timeStamp = true;
        }
        else if(timeStamp == 0) {
            this.timeStamp = false;
        }
        else {
            Log.d("DataStamp", DataStamp.ERROR_TYPE_TIMESTAMP);
            setError(DataStamp.ERROR_TYPE_TIMESTAMP);
        }
    }

    private void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    private void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    private void setEnable(boolean enable) {
        this.enable = enable;
    }

    private void setEnable(int enable) {
        if(enable == 1) {
            this.enable = true;
        }
        else if(enable == 0) {
            this.enable = false;
        }
        else {
            Log.d("DataStamp", DataStamp.ERROR_TYPE_ENABLED);
            setError(DataStamp.ERROR_TYPE_ENABLED);
        }
    }

    private void setPingRadius(int pingRadius) {
        if(pingRadius < 0) {
            Log.d("DataStamp", DataStamp.ERROR_TYPE_PINGRADIUS);
            setError(DataStamp.ERROR_TYPE_PINGRADIUS);
            return;
        }

        this.pingRadius = pingRadius;
    }

    private void setTimeStart(String timeStart) {
        if(isLegalTimePattern(timeStart)) {
            this.timeStart = timeStart;
        }
        else {
            Log.d("DataStamp", DataStamp.ERROR_TYPE_TIMESTART);
            setError(DataStamp.ERROR_TYPE_TIMESTART);
        }
    }

    private void setTimeStop(String timeStop) {
        if(isLegalTimePattern(timeStop)) {
            this.timeStop = timeStop;
        }
        else {
            Log.d("DataStamp", DataStamp.ERROR_TYPE_TIMESTOP);
            setError(DataStamp.ERROR_TYPE_TIMESTOP);
        }
    }

    private void setNewEntity(String newEntity) {
        this.newEntityString = newEntity;
    }

    private void setNewEntity(int newEntity) {
        this.newEntity = newEntity;
    }

    private void setVersion(int version) {
        this.version = version;
    }

    ///
    // DataStamp Public Methods
    //
    ///

    public String getError() {
        if(isReady) return null;

        return this.DATASTAMP_ERROR;
    }

    public String getType() {
        return this.DATASTAMP_TYPE;
    }

    public String getSubType() {
        return this.DATASTAMP_SUBTYPE;
    }

    public int getId() {
        return this.id;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public String getName() {
        return this.name;
    }

    public String getCardGroup() {
        return this.cardGroup;
    }

    public String getMallGroup() {
        return this.mallGroup;
    }

    public boolean hasTimeStamp() {
        return this.timeStamp;
    }

    public int getTimeStampStatus() {
        if(this.timeStamp) return 1;
        return 0;
    }

    public String getLongDescription() {
        return this.longDescription;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public int getEnableStatus() {
        if(this.enable) return 1;
        return 0;
    }

    public int getPingRadius() {
        return this.pingRadius;
    }

    public String getTimeStart() {
        return this.timeStart;
    }

    public String getTimeStop() {
        return this.timeStop;
    }

    public String getNewEntityString() {
        return this.newEntityString;
    }

    public int getNewEntity() {
        return this.newEntity;
    }

    public int getVersion() {
        return this.version;
    }

    ///
    // DataStamp Utilz
    //
    ///

    private void setError(String error) {
        this.DATASTAMP_ERROR = error;
        this.isReady = false;
    }

    private boolean isLegalTimePattern(String pattern) {
        String stampPattern = "^\\d\\d:\\d\\d";
        return pattern.matches(stampPattern);
    }

}

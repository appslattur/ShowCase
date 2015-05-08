package com.special.DataStorage.Messages;

import com.special.DataStorage.Instances.ValueStamp;

/**
 * @author Arnar Jonsson
 * @version 0.2
 * @since ..
 * Originally created on ..
 */
public class ValueMessage {
    ///
    // ValueMessage Control Flow
    //
    ///
    public static final String VALUEMESSAGE_TYPE_SINGLE = "ValueSingle";
    public static final String VALUEMESSAGE_TYPE_MULTIPLE = "ValueAll";
    public static final String VALUEMESSAGE_TYPE_CALLBACK = "ValueCallBack";

    public static final String VALUEMESSAGE_LOCATION_FS = "LocationFS";
    public static final String VALUEMESSAGE_LOCATION_FSMG = "LocationFSMG";

    ///
    // ValueMessage Variables
    //
    ///
    private String VALUESTAMP_TYPE;
    private String VALUESTAMP_LOCATION;

    private ValueStamp[] stamps;
    private String message;
    private boolean isError;
    private int id;

    // Input
    public ValueMessage(String location, int id) {

        setType(ValueMessage.VALUEMESSAGE_TYPE_SINGLE);
        setLocation(location);
        setId(id);
    }

    // Input
    public ValueMessage() {

        setType(ValueMessage.VALUEMESSAGE_TYPE_MULTIPLE);
    }

    public ValueMessage(ValueStamp stamp) {

        setType(ValueMessage.VALUEMESSAGE_TYPE_CALLBACK);
        setDataStamps(stamp);
    }

    // Output
    public ValueMessage(ValueStamp[] stamps) {

        setType(ValueMessage.VALUEMESSAGE_TYPE_CALLBACK);
        setDataStamps(stamps);
    }

    // Output
    public ValueMessage(String message, boolean isError) {

        setType(ValueMessage.VALUEMESSAGE_TYPE_CALLBACK);
        setMessage(message);
        setError(isError);
    }

    ///
    // ValueMessage Public Methods
    //
    ///

    public String getType() {
        return this.VALUESTAMP_TYPE;
    }

    public String getLocation() {
        return this.VALUESTAMP_LOCATION;
    }

    public ValueStamp[] getStamps() {
        return this.stamps;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean isError() {
        return this.isError;
    }

    public int getId() {
        return this.id;
    }

    ///
    // ValueMessage Private Methods
    //
    ///

    private void setType(String type) {
        this.VALUESTAMP_TYPE = type;
    }

    private void setLocation(String location) {
        this.VALUESTAMP_LOCATION = location;
    }

    private void setDataStamps(ValueStamp stamp) {
        this.stamps = new ValueStamp[] { stamp };
    }

    private void setDataStamps(ValueStamp[] stamps) {
        this.stamps = stamps;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    private void setError(boolean isError) {
        this.isError = isError;
    }

    private void setId(int id) {
        this.id = id;
    }
}

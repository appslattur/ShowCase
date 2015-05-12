package com.special.DataStorage.Messages;

import com.special.DataStorage.Instances.IterableStamp;

/**
 * @author Arnar Jonsson
 * @version 0.2
 * @since ..
 * Originally created on ..
 */
public class IterableMessage {

    ///
    // IterableMessage Control Flow
    //
    ///
    public static final String ITERABLEMESSAGE_TYPE_RADAR = "IterableMessageRadar";
    public static final String ITERABLEMESSAGE_TYPE_DISPLAY = "IterableMessageDisplay";
    public static final String ITERABLEMESSAGE_TYPE_VIEW = "IterableMessageView";

    ///
    // IterableMessage Variables
    //
    ///
    private String ITERABLEMESSAGE_TYPE;
    private IterableStamp[] stamps;

    private String message;
    private boolean isError;

    ///
    // IterableMessage Constructors
    //
    ///

    public IterableMessage() {
        setType(IterableMessage.ITERABLEMESSAGE_TYPE_VIEW);
    }

    // Input
    public IterableMessage(String type) {

        setType(type);
    }

    // Input
    public IterableMessage(IterableStamp stamp) {

        setType(IterableMessage.ITERABLEMESSAGE_TYPE_DISPLAY);
        setStamps(stamp);
    }

    // Output
    public IterableMessage(IterableStamp[] stamps) {

        setStamps(stamps);
    }


    // Output
    public IterableMessage(String message, boolean isError) {

        setMessage(message);
        setError(isError);
    }

    ///
    // IterableMessage Public Methods
    //
    ///

    public String getType() {
        return this.ITERABLEMESSAGE_TYPE;
    }

    public IterableStamp[] getStamps() {
        return this.stamps;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean isError() {
        return this.isError;
    }

    ///
    // IterableMessage Private Methods
    //
    ///

    private void setType(String type) {
        this.ITERABLEMESSAGE_TYPE = type;
    }

    private void setStamps(IterableStamp stamp) {
        this.stamps = new IterableStamp[] { stamp };
    }

    private void setStamps(IterableStamp[] stamps) {
        this.stamps = stamps;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    private void setError(boolean isError) {
        this.isError = isError;
    }
}

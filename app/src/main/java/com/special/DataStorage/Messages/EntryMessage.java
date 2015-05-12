package com.special.DataStorage.Messages;

import com.special.DataStorage.Instances.EntryStamp;

/**
 * @author Arnar Jonsson
 * @version 0.2
 *
 * EntryMessage
 * Insertion message for the in-app database
 */
public class EntryMessage {

    ///
    // EntryMessage Control Flow
    //
    ///


    ///
    // EntryMessage Variables
    //
    ///
    private EntryStamp[] stamps;
    private String message;
    private boolean isError;


    public EntryMessage(EntryStamp stamp) {

        setStamps(stamp);
    }

    public EntryMessage(EntryStamp[] stamps) {

        setStamps(stamps);
    }

    public EntryMessage(String message, boolean isError) {

        setMessage(message);
        setError(isError);
    }

    ///
    // EntryMessage Public Methods
    //
    ///

    public EntryStamp[] getStamps() {
        return this.stamps;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean isError() {
        return this.isError;
    }

    ///
    // EntryMessage Private Methods
    //
    ///

    private void setStamps(EntryStamp[] stamps) {
        this.stamps = stamps;
    }

    private void setStamps(EntryStamp stamp) {
        this.stamps = new EntryStamp[] { stamp };
    }

    private void setMessage(String message) {
        this.message = message;
    }

    private void setError(boolean isError) {
        this.isError = isError;
    }
}

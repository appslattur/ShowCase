package com.special.DataStorage.Messages;

/**
 * @author Arnar Jonsson
 * @version 0.2
 *
 * UpdateMessage
 * Update and utility message for the in-app database
 */
public class UpdateMessage {

    ///
    // UpdateMessage Control Flow
    //
    ///

    public static final String UPDATEMESSAGE_TYPE_UPDATE = "UpdateUpdate";
    public static final String UPDATEMESSAGE_TYPE_UTIL = "UpdateUtil";
    public static final String UPDATEMESSAGE_TYPE_ERROR = "UpdateError";

    public static final String UPDATEMESSAGE_SUBTYPE_ENABLEID = "UpdateEnableId";
    public static final String UPDATEMESSAGE_SUBTYPE_ENABLENAME = "UpdateEnableName";

    public static final String UPDATEMESSAGE_UTILTYPE_COUNT = "UpdateCount";

    public static final String UPDATEMESSAGE_LOCATION_FS = "UpdateFS";
    public static final String UPDATEMESSAGE_LOCATION_FSMG = "UpdateFSMG";

    ///
    // UpdateMessage Variables
    //
    ///

    private int id;
    private String name;
    private int version;
    private boolean isError;
    private String message;

    private int newEntity;

    private String UPDATEMESSAGE_TYPE;
    private String UPDATEMESSAGE_SUBTYPE;
    private String UPDATEMESSAGE_LOCATION;

    ///
    // UpdateMessage Constructors
    //
    ///

    ///
    // Util
    ///
    public UpdateMessage(String subType) {

        setTypes(UPDATEMESSAGE_TYPE_UTIL, subType);
    }

    ///
    // Update - id
    // Update - Name
    ///

    public UpdateMessage(String location, int id, int newEntity) {

        setTypes(UPDATEMESSAGE_TYPE_UPDATE, UPDATEMESSAGE_SUBTYPE_ENABLEID);
        setLocation(location);
        setId(id);
        setEntity(newEntity);
    }

    public UpdateMessage(String location, String name, int newEntity) {

        setTypes(UPDATEMESSAGE_TYPE_UPDATE, UPDATEMESSAGE_SUBTYPE_ENABLENAME);
        setLocation(location);
        setName(name);
        setEntity(newEntity);
    }

    ///
    // Error
    ///

    public UpdateMessage(String message, boolean isError) {

        this.UPDATEMESSAGE_TYPE = UPDATEMESSAGE_TYPE_ERROR;
        setMessage(message);
        setErrorStatus(isError);
    }

    ///
    // Callback update
    ///

    public UpdateMessage(boolean isError) {

        this.UPDATEMESSAGE_TYPE = UPDATEMESSAGE_TYPE_UTIL;
        setErrorStatus(!isError);
    }

    ///
    // Callback util
    ///

    public UpdateMessage(int version) {

        this.UPDATEMESSAGE_TYPE = UPDATEMESSAGE_TYPE_UTIL;
        setVersion(version);
    }

    ///
    // UpdateMessage Public Methods
    //
    ///


    public String getType() {
        return this.UPDATEMESSAGE_TYPE;
    }

    public String getSubType() {
        return this.UPDATEMESSAGE_SUBTYPE;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getNewEntity() {
        return this.newEntity;
    }

    public String getLocation() {
        return this.UPDATEMESSAGE_LOCATION;
    }

    public int getVersion() {
        return this.version;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean isError() {
        return this.isError;
    }



    ///
    // UpdateMessage Private Methods
    //
    ///

    private void setTypes(String type, String subType) {
        this.UPDATEMESSAGE_TYPE = type;
        this.UPDATEMESSAGE_SUBTYPE = subType;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setEntity(int entity) {
        this.newEntity = entity;
    }

    private void setLocation(String location) {
        this.UPDATEMESSAGE_LOCATION = location;
    }

    private void setVersion(int version) {
        this.version = version;
    }

    private void setErrorStatus(boolean isError) {
        this.isError = isError;
    }

    private void setMessage(String message) {
        this.message = message;
    }
}

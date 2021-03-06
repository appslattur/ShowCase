package com.special.DataBaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.special.DataStorage.Instances.EntryStamp;
import com.special.DataStorage.Instances.IterableStamp;
import com.special.DataStorage.Instances.ValueStamp;
import com.special.DataStorage.Messages.EntryMessage;
import com.special.DataStorage.Messages.IterableMessage;
import com.special.DataStorage.Messages.UpdateMessage;
import com.special.DataStorage.Messages.ValueMessage;
import com.special.DataStorage.Objects.DataStamp;

import java.sql.SQLException;

/**
 * DataBaseController
 *
 * Handles logic and utility functions for the in-app database DataBaseHelper.class
 */
public class DataBaseController {

    ///
    // DataBaseHelper Variables
    //
    ///

    private int flagCount = 0;

    private SQLiteDatabase db;
    private DataBaseHelper dbHelper;
    private Context context;

    private String[] FS_allColumns = {
            DataBaseHelper.FS_COLUMN_ID,
            DataBaseHelper.FS_COLUMN_LATITUDE,
            DataBaseHelper.FS_COLUMN_LONGITUDE,
            DataBaseHelper.FS_COLUMN_SHOPNAME,
            DataBaseHelper.FS_COLUMN_CARDGROUP,
            DataBaseHelper.FS_COLUMN_MALLGROUP,
            DataBaseHelper.FS_COLUMN_HASTIMELIMIT,
            DataBaseHelper.FS_COLUMN_LONGDESCRIPTION,
            DataBaseHelper.FS_COLUMN_SHORTDESCRIPTION,
            DataBaseHelper.FS_COLUMN_ENABLED,
            DataBaseHelper.FS_COLUMN_PINGRADIUS
    };

    private String[] FSTS_allColumns = {
            DataBaseHelper.FSTS_COLUMN_ID,
            DataBaseHelper.FSTS_COLUMN_TIMESTART,
            DataBaseHelper.FSTS_COLUMN_TIMESTOP
    };

    private String[] FSMG_allColumns = {
            DataBaseHelper.FSMG_COLUMN_ID,
            DataBaseHelper.FSMG_COLUMN_LATITUDE,
            DataBaseHelper.FSMG_COLUMN_LONGITUDE,
            DataBaseHelper.FSMG_COLUMN_NAME,
            DataBaseHelper.FSMG_COLUMN_ENABLED,
            DataBaseHelper.FSMG_COLUMN_PINGRADIUS
    };

    private String[] FS_timeStamps = {
            FSTS_allColumns[1],
            FSTS_allColumns[2]
    };

    private String[] FS_radarIterableEntry = {
            FS_allColumns[0],
            FS_allColumns[1],
            FS_allColumns[2],
            FS_allColumns[3],
            FS_allColumns[9],
            FS_allColumns[10]

    };

    private String[] FS_radarIterableMall = {
            FSMG_allColumns[0],
            FSMG_allColumns[1],
            FSMG_allColumns[2],
            FSMG_allColumns[3],
            FSMG_allColumns[4],
            FSMG_allColumns[5]

    };


    ///
    // DataBaseController Constructors
    //
    ///

    /**
     * DatabaseController
     * Allows connection to the Appslattur database
     * Do not use unless called inside an <b>AsyncTask</b>
     * or other <b>threads</b> separated from the main thread
     * @param context From the calling application
     */
    public DataBaseController(Context context) {
        dbHelper = new DataBaseHelper(context);
        this.context = context;
    }

    ///
    // DataBaseController Public Methods
    //
    ///

    /**
     * Opens the Appslattur database
     * @throws java.sql.SQLException
     */
    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    /**
     * Closes the Appslattur database
     */
    public void close() {
        dbHelper.close();
    }

    /**
     * @param message EntryMessage containing DataStamps for insertion
     * @return EntryMessage
     */
    public EntryMessage insertEntries(EntryMessage message) {
        return manageInsertion(message);
    }

    /**
     * @param message ValueMessage containing extraction flags
     * @return ValueMessage
     */
    public ValueMessage extractEntries(ValueMessage message) {
        return manageExtraction(message);
    }

    /**
     * @param message IterableMessage containing iterable flags
     * @return IterableMessage
     */
    public IterableMessage extractIterables(IterableMessage message) {
        return manageIterables(message);
    }

    /**
     * @param message UpdateMessage containing update flags
     * @return UpdateMessage
     */
    public UpdateMessage executeUpdates(UpdateMessage message) {
        return manageUpdates(message);
    }


    ///
    // DataBaseController insertion methods
    ///

    /**
     * Insertion of FSMG row
     */
    private String insertFSMGEntry(EntryStamp stamp) {

        String report = "";

        ContentValues values = new ContentValues();
        values.put(FSMG_allColumns[0], stamp.getId());
        values.put(FSMG_allColumns[1], stamp.getLatitude());
        values.put(FSMG_allColumns[2], stamp.getLongitude());
        values.put(FSMG_allColumns[3], stamp.getName());
        values.put(FSMG_allColumns[4], stamp.getEnableStatus());
        values.put(FSMG_allColumns[5], stamp.getPingRadius());

        long insertId = db.insert(DataBaseHelper.FSMG_TABLE_NAME, null, values);

        values.clear();

        report += "Insertion of FSMG id : " + stamp.getId() + " returned the corresponding id : " +
                insertId + "\n";

        return report;
    }

    /**
     * Insrtion of FS row
     */
    private String insertFSEntry(EntryStamp stamp) {

        String report = "";

        ContentValues values = new ContentValues();
        values.put(FS_allColumns[0], stamp.getId());
        values.put(FS_allColumns[1], stamp.getLatitude() + "");
        values.put(FS_allColumns[2], stamp.getLongitude() + "");
        values.put(FS_allColumns[3], stamp.getName());
        values.put(FS_allColumns[4], stamp.getCardGroup());
        values.put(FS_allColumns[5], stamp.getMallGroup());
        values.put(FS_allColumns[6], stamp.getTimeStampStatus());
        values.put(FS_allColumns[7], stamp.getLongDescription());
        values.put(FS_allColumns[8], stamp.getShortDescription());
        values.put(FS_allColumns[9], stamp.getEnableStatus());
        values.put(FS_allColumns[10], stamp.getPingRadius());

        long insertId = db.insert(DataBaseHelper.FS_TABLE_NAME, null, values);

        report += "Insertion of FS id : " + stamp.getId() + " returned the corresponding id : " +
                insertId + "\n";

        values.clear();

        if(stamp.hasTimeStamp()) {

            values = new ContentValues();
            values.put(FSTS_allColumns[0], stamp.getId());
            values.put(FSTS_allColumns[1], stamp.getTimeStart());
            values.put(FSTS_allColumns[2], stamp.getTimeStop());

            insertId = db.insert(DataBaseHelper.FSTS_TABLE_NAME, null, values);

            report += "Insertion of FSTS id : " + stamp.getId() + " returned the corresponding id : " +
                    insertId + "\n";

            values.clear();
        }

        return report;

    }

    private EntryMessage manageInsertion(EntryMessage message) {

        String report = "";

        for(EntryStamp stamp : message.getStamps()) {

            if(stamp.getSubType().equals(DataStamp.ENTRYSTAMP_TYPE_FS)) {
                report += insertFSEntry(stamp);
            }
            else if(stamp.getSubType().equals(DataStamp.ENTRYSTAMP_TYPE_FSMG)) {
                report += insertFSMGEntry(stamp);
            }
            else {
                report += "Failed to insert entry w. id : " + stamp.getId() +
                        " and w. type : " + stamp.getSubType() + " - not recognized" + "\n";
            }

        }

        return new EntryMessage(report, false);

    }

    ///
    // DataBaseController extraction methods
    ///

    private ValueStamp extractFSMGEntry(Cursor cursor) {

        return new ValueStamp(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4),
                cursor.getInt(5));

    }

    private ValueStamp extractFSEntry(Cursor cursor) {

        ValueStamp stamp;

        if(cursor.getInt(6) == 1) {

            Cursor tSCursor = fetchTimeStamps(cursor.getInt(0));

            stamp = new ValueStamp(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getInt(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getInt(9),
                    cursor.getInt(10),
                    tSCursor.getString(0),
                    tSCursor.getString(1)
            );

            tSCursor.close();

            return stamp;
        }

        stamp = new ValueStamp(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getInt(6),
                cursor.getString(7),
                cursor.getString(8),
                cursor.getInt(9),
                cursor.getInt(10)
        );

        return stamp;
    }

    private ValueMessage manageMassExtraction() {

        Cursor cursorFS = db.query(
                DataBaseHelper.FS_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );


        Cursor cursorFSMG = db.query(
                DataBaseHelper.FSMG_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );

        ValueStamp[] stamps = new ValueStamp[
                cursorFS.getCount() + cursorFSMG.getCount()];

        int stampCount = 0;

        if(cursorFS.moveToFirst()) {
            do {
                stamps[stampCount++] = extractFSEntry(cursorFS);
            }
            while (cursorFS.moveToNext());
        }


        if(cursorFSMG.moveToFirst()) {
            do {
                stamps[stampCount++] = extractFSMGEntry(cursorFSMG);
            }
            while (cursorFSMG.moveToNext());
        }

        cursorFS.close();
        cursorFSMG.close();

        return new ValueMessage(stamps);
    }

    private ValueMessage manageSingleExtraction(ValueMessage message) {

        Cursor cursor;
        // TODO: FIX AFTER JDK UPDATE
        ValueStamp stamp = null;

        if(message.getLocation().equals(ValueMessage.VALUEMESSAGE_LOCATION_FS)) {
            cursor = db.query(DataBaseHelper.FS_TABLE_NAME,
                    null,
                    FS_allColumns[0] + " = ?",
                    new String[] { message.getId()+""},
                    null,
                    null,
                    null
            );
        }
        else if(message.getLocation().equals(ValueMessage.VALUEMESSAGE_LOCATION_FSMG)) {
            cursor = db.query(DataBaseHelper.FSMG_TABLE_NAME,
                    null,
                    FSMG_allColumns[0] + " = ?",
                    new String[] { message.getId()+""},
                    null,
                    null,
                    null
            );
        }
        else {
            return new ValueMessage("Failure to recognize ValueMessage location", true);
        }

        if(cursor.moveToFirst()) {
            stamp = extractFSMGEntry(cursor);
        }

        return new ValueMessage(stamp);

    }

    private ValueMessage manageExtraction(ValueMessage message) {

        if(message.getType().equals(ValueMessage.VALUEMESSAGE_TYPE_SINGLE)) {
            return manageSingleExtraction(message);
        }
        else if(message.getType().equals(ValueMessage.VALUEMESSAGE_TYPE_MULTIPLE)) {
            return manageMassExtraction();
        }
        else {
            return new ValueMessage("Message subtype not recognized - manageExtraction -- subType value", true);
        }

    }

    ///
    // DataBaseController iterable methods
    ///

    private IterableMessage fetchViewIterables() {

        Cursor cursor = db.query(true,
                DataBaseHelper.FS_TABLE_NAME,
                FS_allColumns,
                null,
                null,
                FS_allColumns[3],
                null,
                null,
                null
        );

        IterableStamp[] stamps = new IterableStamp[cursor.getCount()];
        int stampCount = 0;

        if(cursor.moveToFirst()) {
            do {
                stamps[stampCount++] = fetchDisplayFS(cursor);
            }
            while (cursor.moveToNext());
        }

        cursor.close();

        return new IterableMessage(stamps);

    }

    private IterableStamp fetchDisplayFS(Cursor cursor) {

        if(cursor.getInt(6) == 1) {

            Cursor tSCursor = fetchTimeStamps(cursor.getInt(0));
            tSCursor.moveToFirst();

            IterableStamp stamp = new IterableStamp(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getInt(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getInt(9),
                    cursor.getInt(10),
                    tSCursor.getString(0),
                    tSCursor.getString(1));

            tSCursor.close();

            return stamp;
        }

        return new IterableStamp(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getInt(6),
                cursor.getString(7),
                cursor.getString(8),
                cursor.getInt(9),
                cursor.getInt(10));
    }

    private IterableStamp fetchDisplayFSMG(Cursor cursor) {

        return new IterableStamp(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4),
                cursor.getInt(5));
    }

    private IterableMessage fetchDisplayIterables(IterableMessage message) {

        if(message.getStamps()[0].isMall()) {

            Cursor cursorFS = db.query(
                    DataBaseHelper.FS_TABLE_NAME,
                    FS_allColumns,
                    FS_allColumns[5] + " = ?",
                    new String[] { message.getStamps()[0].getName() },
                    null,
                    null,
                    FS_allColumns[0]
            );

            IterableStamp[] stamps = new IterableStamp[cursorFS.getCount()];
            int stampCount = 0;

            if(cursorFS.moveToFirst()) {
                do {
                    stamps[stampCount++] = fetchDisplayFS(cursorFS);
                }
                while (cursorFS.moveToNext());
            }

            cursorFS.close();

            return new IterableMessage(stamps);
        }


        Cursor cursor = db.query(
                DataBaseHelper.FS_TABLE_NAME,
                FS_allColumns,
                FS_allColumns[0] + " = ?",
                new String[] { message.getStamps()[0].getId()+"" },
                null,
                null,
                null
        );

        if(cursor.moveToFirst()) {
            IterableStamp stamp = fetchDisplayFS(cursor);

            cursor.close();

            return new IterableMessage(stamp);
        }

        return new IterableMessage("Something went wrong", true);
    }

    private IterableStamp fetchRadarFS(Cursor cursor) {

        if(cursor.getInt(6) == 1) {

            Cursor tSCursor = fetchTimeStamps(cursor.getInt(0));

            IterableStamp stamp = new IterableStamp(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(10),
                    tSCursor.getString(0),
                    tSCursor.getString(1));

            tSCursor.close();

            return stamp;
        }

        return new IterableStamp(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(10),
                false);
    }

    private IterableStamp fetchRadarFSMG(Cursor cursor) {

        return new IterableStamp(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4),
                true);
    }
    private IterableMessage fetchRadarIterables() {

        Cursor cursorMall = db.query(
                DataBaseHelper.FSMG_TABLE_NAME,
                null,
                FSMG_allColumns[4] + " = ?",
                new String[] { 1+"" },
                null,
                null,
                null
        );

        Cursor cursorFS = db.query(
                DataBaseHelper.FS_TABLE_NAME,
                FS_allColumns,
                FS_allColumns[9] + " = ? AND " +
                        FS_allColumns[5] + " = ?",
                new String[] { 1+"", "General" },
                null,
                null,
                null
        );

        cursorMall.moveToFirst();
        cursorFS.moveToFirst();

        IterableStamp[] stamps = new IterableStamp[
                cursorMall.getCount() + cursorFS.getCount()];
        int stampCount = 0;
        while (!cursorMall.isAfterLast()) {

            stamps[stampCount++] = fetchRadarFSMG(cursorMall);

            cursorMall.moveToNext();
        }
        while (!cursorFS.isAfterLast()) {

            stamps[stampCount++] = fetchRadarFS(cursorFS);

            cursorFS.moveToNext();
        }

        cursorMall.close();
        cursorFS.close();

        return new IterableMessage(stamps);
    }


    private IterableMessage manageIterables(IterableMessage message) {

        if(message.getType().equals(IterableMessage.ITERABLEMESSAGE_TYPE_RADAR)) {
            return fetchRadarIterables();
        }
        else if(message.getType().equals(IterableMessage.ITERABLEMESSAGE_TYPE_DISPLAY)) {
            return fetchDisplayIterables(message);
        }
        else if(message.getType().equals(IterableMessage.ITERABLEMESSAGE_TYPE_VIEW)) {
            return fetchViewIterables();
        }
        else {
            return new IterableMessage("Message subtype not recognized  - manageIterables -- subType Iterable", true);
        }

    }

    ///
    // DataBaseController Update Methods
    ///

    private UpdateMessage manageUtilities(UpdateMessage message) {

        if(message.getSubType().equals(UpdateMessage.UPDATEMESSAGE_UTILTYPE_COUNT)) {
            return new UpdateMessage(isEmpty());
        }
        else {
            return new UpdateMessage("message type not recognized - manageUtilities", true);
        }

    }

    private UpdateMessage updateEnableName(UpdateMessage message) {

        ContentValues values = new ContentValues();
        boolean worked = false;

        if(message.getLocation().equals(UpdateMessage.UPDATEMESSAGE_LOCATION_FS)) {
            values.put(FS_allColumns[9], message.getNewEntity());

            db.update(
                    DataBaseHelper.FS_TABLE_NAME,
                    values,
                    FS_allColumns[5] + " = ?",
                    new String[] { message.getName() }
            );
            worked = true;
        }
        else if(message.getLocation().equals(UpdateMessage.UPDATEMESSAGE_LOCATION_FSMG)) {
            values.put(FSMG_allColumns[4], message.getNewEntity());

            db.update(
                    DataBaseHelper.FSMG_TABLE_NAME,
                    values,
                    FSMG_allColumns[3] + " = ?",
                    new String[] { message.getName() }
            );
            worked = true;
        }
        else {
            worked = false;
        }

        values.clear();

        return new UpdateMessage(worked);

    }

    private UpdateMessage updateEnableId(UpdateMessage message) {

        ContentValues values = new ContentValues();
        boolean worked;

        if(message.getLocation().equals(UpdateMessage.UPDATEMESSAGE_LOCATION_FS)) {
            values.put(FS_allColumns[9], message.getNewEntity());

            db.update(
                    DataBaseHelper.FS_TABLE_NAME,
                    values,
                    FS_allColumns[0] + " = ?",
                    new String[] { message.getId()+"" }
            );
            worked = true;
        }
        else if(message.getLocation().equals(UpdateMessage.UPDATEMESSAGE_LOCATION_FSMG)) {
            values.put(FSMG_allColumns[4], message.getNewEntity());

            db.update(
                    DataBaseHelper.FSMG_TABLE_NAME,
                    values,
                    FSMG_allColumns[0] + " = ?",
                    new String[] { message.getId()+"" }
            );
            worked = true;
        }
        else {
            worked = false;
        }

        values.clear();

        return new UpdateMessage(worked);

    }

    private UpdateMessage updateTables(UpdateMessage message) {

        if(message.getSubType().equals(UpdateMessage.UPDATEMESSAGE_SUBTYPE_ENABLEID)) {
            return updateEnableId(message);
        }
        else if(message.getSubType().equals(UpdateMessage.UPDATEMESSAGE_SUBTYPE_ENABLENAME)) {
            return updateEnableName(message);
        }
        else {
            return new UpdateMessage("UpdateMessage subType not recognized, updateTables", true);
        }

    }

    private UpdateMessage manageUpdates(UpdateMessage message) {

        if(message.getType().equals(UpdateMessage.UPDATEMESSAGE_TYPE_UPDATE)) {
            return updateTables(message);
        }
        else if(message.getType().equals(UpdateMessage.UPDATEMESSAGE_TYPE_UTIL)) {
            return manageUtilities(message);
        }
        else {
            return new UpdateMessage("Message type not recognized - manageUpdates", true);
        }

    }



    ///
    // DataBaseController Utilz
    //
    ///

    private Cursor fetchTimeStamps(int id) {

        Cursor cursor = db.query(
                DataBaseHelper.FSTS_TABLE_NAME,
                new String[] { FSTS_allColumns[1], FSTS_allColumns[2] },
                FSTS_allColumns[0] + " = ?",
                new String[] { id+"" },
                null,
                null,
                null
        );

        if(cursor.moveToFirst()) {
            return cursor;
        }

        return null;
    }

    private boolean isEmpty() {

        Cursor cursor = db.query(
                DataBaseHelper.FS_TABLE_NAME,
                new String[] { FS_allColumns[0] },
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();

        int count = cursor.getCount();

        cursor.close();

        cursor = db.query(
                DataBaseHelper.FSMG_TABLE_NAME,
                new String[] { FSMG_allColumns[0] },
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();
        count += cursor.getCount();

        return count == 0;
    }

}

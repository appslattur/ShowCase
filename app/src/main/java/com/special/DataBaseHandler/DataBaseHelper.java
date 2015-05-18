package com.special.DataBaseHandler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * DataBaseHelper
 *
 * The in-app database
 *
 * Contains 3 tables
 * Table FS - each row contains details about discounts and where they are to be had
 * Table FSTS - timeStamps for specific rows in the FS table (if a discount is only available
 *      at a certain time)
 * Table FSMG - each row contain details about "group" locations, such as malls and markets
 *
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    // DATABASE NAME AND INITIAL VERSION
    private static final String DATABASE_NAME = "AppslatturDB.db";
    private static final int DATABASE_VERSION = 1;

    // INITIAL TABLE OF THE DATABASE
    public static final String FS_TABLE_NAME = "FSStudentCard";
    public static final String FS_COLUMN_ID = "_ID";
    public static final String FS_COLUMN_LATITUDE = "latitude";
    public static final String FS_COLUMN_LONGITUDE = "longitude";
    public static final String FS_COLUMN_SHOPNAME = "shopname";
    public static final String FS_COLUMN_CARDGROUP = "cardgroup";
    public static final String FS_COLUMN_MALLGROUP = "mallgroup";
    public static final String FS_COLUMN_HASTIMELIMIT = "hastimelimit";
    public static final String FS_COLUMN_LONGDESCRIPTION = "longdescription";
    public static final String FS_COLUMN_SHORTDESCRIPTION = "shortdescription";
    public static final String FS_COLUMN_ENABLED = "enabled";
    public static final String FS_COLUMN_PINGRADIUS = "pingradius";

    // SECONDARY (HELPER TABLE)
    public static final String FSTS_TABLE_NAME = "FSTimeStamps";
    public static final String FSTS_COLUMN_ID = "_ID";
    public static final String FSTS_COLUMN_TIMESTART = "timestart";
    public static final String FSTS_COLUMN_TIMESTOP = "timestop";

    // THIRD (HELPER TABLE)
    public static final String FSMG_TABLE_NAME = "FSMallGroups";
    public static final String FSMG_COLUMN_ID = "_ID";
    public static final String FSMG_COLUMN_LATITUDE = "latitude";
    public static final String FSMG_COLUMN_LONGITUDE = "longitude";
    public static final String FSMG_COLUMN_NAME = "mallname";
    public static final String FSMG_COLUMN_ENABLED = "enabled";
    public static final String FSMG_COLUMN_PINGRADIUS = "pingradius";

    // INITIAL TABLE CREATE STATEMENT
    // TODO : Experiment with AUTOINCREMENT
    private static final String APPSLATTUR_DATABASE_CREATE =
            "CREATE TABLE " + FS_TABLE_NAME + "(" +
                    FS_COLUMN_ID + " INTEGER NOT NULL, " +
                    FS_COLUMN_LATITUDE + " TEXT NOT NULL, " +
                    FS_COLUMN_LONGITUDE + " TEXT NOT NULL, " +
                    FS_COLUMN_SHOPNAME + " TEXT NOT NULL, " +
                    FS_COLUMN_CARDGROUP + " TEXT NOT NULL, " +
                    FS_COLUMN_MALLGROUP + " TEXT NOT NULL, " +
                    FS_COLUMN_HASTIMELIMIT + " INTEGER NOT NULL, " +
                    FS_COLUMN_LONGDESCRIPTION + " TEXT NOT NULL, " +
                    FS_COLUMN_SHORTDESCRIPTION + " TEXT NOT NULL, " +
                    FS_COLUMN_ENABLED + " INTEGER NOT NULL, " +
                    FS_COLUMN_PINGRADIUS + " INTEGER NOT NULL, " +
                    "PRIMARY KEY (" + FS_COLUMN_ID + ") " + ");";

    // SECONDARY TABLE CREATE STATEMENT
    private static final String SECONDARY_DATABASE_CREATE =
            "CREATE TABLE " + FSTS_TABLE_NAME + "(" +
                    FSTS_COLUMN_ID + " INTEGER NOT NULL, " +
                    FSTS_COLUMN_TIMESTART + " TEXT NOT NULL, " +
                    FSTS_COLUMN_TIMESTOP + " TEXT NOT NULL, " +
                    "PRIMARY KEY (" + FSTS_COLUMN_ID + ") " + ");";

    // THIRD TABLE CREATE STATEMENT
    private static final String THIRD_DATABASE_CREATE =
            "CREATE TABLE " + FSMG_TABLE_NAME + "(" +
                    FSMG_COLUMN_ID + " INTEGER NOT NULL, " +
                    FSMG_COLUMN_LATITUDE + " TEXT NOT NULL, " +
                    FSMG_COLUMN_LONGITUDE + " TEXT NOT NULL, " +
                    FSMG_COLUMN_NAME + " TEXT NOT NULL, " +
                    FSMG_COLUMN_ENABLED + " INTEGER NOT NULL, " +
                    FSMG_COLUMN_PINGRADIUS + " INTEGER NOT NULL, " +
                    "PRIMARY KEY (" + FSMG_COLUMN_ID + ", " + FSMG_COLUMN_NAME + ") " + ");";

    /**
     * DataBaseHelper
     * Handles creation and management of
     * the SQLiteDatabase Appslattur.db
     * @param context ApplicationContext or other usable Contexts
     */
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(APPSLATTUR_DATABASE_CREATE);
        db.execSQL(SECONDARY_DATABASE_CREATE);
        db.execSQL(THIRD_DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + APPSLATTUR_DATABASE_CREATE);
        db.execSQL("DROP TABLE IF EXISTS " + SECONDARY_DATABASE_CREATE);
        db.execSQL("DROP TABLE IF EXISTS" + THIRD_DATABASE_CREATE);
        onCreate(db);
    }
}

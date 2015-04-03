package com.special.appslattur.DatabaseHelper;

import android.content.Context;

import com.special.appslattur.LocationChainStructure.LocationChain;

import java.util.ArrayList;

/**
 * Created by Ari on 3.4.2015.
 */
public abstract class TestHandler extends Context {
    private static Context mContext;
    private static DataBaseHelper db;
    private TestHandler(){}

    public static void initHandler(Context c){
        mContext = c;
        db = new DataBaseHelper(mContext);
    }
    public static ArrayList<LocationChain> getLocations(){
        return db.getLocationList();
    }
    public static String getShortDescById(int id){
        return db.getShortDescById(id);
    }

    // Private constructor




}

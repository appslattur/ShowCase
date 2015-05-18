package com.special.ServiceImp.Interfaces;

import android.location.Location;

import com.special.DataStorage.Instances.IterableStamp;

import java.util.ArrayList;

/**
 * AppInterface.class
 *
 * Handles message calls within AppService.class
 */
public interface AppInterface {


    // DataBase Signal Function
    public void onDataBaseChange(int version);

    // Radar Location Update Function
    public void onLocationChange(Location loc);

    // Notification Signal Function
    public void onNotificationRequest(IterableStamp stamp);

    // Logging Function
    public void onStatusUpdate(String desc);

    // Shit
    public ArrayList<String> getLogList();
}

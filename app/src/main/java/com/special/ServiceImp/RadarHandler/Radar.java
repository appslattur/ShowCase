package com.special.ServiceImp.RadarHandler;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.special.DataBaseHandler.AsyncTasks.IterableTask;
import com.special.DataStorage.Instances.IterableStamp;
import com.special.DataStorage.Messages.IterableMessage;
import com.special.ServiceImp.Interfaces.AppInterface;

import java.util.ArrayList;

/**
 * @author Arnar Jonsson
 * @version 0.3
 * @since ..
 * Originally created .. Ari Freyr, Arnar
 *
 * Radar.class
 * Receives location updates from a private LocationClient and checks them against possible location hits.
 * Returns the callback to AppService.class for potential notification display and/or logging
 */
public class Radar implements AppInterface {

    ///
    // Radar Variables
    //
    ///
    private AppInterface callBack;
    private Context context;
    private Location loc;
    private LocationClient locationClient;

    private IterableStamp[] stamps;

    private boolean debug;

    ///
    // Radar Constructors
    //
    ///

    /**
     * Radar
     *
     * Iterates through in-app stored locations and matches them with the phones location
     *
     * @param callBack AppInterface.callback
     * @param context context
     * @param debug debug
     */
    public Radar(AppInterface callBack, Context context, boolean debug) {

        this.debug = debug;
        this.context = context;
        this.callBack = callBack;
        this.locationClient = new LocationClient(context, this, 1000*60*3, 1000*60, debug);

        if(debug) log("Radar - constructor call");
    }

    ///
    // Radar Initialization
    //
    ///

    private void manageInitialization() {
        populateStamps();
        loc = null;
        locationClient.initialize();
    }

    ///
    // Radar Public Methods
    //
    ///

    /**
     * initialize()
     *
     * Initializes Radar.class
     */
    public void initialize() {

        manageInitialization();
    }

    public void releaseFromMemory() {

        locationClient = null;
    }

    ///
    // Radar Private Methods
    //
    ///


    private boolean isHit(IterableStamp stamp) {

        float[] results = new float[1];
        Location.distanceBetween(this.loc.getLatitude(), this.loc.getLongitude(),
                stamp.getLatitude(), stamp.getLongitude(), results);


        if(results[0] < stamp.getPingRadius()) {
            return true;
        }

        return false;


    }

    private void iterateIterables(Location loc) {

        if(stamps == null) {
            log("Radar - iterateIterables - stamps are null");
            return;
        }

        this.loc = loc;

        for(IterableStamp stamp : stamps) {

            if(isHit(stamp)) {
                log("Radar - Hit found on IterableStamp - " + stamp.getType() + " - " + stamp.getId());
                callBack.onNotificationRequest(stamp);
                return;
            }
        }

        if(this.debug) log("Radar - iterateIterables - no hit found");

    }

    private void populateStamps() {
        try {
            IterableMessage message = new IterableTask(context)
                    .execute(new IterableMessage(IterableMessage.ITERABLEMESSAGE_TYPE_RADAR)).get();

            if(message != null && !message.isError()) {
                stamps = message.getStamps();
                if(this.debug) Log.d("Radar", "populateStamps - success");
                return;
            }

            log("Radar - IterableStamp[] population failed, IterableMessage callback faulty");
            stamps = null;
        }
        catch (Exception e) {
            log("Radar - IterableStamp[] population failed, IterableTask failed to return stamps");
            stamps = null;
        }
    }

    ///
    // AppInterface callBack
    //
    ///

    @Override
    public void onDataBaseChange(int version) {
        // Do Nothing
    }

    @Override
    public void onLocationChange(Location loc) {
        iterateIterables(loc);
    }

    @Override
    public void onNotificationRequest(IterableStamp stamp) {
        // Do Nothing
    }

    @Override
    public void onStatusUpdate(String desc) {
        callBack.onStatusUpdate(desc);
    }

    @Override
    public ArrayList<String> getLogList() {
        return null;
    }

    ///
    // Logging
    //
    ///

    private void log(String desc) {
        callBack.onStatusUpdate(desc);
    }
}

package com.special.ServiceImp.RadarHandler;

import android.content.Context;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

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
        this.locationClient = new LocationClient(context, this, 1000*60, 1000*30, debug);

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


    private boolean isHit(IterableStamp stamp, Location loc) {


        double eRad = 6371;
        double vLat = Math.toRadians(stamp.getLatitude()-loc.getLatitude());
        double vLon = Math.toRadians(stamp.getLongitude()-loc.getLongitude());
        double lat1 = loc.getLatitude();
        double lat2 = stamp.getLatitude();

        double acc = Math.sin(vLat/2) + Math.sin(vLat/2) +
                Math.sin(vLon/2) * Math.sin(vLon/2) * Math.cos(lat1) * Math.cos(lat2);

        double cr = 2 * Math.atan2(Math.sqrt(acc), Math.sqrt(1-acc));

        double distance = eRad * cr;

        Log.d("Distance", distance+"");

        return false;
        /*
        //Log.d("Coordenance??", "LAT IS " + loc.getLatitude() + "LON IS " + loc.getLongitude());

        if(distance < 15) return true;

        /*
        Location stampLocation = new Location("StampLoc");
        stampLocation.setLatitude(stamp.getLatitude());
        stampLocation.setLongitude(stamp.getLongitude());

        if(stamp.getName().equals("Home")) {
            Log.d("Radar", "distance to home is : " + loc.distanceTo(stampLocation));
            Log.d("Radar", "pingradius of home is : " + stamp.getPingRadius());
        }
        if(stamp.getLatitude() != 0.0) Log.d("Distance", "dist is :" + loc.distanceTo(stampLocation));
        if(loc.distanceTo(stampLocation) < 25) return true;
        */
        /*
        float[] results = new float[1];
        Location.distanceBetween(loc.getLatitude(), loc.getLongitude(),
                stamp.getLatitude(), stamp.getLongitude(), results);

        if(stamp.getLatitude() != 0.0) Log.d("Distance is ", results[0]+"");
        if(results[0] < 10) {
            return true;
        }

        return false;
        */
        /*
        Location a = new Location("a");
        a.setLatitude(stamp.getLatitude());
        a.setLongitude(stamp.getLongitude());

        Location b = new Location("b");
        b.setLatitude(loc.getLatitude());
        b.setLongitude(loc.getLongitude());

        Log.d("Distance", a.distanceTo(b)+"");
        return false;
        */
    }

    private void iterateIterables(Location loc) {

        if(stamps == null) {
            log("Radar - iterateIterables - stamps are null");
            return;
        }


        for(IterableStamp stamp : stamps) {
            //callBack.onNotificationRequest(stamp);
            if(!stamp.isMall()) callBack.onNotificationRequest(stamp);
            if(isHit(stamp, loc)) {
                //Log.d("Radar", "Hit found on IterableStamp - " + stamp.getType() + " - " + stamp.getId());
                //Log.d("Radar", "Hit found on IterableStamp - Stamp length is :" +stamps.length);
                callBack.onNotificationRequest(stamp);
                return;
            }

        }

        if(this.debug) Log.d("Radar",  "iterateIterables - no hit found");

    }

    private void populateStamps() {
        try {
            IterableMessage message = new IterableTask(context)
                    .execute(new IterableMessage(IterableMessage.ITERABLEMESSAGE_TYPE_RADAR)).get();

            if(message != null && !message.isError()) {
                stamps = message.getStamps();
                Toast.makeText(context, "Stamp size is : " + stamps.length, Toast.LENGTH_SHORT).show();
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

package com.special.ServiceImp.RadarHandler;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.special.ServiceImp.Interfaces.AppInterface;

/**
 * @author Arnar Jonsson
 * @since 3.5.2015
 * @version 0.2
 * Original class created ..
 *
 * LocationClient.class
 *
 * Holds a GoogleApiServices location object and the logic needed to request updates on a time
 * interval.
 *
 * Send the updated location to Radar.class for handling
 */
public class LocationClient implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    ///
    // LocationClient Variables
    //
    ///

    private boolean debug;
    private AppInterface callback;

    private Context context;
    private GoogleApiClient locationClient;
    private LocationRequest locationRequest;

    private int LOCATIONCLIENT_UPDATE_INTERVAL;
    private int LOCATIONCLIENT_FASTEST_INTERVAL;
    private boolean requestUpdates;

    ///
    // LocationClient Constructors
    //
    ///

    /**
     * LocationClient
     *
     * Sends periodic location updates to Radar.class for handling
     * @param context context
     * @param callback AppInterface.callback
     * @param normalInterval Interval for location updating
     * @param fastestInterval fastest possible location update interval - used for input handling
     * @param debug enable logging
     */
    public LocationClient(Context context, AppInterface callback,
                          int normalInterval, int fastestInterval, boolean debug) {

        this.debug = debug;

        this.context = context;
        this.callback = callback;
        this.LOCATIONCLIENT_UPDATE_INTERVAL = normalInterval;
        this.LOCATIONCLIENT_FASTEST_INTERVAL = fastestInterval;

        if(this.debug) log("LocationClient - constructor call");
    }

    ///
    // LocationClient Initialization
    //
    ///

    private void manageInitialization() {
        buildGoogleApiClient();
        createLocationRequest();
    }

    ///
    // LocationClient Public Methods
    //
    ///

    /**
     * initialize()
     *
     * Initializes LocationClient.class
     */
    public void initialize() {
        manageInitialization();
    }

    /**
     * isConnected
     * @return Connection status of Radar.class LocationClient.class
     */
    public boolean isConnected() {
        return this.locationClient.isConnected();
    }

    ///
    // LocationClient Private Methods
    //
    ///

    ///
    // LocationClient GoogleApiService Object initialization
    // LocationClient LocationRequest Object initialization
    ///

    protected synchronized void buildGoogleApiClient() {
        try {
            locationClient = new GoogleApiClient.Builder(context)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API).build();

            locationClient.connect();

            if(debug) log("LocationClient - GoogleApiClient creation and connection successful");
        }
        catch (Exception e) {
            log("LocationClient - Construction of GoogleApiClient failed, stand by for exception");
            log(e.toString());
        }
    }

    private void createLocationRequest() {
        try {
            locationRequest = new LocationRequest();
            locationRequest.setInterval(LOCATIONCLIENT_UPDATE_INTERVAL);
            locationRequest.setFastestInterval(LOCATIONCLIENT_FASTEST_INTERVAL);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            requestUpdates = true;

            if(debug) log("LocationClient - LocationRequest object creation successful");
        }
        catch (Exception e) {
            log("LocationClient - Construction of LocationRequest object failed, stand by for exception");
            log(e.toString());
        }

    }

    ///
    // LocationClient Listener Logic Configuration?
    ///

    @Override
    public void onConnected(Bundle arg0) {

        try {
            Location loc = LocationServices.FusedLocationApi.getLastLocation(locationClient);

            if(loc != null) {
                handleCallBack(loc);
            }

            if(requestUpdates) {
                LocationServices.FusedLocationApi.requestLocationUpdates(locationClient, locationRequest, this);
            }
        }
        catch (Exception e) {
            log("LocationClient - Connection to google play location service failed");
            log(e.toString());
        }
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        // TODO : Bother
        log("LocationClient - onConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // TODO : Bother
        log("LocationClient - onConnectionFailed");
    }

    @Override
    public void onLocationChanged(Location loc) {
        if(loc != null) {
            handleCallBack(loc);
        }
    }

    ///
    // AppInterface callback
    ///
    private void handleCallBack(Location loc) {
        callback.onLocationChange(loc);
    }

    ///
    // Logging
    //
    ///

    private void log(String desc) {
        callback.onStatusUpdate(desc);
    }



}

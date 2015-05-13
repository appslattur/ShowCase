package com.special.ServiceImp.ServiceHandler;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.special.DataStorage.Instances.IterableStamp;
import com.special.ServiceImp.Interfaces.AppInterface;
import com.special.ServiceImp.NotificationHandler.NotificationHandler;
import com.special.ServiceImp.RadarHandler.Radar;
import com.special.ServiceImp.Util.LogPostFix;

import java.util.ArrayList;

/**
 * @author Arnar Jonsson
 * @version 0.1
 * @since 1.5.2015.
 */
public class AppService extends Service implements AppInterface {

    ///
    // AppService Control Flow
    //
    ///

    ///
    // AppService Logging
    //
    ///

    private boolean debug = true; // TODO : False this
    private ArrayList<String> logList = new ArrayList<String>();

    private boolean hasBeenInitialized;

    ///
    // AppService Variables
    //
    ///

    private Radar radar;
    private NotificationHandler notificationHandler;

    ///
    // AppService activity communication variables
    // Old
    ///
    /*
    public static final String HANDLER_NAME = "Whisper";

    public Handler appServiceHandler;

    public class AppServiceHandler extends Handler {

        public AppServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message message) {
            super.handleMessage(message);
        }

    }
    */

    // New
    // TODO : Figure out why callBack returns null on bind

    public final IBinder iBinder = new AppServiceBinder(this);

    public class AppServiceBinder extends Binder {

        AppInterface callBack;

        AppServiceBinder(AppInterface callBack) {
            this.callBack = callBack;
        }

        AppInterface getCallBack() {
            return callBack;
        }
    }


    ///
    // Constructors
    //
    ///


    @Override
    public void onCreate() {
        super.onCreate();
        if(this.debug) log("AppService - onCreate call");

        hasBeenInitialized = false;
        // TODO : Check if this breaks thingies

        /*
        // Simple implementation of a communication thread, depricated
        HandlerThread thread = new HandlerThread(HANDLER_NAME, android.os.Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();
        Looper serviceLooper = thread.getLooper();
        appServiceHandler = new AppServiceHandler(serviceLooper);
        */
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        if(this.debug) log("AppService - onStartCommand called");
        /*
        if(intent != null) {
            Message message = appServiceHandler.obtainMessage();
            message.arg1 = startId;
            message.obj = intent.getStringExtra("something");
            appServiceHandler.handleMessage(message);
        }

        */
        if(!hasBeenInitialized) initializeService();

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    @Override
    public void onRebind(Intent intent) {

    }

    @Override
    public void onDestroy() {

    }

    ///
    // AppService Private Methods
    //
    ///

    private void initializeService() {
        if(debug) Log.d("AppService",  "initialization");

        try {
            radarInitialization();
            notificationHandlerInitialization();
            hasBeenInitialized = true;
            if(debug) Log.d("AppService",  "initialization successful");
        }
        catch (Exception e) {
            log(e.toString());
            if(debug) Log.d("AppService",  "initialization failed");
            // Do nothing
        }
    }

    private void radarInitialization() {

        radar = new Radar(this, getApplicationContext(), debug);
        radar.initialize();
    }

    private void notificationHandlerInitialization() {

        notificationHandler = new NotificationHandler(this, getApplicationContext(), debug);
        notificationHandler.initialize();
    }

    private void releaseNotificationHandler() {
        if(debug) log("AppService - notificationHandler destruction");

        notificationHandler.releaseFromMemory();
        notificationHandler = null;
    }

    ///
    // AppInterface callBack
    //
    ///

    @Override
    public void onDataBaseChange(int version) {

    }

    @Override
    public void onLocationChange(Location loc) {
        // Do Nothing
    }

    @Override
    public void onNotificationRequest(IterableStamp stamp) {
        // TODO : remove from logging list
        if(this.debug) Log.d("AppService", "onNotificationRequest");
        notificationHandler.onNotificationRequest(stamp);
    }

    @Override
    public void onStatusUpdate(String desc) {
        log(desc);
    }

    ///
    // Logging
    //
    ///

    private void log(String str) {
        logList.add(new LogPostFix().attackPostFix(str));
    }

    @Override
    public ArrayList<String> getLogList() {
        return logList;
    }



}

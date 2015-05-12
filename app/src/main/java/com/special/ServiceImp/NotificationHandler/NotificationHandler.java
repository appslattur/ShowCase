package com.special.ServiceImp.NotificationHandler;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.special.DataBaseHandler.AsyncTasks.IterableTask;
import com.special.DataStorage.Instances.IterableStamp;
import com.special.DataStorage.Messages.IterableMessage;
import com.special.MainActivity;
import com.special.R;
import com.special.ServiceImp.Interfaces.AppInterface;
import com.special.ServiceImp.TickTackCounter.TickTackCounter;
import com.special.ServiceImp.Util.StampExplainer;
import com.special.ServiceImp.Util.StampParty;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by arnarjons on 3.5.2015.
 */
public class NotificationHandler implements AppInterface {

    ///
    // NotificationHandler Variables
    //
    ///

    private AppInterface callBack;
    private boolean debug;

    private int activeId;
    private long activeIdTimeStamp;
    private boolean isActive;
    private int activeCounter;
    private long NOTIFICATION_LIFETIME;
    private int NOTIFICATION_INTERVAL;

    private Context context;
    private TickTackCounter tickTackCounter;

    private NotificationManager notificationManager;

    private final String HANDLER_BANNER = "Appslattur!";
    private final String TITLE_BANNER = "Appslattur minnir thig a afslattinn!!";
    private final String CONTENT_BANNER = "Afslattarkort : ";

    ///
    // NotificationHandler Constructors
    //
    ///

    public NotificationHandler(AppInterface callBack, Context context, boolean debug) {

        this.callBack = callBack;
        this.debug = debug;

        this.context = context;
        if(this.debug) log("NotificationHandler - Constructor call");
    }

    ///
    // NotificationHandler Initialization
    //
    ///

    private void manageInitialization() {
        tickTackCounter = new TickTackCounter(this, 1000*60*15, System.currentTimeMillis(), true);
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        isActive = false;
        activeCounter = 0;
        activeIdTimeStamp = -1;
        NOTIFICATION_LIFETIME = 1000*60*2;
    }

    ///
    // NotificationHandler Public Methods
    //
    ///

    /**
     * initialize()
     *
     * Initialization of the NotificationHandler
     */
    public void initialize() {
        manageInitialization();
    }

    public void releaseFromMemory() {
        manageMemoryRelease();
    }

    ///
    // NotificationHandler Private Methods
    //
    ///

    private Notification createBuilder(IterableStamp stamp, IterableStamp[] stamps) {
        if(this.debug) Log.d("NotificationHandler", "createBuilder");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this.context);

        builder.setTicker(this.HANDLER_BANNER);

        builder.setContentTitle(this.TITLE_BANNER);

        builder.setContentText(this.CONTENT_BANNER);

        // TODO: FIX THIS SHIT SO IT WORKS
        builder.setSmallIcon(R.drawable.ic_launcher);

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(uri);

        builder.setVibrate(new long[] { new Long(200), new Long(200) });

        StampParty party;
        if(stamp.isMall()) {
            party = new StampParty(stamps);
        }
        else {
            party = new StampParty(stamps[0]);
        }

        Intent intent = new Intent(this.context, MainActivity.class);
        intent.putExtra("isNotification", true);
        intent.putExtra("isSpecialCase", stamp.isMall());
        intent.putExtra("Stamp", party);
        PendingIntent pIntent = PendingIntent.getActivity(this.context, 0, intent, 0);

        builder.setContentIntent(pIntent);

        builder.setAutoCancel(true);

        return builder.build();
    }

    private void createNotification(IterableStamp stamp) {
        if(this.debug) Log.d("NotificationHandler", "createNotification");
        IterableMessage message = new IterableMessage(stamp);

        try {
            message = new IterableTask(context).execute(message).get();
            Log.d("NotificationHandler", "IterableTask callback success");

        }
        catch (Exception e) {
            Log.d("NotificationHandler", "IterableTask callback failure");
            return;
        }

        Notification notification = createBuilder(stamp, message.getStamps());

        activeId = stamp.getId();
        isActive = true;
        activeIdTimeStamp = System.currentTimeMillis();
        activeCounter++;

        notificationManager.notify(activeCounter, notification);

        Timer activeIdTimer = new Timer();
        activeIdTimer.schedule(new ActiveIdUpdater(), NOTIFICATION_LIFETIME);

    }

    private void manageNotificationRequest(IterableStamp stamp) {
        if(this.debug) Log.d("NotificationHandler", "manageNotificationRequest");
        if(isActive) return;

        if(tickTackCounter.tick(stamp.getId())) {
            this.tickTackCounter.tack(stamp.getId());
            createNotification(stamp);
        }
    }

    ///
    // NotificationHandler Utilz
    //
    ///

    private void refreshActiveId() {
        try {
            notificationManager.cancel(this.activeCounter);
            activeId = -1;
            isActive = false;
        }
        catch (Exception e) {
            log("");
            // Do Nothing, the notification has been canceled
        }
    }

    private class ActiveIdUpdater extends TimerTask {
        @Override
        public void run() {
            refreshActiveId();
        }
    }

    private void manageMemoryRelease() {
        if(isActive) {
            refreshActiveId();
        }

        activeId = -1;
        tickTackCounter = null;
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
        // Do Nothing
    }

    @Override
    public void onNotificationRequest(IterableStamp stamp) {
        if(this.debug) Log.d("NotificationHandler", "onNotificationRequest - callback");
        manageNotificationRequest(stamp);
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

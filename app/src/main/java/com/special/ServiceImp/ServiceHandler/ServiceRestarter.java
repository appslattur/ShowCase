package com.special.ServiceImp.ServiceHandler;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

import java.util.Calendar;

/**
 * ServiceRestarter
 *
 * Restarts the main service every 24 hours
 */
public class ServiceRestarter extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock w1 = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "AppService");

        context.stopService(new Intent(context, AppService.class));
        context.startService(new Intent(context, AppService.class));

        w1.release();
    }

    public void setAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ServiceRestarter.class);
        intent.putExtra("Reboot", true);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        // Calculate time to next restart (00:00:00)
        // TODO : Test if it really does what its suppose to
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        long timeTilMidnight = Math.abs(calendar.getTimeInMillis() - System.currentTimeMillis());

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeTilMidnight, 1000 * 60 * 60 * 24, pendingIntent);
    }

    public void cancelAlarm(Context context) {
        Intent intent = new Intent(context, ServiceRestarter.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }
}

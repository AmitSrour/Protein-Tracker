package il.co.amits.proteintracker.service;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.util.Log;
import android.os.Handler;

import il.co.amits.proteintracker.MainActivity;
import il.co.amits.proteintracker.R;
import il.co.amits.proteintracker.settings.SettingsHelper;
import il.co.amits.proteintracker.tracking.ProteinHelper;

public class BackgroundService extends Service {

    private static String TAG = "MyService";
    private Handler handler;
    private Runnable runnable;
    private final int runTime = 5000;

    public BackgroundService() {
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate() {        // Code to execute when the service is first created
        super.onCreate();
        Log.i(TAG, "onCreate");

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                handler.postDelayed(runnable, runTime);
            }
        };
        handler.post(runnable);
        if(BackgroundService.isMyServiceRunning(BackgroundService.class,this)){
            startNotification(this);
        }


    }

    @Override
    public void onDestroy() {
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
        super.onDestroy();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }
    @SuppressWarnings("deprecation")
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i(TAG, "onStart");
    }
    /**
     *
     * Starts the notification, needs the context because static
     * @param c
     *
     */
    public static void startNotification(Context c) {
       // ProteinHelper myProteinHelper = new ProteinHelper(c);
        SettingsHelper mySettingsHelper = new SettingsHelper(c);
        Bitmap bm = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(c.getResources(), R.drawable.avatar),
            c.getResources().getDimensionPixelSize(android.R.dimen.notification_large_icon_width),
            c.getResources().getDimensionPixelSize(android.R.dimen.notification_large_icon_height),
            true);
        Intent intent = new Intent(c, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(c, 01, intent, Intent.FILL_IN_DATA);
        Notification.Builder builder = new Notification.Builder(c.getApplicationContext());
        builder.setContentTitle("Any new gains?");
        builder.setContentText("click to tell me of your precious gains");
        builder.setSubText("Progress:" + ProteinHelper.getProgress(c));
        //builder.setNumber(101);
        builder.setOngoing(true);
        builder.setContentIntent(pendingIntent);
        builder.setTicker("Fancy Notification");
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setLargeIcon(bm);
        //builder.setAutoCancel(true);
        builder.setPriority(Notification.PRIORITY_HIGH);
        Notification notification = builder.build();
        NotificationManager notificationManger =
            (NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManger.notify(01, notification);
        mySettingsHelper.setNotifOn(true);
    }

    /**
     *
     * Call like this:isMyServiceRunning(MyService.class,this)
     * @param serviceClass
     * @param c
     * @return true/false if a service is running withing provided context
     */
    public static boolean isMyServiceRunning(Class<?> serviceClass,Context c) {
        ActivityManager manager = (ActivityManager) c.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


}

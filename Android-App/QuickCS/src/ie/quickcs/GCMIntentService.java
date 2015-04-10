package ie.quickcs;

import com.google.android.gcm.GCMBaseIntentService;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

public class GCMIntentService extends GCMBaseIntentService {

    private static final String PROJECT_ID = "518668514236";
    
	private static final String TAG = "GCMIntentService";
	
	public GCMIntentService()
	{
		super(PROJECT_ID);
		Log.d(TAG, "GCMIntentService init");
	}
	

	protected void onError(Context ctx, String sError) {
		Log.d(TAG, "Error: " + sError);
	}

	protected void onMessage(Context ctx, Intent intent) {
		String message = intent.getStringExtra("message");
		//sendGCMIntent(ctx, message);
		Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
		r.play();
		/*
		String message = intent.getStringExtra("message");
		NotificationCompat.Builder mBuilder =
			    new NotificationCompat.Builder(this)
			    .setSmallIcon(R.drawable.logo)
			    .setContentTitle("QuickCS | New Message Received")
			    .setContentText(message);
		int mNotificationId = 001;
		NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		mNotifyMgr.notify(mNotificationId, mBuilder.build());
		*/
	}

	private void sendGCMIntent(Context ctx, String message) {
		Intent broadcastIntent = new Intent();
		broadcastIntent.setAction("GCM_RECEIVED_ACTION");
		broadcastIntent.putExtra("gcm", message);
		ctx.sendBroadcast(broadcastIntent);
	}
	
	protected void onRegistered(Context ctx, String regId) {
		// TODO Auto-generated method stub
		// send regId to your server
		Log.e("REGISTERED", regId);
	}

	protected void onUnregistered(Context ctx, String regId) {
		// TODO Auto-generated method stub
		// send notification to your server to remove that regId
	}

}

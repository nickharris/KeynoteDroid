package com.example.fooo;

import android.content.Context;
import android.content.Intent;
import android.app.Notification;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.TableOperationCallback;

public class GCMIntentService extends GCMBaseIntentService {

	public GCMIntentService(){
	    super(ToDoActivity.SENDER_ID);
	}

	
	@Override
	protected void onError(Context arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onMessage(Context context, Intent intent) {
		//TODO change here
		//ToDoActivity.mAdapter.insert(object, index)
		//int a = 1;
		//a += 1;
		//ToDoActivity.mAdapter.insert(new ToDoItem(intent.getStringExtra("message"), -1), 0);
		
		  Intent custom = new Intent("custom-event-name");
		  // You can also include some extra data.
		  custom.putExtra("message", intent.getStringExtra("message"));
		  LocalBroadcastManager.getInstance(context).sendBroadcast(custom);
		  
		//LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
		
	/*   NotificationCompat.Builder mBuilder =
	            new NotificationCompat.Builder(this)
	                .setSmallIcon(R.drawable.ic_launcher)
	                .setContentTitle("Todo Created!")
	                .setPriority(Notification.PRIORITY_HIGH)
	                .setContentText(intent.getStringExtra("message"));
	    NotificationManager mNotificationManager =
	        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	    mNotificationManager.notify(0, mBuilder.build()); */
	}

	@Override
	protected void onRegistered(Context context, String registrationId) {			
		MobileServiceClient client = ToDoActivity.mClient;
		
			MobileServiceTable<Channel> channelTable = client.getTable(Channel.class);
			Channel channel = new Channel();
			channel.setUri(registrationId);		
			channelTable.insert(channel, new TableOperationCallback<Channel>() {
				@Override
				public void onCompleted(Channel entity, Exception exception,
						ServiceFilterResponse response) {
					if (exception != null) {
						Log.e("GCMIntentService", "Exception - " + exception.getMessage());
					} else {
						Log.i("GCMIntentService", "Success");
					}
				}
	 
			});		 
	}

	@Override
	protected void onUnregistered(Context arg0, String arg1) {
		// TODO Auto-generated method stub

	}

}

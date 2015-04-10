package ie.quickcs.messenger;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;

public class Outgoing{
	
	private static final String SENDER_ID = "AIzaSyARyiM6Zbo58ZmHxMRjUnaofFBV9--3U3I";
	
	//Private Constructor
	private Outgoing(){
		
	}
		
	//Send a message
	public static void sendMessage(String content, String deviceId){
		List<String> androidTargets = new ArrayList<String>();
		androidTargets.add(deviceId);
		Sender sender = new Sender(SENDER_ID);
		Message message = new Message.Builder()
			.collapseKey("collapseKey")
			.timeToLive(30)
			.delayWhileIdle(true)
			.addData("message", content)
			.build();
		try {
			MulticastResult result = sender.send(message, androidTargets, 1);
			if (result.getResults() != null) {
				int canonicalRegId = result.getCanonicalIds();
				if (canonicalRegId != 0) {
					
				}
			} else {
				int error = result.getFailure();
				System.out.println("Broadcast failure: " + error);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getServerKey(){
		return SENDER_ID;
	}
	
}
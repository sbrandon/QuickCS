package ie.quickcs.messenger;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class Messenger {
	
	private Messenger(){
		
	}
	
	public static String register(String email, String password, String firstName, String lastName, String apiKey){
	    String uri = "http://54.154.21.107/WebService/register";
		try {
	    	JSONObject obj = new JSONObject();
			obj.put("email", email);
			obj.put("password", password);
			obj.put("firstName", firstName);
			obj.put("lastName", lastName);
			obj.put("apiKey", apiKey);
			return postJson(obj, uri);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String login(String email, String password, String key){
		String uri = "http://54.154.21.107/WebService/login";
		try {
	    	JSONObject obj = new JSONObject();
			obj.put("email", email);
			obj.put("password", password);
			obj.put("password", password);
			obj.put("apiKey", key);
			return postJson(obj, uri);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getCompanies(){
		String uri = "http://54.154.21.107/WebService/getCompanies";
		return postJson(null, uri);
	}
	
	public static String sendMessage(String address, String message, String userEmail){
		String uri = "http://52.17.86.204/AgentDesktop/onMessage";
		//String uri = "http://" + address + "onMessage";
		try {
	    	JSONObject obj = new JSONObject();
	    	obj.put("message", message);
	    	obj.put("email", userEmail);
			return postJson(obj, uri);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String postJson(JSONObject json, String uri){
		try {
		    DefaultHttpClient httpclient = new DefaultHttpClient();
		    HttpPost httpost = new HttpPost(uri);
		    if(json != null){
		    	StringEntity se = new StringEntity(json.toString());
		    	httpost.setEntity(se);
		    	httpost.setHeader("Accept", "application/json");
		    	httpost.setHeader("Content-type", "application/json");
		    }
		    ResponseHandler response = new BasicResponseHandler();
		    String result = httpclient.execute(httpost, response);
		    return result;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

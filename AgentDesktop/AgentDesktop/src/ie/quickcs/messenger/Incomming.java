package ie.quickcs.messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

import com.opensymphony.xwork2.Preparable;

import ie.quickcs.agent.entity.Client;
import ie.quickcs.agent.entity.Conversation;
import ie.quickcs.agent.entity.Message;
import ie.quickcs.agent.session.SessionBeanLocal;
import ie.quickcs.sessionfactory.EJBSessionFactory;

public class Incomming implements Preparable{
	
	private SessionBeanLocal sessionBean;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	@Override
	public void prepare() throws Exception {
		sessionBean = EJBSessionFactory.getSessionBean();
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();  
	}
	
	//Receive a message
	public void onMessage() throws IOException, JSONException{
		JSONObject json = parseJson();
		String email = (String) json.get("email");
		String content = (String) json.get("message");
		PrintWriter out = response.getWriter();
		if(email != null){
			//First we check if the client is known to us. 
			Client client = sessionBean.getClient(email.trim());
			if(client == null){
				//If not we must ask the web service.
				if(getClient(email) != null){
					client = getClient(email);
					sessionBean.persist(client);
				}
				else{
					//Client is not registered with QuickCS
					out.println("ERROR");
					return;
				}
			}
			else{
				//Update clients API key in our database
				String apiKey = getClient(email).getApiKey();
				client.setKey(apiKey);
				sessionBean.merge(client);
			}
			//Create a new message object and add it to conversation. 
			Message message = new Message();
			message.setContent(content);
			Conversation conversation;
			if(!client.getConversations().isEmpty()){
				conversation = client.getConversations().get(0);
				conversation.setStatus("new");
				sessionBean.merge(conversation);
			}
			//If this is the first message a client has ever sent us then we will need to create a conversation too.
			else{
				conversation = new Conversation();
				conversation.setClient(client);
				conversation.setStatus("new");
				sessionBean.persist(conversation);
			}
			message.setConversation(conversation);
			sessionBean.persist(message);
			out.println("SUCCESS");
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	//Get the client object from the QuickCS web service.
	public Client getClient(String email) throws ClientProtocolException, IOException, JSONException{
		JSONObject json = new JSONObject();
		json.put("email", email);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpost = new HttpPost("http://54.154.21.107/WebService/getUser");
		StringEntity postingString = new StringEntity(json.toString());
		httpost.setEntity(postingString);
		httpost.setHeader("Accept", "application/json");
    	httpost.setHeader("Content-type", "application/json");
		ResponseHandler response = new BasicResponseHandler();
		String result = httpclient.execute(httpost, response);
		//CREATE CLIENT FROM RESPONSE
		if(!result.trim().equals("ERROR")){
			JSONObject resultJson = new JSONObject(result.toString());
			String firstName = (String) resultJson.get("firstName");
			String lastName = (String) resultJson.get("lastName");
			String key = (String) resultJson.get("apiKey");
			Client client = new Client();
			client.setEmail(email);
			client.setFirstName(firstName);
			client.setLastName(lastName);
			client.setKey(key);
			return client;
		}
		return null;
	}
	
	public JSONObject parseJson() throws IOException, JSONException{
		StringBuilder sb = new StringBuilder();
	    BufferedReader reader = request.getReader();
		try {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            sb.append(line).append('\n');
	        }
	    } finally {
	        reader.close();
	    }
		System.out.println(sb.toString());
	    JSONObject json = new JSONObject(sb.toString().trim());
	    return json;
	}
	
}

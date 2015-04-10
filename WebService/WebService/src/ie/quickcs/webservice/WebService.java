package ie.quickcs.webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import ie.quickcs.security.PasswordHash;
import ie.quickcs.sessionfactory.EJBSessionFactory;
import ie.quickcs.webservice.entity.Company;
import ie.quickcs.webservice.entity.User;
import ie.quickcs.webservice.session.SessionBeanLocal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.opensymphony.xwork2.Preparable;

public class WebService implements Preparable{

	private HttpServletRequest request;
	private HttpServletResponse response;
	private SessionBeanLocal ejbSession;
	private List<Company> companies;
	private PasswordHash passwordHash = new PasswordHash();
	
	@Override
	public void prepare() throws Exception {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		ejbSession = EJBSessionFactory.getSessionBean();
	}
	
	//Register a new QuickCS User
	public void register() throws IOException, JSONException{
		JSONObject json = parseJson();
		String email = (String) json.get("email");
		String password = (String) json.get("password");
		String firstName = (String) json.get("firstName");
		String lastName = (String) json.get("lastName");
		String apiKey = (String) json.get("apiKey");
		try {
			PrintWriter out = response.getWriter();
			if(ejbSession.authenticate(email) == null){
				User user = new User();
				HashMap<String, String> map = passwordHash.hashPassword(password);
				user.setEmail(email);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setApiKey(apiKey);
				user.setPassword(map.get("password"));
				user.setSalt(map.get("salt"));
				ejbSession.persist(user);
				out.println("SUCCESS");
			}
			else{
				out.println("ERROR");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Log in User
	public void logIn() throws JSONException, IOException{
		JSONObject json = parseJson();
		String email = (String) json.get("email");
		String password = (String) json.get("password");
		String key = (String) json.get("apiKey");
		try {
			PrintWriter out = response.getWriter();
			User user = ejbSession.authenticate(email);
			if(user != null){
				if(passwordHash.authenticate(user.getPassword(), user.getSalt(), password)){
					if(key != null){
						user.setApiKey(key);
						ejbSession.merge(user);
					}
					out.println("SUCCESS");
				}
				else{
					out.println("ERROR");
				}
			}
			else{
				out.println("ERROR");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Return User Object
	public void getUser() throws JSONException, IOException{
		JSONObject json = parseJson();
		String email = (String) json.get("email");
		try {
			PrintWriter out = response.getWriter();
			//Need to check company key to make sure they are allowed request user information.
			User user = ejbSession.authenticate(email);
			if(user != null){
				//For test purposes the entire user object is passed. In production we would want to check that the password and salt are not included.
				//user.setPassword(null);
				//user.setSalt(null);
				String responseJson = new Gson().toJson(user);
				out.println(responseJson);
				out.flush();
			}
			else{
				out.println("ERROR");
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Get a list of Companies
	public void getCompanies(){
		companies = ejbSession.getCompanies();
		String json = new Gson().toJson(companies);
		try {
			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	    JSONObject json = new JSONObject(sb.toString());
	    return json;
	}
}

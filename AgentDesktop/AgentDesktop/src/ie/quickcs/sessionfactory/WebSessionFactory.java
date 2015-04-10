package ie.quickcs.sessionfactory;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class WebSessionFactory {

private static Map<String, Object> session;
	
	//Private constructor to prevent class being instantiated
	private WebSessionFactory(){
		
	}
	
	//Return Web Session
	public static Map<String, Object> getWebSession(){
		session = ActionContext.getContext().getSession();
		return session;
	}
	
}
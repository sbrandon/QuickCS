package ie.quickcs.sessionfactory;

import javax.naming.Context;
import javax.naming.InitialContext;

import ie.quickcs.webservice.session.SessionBeanLocal;

public class EJBSessionFactory {
	private static SessionBeanLocal sessionBean;
	
	//Private Constructor
	private EJBSessionFactory(){
		
	}
	
	public static SessionBeanLocal getSessionBean(){
		try{			
			Context context = new InitialContext();
			//AWS BINDINGsessionBean = (SessionBeanLocal) context.lookup("java:global/WebServiceEAR/WebServiceEJB/SessionBean!ie.quickcs.webservice.session.SessionBean");
			sessionBean = (SessionBeanLocal) context.lookup("java:global/WebServiceEJB/SessionBean!ie.quickcs.webservice.session.SessionBean");
		}catch(Exception e){
			e.printStackTrace();
		}
		return sessionBean;
	}
}
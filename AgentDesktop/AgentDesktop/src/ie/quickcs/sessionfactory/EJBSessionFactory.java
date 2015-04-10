package ie.quickcs.sessionfactory;

import ie.quickcs.agent.session.SessionBeanLocal;

import javax.naming.Context;
import javax.naming.InitialContext;


public class EJBSessionFactory {
	private static SessionBeanLocal sessionBean;
	
	//Private Constructor
	private EJBSessionFactory(){
		
	}
	
	public static SessionBeanLocal getSessionBean(){
		try{			
			Context context = new InitialContext();
			//sessionBean = (SessionBeanLocal) context.lookup("java:global/AgentDesktopEAR/AgentDesktopEJB/SessionBean!ie.quickcs.agent.session.SessionBeanLocal");
			sessionBean = (SessionBeanLocal) context.lookup("java:global/AgentDesktopEAR/AgentDesktopEJB/SessionBean!ie.quickcs.agent.session.SessionBeanLocal");
		}catch(Exception e){
			e.printStackTrace();
		}
		return sessionBean;
	}
}

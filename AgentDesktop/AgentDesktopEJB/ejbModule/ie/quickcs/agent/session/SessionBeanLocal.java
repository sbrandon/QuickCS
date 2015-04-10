package ie.quickcs.agent.session;

import java.util.List;

import ie.quickcs.agent.entity.Agent;
import ie.quickcs.agent.entity.Client;
import ie.quickcs.agent.entity.Conversation;

import javax.ejb.Local;
@Local
public interface SessionBeanLocal {
	
	//Persist Object
	public boolean persist(Object object);
	//Merge Object
	public boolean merge(Object object);
	//Authenticate Agent
	public Agent authenticateAgent(String email);
	//Client Operations
	public Client getClient(String email);
	//Conversation Operations
	public List<Conversation> getConversations();
	public Conversation getConversationById(int conversationId);
}
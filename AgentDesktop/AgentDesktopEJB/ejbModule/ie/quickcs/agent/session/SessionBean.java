package ie.quickcs.agent.session;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import ie.quickcs.agent.entity.Agent;
import ie.quickcs.agent.entity.Client;
import ie.quickcs.agent.entity.Conversation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Stateless
public class SessionBean implements SessionBeanLocal {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	
	@Override
	public boolean persist(Object object) {
		entityManager.persist(object);
		return true;
	}

	@Override
	public boolean merge(Object object) {
		entityManager.merge(object);
		return true;
	}

	@Override
	public Agent authenticateAgent(String email) {
		try{
			Agent agent = (Agent) entityManager.createNamedQuery("Agent.authenticate").setParameter("email", email).getSingleResult();
			return agent;
		}
		catch(Exception e){
			return null;
		}
	}

	@Override
	public Client getClient(String email) {
		try{
			Client client = (Client) entityManager.createNamedQuery("Client.get").setParameter("email", email).getSingleResult();
			return client;
		}
		catch(Exception e){
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Conversation> getConversations() {
		List<Conversation> conversations = new ArrayList<Conversation>();
		try{
			conversations = entityManager.createNamedQuery("Conversation.findAll").getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return conversations;
	}

	@Override
	public Conversation getConversationById(int conversationId) {
		Conversation conversation = (Conversation) entityManager.createNamedQuery("Conversation.findById").setParameter("conversation_id", conversationId).getSingleResult();
		return conversation;
	}

}
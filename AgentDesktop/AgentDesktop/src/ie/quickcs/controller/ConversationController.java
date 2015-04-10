package ie.quickcs.controller;

import ie.quickcs.agent.entity.Agent;
import ie.quickcs.agent.entity.Client;
import ie.quickcs.agent.entity.Conversation;
import ie.quickcs.agent.entity.Message;
import ie.quickcs.agent.session.SessionBeanLocal;
import ie.quickcs.messenger.Outgoing;
import ie.quickcs.sessionfactory.EJBSessionFactory;
import ie.quickcs.sessionfactory.WebSessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class ConversationController extends ActionSupport implements Preparable {

	private static final long serialVersionUID = -5296891472940683336L;
	private SessionBeanLocal sessionBean;
	private Map<String, Object> webSession;
	private Agent agent;
	private int conversationId;
	private List<Message> messages = new ArrayList<Message>();
	private String content;
	private Conversation conversation;
	private Client client;
	
	@Override
	public void prepare() throws Exception {
		sessionBean = EJBSessionFactory.getSessionBean();
		webSession = WebSessionFactory.getWebSession();
		agent = (Agent) webSession.get("agent");
	}
	
	public String viewConversation(){
		conversation = sessionBean.getConversationById(conversationId);
		messages = conversation.getMessages();
		return "success";
	}
	
	public String sendMessage(){
		conversation = sessionBean.getConversationById(conversationId);
		client = conversation.getClient();
		String deviceId = client.getApiKey();
		Outgoing.sendMessage(content, deviceId);
		Message message = new Message();
		message.setContent(content);
		message.setConversation(conversation);
		sessionBean.persist(message);
		return "success";
	}

	//Getters & Setters
	public int getConversationId() {
		return conversationId;
	}

	public void setConversationId(int conversationId) {
		this.conversationId = conversationId;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}

package ie.quickcs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ie.quickcs.agent.entity.Agent;
import ie.quickcs.agent.entity.Conversation;
import ie.quickcs.agent.session.SessionBeanLocal;
import ie.quickcs.security.PasswordHash;
import ie.quickcs.sessionfactory.EJBSessionFactory;
import ie.quickcs.sessionfactory.WebSessionFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class AgentController extends ActionSupport implements Preparable{
	
	private static final long serialVersionUID = -10275290949846537L;
	private SessionBeanLocal sessionBean;
	private Map<String, Object> webSession;
	private PasswordHash passwordHash = new PasswordHash();
	private Agent agent;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String verification;
	private List<Conversation> conversations = new ArrayList<Conversation>();
	private List<Conversation> openConversations = new ArrayList<Conversation>();
	private List<Conversation> newConversations = new ArrayList<Conversation>();
	private List<Conversation> waitingConversations = new ArrayList<Conversation>();
	private List<Conversation> overdueConversations = new ArrayList<Conversation>();

	@Override
	public void prepare() throws Exception {		 
		sessionBean = EJBSessionFactory.getSessionBean();
		webSession = WebSessionFactory.getWebSession();
		agent = (Agent) webSession.get("agent");
	}
	
	//Agent login
	public String login(){		
		agent = sessionBean.authenticateAgent(email);
		if(agent != null){
			if(passwordHash.authenticate(agent.getPassword(), agent.getSalt(), password)){
				webSession.put("agent", agent);
				getConversations(agent);
				return "success";
			}
			else{
				addActionError("Incorrect username/password!");
				return "error";
			}
		}
		addActionError("There is no account with that username!");
		return "error";
	}
	
	//Registration link on home page
	public String registerLink(){
		return "success";
	}
	
	//Register a new agent
	public String agentRegister(){
		if(sessionBean.authenticateAgent(email) == null){
			if(verification.equals("1234")){
				Agent agent = new Agent();
				HashMap<String, String> map = passwordHash.hashPassword(password); 
				agent.setEmail(email);
				agent.setFirstName(firstName);
				agent.setLastName(lastName);
				agent.setPassword(map.get("password"));
				agent.setSalt(map.get("salt"));
				sessionBean.persist(agent);
				webSession.put("agent", agent);
				return "success";
			}
			else{
				addActionError("Incorrect verification code!");
				return "error";
			}
		}
		else{
			addActionError("An account already exists for that email!");
			return "error";
		}
	}
	
	//Agent logout
	public String logout(){
		return "error";
	}
	
	//Sort conversations for dash board
	public void getConversations(Agent agent){
		conversations = sessionBean.getConversations();
		for(Conversation conversation : conversations){
			if(conversation.getStatus().equals("open")){
				openConversations.add(conversation);
			}
			else if(conversation.getStatus().equals("new")){
				newConversations.add(conversation);
			}
			else if(conversation.getStatus().equals("waiting")){
				waitingConversations.add(conversation);
			}
			else if(conversation.getStatus().equals("overdue")){
				overdueConversations.add(conversation);
			}
		}
	}
	
	//Getters & Setters
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}

	public List<Conversation> getOpenConversations() {
		return openConversations;
	}

	public void setOpenConversations(List<Conversation> openConversations) {
		this.openConversations = openConversations;
	}

	public List<Conversation> getNewConversations() {
		return newConversations;
	}

	public void setNewConversations(List<Conversation> newConversations) {
		this.newConversations = newConversations;
	}

	public List<Conversation> getWaitingConversations() {
		return waitingConversations;
	}

	public void setWaitingConversations(List<Conversation> waitingConversations) {
		this.waitingConversations = waitingConversations;
	}

	public List<Conversation> getOverdueConversations() {
		return overdueConversations;
	}

	public void setOverdueConversations(List<Conversation> overdueConversations) {
		this.overdueConversations = overdueConversations;
	}

	public List<Conversation> getConversations() {
		return conversations;
	}

	public void setConversations(List<Conversation> conversations) {
		this.conversations = conversations;
	}
	
}

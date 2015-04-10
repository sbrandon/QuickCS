package ie.quickcs.agent.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@NamedQueries( {
	@NamedQuery(name = "Conversation.findAll", query = "select o from Conversation o"),
	@NamedQuery(name = "Conversation.findById", query = "select o from Conversation o where o.id=:conversation_id"),
})

@Entity
public class Conversation {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String status;
	private String date;
	@ManyToOne
	private Client client;
	@ManyToOne
	private Agent agent;
	@OneToMany
	@JoinColumn(name="conversation_id")
	private List <Message> messages;
	
	//Constructor
	public Conversation(){
		
	}
	
	//Getters & Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
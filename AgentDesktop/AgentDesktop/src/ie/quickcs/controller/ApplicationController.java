package ie.quickcs.controller;

import java.util.ArrayList;
import java.util.List;

import ie.quickcs.agent.entity.Agent;
import ie.quickcs.agent.entity.Conversation;

public class ApplicationController {
	
	private List<Conversation> openConversations = new ArrayList<Conversation>();
	private List<Conversation> newConversations = new ArrayList<Conversation>();
	private List<Conversation> waitingConversations = new ArrayList<Conversation>();
	private List<Conversation> overdueConversations = new ArrayList<Conversation>();
	
	//Constructor
	public ApplicationController(){
		
	}
	
	//Sort conversations for dash board
	public void getConversations(Agent agent){
		List<Conversation> conversations = agent.getConversations();
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
	
}

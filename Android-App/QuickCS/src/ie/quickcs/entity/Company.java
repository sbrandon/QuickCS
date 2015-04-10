package ie.quickcs.entity;

public class Company{
	
	private int id;
	private String name;
	private String serverKey;
	private String phoneNumber;
	private String ipAddress;
	
	//Constructor
	public Company(int id, String name, String serverKey, String phoneNumber, String ipAddress){
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.ipAddress = ipAddress;
	}
	
	//Getters & Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public String getServerKey() {
		return serverKey;
	}
	public void setServerKey(String serverKey) {
		this.serverKey = serverKey;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}

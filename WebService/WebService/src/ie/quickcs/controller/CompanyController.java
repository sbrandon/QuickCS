package ie.quickcs.controller;

import ie.quickcs.sessionfactory.EJBSessionFactory;
import ie.quickcs.webservice.entity.Company;
import ie.quickcs.webservice.session.SessionBeanLocal;

import com.opensymphony.xwork2.Preparable;

public class CompanyController implements Preparable {

	private String companyName;
	private String ipAddress;
	private String phoneNumber;
	private SessionBeanLocal ejbSession;
	
	public void prepare() throws Exception {
		ejbSession = EJBSessionFactory.getSessionBean();
	}
	
	public String addCompany(){
		Company company = new Company();
		company.setName(companyName);
		company.setIpAddress(ipAddress);
		company.setPhoneNumber(phoneNumber);
		ejbSession.persist(company);
		return "success";
	}
	
	//Getters & Setters
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}

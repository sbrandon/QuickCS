package ie.quickcs.webservice.session;

import ie.quickcs.webservice.entity.Company;
import ie.quickcs.webservice.entity.User;

import java.util.List;

import javax.ejb.Local;

@Local
public interface SessionBeanLocal {
	//Persist Object
	public boolean persist(Object object);
	//Merge Object
	public boolean merge(Object object);
	//Company Operations
	public List<Company> getCompanies();
	//User Operations
	public User authenticate(String email);
}

package ie.quickcs.webservice.session;

import ie.quickcs.webservice.entity.Company;
import ie.quickcs.webservice.entity.User;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class SessionBean implements SessionBeanLocal {
	
	@PersistenceContext  
	private EntityManager entityManager;
	
    public SessionBean() {
        
    }
	
	//Persist object to database.
	@Override
	public boolean persist(Object object) {
		entityManager.persist(object);
		return true;
	}
	
	//Merge with an existing object in database.
	@Override
	public boolean merge(Object object) {
		entityManager.merge(object);
		return true;
	}
	
	//Get List of companies from database.
	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getCompanies() {
		List<Company> companies = new ArrayList<Company>();
		try{
			companies = entityManager.createNamedQuery("Company.findAll").getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return companies;
	}
	
	//Return user object based on email.
	@Override
	public User authenticate(String email) {
		try{
			User user = (User) entityManager.createNamedQuery("User.authenticate").setParameter("email", email).getSingleResult();
			return user;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}


}

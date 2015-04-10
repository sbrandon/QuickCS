package ie.quickcs.testing;

import static org.junit.Assert.*;
import ie.quickcs.sessionfactory.EJBSessionFactory;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.ActionProxy;

public class TestWebService extends StrutsTestCase{

	@Test
	public void testPrepare() throws Exception{
		
		ActionProxy proxy = getActionProxy("/createaccount");
		
		assertNotNull(ServletActionContext.getRequest());
		assertNotNull(ServletActionContext.getResponse());
		assertNotNull(EJBSessionFactory.getSessionBean());
	}

	@Test
	public void testRegister() {
		fail("Not yet implemented");
	}

	@Test
	public void testLogIn() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCompanies() {
		fail("Not yet implemented");
	}

	@Test
	public void testParseJson() {
		fail("Not yet implemented");
	}

}

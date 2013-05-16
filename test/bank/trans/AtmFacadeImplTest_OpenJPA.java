package bank.trans;

import static org.junit.Assert.assertTrue;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.openejb.OpenEJB;
import org.apache.openejb.OpenEJBException;
import org.apache.openejb.core.LocalInitialContextFactory;
import org.junit.Before;
import org.junit.Test;

import bank.iface.AtmFacade;

public class AtmFacadeImplTest_OpenJPA {

	private AtmFacade atmFacade;
	
	@Before
	public void setup() throws NamingException, OpenEJBException {
		atmFacade = getBean();
	}

	public AtmFacade getBean() throws NamingException, OpenEJBException {

		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY, LocalInitialContextFactory.class.getName());

		if (!OpenEJB.isInitialized()) {
			OpenEJB.init(p);
		}
		InitialContext initialContext = new InitialContext(p);
		return ((AtmFacade) initialContext.lookup("AtmFacadeImplLocal"));
	}

	@Test
	public void withdraw_endsSuccessfully_whenAmountIsLowEnough() throws NamingException, OpenEJBException {
		assertTrue(atmFacade.withdraw(1234, 500).toLowerCase().contains("transaction successful"));
	}

	@Test
	public void withdraw_endsUnsuccessfully_whenAmountIsTooHigh() throws NamingException, OpenEJBException {
		assertTrue(atmFacade.withdraw(1234, 50000000).toLowerCase().contains("transaction failed"));
	}

}

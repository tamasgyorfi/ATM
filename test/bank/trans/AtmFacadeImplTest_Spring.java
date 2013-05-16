package bank.trans;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import bank.iface.AtmFacade;


	public class AtmFacadeImplTest_Spring {

		private AtmFacade atmFacade;
		
		@Before
		public void setup() {
			atmFacade = getBean();
		}
		
		public AtmFacade getBean() {
			Resource xmlResource = new FileSystemResource(new File("./resources/applicationContext.xml"));
			BeanFactory beanFactory = new XmlBeanFactory(xmlResource);
			
			return (AtmFacade)beanFactory.getBean("atmFacadeImpl");
		}

		@Test
		public void withdraw_endsSuccessfully_whenAmountIsLowEnough() {
			assertTrue(atmFacade.withdraw(1234, 500).toLowerCase().contains("transaction successful"));
		}

		@Test
		public void withdraw_endsUnsuccessfully_whenAmountIsTooHigh() {
			assertTrue(atmFacade.withdraw(1234, 50000000).toLowerCase().contains("transaction failed"));
		}

}

package bank.trans;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import bank.iface.AtmFacade;

/**
 * Session Bean implementation class AtmFacade
 */
@Stateless
public class AtmFacadeImpl implements AtmFacade{

	
	@EJB
	private ClientAuthenticator authenticator;
	
	@EJB
	private BalanceController balanceController;
	
	@EJB
	private ReceiptCreator receiptCreator;
	

	public void setAuthenticator(ClientAuthenticator authenticator) {
		this.authenticator = authenticator;
	}

	public void setBalanceController(BalanceController balanceController) {
		this.balanceController = balanceController;
	}

	public void setReceiptCreator(ReceiptCreator receiptCreator) {
		this.receiptCreator = receiptCreator;
	}



	public String withdraw(int pin, double amount) {
		if (authenticator.canAuthenticate(pin)) {
			if (balanceController.getAmount() > amount) {
				balanceController.withdraw(amount);
				return receiptCreator.createSuccessResponse();
			}
		}
		return receiptCreator.createFailResponse();
	}
}

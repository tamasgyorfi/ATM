package bank.trans;

import javax.ejb.Stateless;

@Stateless
public class BalanceController {

	private double amount = 10000;
	
	public double getAmount() {
		return amount;
	}

	public void withdraw(double amountToWithdraw) {
		amount -= amountToWithdraw;
	}

}

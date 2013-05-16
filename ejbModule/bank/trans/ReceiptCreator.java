package bank.trans;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ReceiptCreator {

	@Inject
	ResponseTextCreator responseTextCreator;

	public void setResponseTextCreator(ResponseTextCreator balanceInfo) {
		this.responseTextCreator = balanceInfo;
	}

	public String createSuccessResponse() {
		return new Date() +": "+ responseTextCreator.createResponse();
	}

	public String createFailResponse() {
		return new Date() +": "+ responseTextCreator.createFailResponse();
	}
}

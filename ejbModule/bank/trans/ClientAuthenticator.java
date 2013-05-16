package bank.trans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class ClientAuthenticator
 */
@Stateless
@LocalBean
public class ClientAuthenticator {

    /**
     * Default constructor. 
     */
    public ClientAuthenticator() {
    }

	public boolean canAuthenticate(int pin) {
		return true;
	}

}

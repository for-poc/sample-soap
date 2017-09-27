/**
 * 
 */
package com.test.security;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

/**
 *  
 *
 */
public class SoapPasswordCallBackHandler implements CallbackHandler {

	private static final String userName = "BitsUser1";
	private static final String password = "BitsPassword1";

	/**
	 * 
	 */
	public SoapPasswordCallBackHandler() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.security.auth.callback.CallbackHandler#handle(javax.security.auth.
	 * callback.Callback[])
	 */
	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		for (int i = 0; i < callbacks.length; i++) {
			WSPasswordCallback pc = (WSPasswordCallback) callbacks[i];
			if (pc.getUsage() == WSPasswordCallback.USERNAME_TOKEN) {
				if (pc.getIdentifier().equalsIgnoreCase(userName))
					pc.setPassword(password);
			}
		}

	}

}

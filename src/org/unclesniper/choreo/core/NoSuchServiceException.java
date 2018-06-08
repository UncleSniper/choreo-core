package org.unclesniper.choreo.core;

import org.unclesniper.choreo.ChoreoRunException;

public class NoSuchServiceException extends ChoreoRunException {

	private final String serviceKey;

	public NoSuchServiceException(String serviceKey) {
		super("No such service object: " + serviceKey);
		this.serviceKey = serviceKey;
	}

	public String getServiceKey() {
		return serviceKey;
	}

}

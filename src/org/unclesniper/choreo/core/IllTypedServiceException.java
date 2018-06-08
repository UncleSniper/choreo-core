package org.unclesniper.choreo.core;

import org.unclesniper.choreo.ChoreoRunException;

public class IllTypedServiceException extends ChoreoRunException {

	private final String serviceKey;

	private final Class<?> requiredType;

	private final Class<?> foundType;

	public IllTypedServiceException(String serviceKey, Class<?> requiredType, Class<?> foundType) {
		super("Service object " + (serviceKey == null ? "<null>" : '\'' + serviceKey + '\'')
				+ " must be of type " + requiredType.getName() + ", but is of type "
				+ foundType.getName());
		this.serviceKey = serviceKey;
		this.requiredType = requiredType;
		this.foundType = foundType;
	}

	public String getServiceKey() {
		return serviceKey;
	}

	public Class<?> getRequiredType() {
		return requiredType;
	}

	public Class<?> getFoundType() {
		return foundType;
	}

}

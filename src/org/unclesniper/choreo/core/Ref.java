package org.unclesniper.choreo.core;

import org.unclesniper.choreo.BuildContext;
import org.unclesniper.choreo.VirtualElementClass;
import org.unclesniper.choreo.annotation.ElementClass;
import org.unclesniper.choreo.annotation.DefaultAdder;

@ElementClass("ref")
public class Ref implements VirtualElementClass {

	private String key;

	private boolean permitNull = true;

	private Class<?> requiredType;

	public Ref() {}

	public Ref(String key) {
		this(key, false, null);
	}

	public Ref(String key, boolean permitNull, Class<?> requiredType) {
		this.key = key;
		this.permitNull = permitNull;
		this.requiredType = requiredType;
	}

	public String getKey() {
		return key;
	}

	@DefaultAdder
	public void setKey(String key) {
		this.key = key;
	}

	public boolean isPermitNull() {
		return permitNull;
	}

	public void setPermitNull(boolean permitNull) {
		this.permitNull = permitNull;
	}

	public Class<?> getRequiredType() {
		return requiredType;
	}

	public void setRequiredType(Class<?> requiredType) {
		this.requiredType = requiredType;
	}

	public void mapToActual(ObjectState state, BuildContext context)
			throws NoSuchServiceException, IllTypedServiceException {
		Object svc = context.getServiceObject(key);
		if(!permitNull && svc == null)
			throw new NoSuchServiceException(key);
		if(svc != null && requiredType != null)
			throw new IllTypedServiceException(key, requiredType, svc.getClass());
		state.setValue(svc);
	}

}

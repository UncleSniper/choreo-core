package org.unclesniper.choreo.core;

import org.unclesniper.choreo.BuildContext;
import org.unclesniper.choreo.VirtualElementClass;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("ref")
public class Ref implements VirtualElementClass {

	private String key;

	private boolean permitNull;

	public Ref() {}

	public Ref(String key) {
		this(key, false);
	}

	public Ref(String key, boolean permitNull) {
		this.key = key;
		this.permitNull = permitNull;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean isPermitNull() {
		return permitNull;
	}

	public void setPermitNull(boolean permitNull) {
		this.permitNull = permitNull;
	}

	public void mapToActual(ObjectState state, BuildContext context) throws NoSuchServiceException {
		Object svc = context.getServiceObject(key);
		if(!permitNull && svc == null)
			throw new NoSuchServiceException(key);
		state.setValue(svc);
	}

}

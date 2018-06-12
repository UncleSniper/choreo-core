package org.unclesniper.choreo.core;

import org.unclesniper.choreo.ChoreoRunException;

public class TargetNotIterableException extends ChoreoRunException {

	private final Class<?> targetType;

	public TargetNotIterableException(Class<?> targetType) {
		super("Target of loop is not iterable: " + targetType.getName());
		this.targetType = targetType;
	}

	public Class<?> getTargetType() {
		return targetType;
	}

}

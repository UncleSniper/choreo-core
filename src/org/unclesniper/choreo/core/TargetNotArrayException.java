package org.unclesniper.choreo.core;

import org.unclesniper.choreo.ChoreoRunException;

public class TargetNotArrayException extends ChoreoRunException {

	private final Class<?> targetType;

	public TargetNotArrayException(Class<?> targetType) {
		super("Target of array operation is not an array: " + targetType.getName());
		this.targetType = targetType;
	}

	public Class<?> getTargetType() {
		return targetType;
	}

}

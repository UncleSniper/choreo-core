package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("greaterThan")
public class GreaterThan extends NativeRelationalOperator {

	public GreaterThan() {
		super(Operator.GREATER);
	}

}

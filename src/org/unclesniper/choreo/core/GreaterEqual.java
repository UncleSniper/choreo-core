package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("greaterOrEqual")
public class GreaterEqual extends NativeRelationalOperator {

	public GreaterEqual() {
		super(Operator.GREATER_EQUAL);
	}

}

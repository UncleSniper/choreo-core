package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("lessOrEqual")
public class LessEqual extends NativeRelationalOperator {

	public LessEqual() {
		super(Operator.LESS_EQUAL);
	}

}

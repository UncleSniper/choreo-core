package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("lessThan")
public class LessThan extends NativeRelationalOperator {

	public LessThan() {
		super(Operator.LESS);
	}

}

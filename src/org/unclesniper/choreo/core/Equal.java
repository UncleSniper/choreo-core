package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("equal")
public class Equal extends EqualityOperator {

	public Equal() {
		super(Operator.EQUAL);
	}

}

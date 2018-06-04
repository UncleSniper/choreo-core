package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("unequal")
public class Unequal extends EqualityOperator {

	public Unequal() {
		super(Operator.UNEQUAL);
	}

}

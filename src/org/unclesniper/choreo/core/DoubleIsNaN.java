package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("doubleIsNaN")
public class DoubleIsNaN extends DoubleClassifierPredicate {

	public DoubleIsNaN() {}

	public DoubleIsNaN(ChoreoExpr<Double> value) {
		super(value);
	}

	protected boolean is(double value) {
		return Double.isNaN(value);
	}

}

package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("doubleIsInfinite")
public class DoubleIsInfinite extends DoubleClassifierPredicate {

	public DoubleIsInfinite() {}

	public DoubleIsInfinite(ChoreoExpr<Double> value) {
		super(value);
	}

	protected boolean is(double value) {
		return Double.isInfinite(value);
	}

}

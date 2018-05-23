package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("floatIsInfinite")
public class FloatIsInfinite extends FloatClassifierPredicate {

	public FloatIsInfinite() {}

	public FloatIsInfinite(ChoreoExpr<Float> value) {
		super(value);
	}

	protected boolean is(float value) {
		return Float.isInfinite(value);
	}

}

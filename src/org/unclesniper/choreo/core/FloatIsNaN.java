package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("floatIsNaN")
public class FloatIsNaN extends FloatClassifierPredicate {

	public FloatIsNaN() {}

	public FloatIsNaN(ChoreoExpr<Float> value) {
		super(value);
	}

	protected boolean is(float value) {
		return Float.isNaN(value);
	}

}

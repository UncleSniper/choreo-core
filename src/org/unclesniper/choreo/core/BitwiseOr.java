package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("bitwiseOr")
public class BitwiseOr extends VariadicIntegralOperator<Object> {

	public BitwiseOr() {}

	protected int combine(int a, int b, Object options) {
		return a | b;
	}

	protected long combine(long a, long b, Object options) {
		return a | b;
	}

}

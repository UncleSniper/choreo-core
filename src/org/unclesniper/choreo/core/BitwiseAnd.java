package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("bitwiseAnd")
public class BitwiseAnd extends VariadicIntegralOperator<Object> {

	public BitwiseAnd() {}

	protected int combine(int a, int b, Object options) {
		return a & b;
	}

	protected long combine(long a, long b, Object options) {
		return a & b;
	}

}

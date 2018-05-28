package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("bitwiseXor")
public class BitwiseXor extends VariadicIntegralOperator<Object> {

	public BitwiseXor() {}

	protected int combine(int a, int b, Object options) {
		return a ^ b;
	}

	protected long combine(long a, long b, Object options) {
		return a ^ b;
	}

}

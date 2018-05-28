package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("shiftLeft")
public class ShiftLeft extends VariadicIntegralOperator<Object> {

	public ShiftLeft() {}

	protected int combine(int a, int b, Object options) {
		if(b < 0)
			throw new IllegalArgumentException("Cowardly refusing to shift by " + b + " bit positions");
		return b >= 32 ? 0 : a << b;
	}

	protected long combine(long a, long b, Object options) {
		if(b < 0l)
			throw new IllegalArgumentException("Cowardly refusing to shift by " + b + " bit positions");
		return b >= 64l ? 0l : a << (int)b;
	}

}

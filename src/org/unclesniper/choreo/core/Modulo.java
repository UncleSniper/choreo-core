package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("modulo")
public class Modulo extends VariadicIntegralOperator<Object> {

	public Modulo() {}

	protected int combine(int a, int b, Object options) {
		return a % b;
	}

	protected long combine(long a, long b, Object options) {
		return a % b;
	}

}

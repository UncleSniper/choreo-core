package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("divide")
public class Division extends VariadicNumericOperator {

	public Division() {}

	protected int combine(int a, int b) {
		return a / b;
	}

	protected long combine(long a, long b) {
		return a / b;
	}

	protected float combine(float a, float b) {
		return a / b;
	}

	protected double combine(double a, double b) {
		return a / b;
	}

}

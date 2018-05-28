package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("subtract")
public class Subtraction extends VariadicNumericOperator {

	public Subtraction() {}

	protected int combine(int a, int b) {
		return a - b;
	}

	protected long combine(long a, long b) {
		return a - b;
	}

	protected float combine(float a, float b) {
		return a - b;
	}

	protected double combine(double a, double b) {
		return a - b;
	}

}

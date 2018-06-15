package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("arrayLength")
public class ArrayLength extends AbstractArrayOperation<Integer, Object> {

	public ArrayLength() {}

	public ArrayLength(ChoreoExpr<?> array) {
		super(array);
	}

	public Class<Integer> getReturnType() {
		return Integer.class;
	}

	protected Object evalRest(RunContext context) {
		return null;
	}

	protected Integer evaluate(byte[] array, Object rest, RunContext context) {
		return array.length;
	}

	protected Integer evaluate(short[] array, Object rest, RunContext context) {
		return array.length;
	}

	protected Integer evaluate(int[] array, Object rest, RunContext context) {
		return array.length;
	}

	protected Integer evaluate(long[] array, Object rest, RunContext context) {
		return array.length;
	}

	protected Integer evaluate(float[] array, Object rest, RunContext context) {
		return array.length;
	}

	protected Integer evaluate(double[] array, Object rest, RunContext context) {
		return array.length;
	}

	protected Integer evaluate(char[] array, Object rest, RunContext context) {
		return array.length;
	}

	protected Integer evaluate(boolean[] array, Object rest, RunContext context) {
		return array.length;
	}

	protected Integer evaluate(Object[] array, Object rest, RunContext context) {
		return array.length;
	}

}

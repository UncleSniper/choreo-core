package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("arrayGet")
public class ArrayGet<ElementT> extends AbstractArrayOperation<ElementT, Integer> {

	private ChoreoExpr<Integer> index;

	public ArrayGet() {}

	public ArrayGet(ChoreoExpr<?> array, ChoreoExpr<Integer> index) {
		super(array);
		this.index = ExprUtils.ensureReturnType(index, Integer.class);
	}

	public ChoreoExpr<Integer> getIndex() {
		return index;
	}

	public void setIndex(ChoreoExpr<Integer> index) {
		this.index = ExprUtils.ensureReturnType(index, Integer.class);
	}

	public void setIndex(Integer index) {
		this.index = ConstantExpr.from(index);
	}

	@SuppressWarnings("unchecked")
	public Class<? extends ElementT> getReturnType() {
		return (Class)getElementType();
	}

	protected Integer evalRest(RunContext context) throws ChoreoRunException {
		return ExprUtils.reduce(index, context);
	}

	@SuppressWarnings("unchecked")
	protected ElementT evaluate(byte[] array, Integer index, RunContext context) {
		return (ElementT)(Byte)array[index];
	}

	@SuppressWarnings("unchecked")
	protected ElementT evaluate(short[] array, Integer index, RunContext context) {
		return (ElementT)(Short)array[index];
	}

	@SuppressWarnings("unchecked")
	protected ElementT evaluate(int[] array, Integer index, RunContext context) {
		return (ElementT)(Integer)array[index];
	}

	@SuppressWarnings("unchecked")
	protected ElementT evaluate(long[] array, Integer index, RunContext context) {
		return (ElementT)(Long)array[index];
	}

	@SuppressWarnings("unchecked")
	protected ElementT evaluate(float[] array, Integer index, RunContext context) {
		return (ElementT)(Float)array[index];
	}

	@SuppressWarnings("unchecked")
	protected ElementT evaluate(double[] array, Integer index, RunContext context) {
		return (ElementT)(Double)array[index];
	}

	@SuppressWarnings("unchecked")
	protected ElementT evaluate(char[] array, Integer index, RunContext context) {
		return (ElementT)(Character)array[index];
	}

	@SuppressWarnings("unchecked")
	protected ElementT evaluate(boolean[] array, Integer index, RunContext context) {
		return (ElementT)(Boolean)array[index];
	}

	@SuppressWarnings("unchecked")
	protected ElementT evaluate(Object[] array, Integer index, RunContext context) {
		return (ElementT)array[index];
	}

}

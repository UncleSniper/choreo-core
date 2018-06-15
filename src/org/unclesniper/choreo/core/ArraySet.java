package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("arraySet")
public class ArraySet<ElementT> extends AbstractArrayOperation<ElementT, ArraySet.Rest<ElementT>> {

	public static final class Rest<ElementT> {

		public final int index;

		public final ElementT element;

		public Rest(int index, ElementT element) {
			this.index = index;
			this.element = element;
		}

	}

	private ChoreoExpr<Integer> index;

	private ChoreoExpr<? extends ElementT> element;

	public ArraySet() {}

	public ArraySet(ChoreoExpr<?> array, ChoreoExpr<Integer> index, ChoreoExpr<? extends ElementT> element) {
		super(array);
		this.index = ExprUtils.ensureReturnType(index, Integer.class);
		this.element = element;
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

	public ChoreoExpr<? extends ElementT> getElement() {
		return element;
	}

	public void setElement(ChoreoExpr<? extends ElementT> element) {
		this.element = element;
	}

	public void setObject(ElementT object) {
		element = ConstantExpr.from(object);
	}

	@SuppressWarnings("unchecked")
	public Class<? extends ElementT> getReturnType() {
		return (Class)getElementType();
	}

	protected Rest<ElementT> evalRest(RunContext context) throws ChoreoRunException {
		Integer eindex = ExprUtils.reduce(index, context);
		ElementT eelem = ExprUtils.reduce(element, context);
		return new Rest<ElementT>(eindex, eelem);
	}

	@SuppressWarnings("unchecked")
	protected ElementT evaluate(byte[] array, Rest<ElementT> rest, RunContext context) {
		Byte value = (Byte)rest.element;
		array[rest.index] = value;
		return (ElementT)value;
	}

	@SuppressWarnings("unchecked")
	protected ElementT evaluate(short[] array, Rest<ElementT> rest, RunContext context) {
		Short value = (Short)rest.element;
		array[rest.index] = value;
		return (ElementT)value;
	}

	@SuppressWarnings("unchecked")
	protected ElementT evaluate(int[] array, Rest<ElementT> rest, RunContext context) {
		Integer value = (Integer)rest.element;
		array[rest.index] = value;
		return (ElementT)value;
	}

	@SuppressWarnings("unchecked")
	protected ElementT evaluate(long[] array, Rest<ElementT> rest, RunContext context) {
		Long value = (Long)rest.element;
		array[rest.index] = value;
		return (ElementT)value;
	}

	@SuppressWarnings("unchecked")
	protected ElementT evaluate(float[] array, Rest<ElementT> rest, RunContext context) {
		Float value = (Float)rest.element;
		array[rest.index] = value;
		return (ElementT)value;
	}

	@SuppressWarnings("unchecked")
	protected ElementT evaluate(double[] array, Rest<ElementT> rest, RunContext context) {
		Double value = (Double)rest.element;
		array[rest.index] = value;
		return (ElementT)value;
	}

	@SuppressWarnings("unchecked")
	protected ElementT evaluate(char[] array, Rest<ElementT> rest, RunContext context) {
		Character value = (Character)rest.element;
		array[rest.index] = value;
		return (ElementT)value;
	}

	@SuppressWarnings("unchecked")
	protected ElementT evaluate(boolean[] array, Rest<ElementT> rest, RunContext context) {
		Boolean value = (Boolean)rest.element;
		array[rest.index] = value;
		return (ElementT)value;
	}

	@SuppressWarnings("unchecked")
	protected ElementT evaluate(Object[] array, Rest<ElementT> rest, RunContext context) {
		Object value = rest.element;
		array[rest.index] = value;
		return (ElementT)value;
	}

}

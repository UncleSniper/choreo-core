package org.unclesniper.choreo.core;

import java.util.Map;
import java.util.HashMap;
import org.unclesniper.choreo.Doom;
import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.DefaultAdder;

public abstract class AbstractArrayOperation<ReturnT, RestT> implements ChoreoExpr<ReturnT> {

	private enum ElementType {

		BYTE(Byte.class),
		SHORT(Short.class),
		INT(Integer.class),
		LONG(Long.class),
		FLOAT(Float.class),
		DOUBLE(Double.class),
		CHAR(Character.class),
		BOOLEAN(Boolean.class);

		private final Class<?> returnType;

		private ElementType(Class<?> returnType) {
			this.returnType = returnType;
		}

		public Class<?> getReturnType() {
			return returnType;
		}

	}

	private static final Map<Class<?>, ElementType> ARRAY_TYPE_TO_SYMBOLIC_ELEMENT_TYPE;

	static {
		ARRAY_TYPE_TO_SYMBOLIC_ELEMENT_TYPE = new HashMap<Class<?>, ElementType>();
		ARRAY_TYPE_TO_SYMBOLIC_ELEMENT_TYPE.put(byte[].class, ElementType.BYTE);
		ARRAY_TYPE_TO_SYMBOLIC_ELEMENT_TYPE.put(short[].class, ElementType.SHORT);
		ARRAY_TYPE_TO_SYMBOLIC_ELEMENT_TYPE.put(int[].class, ElementType.INT);
		ARRAY_TYPE_TO_SYMBOLIC_ELEMENT_TYPE.put(long[].class, ElementType.LONG);
		ARRAY_TYPE_TO_SYMBOLIC_ELEMENT_TYPE.put(float[].class, ElementType.FLOAT);
		ARRAY_TYPE_TO_SYMBOLIC_ELEMENT_TYPE.put(double[].class, ElementType.DOUBLE);
		ARRAY_TYPE_TO_SYMBOLIC_ELEMENT_TYPE.put(char[].class, ElementType.CHAR);
		ARRAY_TYPE_TO_SYMBOLIC_ELEMENT_TYPE.put(boolean[].class, ElementType.BOOLEAN);
	}

	private ChoreoExpr<?> array;

	public AbstractArrayOperation() {}

	public AbstractArrayOperation(ChoreoExpr<?> array) {
		setArray(array);
	}

	public ChoreoExpr<?> getArray() {
		return array;
	}

	@DefaultAdder
	public void setArray(ChoreoExpr<?> array) {
		Class<?> art = array == null ? null : array.getReturnType();
		if(art != null && !art.isArray())
			throw new IllegalArgumentException("Expression must return an array, but returns " + art.getName());
		this.array = array;
	}

	public void setArray(byte[] array) {
		this.array = ConstantExpr.from(array);
	}

	public void setArray(short[] array) {
		this.array = ConstantExpr.from(array);
	}

	public void setArray(int[] array) {
		this.array = ConstantExpr.from(array);
	}

	public void setArray(long[] array) {
		this.array = ConstantExpr.from(array);
	}

	public void setArray(float[] array) {
		this.array = ConstantExpr.from(array);
	}

	public void setArray(double[] array) {
		this.array = ConstantExpr.from(array);
	}

	public void setArray(char[] array) {
		this.array = ConstantExpr.from(array);
	}

	public void setArray(boolean[] array) {
		this.array = ConstantExpr.from(array);
	}

	public void setArray(Object[] array) {
		this.array = ConstantExpr.from(array);
	}

	protected Class<?> getElementType() {
		Class<?> atype = array == null ? null : array.getReturnType();
		if(atype == null)
			return null;
		ElementType etype = AbstractArrayOperation.ARRAY_TYPE_TO_SYMBOLIC_ELEMENT_TYPE.get(atype);
		return etype == null ? null : etype.getReturnType();
	}

	public ReturnT evaluate(RunContext context) throws ChoreoRunException {
		Object aobj = ExprUtils.reduce(array, context);
		RestT rest = evalRest(context);
		Class<?> atype = aobj.getClass();
		ElementType etype = AbstractArrayOperation.ARRAY_TYPE_TO_SYMBOLIC_ELEMENT_TYPE.get(atype);
		if(etype != null) {
			switch(etype) {
				case BYTE:
					return evaluate((byte[])aobj, rest, context);
				case SHORT:
					return evaluate((short[])aobj, rest, context);
				case INT:
					return evaluate((int[])aobj, rest, context);
				case LONG:
					return evaluate((long[])aobj, rest, context);
				case FLOAT:
					return evaluate((float[])aobj, rest, context);
				case DOUBLE:
					return evaluate((double[])aobj, rest, context);
				case CHAR:
					return evaluate((char[])aobj, rest, context);
				case BOOLEAN:
					return evaluate((boolean[])aobj, rest, context);
				default:
					throw new Doom("Unrecognized ElementType:" + etype.name());
			}
		}
		else if(aobj instanceof Object[])
			return evaluate((Object[])aobj, rest, context);
		else
			throw new TargetNotArrayException(atype);
	}

	protected abstract RestT evalRest(RunContext context) throws ChoreoRunException;

	protected abstract ReturnT evaluate(byte[] array, RestT rest, RunContext context) throws ChoreoRunException;

	protected abstract ReturnT evaluate(short[] array, RestT rest, RunContext context) throws ChoreoRunException;

	protected abstract ReturnT evaluate(int[] array, RestT rest, RunContext context) throws ChoreoRunException;

	protected abstract ReturnT evaluate(long[] array, RestT rest, RunContext context) throws ChoreoRunException;

	protected abstract ReturnT evaluate(float[] array, RestT rest, RunContext context) throws ChoreoRunException;

	protected abstract ReturnT evaluate(double[] array, RestT rest, RunContext context) throws ChoreoRunException;

	protected abstract ReturnT evaluate(char[] array, RestT rest, RunContext context) throws ChoreoRunException;

	protected abstract ReturnT evaluate(boolean[] array, RestT rest, RunContext context) throws ChoreoRunException;

	protected abstract ReturnT evaluate(Object[] array, RestT rest, RunContext context) throws ChoreoRunException;

}

package org.unclesniper.choreo.core;

import java.util.Map;
import java.util.HashMap;
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

}

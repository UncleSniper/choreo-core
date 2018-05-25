package org.unclesniper.choreo.core;

import java.util.Map;
import java.util.HashMap;
import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;

public final class ExprUtils {

	private static final Map<Class<?>, Class<?>> INTERCHANGEABLE_TYPES;

	static {
		INTERCHANGEABLE_TYPES = new HashMap<Class<?>, Class<?>>();
		INTERCHANGEABLE_TYPES.put(Byte.class, Byte.TYPE);
		INTERCHANGEABLE_TYPES.put(Byte.TYPE, Byte.class);
		INTERCHANGEABLE_TYPES.put(Short.class, Short.TYPE);
		INTERCHANGEABLE_TYPES.put(Short.TYPE, Short.class);
		INTERCHANGEABLE_TYPES.put(Integer.class, Integer.TYPE);
		INTERCHANGEABLE_TYPES.put(Integer.TYPE, Integer.class);
		INTERCHANGEABLE_TYPES.put(Long.class, Long.TYPE);
		INTERCHANGEABLE_TYPES.put(Long.TYPE, Long.class);
		INTERCHANGEABLE_TYPES.put(Float.class, Float.TYPE);
		INTERCHANGEABLE_TYPES.put(Float.TYPE, Float.class);
		INTERCHANGEABLE_TYPES.put(Double.class, Double.TYPE);
		INTERCHANGEABLE_TYPES.put(Double.TYPE, Double.class);
		INTERCHANGEABLE_TYPES.put(Character.class, Character.TYPE);
		INTERCHANGEABLE_TYPES.put(Character.TYPE, Character.class);
		INTERCHANGEABLE_TYPES.put(Boolean.class, Boolean.TYPE);
		INTERCHANGEABLE_TYPES.put(Boolean.TYPE, Boolean.class);
	}

	private ExprUtils() {}

	public static <ReturnT> ChoreoExpr<ReturnT> ensureReturnType(ChoreoExpr<ReturnT> expression,
			Class<ReturnT> returnType) {
		if(expression == null)
			return null;
		Class<? extends ReturnT> ert = expression.getReturnType();
		if(ert == null)
			return expression;
		if(returnType.isAssignableFrom(ert))
			return expression;
		Class<?> transposed = ExprUtils.INTERCHANGEABLE_TYPES.get(ert);
		if(transposed != null && returnType.isAssignableFrom(transposed))
			return expression;
		throw new IllegalArgumentException("Expression must return " + returnType.getName()
				+ ", but returns " + ert.getName());
	}

	public static <ReturnT> ChoreoExpr<? extends ReturnT> ensureReturnTypeE(ChoreoExpr<? extends ReturnT> expression,
			Class<ReturnT> returnType) {
		if(expression == null)
			return null;
		Class<? extends ReturnT> ert = expression.getReturnType();
		if(ert == null)
			return expression;
		if(returnType.isAssignableFrom(ert))
			return expression;
		Class<?> transposed = ExprUtils.INTERCHANGEABLE_TYPES.get(ert);
		if(transposed != null && returnType.isAssignableFrom(transposed))
			return expression;
		throw new IllegalArgumentException("Expression must return " + returnType.getName()
				+ ", but returns " + ert.getName());
	}

	public static Class<?> commonAncestor(Class<?> a, Class<?> b) {
		while(!a.isAssignableFrom(b))
			a = a.getSuperclass();
		return a;
	}

	public static <ReturnT> ReturnT reduce(ChoreoExpr<ReturnT> expression, RunContext context)
			throws ChoreoRunException {
		return expression == null ? null : expression.evaluate(context);
	}

}

package org.unclesniper.choreo.core;

public final class ExprUtils {

	private ExprUtils() {}

	public static <ReturnT> ChoreoExpr<ReturnT> ensureReturnType(ChoreoExpr<ReturnT> expression,
			Class<ReturnT> returnType) {
		if(expression == null)
			return null;
		Class<? extends ReturnT> ert = expression.getReturnType();
		if(ert != null && !returnType.isAssignableFrom(ert))
			throw new IllegalArgumentException("Expression must return " + returnType.getName()
					+ ", but returns " + ert.getName());
		return expression;
	}

}

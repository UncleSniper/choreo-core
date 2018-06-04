package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("classOf")
public class ClassOf extends AbstractObjectOperation implements ChoreoExpr<Class<?>> {

	public ClassOf() {}

	public ClassOf(ChoreoExpr<?> value) {
		super(value);
	}

	@SuppressWarnings("unchecked")
	public Class<Class<?>> getReturnType() {
		return (Class)Class.class;
	}

	public Class<?> evaluate(RunContext context) throws ChoreoRunException {
		return ExprUtils.reduce(value, context).getClass();
	}

}

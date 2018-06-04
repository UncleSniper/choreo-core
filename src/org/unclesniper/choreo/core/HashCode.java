package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("hashCode")
public class HashCode extends AbstractObjectOperation implements ChoreoExpr<Integer> {

	public HashCode() {}

	public HashCode(ChoreoExpr<?> value) {
		super(value);
	}

	public Class<Integer> getReturnType() {
		return Integer.class;
	}

	public Integer evaluate(RunContext context) throws ChoreoRunException {
		return ExprUtils.reduce(value, context).hashCode();
	}

}

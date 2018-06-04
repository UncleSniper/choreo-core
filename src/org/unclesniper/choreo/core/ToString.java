package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("toString")
public class ToString extends AbstractObjectOperation implements ChoreoExpr<String> {

	public ToString() {}

	public ToString(ChoreoExpr<?> value) {
		super(value);
	}

	public Class<String> getReturnType() {
		return String.class;
	}

	public String evaluate(RunContext context) throws ChoreoRunException {
		return ExprUtils.reduce(value, context).toString();
	}

}

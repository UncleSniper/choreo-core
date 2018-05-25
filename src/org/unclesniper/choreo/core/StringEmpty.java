package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("stringEmpty")
public class StringEmpty extends AbstractStringOperation implements ChoreoExpr<Boolean> {

	public StringEmpty() {}

	public StringEmpty(ChoreoExpr<String> string) {
		super(string);
	}

	public Class<Boolean> getReturnType() {
		return Boolean.class;
	}

	public Boolean evaluate(RunContext context) throws ChoreoRunException {
		String estring = ExprUtils.reduce(string, context);
		return estring.isEmpty();
	}

}

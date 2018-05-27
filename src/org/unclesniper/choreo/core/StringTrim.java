package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("stringTrim")
public class StringTrim extends AbstractStringOperation implements ChoreoExpr<String> {

	public StringTrim() {}

	public StringTrim(ChoreoExpr<String> string) {
		super(string);
	}

	public Class<String> getReturnType() {
		return String.class;
	}

	public String evaluate(RunContext context) throws ChoreoRunException {
		return ExprUtils.reduce(string, context).trim();
	}

}

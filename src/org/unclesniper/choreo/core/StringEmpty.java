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

	public Class<? extends Boolean> getReturnType() {
		return Boolean.class;
	}

	public Boolean evaluate(RunContext context) throws ChoreoRunException {
		String estring = string == null ? null : string.evaluate(context);
		return estring.isEmpty();
	}

}

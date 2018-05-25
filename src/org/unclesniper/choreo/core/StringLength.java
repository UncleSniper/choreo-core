package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("stringLength")
public class StringLength extends AbstractStringOperation implements ChoreoExpr<Integer> {

	public StringLength() {}

	public StringLength(ChoreoExpr<String> string) {
		super(string);
	}

	public Class<? extends Integer> getReturnType() {
		return Integer.class;
	}

	public Integer evaluate(RunContext context) throws ChoreoRunException {
		String estring = string == null ? null : string.evaluate(context);
		return estring.length();
	}

}

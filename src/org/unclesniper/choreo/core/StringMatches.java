package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("stringMatches")
public class StringMatches extends AbstractStringOperation implements ChoreoExpr<Boolean> {

	private ChoreoExpr<String> regex;

	public StringMatches() {}

	public StringMatches(ChoreoExpr<String> string, ChoreoExpr<String> regex) {
		super(string);
		this.regex = ExprUtils.ensureReturnType(regex, String.class);
	}

	public ChoreoExpr<String> getRegex() {
		return regex;
	}

	public void setRegex(ChoreoExpr<String> regex) {
		this.regex = ExprUtils.ensureReturnType(regex, String.class);
	}

	public void setRegex(String regex) {
		this.regex = regex == null ? null : new ConstantExpr<String>(regex);
	}

	public Class<? extends Boolean> getReturnType() {
		return Boolean.class;
	}

	public Boolean evaluate(RunContext context) throws ChoreoRunException {
		String estring = string == null ? null : string.evaluate(context);
		String eregex = regex == null ? null : regex.evaluate(context);
		return estring.matches(eregex);
	}

}

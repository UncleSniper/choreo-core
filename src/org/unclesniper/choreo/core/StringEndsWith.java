package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("stringEndsWith")
public class StringEndsWith extends AbstractStringOperation implements ChoreoExpr<Boolean> {

	private ChoreoExpr<String> suffix;

	public StringEndsWith() {}

	public StringEndsWith(ChoreoExpr<String> string, ChoreoExpr<String> suffix) {
		super(string);
		suffix = ExprUtils.ensureReturnType(suffix, String.class);
	}

	public ChoreoExpr<String> getSuffix() {
		return suffix;
	}

	public void setSuffix(ChoreoExpr<String> suffix) {
		this.suffix = ExprUtils.ensureReturnType(suffix, String.class);
	}

	public void setSuffix(String suffix) {
		this.suffix = ConstantExpr.from(suffix);
	}

	public Class<Boolean> getReturnType() {
		return Boolean.class;
	}

	public Boolean evaluate(RunContext context) throws ChoreoRunException {
		String estring = ExprUtils.reduce(string, context);
		return estring.endsWith(suffix.evaluate(context));
	}

}

package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("stringEqualsIgnoreCase")
public class StringEqualsIgnoreCase extends AbstractStringOperation implements ChoreoExpr<Boolean> {

	private ChoreoExpr<String> other;

	public StringEqualsIgnoreCase() {}

	public StringEqualsIgnoreCase(ChoreoExpr<String> string, ChoreoExpr<String> other) {
		super(string);
		this.other = ExprUtils.ensureReturnType(other, String.class);
	}

	public ChoreoExpr<String> getOther() {
		return other;
	}

	public void setOther(ChoreoExpr<String> other) {
		this.other = ExprUtils.ensureReturnType(other, String.class);
	}

	public void setOther(String other) {
		this.other = ConstantExpr.from(other);
	}

	public Class<Boolean> getReturnType() {
		return Boolean.class;
	}

	public Boolean evaluate(RunContext context) throws ChoreoRunException {
		String estring = ExprUtils.reduce(string, context);
		return estring.equalsIgnoreCase(other.evaluate(context));
	}

}

package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("stringStartsWith")
public class StringStartsWith extends AbstractStringOperation implements ChoreoExpr<Boolean> {

	private ChoreoExpr<String> prefix;

	public StringStartsWith() {}

	public StringStartsWith(ChoreoExpr<String> string, ChoreoExpr<String> prefix) {
		super(string);
		this.prefix = ExprUtils.ensureReturnType(prefix, String.class);
	}

	public ChoreoExpr<String> getPrefix() {
		return prefix;
	}

	public void setPrefix(ChoreoExpr<String> prefix) {
		this.prefix = ExprUtils.ensureReturnType(prefix, String.class);
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix == null ? null : new ConstantExpr<String>(prefix);
	}

	public Class<? extends Boolean> getReturnType() {
		return Boolean.class;
	}

	public Boolean evaluate(RunContext context) throws ChoreoRunException {
		String estring = string == null ? null : string.evaluate(context);
		return estring.startsWith(prefix.evaluate(context));
	}

}

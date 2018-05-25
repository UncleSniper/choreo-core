package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("stringCompare")
public class StringCompare extends AbstractStringOperation implements ChoreoExpr<Integer> {

	private ChoreoExpr<String> other;

	private ChoreoExpr<Boolean> ignoreCase;

	public StringCompare() {}

	public StringCompare(ChoreoExpr<String> string, ChoreoExpr<String> other) {
		this(string, other, null);
	}

	public StringCompare(ChoreoExpr<String> string, ChoreoExpr<String> other, ChoreoExpr<Boolean> ignoreCase) {
		super(string);
		this.other = ExprUtils.ensureReturnType(other, String.class);
		this.ignoreCase = ExprUtils.ensureReturnType(ignoreCase, Boolean.class);
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

	public ChoreoExpr<Boolean> getIgnoreCase() {
		return ignoreCase;
	}

	public void setIgnoreCase(ChoreoExpr<Boolean> ignoreCase) {
		this.ignoreCase = ExprUtils.ensureReturnType(ignoreCase, Boolean.class);
	}

	public void setIgnoreCase(Boolean ignoreCase) {
		this.ignoreCase = ConstantExpr.from(ignoreCase);
	}

	public Class<Integer> getReturnType() {
		return Integer.class;
	}

	public Integer evaluate(RunContext context) throws ChoreoRunException {
		String estring = ExprUtils.reduce(string, context);
		String eother = ExprUtils.reduce(other, context);
		Boolean eignore = ExprUtils.reduce(ignoreCase, context);
		if(eignore == null || !eignore)
			return estring.compareTo(eother);
		else
			return estring.compareToIgnoreCase(eother);
	}

}

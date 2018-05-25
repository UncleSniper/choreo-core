package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.DefaultAdder;

public abstract class CharClassifierPredicate implements ChoreoExpr<Boolean> {

	private ChoreoExpr<Integer> codepoint;

	public CharClassifierPredicate() {}

	public CharClassifierPredicate(ChoreoExpr<Integer> codepoint) {
		this.codepoint = ExprUtils.ensureReturnType(codepoint, Integer.class);
	}

	public ChoreoExpr<Integer> getCodepoint() {
		return codepoint;
	}

	@DefaultAdder
	public void setCodepoint(ChoreoExpr<Integer> codepoint) {
		this.codepoint = ExprUtils.ensureReturnType(codepoint, Integer.class);
	}

	public void setCodepoint(Integer codepoint) {
		this.codepoint = ConstantExpr.from(codepoint);
	}

	public Class<Boolean> getReturnType() {
		return Boolean.class;
	}

	public Boolean evaluate(RunContext context) throws ChoreoRunException {
		return is(codepoint.evaluate(context));
	}

	protected abstract boolean is(int cp);

}

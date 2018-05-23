package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.ChoreoRunException;
import org.unclesniper.choreo.annotation.DefaultAdder;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("charGetName")
public class CharGetName implements ChoreoExpr<String> {

	private ChoreoExpr<Integer> codepoint;

	public CharGetName() {}

	public CharGetName(ChoreoExpr<Integer> codepoint) {
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
		this.codepoint = codepoint == null ? null : new ConstantExpr<Integer>(codepoint);
	}

	public Class<? extends String> getReturnType() {
		return String.class;
	}

	public String evaluate(RunContext context) throws ChoreoRunException {
		return Character.getName(codepoint.evaluate(context));
	}

}

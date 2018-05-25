package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.DefaultAdder;

public abstract class AbstractStringOperation {

	protected ChoreoExpr<String> string;

	public AbstractStringOperation() {}

	public AbstractStringOperation(ChoreoExpr<String> string) {
		this.string = ExprUtils.ensureReturnType(string, String.class);
	}

	public ChoreoExpr<String> getString() {
		return string;
	}

	@DefaultAdder
	public void setString(ChoreoExpr<String> string) {
		this.string = ExprUtils.ensureReturnType(string, String.class);
	}

	@DefaultAdder
	public void setString(String string) {
		this.string = ConstantExpr.from(string);
	}

}

package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.annotation.DefaultAdder;
import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("bool")
public class BooleanLiteral implements ChoreoExpr<Boolean> {

	private boolean value;

	public BooleanLiteral() {}

	public BooleanLiteral(boolean value) {
		this.value = value;
	}

	public boolean getValue() {
		return value;
	}

	@DefaultAdder
	public void setValue(boolean value) {
		this.value = value;
	}

	public Class<? extends Boolean> getReturnType() {
		return Boolean.class;
	}

	public Boolean evaluate(RunContext context) {
		return value;
	}

}

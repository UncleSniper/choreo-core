package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.annotation.ElementClass;
import org.unclesniper.choreo.annotation.DefaultAdder;

@ElementClass("int")
public class IntLiteral implements ChoreoExpr<Integer> {

	private int value;

	public IntLiteral() {}

	public IntLiteral(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@DefaultAdder
	public void setValue(int value) {
		this.value = value;
	}

	public Class<? extends Integer> getReturnType() {
		return Integer.class;
	}

	public Integer evaluate(RunContext context) {
		return value;
	}

}

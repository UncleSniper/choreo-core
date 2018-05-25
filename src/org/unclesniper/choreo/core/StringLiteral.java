package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.annotation.ElementClass;
import org.unclesniper.choreo.annotation.DefaultAdder;

@ElementClass("string")
public class StringLiteral implements ChoreoExpr<String> {

	private String value;

	public StringLiteral() {}

	public StringLiteral(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@DefaultAdder
	public void setValue(String value) {
		this.value = value;
	}

	public Class<String> getReturnType() {
		return String.class;
	}

	public String evaluate(RunContext context) {
		return value;
	}

}

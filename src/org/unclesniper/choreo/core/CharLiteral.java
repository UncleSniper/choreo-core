package org.unclesniper.choreo.core;

import org.unclesniper.choreo.RunContext;
import org.unclesniper.choreo.annotation.ElementClass;
import org.unclesniper.choreo.annotation.DefaultAdder;

@ElementClass("char")
public class CharLiteral implements ChoreoExpr<Character> {

	private char value;

	public CharLiteral() {}

	public CharLiteral(char value) {
		this.value = value;
	}

	public char getValue() {
		return value;
	}

	@DefaultAdder
	public void setValue(char value) {
		this.value = value;
	}

	public Class<Character> getReturnType() {
		return Character.class;
	}

	public Character evaluate(RunContext context) {
		return value;
	}

}

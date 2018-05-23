package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("charIsDigit")
public class CharIsDigit extends CharClassifierPredicate {

	public CharIsDigit() {}

	public CharIsDigit(ChoreoExpr<Integer> codepoint) {
		super(codepoint);
	}

	protected boolean is(int cp) {
		return Character.isDigit(cp);
	}

}

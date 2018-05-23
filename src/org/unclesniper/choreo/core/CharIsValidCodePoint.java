package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("charIsValidCodePoint")
public class CharIsValidCodePoint extends CharClassifierPredicate {

	public CharIsValidCodePoint() {}

	public CharIsValidCodePoint(ChoreoExpr<Integer> codepoint) {
		super(codepoint);
	}

	protected boolean is(int cp) {
		return Character.isValidCodePoint(cp);
	}

}

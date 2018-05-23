package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("charIsAlpha")
public class CharIsAlpha extends CharClassifierPredicate {

	public CharIsAlpha() {}

	public CharIsAlpha(ChoreoExpr<Integer> codepoint) {
		super(codepoint);
	}

	protected boolean is(int cp) {
		return Character.isAlphabetic(cp);
	}

}

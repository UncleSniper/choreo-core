package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("charIsSpace")
public class CharIsSpace extends CharClassifierPredicate {

	public CharIsSpace() {}

	public CharIsSpace(ChoreoExpr<Integer> codepoint) {
		super(codepoint);
	}

	protected boolean is(int cp) {
		return Character.isSpaceChar(cp);
	}

}

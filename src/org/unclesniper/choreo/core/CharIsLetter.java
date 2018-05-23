package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("charIsLetter")
public class CharIsLetter extends CharClassifierPredicate {

	public CharIsLetter() {}

	public CharIsLetter(ChoreoExpr<Integer> codepoint) {
		super(codepoint);
	}

	protected boolean is(int cp) {
		return Character.isLetter(cp);
	}

}

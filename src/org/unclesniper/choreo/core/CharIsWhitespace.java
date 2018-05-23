package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("charIsWhitespace")
public class CharIsWhitespace extends CharClassifierPredicate {

	public CharIsWhitespace() {}

	public CharIsWhitespace(ChoreoExpr<Integer> codepoint) {
		super(codepoint);
	}

	protected boolean is(int cp) {
		return Character.isWhitespace(cp);
	}

}

package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("charIsLowerCase")
public class CharIsLowerCase extends CharClassifierPredicate {

	public CharIsLowerCase() {}

	public CharIsLowerCase(ChoreoExpr<Integer> codepoint) {
		super(codepoint);
	}

	protected boolean is(int cp) {
		return Character.isLowerCase(cp);
	}

}

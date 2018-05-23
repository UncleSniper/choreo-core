package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("charIsUpperCase")
public class CharIsUpperCase extends CharClassifierPredicate {

	public CharIsUpperCase() {}

	public CharIsUpperCase(ChoreoExpr<Integer> codepoint) {
		super(codepoint);
	}

	protected boolean is(int cp) {
		return Character.isUpperCase(cp);
	}

}

package org.unclesniper.choreo.core;

import org.unclesniper.choreo.annotation.ElementClass;

@ElementClass("charIsControl")
public class CharIsControl extends CharClassifierPredicate {

	public CharIsControl() {}

	public CharIsControl(ChoreoExpr<Integer> codepoint) {
		super(codepoint);
	}

	protected boolean is(int cp) {
		return Character.isISOControl(cp);
	}

}
